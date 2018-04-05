package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.geo.place.PlaceModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.util.AppUtil;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.Place;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.PlaceService;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(PlaceModelService.class);
    
    @Autowired
    private PlaceService placeService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private MediaModelService mediaModelService;

    @Autowired
    private AppUtil util;

   


    public Place getPlace(String placeUid) {
        Place place = placeService.fetchByUid(placeUid);

        if (place == null) {
            throw new NotFoundException("GooglePlace not found for uid: " + placeUid);
        }

        return place;
    }



    public Place getPlace(Long placeId) {
        Place place = placeService.fetchById(placeId);

        if (place == null) {
            throw new NotFoundException("GooglePlace not found for id: " + placeId);
        }

        return place;
    }
    


    public Place getPlace(PlaceModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("GooglePlace not found for model: " + model);
        }

        return getPlace(uid);
    }



    public final PlaceModel toModel(Place place, String... includeKeys) {
        
        Media media = null;
        
        if (place.getPhotoId() != null) {
            media = mediaModelService.getMedia(place.getPhotoId());
        }

        String photoUrl = place.getUrl();
        String thumbnailUrl = place.getThumbnailUrl();

        if (media != null) {
            photoUrl = util.toAbsoluteUrl(media.getUrlPath());
        }

        if (media != null) {
            thumbnailUrl = util.toAbsoluteUrl(media.getThumbnailUrlPath());
        }

        PlaceModel model = new PlaceModel();
        
        model.fromBean(place);

        model.setPhotoUrl(photoUrl);
        model.setThumbnailUrl(thumbnailUrl);

        if (place.getOwnerId() != null) {
            User user = userModelService.getUser(place.getOwnerId());
            model.setOwner(UserModel.create(user.getUid()));
        }
        

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }



    public final List<PlaceModel> toModelList(List<Place> placeList, String... includeKeys) {
        List<PlaceModel> modelList = new ArrayList<PlaceModel>();

        for (Place item : placeList) {
            modelList.add(toModel(item, includeKeys));
        }

        return modelList;
    }
    
    


    public Place toEntity(PlaceModel model) {
        Place place = new Place();

        return mergeEntity(place, model);
    }



    public Place mergeEntity(Place place, PlaceModel model) {
        logger.debug("toEntity called for model: " + model);
        
        String photoUrl = model.getPhotoUrl();
        String thumbnailUrl = model.getThumbnailUrl();
        
        util.copyModelToObject(model, place);
        
        for (String key : model.initializedKeys()) {

            switch (key) {
                
                case "photoUrl":
                    if (place.getPhotoId() == null) {
                        place.setPhotoUrl(photoUrl);
                    }
                    break;

                case "thumbnailUrl":
                    if (place.getPhotoId() == null) {
                        place.setThumbnailUrl(thumbnailUrl);
                    }
                    break;
                    
                case "owner":
                    UserModel userModel = model.getOwner();
                    User user = userModelService.getUser(userModel);
                    place.setOwnerId(user.getId());
                    break; 
            }

        }

        return place;
    }
    

   
    
}
