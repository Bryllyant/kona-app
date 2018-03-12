/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.controller.admin;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.controller.auth.AuthController;
import com.bryllyant.kona.app.api.model.ModelResultSet;
import com.bryllyant.kona.app.api.model.auth.AuthSessionModel;
import com.bryllyant.kona.app.api.model.auth.RegistrationRequest;
import com.bryllyant.kona.app.api.model.device.DeviceModel;
import com.bryllyant.kona.app.api.model.geo.position.PositionModel;
import com.bryllyant.kona.app.api.model.media.MediaModel;
import com.bryllyant.kona.app.api.model.user.MeModel;
import com.bryllyant.kona.app.api.model.user.PositionRequest;
import com.bryllyant.kona.app.api.service.AccountModelService;
import com.bryllyant.kona.app.api.service.AppModelService;
import com.bryllyant.kona.app.api.service.DeviceModelService;
import com.bryllyant.kona.app.api.service.MediaModelService;
import com.bryllyant.kona.app.api.service.PositionModelService;
import com.bryllyant.kona.app.api.service.UserModelService;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AuthCodeService;
import com.bryllyant.kona.app.service.MediaService;
import com.bryllyant.kona.app.service.PositionService;
import com.bryllyant.kona.app.service.RegistrationService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.app.util.ApiUtil;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.rest.exception.ForbiddenException;
import com.bryllyant.kona.rest.exception.NotFoundException;
import com.bryllyant.kona.rest.exception.ValidationException;
import com.bryllyant.kona.util.KJsonUtil;
import com.bryllyant.kona.util.KResultList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * User Controller.
 */
@RestController
@RequestMapping("/api/admin/users")
public class UserController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
    @Autowired
    private UserModelService userModelService;

    @Autowired
    private AppModelService appModelService;;

    @Autowired
    private AccountModelService accountModelService;;

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
    AuthController authController;


    @Autowired
    private ApiUtil util;
    

	// filter={'email':'abc@abc.com'}
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<ModelResultSet<MeModel>> search(
	        HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="sort", required=false) String[] sortOrder,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit
    ) {

		logApiRequest(req, "GET /admin/users");

		logger.debug("UserController: raw query: " + query);

        Map<String,Object> filter = toFilterCriteria(query);  // returns keys in camelCase

        // exclude system users
        filter.put("!type", User.Type.SYSTEM);

        if (sortOrder == null) {
            sortOrder = new String[]{
                    "lastName",
                    "firstName",
                    "createdDate"
            };
        }
		
		boolean distinct = false;
		
		if (offset == null) {
		    offset = 0;
		}
		
		if (limit == null) {
			limit = 999;
		}

		logger.debug("UserController: filter: " + KJsonUtil.toJson(filter));
		
        KResultList result = userService.fetchByCriteria(offset, limit, sortOrder, filter, distinct);

        ModelResultSet resultSet = ModelResultSet.from(result, userModelService.toMeModelList(result));

        return okList(resultSet);
	}
	

	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public ResponseEntity<MeModel> get(HttpServletRequest req,
			@PathVariable String username) {
		logApiRequest(req, "GET /admin/users/" + username);

		return ok(userModelService.toMeModel(userModelService.getUser(username)));
	}


    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<MeModel> updateUser(HttpServletRequest req,
                                            @PathVariable String username,
                                            @RequestBody MeModel model) {
        logApiRequest(req, "PUT /admin/users/" + username);

        User user = userModelService.getUser(username);

        if (model.getUid() != null && !model.getUid().equals(user.getUid())) {
            throw new ValidationException("Object UID does not match requested UID");
        }

        user = userModelService.mergeEntity(user, model, false, true);

        return ok(userModelService.toMeModel(user));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AuthSessionModel> create(
            HttpServletRequest req,
            @RequestBody RegistrationRequest registrationRequest) {
        logApiRequest(req, "POST /admin/users");

        logger.debug("create: registrationRequest: " + registrationRequest);

        AuthSessionModel session = authController.createUser(req, registrationRequest);

        return created(session);
    }


	@SuppressWarnings("unchecked")
	@RequestMapping(value="/{username}/positions", method=RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> addPositions(HttpServletRequest req,
	        @PathVariable String username,
	        @RequestBody PositionRequest positionRequest) {
	    logApiRequest(req, "POST /admin/users/ " + username + "/positions");
	    
	    User user = userModelService.getUser(username);

	    Long deviceId = null;

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

	        if (deviceId != null) {
	            position.setDeviceId(deviceId);
	        }

	        positions.add(position);
	    }

	    positionService.addPositions(positions, true);

	    return created(getResultObject("count", positions.size()));
	}



    @RequestMapping(value = "/{username}/media", method = RequestMethod.POST, consumes="multipart/form-data")
    public ResponseEntity<MediaModel> addMediaRequest(MultipartHttpServletRequest req,
                                                      @PathVariable String username,
                                                      @RequestParam(value="upload_date", required=false) Long uploadDate,
                                                      @RequestParam(value="latitude", required=false) Double latitude,
                                                      @RequestParam(value="longitude", required=false) Double longitude,
                                                      @RequestParam(value="description", required=false) String description) {
        logApiRequest(req, "POST /admin/users/ " + username + "/media");

        User user = userModelService.getUser(username);

        Media media = addMedia(req, user, uploadDate, latitude, longitude, description);

        return created(mediaModelService.toModel(media));
    }



    @RequestMapping(value="/{username}/media/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<MediaModel> removeMedia(HttpServletRequest req,
                                                  @PathVariable String username,
                                                  @PathVariable String uid) {
        logApiRequest(req, "DELETE /admin/users/ " + username + "/media/" + uid);

        User user = userModelService.getUser(username);

        Media media = mediaModelService.getMedia(uid);

        if (!user.getId().equals(media.getUserId())) {
            throw new ForbiddenException("Access not allowed");
        }

        mediaService.remove(media);

        return ok(mediaModelService.toModel(media));
    }


    // NOTE: spring doesn't work well with PUT and multipart/form-data
    //@RequestMapping(value = "/photo", method = RequestMethod.PUT, consumes="multipart/form-data")

    @Transactional
    @RequestMapping(value = "/{username}/photo", method = RequestMethod.POST, consumes="multipart/form-data")
    public ResponseEntity<MediaModel> updatePhoto(MultipartHttpServletRequest req,
                                                  @PathVariable String username,
                                                  @RequestParam(value="upload_date", required=false) Long uploadDate,
                                                  @RequestParam(value="latitude", required=false) Double latitude,
                                                  @RequestParam(value="longitude", required=false) Double longitude,
                                                  @RequestParam(value="description", required=false) String description) {
        logApiRequest(req, "POST /admin/users/ " + username + "/photo");

        User user = userModelService.getUser(username);

        Media media = addMedia(req, user, uploadDate, latitude, longitude, description);

        userService.updatePhoto(user, media.getId(), media.getUrlPath(), media.getThumbnailUrlPath());

        return created(mediaModelService.toModel(media));
    }


    @RequestMapping(value="/{username}/photo", method=RequestMethod.DELETE)
    public ResponseEntity<MediaModel> removePhoto(
            HttpServletRequest req,
            @PathVariable String username
    ) {
        logApiRequest(req, "DELETE /admin/users/ " + username + "/photo");

        User user = userModelService.getUser(username);

        if (user.getPhotoId() == null) {
            throw new NotFoundException("Photo not defined for user uid: " + user.getUid());
        }

        Media media = mediaService.fetchById(user.getPhotoId());

        if (media == null) {
            throw new NotFoundException("Photo not defined for user uid: " + user.getUid());
        }

        user = userService.removePhoto(user);

        return ok(mediaModelService.toModel(media));
    }

    protected Media addMedia(MultipartHttpServletRequest req,
                             User user,
                             Long uploadDate,
                             Double latitude,
                             Double longitude,
                             String description) {

        logger.debug("MeController: addMedia: uploadDate: " + uploadDate);

        Long uploadTime = null;

        if (uploadDate != null) {
            uploadTime = new Date().getTime() - uploadDate;
        }

        Map<String,Object> options = new HashMap<>();

        options.put("latitude", latitude);
        options.put("longitude", longitude);
        options.put("description", description);
        options.put("uploadTime", uploadTime);

        return addMedia(req, user, options);
    }
	
    @Override
    public Map<String,Object> toFilterCriteria(String json) {
        Map<String,Object> filter = super.toFilterCriteria(json);

        if (filter == null) {
            return new HashMap<>();
        }

        Map<String,Object> result = new HashMap<>();

        for (String key : filter.keySet()) {
            Object value = filter.get(key);

            String[] parts = util.splitKey(key);
            String prefix = parts[0];
            key = parts[1];

            switch (key) {
                case "accountUid":
                    Account account = accountModelService.getAccount(util.getStringValue(value));
                    result.put(prefix + "accountId", account.getId());
                    break;

//                case "uid":
//                    String uid = util.getStringValue(value);
//                    result.put(prefix + key, uid);
//                    break;
//
//                case "email":
//                    String email = util.getStringValue(value);
//                    result.put(prefix + key, email);
//                    break;
//
//                case "username":
//                    String username = util.getStringValue(value);
//                    result.put(prefix + key, username);
//                    break;
//
//                case "gender":
//                    String gender = util.getStringValue(value);
//                    result.put(prefix + key, gender);
//                    break;

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
                    result.put(prefix + key, value);
            }
        }
        
        logger.debug("toUserFilter: filter: " + KJsonUtil.toJson(filter));

        return result;
    } 

}
