package com.bryllyant.kona.api.controller.system;

import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.api.service.ApiAuthService;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Policy;
import com.bryllyant.kona.app.service.PolicyService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.rest.exception.NotFoundException;
import com.bryllyant.kona.rest.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * System Controller.
 */
@RestController
@RequestMapping("/api/system")
public class SystemController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(SystemController.class);


	
	@Autowired
	private PolicyService policyService;

	@Autowired
	private ApiAuthService apiAuthService;

	@Autowired
	private SystemService system;
	


	@RequestMapping(value="/alert", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> alert(HttpServletRequest req,
			@RequestBody Map<String,Object> map) {
		logApiRequest(req, "/system/alert");

		try {
			String message = (String) map.get("message");
			
            if (message == null) {
            	throw new BadRequestException("Message cannot be null");
            }
            
			message = apiAuthService.getUserInfo(req) + message;
			
			App app = apiAuthService.getApp();

			String subject = "[" + app.getName() + "] Client Alert";
			
			system.alert(subject, message);
			
			return created(getResultObject("alert_sent", true));
		} catch (Exception e) {
		    throw new SystemException("Unable to send alert");
		}
	}



	@RequestMapping(value="/legal/terms", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> getTerms(HttpServletRequest req) {
	    logApiRequest(req, "/system/legal/terms");

	    Policy terms = policyService.fetchActive(Policy.Type.TERMS);

	    if (terms == null) {
	        throw new NotFoundException("Terms not found");
	    }

	    Map<String,Object> result = getResultObject("content", terms.getContent());

	    return ok(toMap(terms));
	}



	@RequestMapping(value="/legal/privacy", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> getPrivacy(HttpServletRequest req) {
	    logApiRequest(req, "/system/legal/privacy");

	    Policy privacy = policyService.fetchActive(Policy.Type.PRIVACY);

	    if (privacy == null) {
	        throw new NotFoundException("Privacy not found");
	    }

	    return ok(toMap(privacy));
	}

	   

    protected Map<String,Object> toMap(Policy appLegal) {
        if (appLegal == null) return null;

        Map<String,Object> result = new HashMap<String,Object>();
        result.put("uid", appLegal.getUid());
        result.put("type", appLegal.getType());
        result.put("content", appLegal.getContent());
        result.put("version", appLegal.getVersion());
        result.put("active", appLegal.isActive());
        result.put("published_date", appLegal.getPublishedDate());
        return result;
    }
}
