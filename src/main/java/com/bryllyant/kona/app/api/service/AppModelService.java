package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.app.AppModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(AppModelService.class);
    
    @Autowired
    private AppService appService;
    
    @Autowired
    private UserModelService userModelService;

    @Autowired
    private AppUtil util;
    


    public App getApp(String uid) {
        App app = appService.fetchByUid(uid);

        if (app == null) {
            throw new NotFoundException("App not found for uid: " + uid);
        }

        return app;
    }
    


    public App getApp(Long appId) {
        App app = appService.fetchById(appId);

        if (app == null) {
            throw new NotFoundException("App not found for id: " + appId);
        }

        return app;
    }
    


    public App getApp(AppModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("App not found for model: " + model);
        }

        return getApp(uid);
    }


    
    public final AppModel toModel(App app, String... includeKeys) {
        if (app == null) return null;

        User user = userModelService.getUser(app.getUserId());

        AppModel model = new AppModel(); 

        model.fromBean(app);

        model.setLogoUrl(util.toAbsoluteUrl(app.getLogoUrlPath()));

        UserModel userModel = UserModel.create(user.getUid());
        model.setUser(userModel);

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }



    public final List<AppModel> toModelList(List<App> apps, String... includeKeys) {
        List<AppModel> modelList = new ArrayList<AppModel>();

        for (App app : apps) {
            modelList.add(toModel(app, includeKeys));
        }

        return modelList;
    }



    public App toEntity(AppModel model) {
        App app = new App();

        return mergeEntity(app, model);
    }



    public App mergeEntity(App app, AppModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, app);

        for (String key : model.initializedKeys()) {

            switch (key) {
                case "user":
                    UserModel userModel = model.getUser();
                    User user = userModelService.getUser(userModel);
                    app.setUserId(user.getId());
                    break;
            }
        }

        return app;
    }
    

   
}
