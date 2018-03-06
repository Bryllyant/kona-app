package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.app.AppModel;
import com.bryllyant.kona.app.api.model.sales.campaign.LandingPageModel;
import com.bryllyant.kona.app.api.model.sales.campaign.LandingPageTemplateModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.util.ApiUtil;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.LandingPageService;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LandingPageModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(LandingPageModelService.class);
    
    @Autowired
    private LandingPageService landingPageService;

    @Autowired
    private LandingPageTemplateModelService landingPageTemplateModelService;
    
    @Autowired
    private UserModelService userModelService;

    @Autowired
    private AppModelService appModelService;

    @Autowired
    private PromoModelService promoModelService;

    @Autowired
    private PartnerModelService partnerModelService;


    @Autowired
    private ApiUtil util;



    public LandingPage getLandingPage(String uid) {
        LandingPage landingPage = landingPageService.fetchByUrlPath(uid);

        if (landingPage == null) {
            landingPage = landingPageService.fetchByUid(uid);

            if (landingPage == null) {
                throw new NotFoundException("LandingPage not found for uid: " + uid);
            }
        }

        return landingPage;
    }



    public LandingPage getLandingPage(Long landingPageId) {
        LandingPage landingPage = landingPageService.fetchById(landingPageId);

        if (landingPage == null) {
            throw new NotFoundException("LandingPage not found for id: " + landingPageId);
        }

        return landingPage;
    }
    
    


    public LandingPage getLandingPage(LandingPageModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("LandingPage not found for model: " + model);
        }

        return getLandingPage(uid);
    }
   


    public LandingPageModel toModel(LandingPage landingPage, String... includeKeys) {
        if (landingPage == null) return null;

        LandingPageModel model = new LandingPageModel();
        
        model.fromBean(landingPage);

        // set model references
        if (landingPage.getAppId() != null) {
            App app = appModelService.getApp(landingPage.getAppId());
            model.setApp(AppModel.create(app.getUid()));
        }

        if (landingPage.getAddedById() != null) {
            User addedBy = userModelService.getUser(landingPage.getAddedById());
            model.setAddedBy(UserModel.create(addedBy.getUid()));
        }

        if (landingPage.getTemplateId() != null) {
            LandingPageTemplate template = landingPageTemplateModelService.getTemplate(landingPage.getTemplateId());
            model.setTemplate(LandingPageTemplateModel.create(template.getUid()));
        }

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }



    public List<LandingPageModel> toModelList(List<LandingPage> landingPages, String... includeKeys) {
        List<LandingPageModel> modelList = new ArrayList<>();

        for (LandingPage landingPage : landingPages) {
            modelList.add(toModel(landingPage, includeKeys));
        }

        return modelList;
    }



    public LandingPage toEntity(LandingPageModel model) {
        LandingPage landingPage = new LandingPage();

        return mergeEntity(landingPage, model);
    }

    


    public LandingPage mergeEntity(LandingPage landingPage, LandingPageModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, landingPage);

        for (String key : model.initializedKeys()) {

            switch (key) {

                case "app":
                    App app = appModelService.getApp(model.getApp());
                    landingPage.setAppId(app.getId());
                    break;

                case "addedBy":
                    User addedBy = userModelService.getUser(model.getAddedBy());
                    landingPage.setAddedById(addedBy.getId());
                    break;

                case "template":
                    LandingPageTemplate template = landingPageTemplateModelService.getTemplate(model.getTemplate());
                    landingPage.setTemplateId(template.getId());
                    break;
            }

        }

        return landingPage;
    }
    

   
}
