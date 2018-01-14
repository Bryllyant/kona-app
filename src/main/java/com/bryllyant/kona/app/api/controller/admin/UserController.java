/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bryllyant.kona.app.entity.KUserType;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.rest.exception.NotFoundException;
import com.bryllyant.kona.util.KJsonUtil;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.model.app.AppModel;
import com.bryllyant.kona.app.api.model.device.DeviceModel;
import com.bryllyant.kona.app.api.model.position.PositionModel;
import com.bryllyant.kona.app.api.model.user.PositionRequest;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.api.service.AppModelService;
import com.bryllyant.kona.app.api.service.DeviceModelService;
import com.bryllyant.kona.app.api.service.MediaModelService;
import com.bryllyant.kona.app.api.service.PositionModelService;
import com.bryllyant.kona.app.api.service.UserModelService;
import com.bryllyant.kona.app.api.util.ApiUtil;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AuthCodeService;
import com.bryllyant.kona.app.service.MediaService;
import com.bryllyant.kona.app.service.PositionService;
import com.bryllyant.kona.app.service.RegistrationService;
import com.bryllyant.kona.app.service.UserService;


/**
 * User Controller.
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
    // ----------------------------------------------------------------------

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private AppModelService appModelService;;

    @Autowired
    private UserService userService;;
    
    @Autowired
    private MediaService mediaService;

    @Autowired
    private AuthCodeService authCodeService;

    @Autowired
    private RegistrationService registrationService;
    
    @Autowired
    private MediaModelService mediaModelService;

    @Autowired
    private DeviceModelService deviceModelService;

    @Autowired
    private PositionModelService positionModelService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private ApiUtil util;
    
	// ----------------------------------------------------------------------
	
	// filter={'email':'abc@abc.com'}
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserModel>> search(HttpServletRequest req,
			@RequestParam(value="q", required=false) String query,
			@RequestParam(value="offset", required=false) Integer offset,
			@RequestParam(value="limit", required=false) Integer limit) {
		logApiRequest(req, "GET /admin/users");

		logger.debug("UserController: raw query: " + query);

        Map<String,Object> filter = toFilterCriteria(query);  // returns keys in camelCase

        if (filter == null) {
            filter = new HashMap<String,Object>();
        }
        
        // exclude system users
        filter.put("!typeId", KUserType.SYSTEM.getId());

		String[] sortOrder = { "created_date" };
		
		boolean distinct = false;
		
		if (offset == null) {
		    offset = 0;
		}
		
		if (limit == null) {
			limit = 100;
		}

		logger.debug("UserController: filter: " + KJsonUtil.toJson(filter));
		
		return okList(userModelService.toUserModelList(
				userService.fetchByCriteria(offset, limit, sortOrder, filter, distinct)));
	}
	
	// ----------------------------------------------------------------------

	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public ResponseEntity<UserModel> get(HttpServletRequest req,
			@PathVariable String username) {
		logApiRequest(req, "GET /admin/users/" + username);

		return ok(userModelService.toModel(userModelService.getUser(username)));
	}

	// ----------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/{username}/positions", method=RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> addPositions(HttpServletRequest req,
	        @PathVariable String username,
	        @RequestBody PositionRequest positionRequest) {
	    logApiRequest(req, "POST /admin/users/ " + username + "/positions");
	    
	    User user = userModelService.getUser(username);

	    Long appId = null;

	    Long deviceId = null;

	    AppModel appModel = positionRequest.getApp();

        if (appModel != null) {
            try {
                App app = appModelService.getApp(appModel);
                appId = app.getId();
            } catch (NotFoundException e) {
                // ignore
            }
        }

	    DeviceModel deviceModel = positionRequest.getDevice();

        if (deviceModel != null) {
            try {
                Device device = deviceModelService.getDevice(deviceModel);
                deviceId = device.getId();
            } catch (NotFoundException e) {
                // ignore
            }
        }

	    List<PositionModel> positionList = positionRequest.getPositions();

	    List<Position> positions = new ArrayList<>(positionList.size());

	    for (PositionModel positionModel : positionList) {

	        if (positionModel == null) {
	            logger.warn("Got null object in location object list");
	            continue;
	        }

	        Position position = positionModelService.toEntity(positionModel);
	        position.setUserId(user.getId());

	        if (appId != null) {
	            position.setAppId(appId);
	        }

	        if (deviceId != null) {
	            position.setDeviceId(deviceId);
	        }

	        positions.add(position);
	    }

	    positionService.addPositions(positions, true);

	    return created(getResultObject("count", positions.size()));
	}
	
	  // ----------------------------------------------------------------------
    @Override
    public Map<String,Object> toFilterCriteria(String json) {
        Map<String,Object> filter = super.toFilterCriteria(json);

        if (filter == null) {
            return null;
        }

        Map<String,Object> result = new HashMap<>();

        for (String key : filter.keySet()) {
            Object value = filter.get(key);

            String[] parts = util.splitKey(key);
            String prefix = parts[0];
            key = parts[1];

            switch (key) {
                case "uid":
                    String uid = util.getStringValue(value);
                    result.put(prefix + key, uid);
                    break;

                case "email":
                    String email = util.getStringValue(value);
                    result.put(prefix + key, email);
                    break;

                case "username":
                    String username = util.getStringValue(value);
                    result.put(prefix + key, username);
                    break;

                case "gender":
                    String gender = util.getStringValue(value);
                    result.put(prefix + key, gender);
                    break;

                case "mobileNumber":
                    String mobileNumber = util.getStringValue(value);
                    // Remove whitespace from the number
                    if (mobileNumber != null) {
                        mobileNumber = mobileNumber.replaceAll("\\s+","");
                    }
                    result.put(prefix + key, mobileNumber);
                    break;

                case "birthDate":
                    String birthDate = util.getStringValue(value);
                    SimpleDateFormat df = new SimpleDateFormat(ApiUtil.BIRTH_DATE_FORMAT);
                    try {
                        result.put(prefix + key, df.parse(birthDate));
                    } catch (ParseException e) {
                        throw new BadRequestException("Invalid birth date: " + birthDate);
                    }
                    break;

                default:
                    throw new BadRequestException("Invalid property: " + key);
            }
        }
        
        logger.debug("toUserFilter: filter: " + KJsonUtil.toJson(filter));

        return result;
    } 

}
