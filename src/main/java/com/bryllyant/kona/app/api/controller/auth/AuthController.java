/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.controller.auth;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.model.auth.AuthSessionModel;
import com.bryllyant.kona.app.api.model.auth.LoginRequest;
import com.bryllyant.kona.app.api.model.auth.RegistrationMeta;
import com.bryllyant.kona.app.api.model.auth.RegistrationRequest;
import com.bryllyant.kona.app.api.model.auth.TokenModel;
import com.bryllyant.kona.app.api.model.device.DeviceModel;
import com.bryllyant.kona.app.api.model.geo.position.PositionModel;
import com.bryllyant.kona.app.api.model.user.MeModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.api.service.ApiAuthService;
import com.bryllyant.kona.app.api.service.AuthModelService;
import com.bryllyant.kona.app.api.service.DeviceModelService;
import com.bryllyant.kona.app.api.service.UserModelService;
import com.bryllyant.kona.app.api.util.ApiUtil;
import com.bryllyant.kona.app.entity.AuthCode;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.KAuthCodeType;
import com.bryllyant.kona.app.entity.KDeviceType;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.entity.KUserRole;
import com.bryllyant.kona.app.entity.KUserType;
import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.app.entity.Registration;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AuthCodeService;
import com.bryllyant.kona.app.service.AuthService;
import com.bryllyant.kona.app.service.DeviceService;
import com.bryllyant.kona.app.service.RegistrationService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.app.service.UserAuthService;
import com.bryllyant.kona.app.service.UserDeviceService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.http.KServletUtil;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.rest.exception.AuthenticationException;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.rest.exception.ForbiddenException;
import com.bryllyant.kona.rest.exception.NotFoundException;
import com.bryllyant.kona.rest.exception.ValidationException;
import com.bryllyant.kona.util.KClassUtil;
import com.bryllyant.kona.util.KJsonUtil;
import com.bryllyant.kona.util.KPassGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Auth Controller.
 */
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("hasRole('APP_INTERNAL')")
public class AuthController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private ApiAuthService apiAuthService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthCodeService authCodeService;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserDeviceService userDeviceService;

    @Autowired
    private SystemService system;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private DeviceModelService deviceModelService;

    @Autowired
    private AuthModelService authModelService;

    @Autowired
    private ApiUtil util;




    /**
     * Login a user.
     *
     * @param req HttpServletRequest
     * @param loginRequest
     * @return
     */
    @PreAuthorize("hasRole('APP_INTERNAL')")
    @RequestMapping(value = "/login", method = RequestMethod.POST)

    //public ResponseEntity<AuthSessionModel> login(HttpServletRequest req,
    //		@RequestBody Map<String,Object> map) {

    //public ResponseEntity<AuthSessionModel> login(HttpServletRequest req,
    public ResponseEntity<AuthSessionModel> login(HttpServletRequest req,
                                                  @RequestBody LoginRequest loginRequest) {
        logApiRequest(req, "POST /auth/login");

        logger.debug("login called with credentials: " + loginRequest);

        //Credentials credentials = toCredentials(map);

        validateLoginRequest(loginRequest);

        KServiceClient client = getServiceClient(req);

        Token token = apiAuthService.oauth2Login(req, loginRequest, client);

        User user = userModelService.getUser(token.getUserId());

        //return loggedIn(authModelService.toModel(user, token));
        return loggedIn(authModelService.toModel(user, token));
    }



    @PreAuthorize("hasRole('APP_INTERNAL')")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<TokenModel> logout(HttpServletRequest req,
                                             @RequestBody Map<String, Object> map) {
        logApiRequest(req, "POST /auth/logout");

        // Determine user making request
        String accessToken = KServletUtil.getAccessToken(req);

        Token token = null;

        if (accessToken != null) {
            authService.logout(accessToken);
            token = apiAuthService.fetchTokenByAccessToken(accessToken, false);
        }

        logger.debug("AuthController: logout: accessToken: " + accessToken);
        logger.debug("AuthController: logout: token: " + KJsonUtil.toJson(token));

        return ok(authModelService.toModel(token));
    }



    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @PreAuthorize("hasRole('APP_INTERNAL')")
    public ResponseEntity<AuthSessionModel> register(HttpServletRequest req,
            @RequestBody RegistrationRequest registrationRequest) {
        logApiRequest(req, "POST /auth/users");

        logger.debug("register: registrationRequest: " + registrationRequest);

        AuthSessionModel session = createUser(req, registrationRequest);

        return created(session, "/api/me");
    }



    // Confirmation Request
    @RequestMapping(value="/confirmation", method=RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> confirmationRequest(
            HttpServletRequest req,
            @RequestParam(value="resend", required=false) boolean resend) {

        logApiRequest(req, "POST /auth/confirmation");

        User user = getUser();

        requestAuthCodes(user, getAppId(req), resend);

        return created(getResultObject("request_sent", true));
    }



    @RequestMapping(value = "/users/{auth_code}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('APP_INTERNAL')")
    public ResponseEntity<MeModel> getUserByAuthCode(HttpServletRequest req,
                                                     @PathVariable(value = "auth_code") String code) {
        logApiRequest(req, "GET /auth/users/" + code);

        if (code == null) {
            throw new BadRequestException("code must be specified");
        }

        AuthCode authCode = authCodeService.accessCode(code);

        if (authCode == null) {
            throw new NotFoundException("Invalid or expired authorization code: " + code);
        }

        User user = userModelService.getUser(authCode.getUserId());

        return ok(userModelService.toMeModel(user));
    }



    // Request a password reset
    @RequestMapping(value = "/password/reset", method = RequestMethod.POST)
    @PreAuthorize("hasRole('APP_INTERNAL')")
    public ResponseEntity<Map<String, Object>> resetPassword(HttpServletRequest req,
                                                             @RequestBody Map<String, Object> map) {
        logApiRequest(req, "POST /auth/password/reset");

        String username = (String) map.get("username");

        if (username == null) {
            throw new BadRequestException("Missing username");
        }

        User user = userModelService.getUser(username);

        authCodeService.requestAuthCode(
                KAuthCodeType.PASSWORD_RESET.getId(), getAppId(req), user.getId(), true);

        return ok(getResultObject("password_reset", true));
    }



    // if password
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('APP_INTERNAL')")
    public ResponseEntity<Map<String, Object>> changePassword(HttpServletRequest req,
                                                              @RequestBody Map<String, Object> map) {
        logApiRequest(req, "PUT /auth/password");

        String username = null;
        String password = null;
        String oldPassword = null;

        if (map != null) {
            for (String key : map.keySet()) {
                Object value = map.get(key);
                if (value == null) {
                    logger.warn("Null value for key: " + key);
                    continue;
                }

                switch (key) {
                    case "username":
                        username = value.toString();
                        break;

                    case "old_password":
                        oldPassword = value.toString();
                        break;

                    case "password":
                        password = value.toString();
                        break;

                    default:
                        throw new BadRequestException("Invalid property: " + key);
                }
            }
        }

        if (username == null) {
            throw new BadRequestException("Missing username");
        }

        if (password == null) {
            throw new BadRequestException("Missing password");
        }

        if (oldPassword != null) {
            // make sure oldPassword matches currentPassword
            User u = null;
            try {
                u = userAuthService.validateCredentials(username, oldPassword);
            } catch (Exception e) {
                logger.debug(e.getMessage());

            }
            if (u == null) {
                throw new AuthenticationException("Old password is not valid.");
            }
        }

        apiAuthService.changePassword(username, password);

        return ok(getResultObject("password_changed", true));
    }



    @RequestMapping(value = "/mobile-verification", method = RequestMethod.POST)
    @PreAuthorize("hasRole('APP_INTERNAL')")
    public ResponseEntity<Map<String, Object>> mobileVerificationCode(
            HttpServletRequest req,
            @RequestBody Map<String, Object> map,
            @RequestParam(value = "_test", required = false) Boolean sendTestCode) {
        logApiRequest(req, "POST /auth/mobile-verification");

        String mobileNumber = null;
        if (map != null) {
            for (String key : map.keySet()) {
                Object value = map.get(key);
                if (value == null) {
                    logger.warn("Null value for key: " + key);
                    continue;
                }

                switch (key) {
                    case "mobile_number":
                        mobileNumber = value.toString();
                        break;

                    default:
                        throw new BadRequestException("Invalid property: " + key);
                }
            }
        }

        if (mobileNumber == null) {
            throw new BadRequestException("Missing mobile number");
        }

        // Remove whitespace from the number
        mobileNumber = mobileNumber.replaceAll("\\s+", "");

        if (sendTestCode == null) {
            sendTestCode = false;
        }

        String code = apiAuthService.sendVerificationCode(getAppId(req), mobileNumber, null, sendTestCode);

        return ok(getResultObject("code", code));
    }



    // USE WITH CARE! Generate an access token for a user for a specific clientId.
    // Typical use case is the generating a token for a developer for a new app they created.

    /**
     * @param expire If true, expire existing tokens before creating a new one
     */
    @RequestMapping(value = "/tokens", method = RequestMethod.POST)
    @PreAuthorize("hasRole('APP_INTERNAL')")
    public ResponseEntity<TokenModel> createAccessToken(
            HttpServletRequest req,
            @RequestParam(value = "expire", required = false) boolean expire,
            @RequestBody Map<String, Object> map) {
        logApiRequest(req, "POST /auth/tokens");

        String clientId = null;
        String userUid = null;

        if (map != null) {
            for (String key : map.keySet()) {
                Object value = map.get(key);
                if (value == null) {
                    logger.warn("Null value for key: " + key);
                    continue;
                }

                switch (key) {
                    case "client_id":
                        clientId = value.toString();
                        break;

                    case "user_uid":
                        userUid = value.toString();
                        break;

                    default:
                        throw new BadRequestException("Invalid property: " + key);
                }
            }
        }

        if (clientId == null) {
            throw new BadRequestException("Missing client_id");
        }

        if (userUid == null) {
            throw new BadRequestException("Missing user_uid");
        }

        User user = userModelService.getUser(userUid);

        // first see if there's an existing token. if so, return that token
        List<Token> tokenList = fetchTokenList(clientId, user);

        if (tokenList != null && tokenList.size() > 0) {
            if (!expire) {
                logger.debug("createAccessToken: found existing token(s) for user/client: " + KClassUtil.toString(tokenList));
                return ok(authModelService.toModel(tokenList.get(0)));
            } else {
                for (Token token : tokenList) {
                    token = tokenService.expire(token);
                }
            }
        }

        // these tokens are generated with the user's consent for this client, so set approved to true
        Token token = apiAuthService.createToken(user.getId(), clientId);

        if (!token.isApproved()) {
            token.setApproved(true);
            tokenService.update(token);
        }

        return ok(authModelService.toModel(token));
    }



    @RequestMapping(value = "/tokens", method = RequestMethod.GET)
    @PreAuthorize("hasRole('APP_INTERNAL')")
    public ResponseEntity<List<TokenModel>> listAccessTokens(HttpServletRequest req,
                                                             @RequestParam(value = "user_uid", required = false) String userUid,
                                                             @RequestParam(value = "client_id", required = false) String clientId) {
        logApiRequest(req, "GET /auth/tokens");

        if (userUid == null && clientId == null) {
            throw new BadRequestException("user_uid and/or client_id must be specified");
        }

        User user = null;
        if (userUid != null) {
            user = userModelService.getUser(userUid);
        }

        List<Token> tokenList = fetchTokenList(clientId, user);

        return okList(authModelService.toTokenModelList(tokenList));
    }



    @RequestMapping(value = "/tokens/{access_token}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('APP_INTERNAL')")
    public ResponseEntity<AuthSessionModel> fetchUserAndAccessToken(HttpServletRequest req,
                                                                    @PathVariable(value = "access_token") String accessToken,
                                                                    @RequestParam(value = "client_id", required = true) String clientId) {
        logApiRequest(req, "GET /auth/tokens/" + accessToken);

        if (accessToken == null) {
            throw new BadRequestException("access_token must be specified");
        }

        Token token = apiAuthService.fetchTokenByAccessToken(accessToken, false);

        if (token == null) {
            throw new NotFoundException("access_token not found: " + accessToken);
        }

        logger.debug("token object:\n" + token);

        if (!tokenService.isValid(token, false)) {
            throw new BadRequestException("access_token is not valid");
        }

        if (!token.isApproved()) {
            throw new BadRequestException("access_token is not approved");
        }

        if (clientId != null && token.getAppClientId() != null && !token.getAppClientId().equals(clientId)) {
            logger.debug("token clientId mismatch; return false");
            system.alert("ClientId/Token mismatch", "\n--- clientId ---\n"
                    + clientId + "\n\n--- ntoken ---\n" + token);
            throw new ForbiddenException("not authorized to access token");
        }

        User user = userModelService.getUser(token.getUserId());

        return ok(authModelService.toModel(user, token));
    }



    @PreAuthorize("hasRole('APP_INTERNAL')")
    @RequestMapping(value = "/tokens/{access_token}", method = RequestMethod.DELETE)
    public ResponseEntity<TokenModel> expireAccessToken(HttpServletRequest req,
                                                        @PathVariable(value = "access_token") String accessToken) {
        logApiRequest(req, "DELETE /auth/tokens/" + accessToken);

        if (accessToken == null) {
            throw new BadRequestException("access_token must be specified");
        }

        Token token = apiAuthService.fetchTokenByAccessToken(accessToken);

        if (token == null) {
            throw new NotFoundException("access_token not found: " + accessToken);
        }

        token = tokenService.expire(token);

        return ok(authModelService.toModel(token));
    }


    @PreAuthorize("hasRole('APP_INTERNAL')")
    @RequestMapping(value = "/confirmations/{code}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> confirmCode(HttpServletRequest req,
                                                           @PathVariable String code) {
        logApiRequest(req, "GET /auth/confirmations/" + code);

        // Determine user making request
        String accessToken = KServletUtil.getAccessToken(req);

        User user = userService.fetchByAccessToken(accessToken, false);

        if (user == null) {
            throw new BadRequestException("User not found");
        }


        AuthCode authCode = authCodeService.accessCode(code);

        if (authCode == null) {
            throw new NotFoundException("Invalid confirmation code: " + code);
        }

        if (!user.getId().equals(authCode.getUserId())) {
            throw new ForbiddenException("Current user not authorized to access confirmation code");
        }

        Map<String, Object> result = getResultObject();

        KAuthCodeType type = KAuthCodeType.getInstance(authCode.getTypeId());

        if (type == KAuthCodeType.EMAIL_CONFIRMATION) {
            result.put("channel", "email");
        }

        if (type == KAuthCodeType.MOBILE_CONFIRMATION) {
            result.put("channel", "mobile");
        }

        result.put("confirmed", true);

        return ok(result);
    }

    private void requestAuthCodes(User user, Long appId, boolean resend) {
        Registration registration = registrationService.fetchByUserId(user.getId());

        List<Long> typeIdList = new ArrayList<>();

        if (user.getMobileNumber() != null && !registration.isMobileVerified()) {
            typeIdList.add(KAuthCodeType.MOBILE_CONFIRMATION.getId());
        }

        if (user.getEmail() != null && !registration.isEmailVerified()) {
            typeIdList.add(KAuthCodeType.EMAIL_CONFIRMATION.getId());
        }

        Long[] typeIds = typeIdList.toArray(new Long[0]);

        authCodeService.requestAuthCodes(typeIds, appId, user.getId(), resend);
    }

    private List<Token> fetchTokenList(String clientId, User user) {
        Map<String, Object> filter = new HashMap<String, Object>();

        filter.put("active", true);

        if (clientId != null) {
            filter.put("clientId", clientId);
        }

        if (user != null) {
            filter.put("userId", user.getId());
        }

        List<Token> tokenList = tokenService.fetchByFilter(filter);
        return tokenList;
    }


    
    /*
    protected Credentials toCredentials(Map<String,Object> map) {
        Credentials credentials = new Credentials();
        
        String username = null;
        String password = null;
        String loginCode = null;
        String appVersion = null;
        String appBuild = null;
        boolean authRequest = false;
        
        
        if (map != null) {
        	for (String key : map.keySet()) {
        		Object value = map.get(key);
        		if (value == null) {
        			logger.warn("Null value for key: " + key);
        			continue;
        		}
        		
    			switch (key) {
    			case "username":
                    username = value.toString();
    				break;
                    
    			case "password":
                    password = value.toString();
    				break;
                    
    			case "login_code":
                    loginCode = value.toString();
    				break;
                    
    			case "auth_request":
                    authRequest = Boolean.valueOf(value.toString());
    				break;
                    
    			case "app_version":
                    appVersion = value.toString();
    				break;
                    
    			case "app_build":
                    appBuild = value.toString();
    				break;
    				
    				
    			default:
    				throw new BadRequestException("Invalid property: " + key);
    			}
        	}
        }
        
        credentials.setUsername(username);
        credentials.setPassword(password);
        credentials.setLoginCode(loginCode);
        credentials.setAppVersion(appVersion);
        credentials.setAppBuild(appBuild);
        credentials.setAuthRequest(authRequest);
        
        return validateCredentials(credentials);
	}
	*/


    protected void validateLoginRequest(LoginRequest request) {
        User user = null;

        String email = null;
        String mobileNumber = null;
        String username = request.getUsername();
        String loginCode = request.getLoginCode();
        boolean mobileVerified = false;

        if (request.getUsername() == null || request.getPassword() == null) {
            throw new BadRequestException("Missing username and/or password");
        }

        if (KValidator.isEmail(username)) {
            email = username;

            username = null;

            user = userService.fetchByEmail(email);

            if (user != null) {
                username = user.getUsername();
            }
        } else if (KValidator.isE164PhoneNumber(username)) {
            mobileNumber = username;        // Remove whitespace from the number

            mobileNumber = mobileNumber.replaceAll("\\s+", "");

            username = null;

            user = userService.fetchByMobileNumber(mobileNumber);

            if (user != null) {
                username = user.getUsername();
            }
        } else {
            user = userService.fetchByUsername(username);

            if (user == null) {
                username = null;
            }
        }

        // if username is null at this point then we don't have a valid user
        if (username == null) {
            throw new AuthenticationException("Invalid username and/or password.");
        }

        // if mobileNumber is not null, then we're a valid user with a valid number
        // check to see if the client requested a loginCode. if so, make sure we have
        // one and it's valid
        if (mobileNumber != null) {
            List<String> codes = apiAuthService.getVerificationCodes(mobileNumber);

            if (loginCode == null) {
                if (codes != null) {
                    throw new BadRequestException("loginCode is missing");
                } else {
                    // no loginCode and client didn't request one
                }
            } else {
                if (system.isTestPhoneNumber(mobileNumber) && system.isTestLoginCode(loginCode)) {
                    mobileVerified = true;
                } else {
                    // it's an error if we have a loginCode but client didn't request one
                    if (codes == null || !codes.contains(loginCode)) {
                        throw new ValidationException("loginCode not valid");
                    } else {
                        // ok we have a valid loginCode
                        apiAuthService.removeVerificationCodes(mobileNumber);
                        mobileVerified = true;
                    }
                }
            }
        }

        request.setUsername(username);
        request.setMobileVerified(mobileVerified);
    }



//    private Device getOrCreateDevice(DeviceModel model) {
//        if (model == null) return null;
//
//        // first see if this device already exists
//        Device device = null;
//
//        try {
//            device = deviceModelService.getDevice(model);
//        } catch (NotFoundException e) {
//            // ignore
//        }
//
//
//        if (device == null) {
//            device = deviceModelService.toEntity(model);
//            device.setEnabled(true);
//        } else {
//            Device _device = deviceModelService.toEntity(model);
//
//            // preserve saved object's enabled status
//            boolean _enabled = device.isEnabled();
//
//            // this will overwrite all non null values including booleans
//            device = (Device) util.copyBean(_device, device, true);
//
//            device.setEnabled(_enabled);
//        }
//
//        if (device.getTypeId() == null) {
//            device.setTypeId(KDeviceType.OTHER.getId());
//        }
//
//        if (device.getAdvertiserId() != null && device.getAdvertiserIdType() == null && device.getOsName() != null) {
//            String os = device.getOsName().toLowerCase();
//            switch (os) {
//                case "ios":
//                    device.setAdvertiserIdType(DeviceModel.AdvertiserIdType.IDFA.name());
//                    break;
//                case "android":
//                    device.setAdvertiserIdType(DeviceModel.AdvertiserIdType.AAID.name());
//                    break;
//            }
//        }
//
//        device = deviceService.save(device);
//
//        logger.debug("getOrCreateDevice: device:\n" + device);
//
//        return device;
//    }


//    Boolean verify,
//    Boolean emailVerified,
//    Boolean mobileVerified,
//    Boolean updateUser,
//    boolean login,
//    String scope,
//    String webhookUrl,

    @Transactional
    public AuthSessionModel createUser(
            HttpServletRequest req,
            RegistrationRequest registrationRequest
    ) {

        RegistrationMeta.Options options = registrationRequest.getMeta().getOptions();
        Boolean verify = options.getVerifyUser();
        Boolean emailVerified = options.getEmailVerified();
        Boolean mobileVerified = options.getMobileVerified();
        Boolean updateCurrentUser = options.getUpdateCurrentUser();
        Boolean login = options.getLogin();
        String scope = options.getScope();
        String webhookUrl = options.getWebhookUrl();

        if (login == null) login = false;
        if (verify == null) verify = true;
        if (emailVerified == null) emailVerified = false;
        if (mobileVerified == null) mobileVerified = false;
        if (updateCurrentUser == null) updateCurrentUser = false;

        User user = null;

        if (updateCurrentUser) {
            String _mobileNumber = registrationRequest.getMobileNumber();
            String _username = registrationRequest.getUsername();
            String _email = registrationRequest.getEmail();

            if (_mobileNumber != null) {
                user = userService.fetchByMobileNumber(_mobileNumber);
            }

            if (user == null && _username != null) {
                user = userService.fetchByUsername(_username);
            }

            if (user == null && _email != null) {
                user = userService.fetchByEmail(_email);
            }

            if (user == null) {
                throw new BadRequestException("User not found. _update flag set.");
            }

            logger.debug("updateCurrentUser: user: " + user);
        } else {
            user = new User();

            user.setTypeId(KUserType.USER.getId());
            user.setEnabled(true);
            user.setPresence(KUser.Presence.OFFLINE);
        }

        DeviceModel deviceModel = registrationRequest.getMeta().getDevice();

        String password = registrationRequest.getPassword();

        //user = saveObject(req, user, map);
        UserModel model = registrationRequest.toUserModel();
        user = userModelService.mergeEntity(user, model, false, updateCurrentUser);

        if (password == null) {
            password = KPassGen.generatePassword(8);
        }

        if (user.getEmail() == null && user.getUsername() == null && user.getMobileNumber() == null) {
            throw new BadRequestException("Email, username or mobile number must be specified.");
        }

        KServiceClient client = getServiceClient(req);

        if (updateCurrentUser) {
            // No need to update object here since it will be updated in mergeEntity
            //user = userService.update(user);

            userAuthService.setPlainPassword(user.getId(), password);

        } else {
            // create device object
            //Device device = getOrCreateDevice(deviceModel);
            Device device = deviceModelService.getOrCreateDevice(deviceModel);

            if (device != null) {
                client.setDeviceId(device.getId());
            }

            user = userService.registerUser(user, password, null, client, false);

            if (device != null) {
                String type = KDeviceType.getInstance(device.getTypeId()).getName().toLowerCase();

                if (type.equals("other")) {
                    type = "device";
                }

                String displayName = user.getUsername() + "-" + type;

                userDeviceService.create(user, device, displayName);
            }
        }

        Long appId = getAppId(req);

        //if (emailVerified || mobileVerified) {
        RegistrationMeta meta = registrationRequest.getMeta();

        Registration registration = registrationService.fetchByUserId(user.getId());

        registration.setEmailVerified(emailVerified);
        registration.setMobileVerified(mobileVerified);
        registration.setSignupTime(meta.getSignupTime());

        if (deviceModel != null) {
            registration.setOsName(deviceModel.getOsName());
            registration.setOsVersion(deviceModel.getOsVersion());
        }

        if (meta.getAppVersion() != null) {
            registration.setAppVersion(meta.getAppVersion().getVersion());
            registration.setAppBuild(meta.getAppVersion().getBuild());
        }

        if (meta.getReferredBy() != null) {
            User referredBy = userModelService.getUser(meta.getReferredBy());
            registration.setReferredById(referredBy.getId());
        }

        if (meta.getPosition() != null && meta.getPosition().getCoordinates() != null) {
            PositionModel.Coordinates coords = meta.getPosition().getCoordinates();
            registration.setLatitude(coords.getLatitude());
            registration.setLongitude(coords.getLongitude());
        }

        // TODO: get promo, partner, campaign, etc.
//        if (meta.getPromo() != null) {
//            Promo promo = promoModelService.getPromo(meta.getPromo());
//            registration.setPromoId(promo.getId());
//        }


        registrationService.update(registration);
        //}

        // if updateUser, mergeEntity will auto send email and mobile verifications

        if (!updateCurrentUser && verify && !mobileVerified && user.getMobileNumber() != null) {
            userModelService.sendMobileVerification(appId, user.getId());
        }

        if (!updateCurrentUser && verify && !emailVerified && user.getEmail() != null) {
            userModelService.sendEmailVerification(appId, user.getId());
        }


        //AuthSessionModel session = new AuthSessionModel();
        //session.set("user", userModelService.toMeModel(user));

        Token token = null;

        if (login) {
            LoginRequest credentials = new LoginRequest();
            credentials.setUsername(user.getUsername());
            credentials.setPassword(password);
            credentials.setDevice(deviceModel);
            credentials.setPosition(meta.getPosition());
            credentials.setAppVersion(meta.getAppVersion());

            logger.debug("createUser: login credentials:\n" + credentials + "\n\nuser:\n" + user);

            token = apiAuthService.oauth2Login(req, credentials, client);
            //session.set("token", authModelService.toModel(token));
        }

        return authModelService.toModel(user, token);
    }

}
