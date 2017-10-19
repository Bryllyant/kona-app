package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.app.AppModel;
import com.bryllyant.kona.app.api.model.device.DeviceModel;
import com.bryllyant.kona.app.api.model.place.PlaceModel;
import com.bryllyant.kona.app.api.model.position.PositionModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.api.util.ApiUtil;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.Place;
import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.PositionService;
import com.bryllyant.kona.rest.exception.NotFoundException;
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
    private ApiUtil util;

    // ----------------------------------------------------------------------

    public Position getPosition(String positionUid) {
        Position position = positionService.fetchByUid(positionUid);

        if (position == null) {
            throw new NotFoundException("Position not found for uid: " + positionUid);
        }

        return position;
    }

    // ----------------------------------------------------------------------

    public Position getPosition(Long positionId) {
        Position position = positionService.fetchById(positionId);

        if (position == null) {
            throw new NotFoundException("Position not found for id: " + positionId);
        }

        return position;
    }
    
    // ----------------------------------------------------------------------

    public Position getPosition(PositionModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("Position not found for model: " + model);
        }

        return getPosition(uid);
    }

    // ----------------------------------------------------------------------

    public final PositionModel toModel(Position position, String... includeKeys) {

        PositionModel model = new PositionModel();
        
        model.fromBean(position);
        
        // TODO: manage this better
        if (model.getIndoorDetail() != null) {
            model.setIndoorDetail(null);
        }

        if (position.getAppId() != null) {
            App app = appModelService.getApp(position.getAppId());
            model.setApp(AppModel.create(app.getUid()));
        }
        
        if (position.getUserId() != null) {
            User user = userModelService.getUser(position.getUserId());
            model.setUser(UserModel.create(user.getUid()));
        }
        
        if (position.getPlaceId() != null) {
            Place place = placeModelService.getPlace(position.getPlaceId());
            model.setPlace(PlaceModel.create(place.getUid()));
        }
        
        if (position.getDeviceId() != null) {
            Device device = deviceModelService.getDevice(position.getDeviceId());
            model.setDevice(DeviceModel.create(device.getUid()));
        }

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }

    // ----------------------------------------------------------------------

    public final List<PositionModel> toPositionModelList(List<Position> positionList, String... includeKeys) {
        List<PositionModel> modelList = new ArrayList<PositionModel>();

        for (Position item : positionList) {
            modelList.add(toModel(item, includeKeys));
        }

        return modelList;
    }
    
    // ----------------------------------------------------------------------

    public Position toEntity(PositionModel model) {
        Position position = new Position();

        return mergeEntity(position, model);
    }
    
    // ----------------------------------------------------------------------

    public Position mergeEntity(Position position, PositionModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, position);
        
        for (String key : model.initializedKeys()) {

            switch (key) {
                
                case "app":
                    AppModel appModel = model.getApp();
                    App app = appModelService.getApp(appModel);
                    position.setAppId(app.getId());
                    break; 
                    

                case "user":
                    UserModel userModel = model.getUser();
                    User user = userModelService.getUser(userModel);
                    position.setUserId(user.getId());
                    break; 


                case "place":
                    PlaceModel placeModel = model.getPlace();
                    Place place = placeModelService.getPlace(placeModel);
                    position.setPlaceId(place.getId());
                    break; 
                    

                case "device":
                    DeviceModel deviceModel =  model.getDevice();
                    Device device = deviceModelService.getDevice(deviceModel);
                    position.setDeviceId(device.getId());
                    break; 
            }

        }
        
        return position;
    }
    
    // ----------------------------------------------------------------------
   
    
}
