package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.notification.NotificationModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.app.entity.Notification;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.NotificationService;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationModelService.class);
    
    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private UserModelService userModelService;
    
    @Autowired
    private AppUtil util;



    public Notification getNotification(String uid) {
        Notification notification = notificationService.fetchByUid(uid);

        if (notification == null) {
            throw new NotFoundException("Notification not found for uid: " + uid);
        }

        return notification;
    }



    public Notification getNotification(Long notificationId) {
        Notification notification = notificationService.fetchById(notificationId);

        if (notification == null) {
            throw new NotFoundException("Notification not found for id: " + notificationId);
        }

        return notification;
    }
    
    


    public Notification getNotification(NotificationModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("Notification not found for model: " + model);
        }

        return getNotification(uid);
    }
   


    public final NotificationModel toModel(Notification notification, String... includeKeys) {
        if (notification == null) return null;

        User user = userModelService.getUser(notification.getUserId()); 

        NotificationModel model = new NotificationModel(); 
        
        model.fromBean(notification);
        
        // set 
        UserModel userModel = UserModel.create(user.getUid());
        model.setUser(userModel);
        
        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }



    public final List<NotificationModel> toModelList(List<Notification> notifications, String... includeKeys) {
        List<NotificationModel> modelList = new ArrayList<NotificationModel>();

        for (Notification notification : notifications) {
            modelList.add(toModel(notification, includeKeys));
        }

        return modelList;
    }



    public Notification toEntity(NotificationModel model) {
        Notification notification = new Notification();

        return mergeEntity(notification, model);
    }

    


    public Notification mergeEntity(Notification notification, NotificationModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, notification); 

        for (String key : model.initializedKeys()) {

            switch (key) {
                
                case "user":
                    UserModel userModel = model.getUser();
                    User user = userModelService.getUser(userModel);
                    notification.setUserId(user.getId());
                    break;
            }

        }

        return notification;
    }
    

   
}
