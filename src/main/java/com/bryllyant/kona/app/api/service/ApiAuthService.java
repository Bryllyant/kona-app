/*
 * Copyright (C) 2017 Bryllyant, Inc  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.auth.LoginRequest;
import com.bryllyant.kona.app.api.model.geo.position.PositionModel;
import com.bryllyant.kona.app.api.security.token.AccessToken;
import com.bryllyant.kona.app.api.security.token.HttpHeaderTokenReader;
import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AppCreds;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserAuth;
import com.bryllyant.kona.app.service.AppCredsService;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.AuthService;
import com.bryllyant.kona.app.service.KAuthException;
import com.bryllyant.kona.app.service.SmsService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.app.service.UserAuthService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.http.KServletUtil;
import com.bryllyant.kona.locale.KLocaleUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.rest.exception.AuthenticationException;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.rest.exception.SystemException;
import com.bryllyant.kona.util.KRandomUtil;
import com.bryllyant.kona.util.KStringUtil;
import com.google.i18n.phonenumbers.NumberParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ApiAuthService {
	private static Logger logger = LoggerFactory.getLogger(ApiAuthService.class);
	
	private static Map<String,List<String>> verificationCodeMap = new HashMap<String,List<String>>();
	
	@Autowired
	private KConfig config;

	@Autowired
	private HttpHeaderTokenReader tokenReader;
	
	@Autowired
	private SystemService system;
	
	@Autowired
	private OAuth2TokenService oauth2TokenService;
    
	@Autowired
	private AppService appService;
    
	@Autowired
	private AppCredsService appCredsService;
    
	@Autowired
	private SmsService smsService;
    
	@Autowired
	private AuthService authService;
    
	@Autowired
	private UserService userService;
    
	@Autowired
	private UserAuthService userAuthService;
    
	@Autowired
	private TokenService tokenService;

    @Autowired
    private DeviceModelService deviceModelService;

	@Autowired
	private UserModelService userModelService;

    @Autowired
    private PositionModelService positionModelService;


	// ----------------------------------------------------------------------


	// NOTE: this accessToken could either be an APP clientId or a USER accessToken 
	// depending on the context in which it is called.  If this method returns a
	// a value it should be checked against both the TOKEN and APP_CREDS
	public String getAccessToken() {
		String accessToken = null;
		Object obj = SecurityContextHolder.getContext().getAuthentication();

		if (obj != null) {

			//logger.debug("found user authentication object; checking principal");

			Object principal = SecurityContextHolder
					.getContext()
					.getAuthentication()
					.getPrincipal();

			if (principal != null) {
				//logger.debug("found principal object: " + principal);

				if (principal instanceof UserDetails) {
					UserDetails details = (UserDetails) principal;
					accessToken = details.getUsername();
				} else if (principal instanceof String) {
					accessToken = principal.toString();
				}
			}
			else {
				//logger.debug("principal object NOT found");
			}
		}

		logger.debug("getAccessToken: SecurityContext accessToken: " + accessToken);
		return accessToken;
	}
	


	// ----------------------------------------------------------------------

	public  HttpServletRequest getCurrentHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}

	// ----------------------------------------------------------------------

	public User getUser() {
		User user = null;

		String accessToken = getAccessToken();

		if (accessToken != null) {
			user = fetchUserByAccessToken(accessToken);
		} 

		return user;
	}

	// ----------------------------------------------------------------------
	public User getUser(HttpServletRequest req) {
	    User user = null;

	    String accessToken = getAccessToken(req);

	    if (accessToken != null) {
	        user = fetchUserByAccessToken(accessToken);
	    }

	    return user;
	}

	// ----------------------------------------------------------------------

	public Token getToken() {
	    Token token = null;

	    String accessToken = getAccessToken();

		if (accessToken != null) {
			token = fetchTokenByAccessToken(accessToken, false);
		} 

		return token;
	}


	// ----------------------------------------------------------------------
	
	public String getAccessToken(HttpServletRequest req) {
		String accessToken = getAccessToken();
		
		if (accessToken == null) {
			accessToken = KServletUtil.getAccessToken(req);
		}
		
		return accessToken;
	}
	
	// ----------------------------------------------------------------------
	
	public App getApp() {
	    return getApp(getCurrentHttpServletRequest());
	}
	
	// ----------------------------------------------------------------------
	
	public App getApp(HttpServletRequest req) {
		return fetchAppByClientId(getClientId(req));
	}
	
	// ----------------------------------------------------------------------

    public Long getAppId() {
        return getAppId(getCurrentHttpServletRequest()); 
    }

	// ----------------------------------------------------------------------
    public Long getAppId(HttpServletRequest req) {
        Long appId = null;

		App app = fetchAppByClientId(getClientId(req));

		if (app != null) {
		    appId = app.getId();
		}

		return appId;
    }
	
	// ----------------------------------------------------------------------

	public String getClientId(HttpServletRequest req) {
		String clientId = null;
		
		AccessToken token = tokenReader.findToken(req);
		
		if (token != null) {
			clientId = token.getValue();
		}
		
		return clientId;
	}

	// ----------------------------------------------------------------------
	
	public Token directLogin(String username, String password) {
		return directLogin(null, username, password);
	}
	
	// ----------------------------------------------------------------------
	
	public Token directLogin(String clientId, String username, String password) {
		Token token = null;

		try {
			if (clientId == null) {
				clientId = getClientId(getCurrentHttpServletRequest());
			}

			if (clientId == null) {
				throw new BadCredentialsException("Request has a null or invalid clientId.");
			}

			token = authService.login(clientId, username, password);

		} catch (KAuthException e) {
            logger.debug(e.getMessage(), e);
			throw new AuthenticationException("Invalid username and/or password.");
		}

		return token;
	}
	
	// ----------------------------------------------------------------------

	public Token oauth2Login(String clientId, String username, String password) {
		Token token = null;

		try {
			if (clientId == null) {
				clientId = getClientId(getCurrentHttpServletRequest());
			}

			if (clientId == null) {
				throw new BadCredentialsException("Request has a null or invalid clientId.");
			}

			token = oauth2TokenService.login(clientId, username, password);

		} catch (KAuthException e) {
			throw new AuthenticationException("Invalid username and/or password.", e);
		}

		return token;
	}
	
	// ----------------------------------------------------------------------
	
	public Token oauth2Login(HttpServletRequest req, LoginRequest credentials, KServiceClient client) {
		String clientId = getClientId(req);

		try {
			Token token = oauth2Login(clientId, credentials.getUsername(), credentials.getPassword());

			if (token == null) {
			    logger.debug("oauth2Login: invalid username or password:\n" + credentials);

				throw new AuthenticationException("Invalid username and/or password");
			}

			token.setApproved(true);
			
			if (credentials.getAuthRequest() != null && credentials.getAuthRequest()) {
				token.setApproved(false);
			}

			token.setHostname(client.getHostname());
			token.setUserAgent(client.getUserAgent());

			if (credentials.getAppVersion() != null) {
			    token.setAppVersion(credentials.getAppVersion().getVersion());
			    token.setAppBuild(credentials.getAppVersion().getBuild());
            }

            if (credentials.getDevice() != null) {
			    Device device = deviceModelService.getOrCreateDevice(credentials.getDevice());
			    token.setDeviceId(device.getId());
            }

            if (credentials.getPosition() != null && credentials.getPosition().getCoordinates() != null) {
                PositionModel.Coordinates coords = credentials.getPosition().getCoordinates();
			    token.setLatitude(coords.getLatitude());
                token.setLongitude(coords.getLongitude());
            }


			token = tokenService.update(token);

			logger.debug("AccessToken for username [" + credentials.getUsername() +"]: "
					+ token.getAccessToken());

			return token;
		} catch (Exception e) {
		    logger.error(e.getMessage(), e);
			throw new AuthenticationException(e.getMessage());
		}
	}

	// ----------------------------------------------------------------------

	public Token fetchTokenByRefreshToken(String refreshToken) {
		return tokenService.fetchByRefreshToken(refreshToken);
	}

	// ----------------------------------------------------------------------

	public Token fetchTokenByAccessToken(String accessToken) {
		return fetchTokenByAccessToken(accessToken, true);
	}

	// ----------------------------------------------------------------------

	public Token fetchTokenByAccessToken(String accessToken, boolean validate) {
		return tokenService.fetchByAccessToken(accessToken, validate);
	}

	// ----------------------------------------------------------------------
	
	public User fetchUserByAccessToken(String accessToken) {
	    return userService.fetchByAccessToken(accessToken, false);
	}
	
	// ----------------------------------------------------------------------

	public List<Token> fetchTokensByClientId(String clientId) {
		return tokenService.fetchByClientId(clientId);
	}

	// ----------------------------------------------------------------------

	public Token updateToken(Token token) {
		return tokenService.update(token);
	}

	// ----------------------------------------------------------------------
	
	public Token createToken(Long userId, String clientId) {
		return oauth2TokenService.createToken(clientId, userId);
	}

	// ----------------------------------------------------------------------

	public User fetchUserById(Long userId) {
		return userService.fetchById(userId);
	}

	// ----------------------------------------------------------------------

	public App fetchAppByClientId(String clientId) {
		App app = null;

		AppCreds creds = appCredsService.fetchByClientId(clientId);

		if (creds != null) {
			app = appService.fetchById(creds.getAppId());
		}

		return app;
	}

	// ----------------------------------------------------------------------

	public App fetchAppById(Long appId) {
		return appService.fetchById(appId);
	}


	// ----------------------------------------------------------------------

	public AppCreds fetchAppCredsByClientId(String clientId) {
		return appCredsService.fetchByClientId(clientId);
	}

	// ----------------------------------------------------------------------

	public AccessToken getClientAccessToken(HttpServletRequest req) {
		AccessToken accessToken = null;

		try {

			// first check to see if we have a bearer token
			String bearerToken = KServletUtil.getBearerToken(req);

			if (bearerToken != null) {
				Token t = fetchTokenByAccessToken(bearerToken);

				if (t != null && t.isActive() && t.getAppClientId() != null) {
					accessToken = new AccessToken(t.getAppClientId());

					logger.debug("getClientAccessToken(): have bearer token [" 
							+ bearerToken +"] for clientId: " + t.getAppClientId());
				}
			} 

			// if we don't have a bearer token (or token is invalid), check header
			if (accessToken == null) {
				accessToken = tokenReader.findToken(req);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return accessToken;
	}

	// ----------------------------------------------------------------------

	public List<String> toScopeList(String scope) {
		if (scope == null) {
			return null;
		}

		List<String> scopeList = new ArrayList<String>();

		// split scope on comma or whitespace
		String[] scopes = scope.split("\\s*(,|\\s)\\s*");

		for (String s : scopes) {
			s = s.trim().toLowerCase();
			scopeList.add(s);
		}

		return scopeList;
	}
	
	
	// ----------------------------------------------------------------------
	
	// ensure that a comma or space separated list of scopes is always saved as comma separated
    public String toScopeString(String scope) {
        List<String> scopeList = toScopeList(scope);
        return KStringUtil.toCommaList(scopeList);
    }

	// ----------------------------------------------------------------------
	
	public String getUserInfo(HttpServletRequest req) {
		User user = getUser();
		return getUserInfo(req, user);
	}
	
	// ----------------------------------------------------------------------
		
	public String getUserInfo(HttpServletRequest req, User user) {
		// If we're accessing this method from an authenticated resource
		// getAccessToken() should return the user's current token.  However
		// if we're accessing this method from an unauthenticated resource
		// (e.g. System.alert()) then getAccessToken() will be null so we need
		// to manually inspect the headers of the request.
		String accessToken = getAccessToken();

		if (accessToken == null && req != null) {
			accessToken = KServletUtil.getAccessToken(req);
		}

		if (user == null && accessToken != null) {
			user = fetchUserByAccessToken(accessToken);
		}

		String message = "\n-------------------------------------------------\n"
				+ "\nuserId: " + (user == null ? null : user.getId())
				+ "\nusername: " + (user == null ? null : user.getUsername())
				+ "\nfirstName: " + (user == null ? null : user.getFirstName())
				+ "\nlastName: " + (user == null ? null : user.getLastName())
				+ "\nemail: " + (user == null ? null : user.getEmail())
				+ "\nmobileNumber: " + (user == null ? null : user.getMobileNumber())
				+ "\naccessToken: " + (accessToken == null ? null : accessToken)
				+ "\nhostname: " + (req == null ? null : KServletUtil.getClientHostname(req))
				+ "\nuserAgent: " +  (req == null ? null : KServletUtil.getClientUserAgent(req))
				+ "\nreqURL: " +  (req == null ? null : KServletUtil.getFullRequestURL(req))
				+ "\n\n -------------------------------------------------\n\n";

		return message;
	}
	
	// ----------------------------------------------------------------------
	
	public List<String> getVerificationCodes(String mobileNumber) {
        return verificationCodeMap.get(mobileNumber);
    }
    
	// ----------------------------------------------------------------------

    public void addVerificationCode(String mobileNumber, String code) {
        List<String> codes = getVerificationCodes(mobileNumber);
        
        if (codes == null) {
            codes = new ArrayList<String>();
        }
        
        codes.add(code);
        
        verificationCodeMap.put(mobileNumber, codes);
    }
    
	// ----------------------------------------------------------------------

    public void removeVerificationCodes(String mobileNumber) {
        verificationCodeMap.remove(mobileNumber);
    }
    
	// ----------------------------------------------------------------------

    public String sendVerificationCode(Long appId, String mobileNumber,
            final String country, final boolean sendTestCode) {

        // since toPhoneNumber will throw an error on the test number
        // we need to check for the test number in another way.
        // For now assume, US formatted numbers: (xxx) xxx-xxxx

        String code = "";
        
        String phoneNumber = system.formatPhoneNumber(mobileNumber);
        if (system.isTestPhoneNumber(phoneNumber) || sendTestCode) {
            code = config.getString("system.test.loginCode");
            //mobileNumber = KLocaleUtil.toE164PhoneNumber(mobileNumber, country);
            mobileNumber = phoneNumber;
            addVerificationCode(mobileNumber, code);
            return code;
        }

        String originalNumber = mobileNumber;
        
        try {
            mobileNumber = KLocaleUtil.toE164PhoneNumber(mobileNumber, country);
        } catch (NumberParseException e1) {
            throw new BadRequestException("Invalid mobile number: " + originalNumber + "  country: " + country);
        }

        Integer[] digits = KRandomUtil.getRandomDigits(4, 1, 9);
        for (int i=0; i<digits.length; i++) {
            code += digits[i];
        }

        App app = appService.fetchById(appId);

        String message = app.getName() + " verification code: " + code;

        try {
            //logger.debug("Sending sms code [" + code + "] to mobileNumber: " + mobileNumber);
            smsService.sendMessage(mobileNumber, message);
        } catch(Exception e) {
            throw new SystemException("Error sending sms code [" + code + "] to mobileNumber: " + mobileNumber);
        }

        addVerificationCode(mobileNumber, code);
        return code;
    }
    
    // ----------------------------------------------------------------------
    
    public void changePassword(final String username, String password) {
        //logger.debug("change password for username: [" + username + "]");

        User user = userModelService.getUser(username);
        userAuthService.setPlainPassword(user.getId(), password);
    }
    
    // ----------------------------------------------------------------------

    public boolean resetPassword(final String username) {
        //logger.debug("reset password for username: [" + username + "]");

        User user = userModelService.getUser(username);
        UserAuth userAuth = userAuthService.resetPassword(user.getId());
        return userAuth != null;
    }
}
