package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.media.MediaModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.api.util.ApiUtil;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.MediaService;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MediaModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(MediaModelService.class);
    
    @Autowired
    private MediaService mediaService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private ApiUtil util;

   
    // ----------------------------------------------------------------------

    public Media getMedia(String mediaUid) {
        Media media = mediaService.fetchByUid(mediaUid);

        if (media == null) {
            throw new NotFoundException("User media not found for uid: " + mediaUid);
        }

        return media;
    }

    // ----------------------------------------------------------------------

    public Media getMedia(Long mediaId) {
        Media media = mediaService.fetchById(mediaId);

        if (media == null) {
            throw new NotFoundException("User media not found for id: " + mediaId);
        }

        return media;
    }
    
    
    // ----------------------------------------------------------------------

    public Media getMedia(MediaModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("Media not found for model: " + model);
        }

        return getMedia(uid);
    }

    // ----------------------------------------------------------------------

    public final MediaModel toModel(Media media, String... includeKeys) {
        
        String url = media.getUrl();

        if (url == null) {
            url = util.toAbsoluteUrl(media.getUrlPath());
        }

        String thumbnailUrl = media.getThumbnailUrl();

        if (thumbnailUrl == null) {
            thumbnailUrl = util.toAbsoluteUrl(media.getThumbnailUrlPath());
        }

        User user = userModelService.getUser(media.getUserId()); 

        MediaModel model = new MediaModel();
        
        model.fromBean(media);
        

        model.setUser(userModelService.toModel(user, "uid", "account"));
        model.setUrl(url);
        model.setThumbnailUrl(thumbnailUrl);


        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }

    // ----------------------------------------------------------------------

    public final List<MediaModel> toMediaModelList(List<Media> mediaList, String... includeKeys) {
        List<MediaModel> modelList = new ArrayList<MediaModel>();

        for (Media item : mediaList) {
            modelList.add(toModel(item, includeKeys));
        }

        return modelList;
    }
    
    
    // ----------------------------------------------------------------------

    public Media toEntity(MediaModel model) {
        Media media = new Media();

        return mergeEntity(media, model);
    }

    // ----------------------------------------------------------------------

    public Media mergeEntity(Media media, MediaModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, media);
        
        for (String key : model.initializedKeys()) {

            switch (key) {
                
                case "user":
                    UserModel userModel = model.getUser();
                    User user = userModelService.getUser(userModel);
                    media.setUserId(user.getId());
                    media.setAccountId(user.getAccountId());
                    break;
            }

        }

        return media;
    }
    
    // ----------------------------------------------------------------------
   
    
}
