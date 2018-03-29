package com.bryllyant.kona.app.api.controller.admin;

import com.bryllyant.kona.app.api.controller.BaseController;
import com.bryllyant.kona.app.api.model.device.DeviceModel;
import com.bryllyant.kona.app.api.service.DeviceModelService;
import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.service.DeviceService;
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
@RequestMapping("/api/admin/devices")
public class DeviceController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(DeviceController.class);



    @Autowired
    private DeviceService deviceService;
    
    @Autowired
    private DeviceModelService deviceModelService;
    
    @Autowired
    private AppUtil util;



    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<DeviceModel>> search(HttpServletRequest req,
            @RequestParam(value="q", required=false) String query,
            @RequestParam(value="offset", required=false) Integer offset,
            @RequestParam(value="limit", required=false) Integer limit) {
        logApiRequest(req, "GET /admin/devices");

        logger.debug("DeviceController: raw query: " + query);

        Map<String,Object> filter = toFilterCriteria(query);  // returns keys in camelCase

        if (filter == null) {
            filter = new HashMap<String,Object>();
        }
        
        logger.debug("filter: " + KJsonUtil.toJson(filter));

        String[] sortOrder = { "createdDate" };

        boolean distinct = false;

        if (offset == null) {
            offset = 0;
        }

        if (limit == null) {
            limit = 99999;
        }

        logger.debug("DeviceController: filter: " + KJsonUtil.toJson(filter));

        return okList(deviceModelService.toDeviceModelList(
                deviceService.fetchByCriteria(offset, limit, sortOrder, filter, distinct)));
    }





    @RequestMapping(value="/{uid}", method=RequestMethod.GET)
    public ResponseEntity<DeviceModel> get(HttpServletRequest req,
            @PathVariable String uid) {
        logApiRequest(req, "GET /admin/devices/" + uid);

        Device device = deviceModelService.getDevice(uid);

        return ok(deviceModelService.toModel(device));
    }



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<DeviceModel> create(HttpServletRequest req,
            @RequestBody DeviceModel model) {
        logApiRequest(req, "POST /admin/devices");

        Device device = new Device();

        device = saveObject(req, device, model);

        return created(deviceModelService.toModel(device));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.PUT)
    public ResponseEntity<DeviceModel> update(HttpServletRequest req,
            @PathVariable String uid,
            @RequestBody DeviceModel model) {
        logApiRequest(req, "PUT /admin/devices/" + uid);

        Device device = deviceModelService.getDevice(uid);

        if (model.getUid() != null && !model.getUid().equals(uid)) {
            throw new ValidationException("Object UID does not match requested UID");
        }
        
        device = saveObject(req, device, model);

        return ok(deviceModelService.toModel(device));
    }



    @RequestMapping(value = "/{uid}", method=RequestMethod.DELETE)
    public ResponseEntity<DeviceModel> remove(
            HttpServletRequest req,
            @PathVariable String uid) {
        logApiRequest(req, "DELETE /admin/devices/" + uid);

        Device device = deviceModelService.getDevice(uid);

        deviceService.remove(device);

        //return ok(deviceModelService.toModel(device));
        return ok(DeviceModel.create(device.getUid()));
    }



    public Device saveObject(HttpServletRequest req, Device device, DeviceModel model) {
        logger.debug("mapToObject called for device: " + device);
        
        device = deviceModelService.mergeEntity(device, model);

        if (device.getType() == null) {
            device.setType(Device.Type.OTHER);
        }
        
        if (device.getId() == null) {
            device.setEnabled(true);
        }

        return deviceService.save(device);
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
                    Long id = -1L;
                    Device parent = deviceModelService.getDevice(util.getStringValue(value));
                    if (parent != null) id = parent.getId();
                    result.put(prefix + "parentId", id);
                    break;

                default:
                    result.put(prefix + key, value);
            }
        }

        return result;
    } 

}

