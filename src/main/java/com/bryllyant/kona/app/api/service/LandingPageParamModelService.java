package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.sales.landingPage.LandingPageModel;
import com.bryllyant.kona.app.api.model.sales.landingPage.LandingPageParamModel;
import com.bryllyant.kona.app.api.model.sales.landingPage.LandingPageTemplateModel;
import com.bryllyant.kona.app.util.ApiUtil;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.entity.LandingPageParam;
import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.service.LandingPageParamService;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LandingPageParamModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(LandingPageParamModelService.class);
    
    @Autowired
    private LandingPageParamService landingPageParamService;

    @Autowired
    private LandingPageModelService landingPageModelService;

    @Autowired
    private LandingPageTemplateModelService landingPageTemplateModelService;
    
    @Autowired
    private UserModelService userModelService;

    @Autowired
    private AppModelService appModelService;

    @Autowired
    private PromoModelService promoModelService;

    @Autowired
    private FileService fileService;


    @Autowired
    private ApiUtil util;



    public LandingPageParam getLandingPageParam(String uid) {
        LandingPageParam param = landingPageParamService.fetchByUid(uid);

        if (param == null) {
            throw new NotFoundException("LandingPageParam not found for uid: " + uid);
        }

        return param;
    }



    public LandingPageParam getLandingPageParam(Long paramId) {
        LandingPageParam param = landingPageParamService.fetchById(paramId);

        if (param == null) {
            throw new NotFoundException("LandingPageParam not found for id: " + paramId);
        }

        return param;
    }
    
    


    public LandingPageParam getLandingPageParam(LandingPageParamModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("LandingPageParam not found for model: " + model);
        }

        return getLandingPageParam(uid);
    }
   


    public LandingPageParamModel toModel(LandingPageParam param, String... includeKeys) {
        if (param == null) return null;

        LandingPageParamModel model = new LandingPageParamModel();
        
        model.fromBean(param);
        /*
    private Long landingPageId;
    private Long templateId;
    private Long fileId;
    private String value;
         */

        // set model references
        if (param.getLandingPageId() != null) {
            LandingPage page = landingPageModelService.getLandingPage(param.getLandingPageId());
            model.setLandingPage(LandingPageModel.create(page.getUid()));
        }

        if (param.getTemplateId() != null) {
            LandingPageTemplate template = landingPageTemplateModelService.getTemplate(param.getTemplateId());
            model.setTemplate(LandingPageTemplateModel.create(template.getUid()));
        }

        if (param.getFileId() != null) {
            File file = fileService.fetchById(param.getFileId());
            model.setValue(util.toAbsoluteUrl(file.getUrlPath()));
        }

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }


    public List<LandingPageParamModel> toModelList(List<LandingPageParam> params, String... includeKeys) {
        List<LandingPageParamModel> modelList = new ArrayList<>();

        for (LandingPageParam param : params) {
            modelList.add(toModel(param, includeKeys));
        }

        return modelList;
    }



    public LandingPageParam toEntity(LandingPageParamModel model) {
        LandingPageParam param = new LandingPageParam();

        return mergeEntity(param, model);
    }

    


    public LandingPageParam mergeEntity(LandingPageParam param, LandingPageParamModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, param);

        for (String key : model.initializedKeys()) {

            switch (key) {

                case "landingPage":
                    LandingPage page = landingPageModelService.getLandingPage(model.getLandingPage());
                    param.setLandingPageId(page.getId());
                    break;

                case "template":
                    LandingPageTemplate template = landingPageTemplateModelService.getTemplate(model.getTemplate());
                    param.setTemplateId(template.getId());
                    break;
            }

        }

        return param;
    }
    

   
}
