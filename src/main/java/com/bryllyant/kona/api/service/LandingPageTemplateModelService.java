package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.marketing.landingpage.LandingPageTemplateModel;
import com.bryllyant.kona.api.model.marketing.landingpage.LandingPageTemplateModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.service.LandingPageTemplateService;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LandingPageTemplateModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(LandingPageTemplateModelService.class);

    @Autowired
    private LandingPageTemplateService templateService;

    @Autowired
    private FileService fileService;

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



    public LandingPageTemplate getTemplate(String uid) {
        LandingPageTemplate template = templateService.fetchByUid(uid);

        if (template == null) {
            throw new NotFoundException("LandingPageTemplate not found for uid: " + uid);
        }

        return template;
    }


    public LandingPageTemplate getTemplate(Long templateId) {
        LandingPageTemplate template = templateService.fetchById(templateId);

        if (template == null) {
            throw new NotFoundException("LandingPageTemplate not found for id: " + templateId);
        }

        return template;
    }
    

    public LandingPageTemplate getTemplate(LandingPageTemplateModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("LandingPageTemplate not found for model: " + model);
        }

        return getTemplate(uid);
    }
   


    public LandingPageTemplateModel toModel(LandingPageTemplate template, String... includeKeys) {
        if (template == null) return null;

        LandingPageTemplateModel model = new LandingPageTemplateModel();
        
        model.fromBean(template);

        // set model references
        if (template.getFileId() != null) {
            File file = fileService.fetchById(template.getFileId());
            model.setUrl(util.toAbsoluteUrl(file.getUrlPath()));
        }

        if (template.getOwnerId() != null) {
            User owner = userModelService.getUser(template.getOwnerId());
            model.setOwner(UserModel.create(owner.getUid()));
        }

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }



    public List<LandingPageTemplateModel> toModelList(List<LandingPageTemplate> templates, String... includeKeys) {
        List<LandingPageTemplateModel> modelList = new ArrayList<>();

        for (LandingPageTemplate template : templates) {
            modelList.add(toModel(template, includeKeys));
        }

        return modelList;
    }



    public LandingPageTemplate toEntity(LandingPageTemplateModel model) {
        LandingPageTemplate template = new LandingPageTemplate();

        return mergeEntity(template, model);
    }

    


    public LandingPageTemplate mergeEntity(LandingPageTemplate template, LandingPageTemplateModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, template);

        for (String key : model.initializedKeys()) {

            switch (key) {

                case "owner":
                    User owner = userModelService.getUser(model.getOwner());
                    template.setOwnerId(owner == null ? null : owner.getId());
                    break;
            }

        }

        return template;
    }
    

   
}
