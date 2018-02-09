package com.bryllyant.kona.app.api.controller.me;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.PushDevice;
import com.bryllyant.kona.app.service.DeviceService;
import com.bryllyant.kona.app.service.PushDeviceService;
import com.bryllyant.kona.app.service.PushProviderService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserDeviceService;
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
 * PushProvider Controller.
 */
@RestController
@RequestMapping("/api/me/push")
public class MyPushController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(MyPushController.class);

    // ----------------------------------------------------------------------

    @Autowired
    private PushProviderService pushProviderService;

    @Autowired
    private PushDeviceService pushDeviceService;

    @Autowired
    private UserDeviceService userDeviceService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private SystemService system;

    // ----------------------------------------------------------------------

    @RequestMapping(value="/devices", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> create(HttpServletRequest req,
                                                     @RequestBody Map<String,Object> map) {
        logApiRequest(req, "POST /me/push/devices");

        Long appId = getAppId(req);

        String pushToken = (String) map.get("push_token");
        String pushPlatform = (String) map.get("platform");
        String deviceUuid = (String) map.get("device_uuid");
        String advertiserId = (String) map.get("advertiser_id");
        Boolean limitAdTrackingEnabled = (Boolean) map.get("limit_ad_tracking_enabled");
        Boolean sandbox = (Boolean) map.get("sandbox");

        if (pushToken == null) {
            throw new BadRequestException("push_token must be set");
        }

        if (pushPlatform == null) {
            throw new BadRequestException("platform must be set");
        }

        pushToken = pushToken.trim();

        pushPlatform = pushPlatform.trim().toLowerCase();

        if (advertiserId != null) {
            advertiserId = advertiserId.trim().toUpperCase();
        }

        if (deviceUuid != null) {
            deviceUuid = deviceUuid.trim().toUpperCase();
        }

        if (sandbox == null) {
            sandbox = false;
        }

        if (limitAdTrackingEnabled == null) {
            limitAdTrackingEnabled = false;
        }

        if (pushProviderService.getPushPlatform(pushPlatform, sandbox) == null) {
            throw new BadRequestException("Invalid platform: " + pushPlatform);
        }


        Device device = new Device();
        device.setAdvertiserIdType(advertiserId);
        device.setDeviceUuid(deviceUuid);
        device.setOsName(pushPlatform);
        device.setLimitAdTrackingEnabled(limitAdTrackingEnabled);

        try {
            PushDevice pushDevice = pushDeviceService.save(appId, getUser(), device, pushToken, sandbox);

            if (pushDevice == null) {
                throw new SystemException("Error creating push notification device");
            }

            return created(toMap(pushDevice));
        } catch (Throwable t) {
            throw new SystemException(t);
        }

//        List<UserDevice> userDeviceList = userDeviceService.fetchByUserId(getUser().getId());
//
//        if (userDeviceList == null || userDeviceList.size() == 0) {
//            Device device = new Device();
//            device.setAdvertiserIdType(advertiserId);
//            device.setDeviceUuid(deviceUuid);
//            device.setOsName(pushPlatform);
//            device.setLimitAdTrackingEnabled(limitAdTrackingEnabled);
//            device.setEnabled(true);
//            userDevice = userDeviceService.create(getUser(), device, null);
//        } else if (advertiserId != null) {
//            for (UserDevice ud : userDeviceList) {
//                if (ud.getAdvertiserId() != null && ud.getAdvertiserId().equalsIgnoreCase(advertiserId)) {
//                    userDevice = ud;
//                    break;
//                }
//            }
//        } else if (deviceUuid != null) {
//            for (UserDevice ud : userDeviceList) {
//                if (ud.getDeviceUuid() != null && ud.getAdvertiserId().equalsIgnoreCase(deviceUuid)) {
//                    userDevice = ud;
//                    break;
//                }
//            }
//        } else {
//            // we have at least one device and no advertiserId or deviceUuid
//            // check if we have a matching platform, if so just grab the first match.
//            for (UserDevice ud : userDeviceList) {
//                if (ud.getOsName() != null && ud.getOsName().equalsIgnoreCase(pushPlatform)) {
//                    userDevice = ud;
//
//                    // check if this device is already assigned to this token
//                    pushDevice = pushDeviceService
//                            .fetchByAppIdAndUserIdAndDeviceId(appId, userId, ud.getDeviceId(), sandbox);
//
//                    if (pushDevice != null
//                            && pushDevice.getPushToken() != null
//                            && pushDevice.getPushToken().equalsIgnoreCase(pushToken)) {
//                        break;
//                    }
//
//                    pushDevice = null;
//                }
//            }
//        }
//
//        // if don't have a device at this point, create a new one for this user and platform
//        if (userDevice == null) {
//            Device device = new Device();
//            device.setAdvertiserIdType(advertiserId);
//            device.setDeviceUuid(deviceUuid);
//            device.setOsName(pushPlatform);
//            device.setLimitAdTrackingEnabled(limitAdTrackingEnabled);
//            device.setEnabled(true);
//            userDevice = userDeviceService.create(getUser(), device, null);
//        }
//
//        deviceId = userDevice.getDeviceId();
//
//
//        if (pushDevice != null) {
//            pushDevice = pushDeviceService
//                    .fetchByAppIdAndUserIdAndDeviceId(appId, userId, deviceId, sandbox);
//        }
//
//        // sanity check - see if this push token is tied to another
//        PushDevice otherPushDevice =
//                pushDeviceService.fetchByPushToken(pushToken);
//
//        if (otherPushDevice != null && (pushDevice == null || !pushDevice.getId().equals(otherPushDevice.getId()))) {
//            logger.info("Push token already assigned to different device:\n other device: ${otherPushDevice}\n current push device: ${pushDevice}");
//            throw new ValidationException("Push token already assigned to a different device");
//        }
//
//        if (pushDevice == null) {
//            PushProvider pushProvider =
//                    pushProviderService
//                            .fetchByAppIdAndPlatformAndSandbox(appId, pushPlatform, sandbox);
//
//            pushDevice = new PushDevice();
//            pushDevice.setAppId(appId);
//            pushDevice.setUserId(userId);
//            pushDevice.setDeviceId(deviceId);
//            pushDevice.setPushProviderId(pushProvider.getId());
//        }
//
//        pushDevice.setPushToken(pushToken);
//        pushDevice.setSandbox(sandbox);
//
//        pushDevice = pushDeviceService.save(pushDevice);
//
//        logger.debug("created pushDevice: " + pushDevice);



    }


    public Map<String,Object> toMap(PushDevice pushDevice) {
        Device device = deviceService.fetchById(pushDevice.getDeviceId());

        Map<String,Object> map = new HashMap<>();

        map.put("device_uid", device.getUid());
        map.put("platform_name", pushDevice.getPushPlatform());
        map.put("push_token", pushDevice.getPushToken());
        map.put("sandbox", pushDevice.isSandbox());
        return map;
    }


}
