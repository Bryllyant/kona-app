package com.bryllyant.kona.api.controller.me;

import com.bryllyant.kona.api.controller.BaseController;
import com.bryllyant.kona.api.model.device.UserDeviceModel;
import com.bryllyant.kona.api.service.DeviceModelService;
import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserDevice;
import com.bryllyant.kona.app.service.UserDeviceService;
import com.bryllyant.kona.rest.exception.ValidationException;
import com.bryllyant.kona.util.KJsonUtil;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Device Controller.
 */
@RestController
@RequestMapping("/api/me/devices")
public class MyDeviceController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(MyDeviceController.class);



    @Autowired
    private UserDeviceService userDeviceService;
    
    @Autowired
    private DeviceModelService deviceModelService;
    
    @Autowired
    private AppUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<UserDeviceModel>> search(HttpServletRequest req,
                                                        @RequestParam(value="q", required=false) String query,
                                                        @RequestParam(value="offset", required=false) Integer offset,
                                                        @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /me/devices");

        logger.debug("MyDeviceController: raw query: " + query);

        Map<String,Object> filter = toFilterCriteria(query);  // returns keys in camelCase

        if (filter == null) {
            filter = new HashMap<String,Object>();
        }
        
        // limit searches to our own devices
        filter.put("userId", getUser().getId());

        logger.debug("filter: " + KJsonUtil.toJson(filter));

        String[] sortOrder = { "createdDate" };

        boolean distinct = false;

        if (offset == null) {
            offset = 0;
        }

        if (limit == null) {
            limit = 99999;
        }

        logger.debug("MyDeviceController: filter: " + KJsonUtil.toJson(filter));

        return okList(deviceModelService.toUserDeviceModelList(
                userDeviceService.fetchByCriteria(offset, limit, sortOrder, filter, distinct)));
    }





    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<UserDeviceModel> get(HttpServletRequest req,
            @PathVariable String uid) {
        logApiRequest(req, "GET /me/devices/" + uid);

        UserDevice device = deviceModelService.getUserDevice(getUser(), uid);

        return ok(deviceModelService.toUserDeviceModel(device));
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDeviceModel> create(HttpServletRequest req,
            @RequestBody UserDeviceModel model) {
        logApiRequest(req, "POST /devices");

        UserDevice device = new UserDevice();

        device = saveObject(device, model);

        return created(deviceModelService.toUserDeviceModel(device));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<UserDeviceModel> update(HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody UserDeviceModel model) {
        logApiRequest(req, "PUT /me/devices/" + uid);

        UserDevice device = deviceModelService.getUserDevice(getUser(), uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }
        
        device = saveObject(device, model);

        return ok(deviceModelService.toUserDeviceModel(device));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<UserDeviceModel> remove(HttpServletRequest req,
            @PathVariable String uid) {
        logApiRequest(req, "DELETE /me/devices/" + uid);

        UserDevice userDevice = deviceModelService.getUserDevice(getUser(), uid);

        userDeviceService.remove(userDevice, true);

        return ok(deviceModelService.toUserDeviceModel(userDevice));
    }



    public UserDevice saveObject(UserDevice userDevice, UserDeviceModel model) {
        return saveObject(getUser(), userDevice, model);
    }



    public UserDevice saveObject(User user, UserDevice userDevice, UserDeviceModel model) {
        logger.debug("saveObject: userDevice: " + userDevice);

        // remove UserDevice specific fields from map
        String displayName = model.getName();
        
        Device device = deviceModelService.toEntity(model);

        if (device.getType() == null) {
            device.setType(Device.Type.OTHER);
        }

        logger.debug("saveObject: device: " + device);

        if (userDevice.getId() == null) {
            device.setEnabled(true);
            userDevice = userDeviceService.create(user, device, displayName);
        } else {
            userDevice = userDeviceService.update(userDevice, device, displayName);
        }


        logger.debug("saveObject: userDevice: " + userDevice);

        return userDevice;
    }



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
                case "parentUid": 
                    Device parent = deviceModelService.getDevice(util.getStringValue(value));
                    result.put(prefix + "parentId", parent.getId());
                    break;

                default:
                    result.put(prefix + key, value);
            }
        }

        return result;
    } 
 
}

