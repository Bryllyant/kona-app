package com.bryllyant.kona.app.api.controller.user;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.util.ApiUtil;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.SettingService;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.util.KInflector;
import com.bryllyant.kona.util.KJsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
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
 * Settings Controller.
 */
@RestController
@RequestMapping("/api/settings")
public class SettingsController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(SettingsController.class);

	// ----------------------------------------------------------------------
    @Autowired
    private SettingService settingService;    
    
    @Autowired
    private ApiUtil util;

	// ----------------------------------------------------------------------
	
	//private Gson gson = new Gson();
	ObjectMapper mapper = new ObjectMapper();

	// ----------------------------------------------------------------------

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> get(HttpServletRequest req) {
		logApiRequest(req, "GET /settings");
		return ok(getSettings());
	}

	// ----------------------------------------------------------------------

	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Map<String,Object>> update(HttpServletRequest req,
			@RequestBody Map<String,Object> map) {
		logApiRequest(req, "PUT /settings");

		// NOTE convention:
		// settings are stored using camelCaseKeys
		// API transports data using snake_case so each key needs to be transformed before being saved
		
		Map<String,Object> tmp = new HashMap<String,Object>();

		for (String key : map.keySet()) {
		    Object value = map.get(key);

		    key = KInflector.getInstance().camelCase(key.trim().toLowerCase(), false);

		    if (!isValidKey(key)) {
		        throw new BadRequestException("Invalid setting: " + key);
		    }
		    
		    tmp.put(key, value);
		}


		Map<String,Object> settings = saveSettings(getUser(), tmp);

		logger.debug("updated settings object: " + KJsonUtil.toJson(settings));

		return ok(settings); 
	}

	// ----------------------------------------------------------------------

	private Map<String,Object> saveSettings(User user, Map<String,Object> map) {
	    
	    boolean overwriteGlobal = false;

	    settingService.save(user.getId(), user.getAccountId(), map, overwriteGlobal);

	    return getSettings(user);
	}

	// ----------------------------------------------------------------------

	private boolean isValidKey(String key) {
		return true;
	}


	// ----------------------------------------------------------------------

	protected Map<String,Object> getSettings(User user) {
		Map<String,Object> settings = settingService.getUserSettings(user);
		return util.snakeCaseKeys(settings);
	}
}
