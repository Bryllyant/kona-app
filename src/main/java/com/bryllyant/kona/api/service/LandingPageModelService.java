package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.marketing.landingpage.LandingPageModel;
import com.bryllyant.kona.api.model.marketing.landingpage.LandingPageTemplateModel;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.app.service.LandingPageService;
import com.bryllyant.kona.util.AppUtil;
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
    KConfig config;
    
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
    private AppUtil util;


    public LandingPage getLandingPage(String uid) {
        LandingPage landingPage = landingPageService.fetchByUid(uid);

        if (landingPage == null) {
            throw new NotFoundException("Campaign not found for uid: " + uid);
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

        // generate the previewUrl
        String previewUrl = config.getString("landingPage.previewBaseUrl");

        if (previewUrl != null) {
            if (!previewUrl.endsWith("/")) {
                previewUrl += "/";
            }

            previewUrl += landingPage.getUid() + "/";

            model.setPreviewUrl(previewUrl);
        }

        // set model references
        if (landingPage.getTemplateId() != null) {
            LandingPageTemplate template = landingPageTemplateModelService.getTemplate(landingPage.getTemplateId());
            model.setTemplate(LandingPageTemplateModel.from(template));
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
                case "template":
                    LandingPageTemplate template = landingPageTemplateModelService.getTemplate(model.getTemplate());
                    landingPage.setTemplateId(template == null ? null : template.getId());
                    break;
            }

        }

        return landingPage;
    }
}
