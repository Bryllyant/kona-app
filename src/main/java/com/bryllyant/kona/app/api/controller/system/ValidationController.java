package com.bryllyant.kona.app.api.controller.system;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.service.ApiAuthService;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.Product;
import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.AuthService;
import com.bryllyant.kona.app.service.ProductService;
import com.bryllyant.kona.app.service.PromoService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * Validation Controller.
 */
@RestController
@RequestMapping("/api/system/validations")
public class ValidationController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(ValidationController.class);

    // ----------------------------------------------------------------------

    @Autowired
    private ApiAuthService apiAuthService;

    @Autowired
    private AppService appService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PromoService promoService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SystemService system;

    // ----------------------------------------------------------------------

	@RequestMapping(value="/access-tokens/{accessToken}", method=RequestMethod.GET)
	@PreAuthorize("hasRole('APP')")
	public ResponseEntity<Map<String,Object>> isTokenValid(
			HttpServletRequest req,
			@PathVariable final String accessToken,
			@RequestParam(value="login", required=false) Boolean login,
			@RequestParam(value="appVersion", required=false) String appVersion,
			@RequestParam(value="appBuild", required=false) String appBuild) {
		logApiRequest(req, "GET /system/validations/access-tokens/" + accessToken);

		if (login == null) {
			login = false;
		}

		String clientId = apiAuthService.getClientId(req);

		logger.debug("Checking accessToken: " + accessToken
				+ "\nclientId: " + clientId
				+ "\nlogin: " + login
				+ "\nappVersion: " + appVersion
				+ "\nappBuild: " + appBuild
				);

		if (accessToken == null) {
			throw new BadRequestException("Token key is null");
		}
		
		Token token = apiAuthService.fetchTokenByAccessToken(accessToken, false);

		if (token == null) {
			logger.debug("token is null; return false"); 
			return ok(getResultObject("valid", false));
		}

		logger.debug("token object:\n" + token); 

		if (!tokenService.isValid(token, false)) {
			logger.debug("token is not valid; return false"); 
			return ok(getResultObject("valid", false));
		}

		if (!token.isApproved()) {
			logger.debug("token is not approved; return false"); 
			return ok(getResultObject("valid", false));
		}

		if (clientId != null && token.getAppClientId() != null && !token.getAppClientId().equals(clientId)) {
			logger.debug("token clientId mismatch; return false"); 

			system.alert("ClientId/Token mismatch", 
					"\n--- clientId ---\n" + clientId + "\n\n--- token ---\n" + token);

			return ok(getResultObject("valid", false));
		}

		boolean updateToken = false;

		if (login) {
			KServiceClient client = getServiceClient(req);
			client.setAppVersion(appVersion);
			client.setAppBuild(appBuild);
			authService.login(token, client);
			return ok(getResultObject("valid", true));
		}

		if (appVersion != null)  {
			updateToken = true;
			token.setAppVersion(appVersion);
		}

		if (appBuild != null) {
			updateToken = true;
			token.setAppBuild(appBuild);
		}

		if (updateToken) {
			tokenService.update(token);
		}

		return ok(getResultObject("valid", true));
	}

	// ----------------------------------------------------------------------

	// Allow periods in username
	@RequestMapping(value="/usernames/{username:.*}", method=RequestMethod.GET)
	@PreAuthorize("hasRole('APP')")
	public ResponseEntity<Map<String,Object>> isUsernameAvailable(
			HttpServletRequest req,
			@PathVariable String username,
			@RequestParam(value="uid", required=false) String uid) {
		logApiRequest(req, "GET /system/validations/usernames/" + username);

		User user = null;

		if (username == null) {
			throw new BadRequestException("Username is null.");
		}

		if (uid != null) {
			user = userService.fetchByUid(uid);

			if (user == null) {
				throw new BadRequestException("Invalid uid: " + uid);
			}

			if (user.getUsername().equals(username)) {
				return ok(getResultObject("valid", true));
			}
		}

		boolean valid = userService.isUsernameAvailable(username);

		return ok(getResultObject("valid", valid));
	}

	// ----------------------------------------------------------------------

	// NOTE: default PathVariable will not accept period in URI so it must
	// be expressed the the following way: {variable:.*}
	// FIXME: seems to fail on .com addresses
	@RequestMapping(value="/emails/{email:.*}", method=RequestMethod.GET)
	@PreAuthorize("hasRole('APP')")
	public ResponseEntity<Map<String,Object>> isEmailAvailable(
			HttpServletRequest req,
			@PathVariable final String email,
			@RequestParam(value="uid", required=false) String uid) {
		logApiRequest(req, "GET /system/validations/emails/" + email);

		User user = null;

		if (email == null) {
			throw new BadRequestException("Email is null");
		}

		if (!KValidator.isEmail(email)) {
			return ok(getResultObject("valid", false));
		}

		if (uid != null) {
			user = userService.fetchByUid(uid);

			if (user == null) {
				throw new BadRequestException("Invalid uid: " + uid);
			}

			if (user.getEmail() != null && user.getEmail().equals(email)) {
				return ok(getResultObject("valid", true));
			}
		}


		user = userService.fetchByEmail(email);

		boolean valid = (user == null);

		return ok(getResultObject("valid", valid));
	}
	// ----------------------------------------------------------------------

	// NOTE: default PathVariable will not accept period in URI so it must
	// be expressed the the following way: {variable:.*}
	@RequestMapping(value="/mobile-numbers/{mobileNumber:.*}", method=RequestMethod.GET)
	@PreAuthorize("hasRole('APP')")
	public ResponseEntity<Map<String,Object>> isMobileNumberAvailable(
			HttpServletRequest req,
			@PathVariable String mobileNumber,
			@RequestParam(value="country", required=false) String country,
			@RequestParam(value="match_username", required=false) Boolean matchUsername,
			@RequestParam(value="uid", required=false) String uid) {
		logApiRequest(req, "GET /system/validations/mobile-numbers/" + mobileNumber);

		User user = null;

		//Remove whitespace from the number
		mobileNumber = mobileNumber.replaceAll("\\s+","");

		if (country == null && !mobileNumber.startsWith("+")) {
			country = "US";
		}
		
		if (matchUsername == null) {
		    matchUsername = false;
		}

		String phoneNumber = system.formatPhoneNumber(mobileNumber);
		if (system.isTestPhoneNumber(phoneNumber)) {
			mobileNumber = phoneNumber;
		} else {
			try {
				PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
				PhoneNumber proto = phoneUtil.parse(mobileNumber, country);
				boolean isValid = phoneUtil.isValidNumber(proto); 
				if (isValid) {
					mobileNumber = phoneUtil.format(proto, PhoneNumberFormat.E164);
				}
			} catch (NumberParseException e) {
				logger.debug("Mobile number is not a valid phone number: " + mobileNumber, e);
				return ok(getResultObject("valid", false));
			}
		}

		if (uid != null) {
			user = userService.fetchByUid(uid);
			
			if (user == null) {
				throw new BadRequestException("Invalid uid: " + uid);
			}
			
			if (user.getMobileNumber() != null && user.getMobileNumber().equals(mobileNumber)) {
				return ok(getResultObject("valid", true));
			}
		}

		user = userService.fetchByMobileNumber(mobileNumber);
		
		// if flag is set then check if mobileNumber equals username.  if so, this user was auto-registered
		// and so this mobile number is available to be registered
		if (user != null && matchUsername) {
		    // remove leading '+'
		    String tmpUsername = mobileNumber.substring(1);

		    // we have a match
		    if (user.getUsername() != null && user.getUsername().equals(tmpUsername)) {
		        return ok(getResultObject("valid", true));
		    }
		}
		
		boolean valid = (user == null);

		return ok(getResultObject("valid", valid));
	}


	// ----------------------------------------------------------------------

	@RequestMapping(value="/apps/{name:.*}", method=RequestMethod.GET)
	@PreAuthorize("hasRole('APP')")
	public ResponseEntity<Map<String,Object>> isAppNameAvailable(
			HttpServletRequest req,
			@PathVariable String name) {
		logApiRequest(req, "GET /system/validations/apps/" + name);
		
		boolean valid = appService.isAppNameAvailable(name);

		return ok(getResultObject("valid", valid));
	}


	// ----------------------------------------------------------------------

	// Allow periods in promoCode
	@RequestMapping(value="/promo-codes/{promoCode:.*}", method=RequestMethod.GET)
	@PreAuthorize("hasRole('APP')")
	public ResponseEntity<Map<String,Object>> isPromoCodeValid(
			HttpServletRequest req,
			@PathVariable final String promoCode,
			@RequestParam(required=false) final Long accountId,
			@RequestParam(required=false) final Long productId) {
		logApiRequest(req, "GET /system/validations/promo-codes/" + promoCode);

		if (promoCode == null) {
			throw new BadRequestException("Promo code is null");
		}

		Account account = null;

		if (accountId != null) {
			account = accountService.fetchById(accountId);
		}


		Product product = null;
		if (productId != null) {
			product = productService.fetchById(productId);
		}

		
		Promo promo = promoService.fetchByPromoCode(promoCode, account, product);

		boolean valid = promo != null;
		
		return ok(getResultObject("valid", valid));
	}

}
