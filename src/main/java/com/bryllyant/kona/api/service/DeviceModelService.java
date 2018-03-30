package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.device.DeviceModel;
import com.bryllyant.kona.api.model.device.UserDeviceModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.KDevice;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserDevice;
import com.bryllyant.kona.app.service.DeviceService;
import com.bryllyant.kona.app.service.UserDeviceService;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(DeviceModelService.class);
    
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserDeviceService userDeviceService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private AppUtil util;




    public Device getDevice(String uid) {
        Device device = deviceService.fetchByUid(uid);

        if (device == null) {
            device = deviceService.fetchByAdvertiserId(uid);
        }

        if (device == null) {
            device = deviceService.fetchByDeviceUuid(uid);
        }

        if (device == null) {
            throw new NotFoundException("Device not found for uid: " + uid);
        }

        return device;
    }



    public Device getDevice(DeviceModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            uid = model.getAdvertiserId();
        }

        if (uid == null) {
            uid = model.getDeviceUuid();
        }

        if (uid == null) {
            throw new NotFoundException("Device not found for model: " + model);
        }

        return getDevice(uid);
    }

    public Device getOrCreateDevice(DeviceModel model) {
        if (model == null) return null;

        // first see if this device already exists
        Device device = null;

        try {
            device = getDevice(model);
        } catch (NotFoundException e) {
            // ignore
        }


        if (device == null) {
            device = toEntity(model);
            device.setEnabled(true);
        } else {
            Device _device = toEntity(model);

            // preserve saved object's enabled status
            boolean _enabled = device.isEnabled();

            // this will overwrite all non null values including booleans
            device = (Device) util.copyBean(_device, device, true);

            device.setEnabled(_enabled);
        }

        if (device.getType() == null) {
            device.setType(Device.Type.OTHER);
        }

        device = deviceService.save(device);

        // update device model with newly created device in case it's referenced again after making this call
        setModel(model, device);

        logger.debug("getOrCreateDevice: device:\n" + device);

        return device;
    }



    public Device getDevice(Long deviceId) {
        Device device = deviceService.fetchById(deviceId);

        if (device == null) {
            throw new NotFoundException("Device not found for id: " + deviceId);
        }

        return device;
    }
    


    public UserDevice getUserDevice(User user, Long deviceId) {
        Device device = getDevice(deviceId);

        return getUserDevice(user, device);
    }
    


    public UserDevice getUserDevice(User user, String deviceUid) {
        Device device = getDevice(deviceUid);

        return getUserDevice(user, device);
    }
    


    public UserDevice getUserDevice(User user, Device device) {

        UserDevice userDevice = userDeviceService.fetchByUserIdAndDeviceId(user.getId(), device.getId());
        
        if (userDevice == null) {
            throw new NotFoundException("User device not found for device uid: " + device.getUid());
        }
        
        return userDevice;
    }




    public Long getDeviceId(Object uid, boolean checkAuthorization) {
        Long id = null;

        if (uid != null) {
            Device obj = getDevice(util.getStringValue(uid));
            id = obj.getId();
        }

        return id;
    }


    
    public UserDeviceModel toUserDeviceModel(UserDevice userDevice, String... includeKeys) {
        
        UserDeviceModel model = new UserDeviceModel();

        // create base UserDeviceModel from DeviceModel
        DeviceModel deviceModel = toModel(userDevice);
        
        util.copyBean(deviceModel, model, true);
        
        User user = userModelService.getUser(userDevice.getUserId());
        model.setUser(UserModel.create(user.getUid()));

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        logger.debug("toModel: userDevice: model: " + model);

        return model;
    }
    


    public List<UserDeviceModel> toUserDeviceModelList(List<UserDevice> deviceList, String... includeKeys) {
        List<UserDeviceModel> modelList = new ArrayList<>();

        for (UserDevice device : deviceList) {
            modelList.add(toUserDeviceModel(device, includeKeys));
        }

        return modelList;
    }



    private <T extends KDevice> void setModel(DeviceModel model, T device) {
        model.fromBean(device);

        model.setAdvertiserIdType(DeviceModel.AdvertiserIdType.from((Device)device));

        if (device.getParentId() != null) {
            Device parent = getDevice(device.getParentId());
            model.setParent(DeviceModel.create(parent.getUid()));
        }
    }

    public <T extends KDevice> DeviceModel toModel(T device, String... includeKeys) {
        DeviceModel model = new DeviceModel();

        setModel(model, device);

        logger.debug("toModel: device: includeKeys: " + includeKeys);

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        logger.debug("toModel: device: model: " + model);

        return model;
    }



    public List<DeviceModel> toDeviceModelList(List<Device> deviceList, String... includeKeys) {
        List<DeviceModel> modelList = new ArrayList<DeviceModel>();

        for (Device device : deviceList) {
            modelList.add(toModel(device, includeKeys));
        }

        return modelList;
    }
    

    


    public Device toEntity(DeviceModel model) {
        Device device = new Device();

        return mergeEntity(device, model);
    }



    public Device mergeEntity(Device device, DeviceModel model) {
        logger.debug("toEntity called for model: " + model);

        // validate

        util.copyModelToObject(model, device);

        logger.debug("mergeEntity: copyModeltoObject: device: " + device);
        
        for (String key : model.initializedKeys()) {
            
            logger.debug("mergeEntity: processing initialized key: " + key);

            switch (key) {
                case "parent":
                    DeviceModel deviceModel = model.getParent();
                    Device parent = getDevice(deviceModel);
                    device.setParentId(parent.getId());
                    break;
            }

        }
        
        logger.debug("toEntity completed: entity: " + device);

        return device;
    }
}
