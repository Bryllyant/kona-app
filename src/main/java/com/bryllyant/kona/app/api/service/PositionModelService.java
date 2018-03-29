package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.device.DeviceModel;
import com.bryllyant.kona.app.api.model.geo.place.PlaceModel;
import com.bryllyant.kona.app.api.model.geo.position.PositionModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.Place;
import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.DeviceService;
import com.bryllyant.kona.app.service.PositionService;
import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import com.bryllyant.kona.rest.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(PositionModelService.class);
    
    @Autowired
    private PositionService positionService;

    @Autowired
    private AppModelService appModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private PlaceModelService placeModelService;

    @Autowired
    private DeviceModelService deviceModelService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private AppUtil util;



    public Position getPosition(String positionUid) {
        Position position = positionService.fetchByUid(positionUid);

        if (position == null) {
            throw new NotFoundException("Position not found for uid: " + positionUid);
        }

        return position;
    }



    public Position getPosition(Long positionId) {
        Position position = positionService.fetchById(positionId);

        if (position == null) {
            throw new NotFoundException("Position not found for id: " + positionId);
        }

        return position;
    }
    


    public Position getPosition(PositionModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("Position not found for model: " + model);
        }

        return getPosition(uid);
    }



    public final PositionModel toModel(Position position, String... includeKeys) {

        PositionModel model = new PositionModel();
        
        model.fromBean(position);
        
        if (position.getUserId() != null) {
            User user = userModelService.getUser(position.getUserId());
            model.setUser(UserModel.create(user.getUid()));
        }
        
        if (position.getPlaceId() != null) {
            Place place = placeModelService.getPlace(position.getPlaceId());
            model.setPlace(PlaceModel.create(place.getUid()));
        }

        DeviceModel deviceModel = null;
        if (position.getDeviceId() != null) {
            Device device = deviceModelService.getDevice(position.getDeviceId());
            deviceModel = deviceModelService.toModel(device);
        }

        PositionModel.GeoDeviceModel geoDevice = PositionModel.GeoDeviceModel.from(deviceModel, position);
        model.setDevice(geoDevice);

        PositionModel.Coordinates coordinates =  PositionModel.Coordinates.from(position);
        model.setCoordinates(coordinates);

        PositionModel.GeoSource geoSource =  PositionModel.GeoSource.from(position);
        model.setSource(geoSource);

        PositionModel.Error error =  PositionModel.Error.from(position);
        model.setError(error);


        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }



    public final List<PositionModel> toModelList(List<Position> positionList, String... includeKeys) {
        List<PositionModel> modelList = new ArrayList<PositionModel>();

        for (Position item : positionList) {
            modelList.add(toModel(item, includeKeys));
        }

        return modelList;
    }
    


    public Position toEntity(PositionModel model) {
        Position position = new Position();

        return mergeEntity(position, model);
    }
    


    public Position mergeEntity(Position position, PositionModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, position);
        
        for (String key : model.initializedKeys()) {

            switch (key) {
                // if user is defined, then should have valid uid
                case "user":
                    UserModel userModel = model.getUser();
                    User user = userModelService.getUser(userModel);
                    position.setUserId(user.getId());
                    break;


                // if place is defined, then should have valid uid
                case "place":
                    PlaceModel placeModel = model.getPlace();
                    Place place = placeModelService.getPlace(placeModel);
                    position.setPlaceId(place.getId());
                    break;


                // if coordinates are defined, then fields should match position object
                case "coordinates":
                    PositionModel.Coordinates coordinates = model.getCoordinates();
                    util.copyModelToObject(coordinates, position);
                    break;

                // if source is defined, then fields should match position object
                // TODO: will toString() be called enum values or will it throw an error?
                case "source":
                    PositionModel.GeoSource source = model.getSource();
                    util.copyModelToObject(source, position);
                    logger.debug("mergeEntity: source copied: position object: " + position);
                    break;

                case "error":
                    PositionModel.Error error = model.getError();
                    position.setErrorCode(error.getCode());
                    position.setErrorMessage(error.getMessage());
                    position.setErrorDate(error.getErrorDate());
                    break;

                // if device is defined, device object may not yet exist for the specified AdvertiserId
                case "device":
                    PositionModel.GeoDeviceModel deviceModel =  model.getDevice();
                    Device device = getOrCreateDevice(position, deviceModel);

                    if (device != null) {
                        position.setDeviceId(device.getId());
                    }

                    position.setUserAgent(deviceModel.getUserAgent());
                    position.setBattery(deviceModel.getBattery());
                    position.setCharging(deviceModel.getCharging());
                    position.setBackground(deviceModel.getBackground());
                    break;
            }

        }
        
        return position;
    }

    private Device getOrCreateDevice(Position position, PositionModel.GeoDeviceModel deviceModel) {
        Device device = null;

        try {
            device = deviceModelService.getDevice(deviceModel);
        } catch (NotFoundException e) {
            // device does not exist.  make sure we have
            if (position.getDeviceId() != null) {
                device = deviceModelService.getDevice(position.getDeviceId());
            }

            // if we don't have an existing device, create a new one if we have an advertiserId.
            if (device == null && deviceModel.getAdvertiserId() != null) {
                device = new Device();
            }

            // if no device at this point give up
            if (device == null) {
                return null;
            }

        }

        // at this point we either have a new device or an existing one
        // merge in new deviceModel properties for this device

        // sanity check
        if (device.getAdvertiserId() != null && deviceModel.getAdvertiserId() != null
                && !deviceModel.getAdvertiserId().equalsIgnoreCase(device.getAdvertiserId())) {
            throw new ValidationException(
                    "Device advertiser ID mismatch:"
                            + "\ndevice advertiser ID: " + device.getAdvertiserId()
                            + "\nposition advertiser ID: " + deviceModel.getAdvertiserId()
            );
        }

        device = deviceModelService.mergeEntity(device, deviceModel);

        if (device.getType() == null) {
            // TODO: try to determine device type from user-agent if available
            Device.Type deviceType = Device.Type.PHONE;

            if (deviceModel.getUserAgent() != null && deviceModel.getUserAgent().toLowerCase().contains("ipad")) {
                deviceType = Device.Type.TABLET;
            }

            device.setType(deviceType);


            if (device.getOsName() == null) {
                if (deviceModel.getAdvertiserIdType() != null) {
                    if (deviceModel.getAdvertiserIdType() == DeviceModel.AdvertiserIdType.IDFA) {
                        device.setOsName("ios");
                    } else if (deviceModel.getAdvertiserIdType() == DeviceModel.AdvertiserIdType.AAID) {
                        device.setOsName("android");
                    }
                } else if (deviceModel.getUserAgent() != null) {
                    String userAgent = deviceModel.getUserAgent().trim().toLowerCase();
                    if (userAgent.contains("iphone") || userAgent.contains("ipad")) {
                        device.setOsName("ios");
                    } else if (userAgent.contains("android")) {
                        device.setOsName("android");
                    }
                }
            }

            device = deviceService.save(device);
        }

        return device;
    }


   
    
}
