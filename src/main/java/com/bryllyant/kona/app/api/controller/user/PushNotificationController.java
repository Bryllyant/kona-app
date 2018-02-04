package com.bryllyant.kona.app.api.controller.user;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.entity.PushNotificationDevice;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.PushNotificationDeviceService;
import com.bryllyant.kona.app.service.PushProviderService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.rest.exception.BadRequestException;
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
 * PushNotificationProvider Controller.
 */
@RestController
@RequestMapping("/api/push-notifications")
public class PushNotificationController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(PushNotificationController.class);

	// ----------------------------------------------------------------------

    @Autowired
    private PushProviderService pushProviderService;	

    @Autowired
    private PushNotificationDeviceService pushNotificationDeviceService;	
	
    @Autowired
    private SystemService system;	

	// ----------------------------------------------------------------------
	
    @RequestMapping(value="/devices", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> create(HttpServletRequest req,
            @RequestBody Map<String,Object> map) {
    	logApiRequest(req, "POST /push-notifications/devices");
    	
    	User user = getUser();
    	Long appId = getAppId(req);
    	
        String mobileNumber = (String) map.get("mobile_number");
        String pushToken = (String) map.get("push_token");
        String platformName = (String) map.get("platform");
        String deviceUuid = (String) map.get("device_uuid");
        Boolean sandbox = (Boolean) map.get("sandbox");
        
        if (mobileNumber == null) {
            throw new BadRequestException("mobile_number must be set");
        }

        mobileNumber = mobileNumber.replaceAll("\\s+","");

		if (!system.isTestPhoneNumber(mobileNumber) && !KValidator.isE164PhoneNumber(mobileNumber)) {
			throw new BadRequestException("Invalid mobile number [" + mobileNumber + "]. Mobile numbers must be in E.164 format.");
		}

        
        if (pushToken == null) {
            throw new BadRequestException("push_token must be set");
        }
        
        if (platformName == null) {
            throw new BadRequestException("platform must be set");
        }
        
        if (pushProviderService.getPushPlatform(platformName, false) == null) {
            throw new BadRequestException("Invalid platform: " + platformName);
        }
        
        if (deviceUuid == null) {
            throw new BadRequestException("device_uuid must be set");
        }
        
        if (sandbox == null) {
        	sandbox = false;
        }
        
        
        PushNotificationDevice pushNotificationDevice = 
        		pushNotificationDeviceService
        		.fetchByAppIdAndUserIdAndDeviceUuid(appId, user.getId(), deviceUuid);
        
        if (pushNotificationDevice == null) {
        	pushNotificationDevice = new PushNotificationDevice();
        	pushNotificationDevice.setAppId(appId);
        	pushNotificationDevice.setUserId(user.getId());
        	pushNotificationDevice.setDeviceUuid(deviceUuid);
        }
        
        pushNotificationDevice.setPushToken(pushToken);
        pushNotificationDevice.setSandbox(sandbox);
        
        pushNotificationDevice = pushNotificationDeviceService.save(pushNotificationDevice);
        
        logger.debug("created pushNotificationDevice: " + pushNotificationDevice);
        
        
        if (pushNotificationDevice == null) {
            throw new SystemException("Error creating push notification device");
        }
            
        return created(toMap(pushNotificationDevice));
    }
    
	// ----------------------------------------------------------------------
    
    public Map<String,Object> toMap(PushNotificationDevice device) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("device_uuid", device.getDeviceUuid());
		map.put("platform_name", device.getPlatformName());
		map.put("push_token", device.getPushToken());
		map.put("sandbox", device.isSandbox());
		return map;
	}

	
}
