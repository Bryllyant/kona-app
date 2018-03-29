package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.social.friendship.FriendshipCircleModel;
import com.bryllyant.kona.app.api.model.social.friendship.FriendshipModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Friendship;
import com.bryllyant.kona.app.entity.FriendshipCircle;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.FriendshipCircleService;
import com.bryllyant.kona.app.service.FriendshipService;
import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendshipModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(FriendshipModelService.class);

    @Autowired
    private FriendshipService friendshipService;
    
    @Autowired
    private FriendshipCircleService friendshipCircleService;

    @Autowired
    private UserModelService userModelService;
    
    @Autowired
    private AppUtil util;
    


    public Friendship getFriendship(String friendshipUid) {
        Friendship friendship = friendshipService.fetchByUid(friendshipUid);

        if (friendship == null) {
            throw new NotFoundException("Friendship not found for uid: " + friendshipUid);
        }

        return friendship;
    }



    public Friendship getFriendship(Long friendshipId) {
        Friendship friendship = friendshipService.fetchById(friendshipId);

        if (friendship == null) {
            throw new NotFoundException("Friendship not found for id: " + friendshipId);
        }

        return friendship;
    }
    
    


    public Friendship getFriendship(FriendshipModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("Friendship not found for model: " + model);
        }

        return getFriendship(uid);
    }
    
    


    public FriendshipCircle getFriendshipCircle(String uid) {
        FriendshipCircle circle = friendshipCircleService.fetchByUid(uid);
        
        if (circle == null) {
            throw new NotFoundException("Friendship circle not found for uid: " + uid);
        }
        
        return circle;
    }
    


    public FriendshipCircle getFriendshipCircle(FriendshipCircleModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("Friendship circle not found for model: " + model);
        }
        
        return getFriendshipCircle(uid);
    }


    
    public final FriendshipModel toModel(Friendship friendship, String... includeKeys) {
        if (friendship == null) return null;

        User friend = userModelService.getUser(friendship.getFriendId());

        FriendshipModel model = new FriendshipModel();
        
        model.setUid(friendship.getUid());
        model.setFriend(userModelService.toModel(friend));
        model.setStatus(friendship.getStatus());
        model.setCreatedDate(friendship.getCreatedDate());
        
        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        logger.debug("toModel: device: model: " + model);

        return model;
    }
    


    public final List<FriendshipModel> toModelList(List<Friendship> friendships, String... includeKeys) {
        List<FriendshipModel> modelList = new ArrayList<>();

        for (Friendship friendship : friendships) {
            modelList.add(toModel(friendship, includeKeys));
        }

        return modelList;
    }
    


    public Friendship toEntity(FriendshipModel model) {
        Friendship friendship = new Friendship();

        return mergeEntity(friendship, model);
    }



    public Friendship mergeEntity(Friendship friendship, FriendshipModel model) {
        logger.debug("toEntity called for model: " + model);
        
        for (String key : model.initializedKeys()) {

            switch (key) {
                case "uid":
                    friendship.setUid(model.getUid());
                    break;

                case "status":
                    friendship.setStatus(model.getStatus());
                    break;

                case "friend":
                    UserModel userModel = model.getFriend();
                    User friend = userModelService.getUser(userModel);
                    friendship.setFriendId(friend.getId());
                    break;
                    
            }

        }

        return friendship;
    }
    

   
    
}
