package com.bryllyant.kona.api.controller.me;

import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.api.model.device.DeviceModel;
import com.bryllyant.kona.api.model.geo.position.PositionModel;
import com.bryllyant.kona.api.model.media.MediaModel;
import com.bryllyant.kona.api.model.user.PositionRequest;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.api.service.AppModelService;
import com.bryllyant.kona.api.service.DeviceModelService;
import com.bryllyant.kona.api.service.MediaModelService;
import com.bryllyant.kona.api.service.PositionModelService;
import com.bryllyant.kona.api.service.UserModelService;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AuthCodeService;
import com.bryllyant.kona.app.service.DeviceService;
import com.bryllyant.kona.app.service.MediaService;
import com.bryllyant.kona.app.service.PositionService;
import com.bryllyant.kona.app.service.RegistrationService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.app.util.KGeoUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import com.bryllyant.kona.rest.exception.ValidationException;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Me Controller.
 */
@RestController
@RequestMapping("/api/me")
public class MeController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(MeController.class);





    @Autowired
    private UserService userService;
    
    @Autowired
    private MediaService mediaService;

    @Autowired
    private AuthCodeService authCodeService;
    
    @Autowired
    private PositionService positionService;

    @Autowired
    private RegistrationService registrationService;
    
    @Autowired
    private AppModelService appModelService;
    
    @Autowired
    private UserModelService userModelService;

    @Autowired
    private MediaModelService mediaModelService;

    @Autowired
    private DeviceModelService deviceModelService;

    @Autowired
    private PositionModelService positionModelService;

    @Autowired
    private DeviceService deviceService;

   



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<UserModel> getMe(HttpServletRequest req) {
        logApiRequest(req, "GET /me");

		User user = getUser();
		return ok(userModelService.toModel(user));
	}



	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<UserModel> updateMe(HttpServletRequest req,
                                              @RequestBody UserModel model) {
		logApiRequest(req, "PUT /me");

		User user = getUser();

		if (model.getUid() != null && !model.getUid().equals(user.getUid())) {
			throw new ValidationException("Object UID does not match requested UID");
		}
		
		//user = saveObject(req, user, map);
		user = userModelService.mergeEntity(user, model, false, true);

		return ok(userModelService.toModel(user));
	}


	// REQUEST
    /*{
        "device": {
            "advertiser_id": "1234"
        },

        "positions": [
            {                                        
                "latitude":40.01568116251381,                    
                "longitude":-105.2499779687312,                    
                "position_date": 1435755221205                      
            },
            {                                      
                "latitude":40.01554089136423,                    
                "longitude":-105.2499387414243,                    
                "position_date": 1435755235214
            }
        ]
    }*/


    private Device getOrCreateDevice(DeviceModel deviceModel) {
        if (deviceModel == null) return null;

        Device device = null;

        try {
            device = deviceModelService.getDevice(deviceModel);
        } catch (NotFoundException e) {
            if (deviceModel.getAdvertiserId() == null) {
                return null;
            }

            device = new Device();
        }

        device = deviceModelService.mergeEntity(device, deviceModel);

        if (device.getType() == null) {
            // FIXME: Assume phone if no device type

            device.setType(Device.Type.PHONE);

            if (device.getOsName() == null) {
                if (deviceModel.getAdvertiserIdType() != null) {
                    if (deviceModel.getAdvertiserIdType() == DeviceModel.AdvertiserIdType.IDFA) {
                        device.setOsName("ios");
                    } else if (deviceModel.getAdvertiserIdType() == DeviceModel.AdvertiserIdType.AAID) {
                        device.setOsName("android");
                    }
                }
            }

            device = deviceService.save(device);
        }

        return device;
    }

	@SuppressWarnings("unchecked")
    @RequestMapping(value="/positions", method=RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> addPositions(HttpServletRequest req,
	        @RequestBody PositionRequest positionRequest) {
	    logApiRequest(req, "POST /me/positions");

	    User user = getUser();

	    Long deviceId = null;
	    
	    Device device = getOrCreateDevice(positionRequest.getDevice());

	    if (device != null) {
	        deviceId = device.getId();
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
            
            if (position.getDeviceId() == null && deviceId != null) {
                position.setDeviceId(deviceId);
            }
            
            positions.add(position);
        }
        
        positionService.addPositions(positions, true);

        Map<String,Object> result = getResultObject("count", positions.size());
        result.put("encoded_path", KGeoUtil.encode(positions));

	    return created(result);
	}




	@RequestMapping(value = "/media", method = RequestMethod.POST, consumes="multipart/form-data")
	public ResponseEntity<MediaModel> addMediaRequest(MultipartHttpServletRequest req,
                                               @RequestParam(value="upload_date", required=false) Long uploadDate,
                                               @RequestParam(value="latitude", required=false) Double latitude,
                                               @RequestParam(value="longitude", required=false) Double longitude,
                                               @RequestParam(value="description", required=false) String description) {
		logApiRequest(req, "POST /me/media");

		Media media = addMedia(req, uploadDate, latitude, longitude, description);

		return created(mediaModelService.toModel(media));
	}



    @RequestMapping(value="/media/{uid}", method=RequestMethod.DELETE)
	public ResponseEntity<MediaModel> removeMedia(HttpServletRequest req, 
			@PathVariable String uid) {
		logApiRequest(req, "DELETE /me/media/" + uid);

		Media media = mediaModelService.getMedia(uid);

		checkUserAuthorization(media.getUserId());

		mediaService.remove(media);

		return ok(mediaModelService.toModel(media));
	}
	

    // NOTE: spring doesn't work well with PUT and multipart/form-data
    //@RequestMapping(value = "/photo", method = RequestMethod.PUT, consumes="multipart/form-data")
    
    @Transactional
    @RequestMapping(value = "/photo", method = RequestMethod.POST, consumes="multipart/form-data")
    public ResponseEntity<MediaModel> updatePhoto(MultipartHttpServletRequest req,
            @RequestParam(value="upload_date", required=false) Long uploadDate,
            @RequestParam(value="latitude", required=false) Double latitude,
            @RequestParam(value="longitude", required=false) Double longitude,
            @RequestParam(value="description", required=false) String description) {
        logApiRequest(req, "POST /me/photo");

        Media media = addMedia(req, uploadDate, latitude, longitude, description);

        userService.updatePhoto(getUser(), media.getId(), media.getUrlPath(), media.getThumbnailUrlPath());

        return created(mediaModelService.toModel(media));
    }


    @RequestMapping(value="/photo", method=RequestMethod.DELETE)
    public ResponseEntity<MediaModel> removePhoto(HttpServletRequest req) {
        logApiRequest(req, "DELETE /me/photo");

        User user = getUser();

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

        return addMedia(req, getUser(), options);
    }
}
