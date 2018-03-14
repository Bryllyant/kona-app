package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.sales.landingPage.LandingPageTemplateModel;
import com.bryllyant.kona.app.api.model.sales.landingPage.LandingPageTemplateParamModel;
import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.app.entity.LandingPageTemplateParam;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.service.LandingPageTemplateParamService;
import com.bryllyant.kona.app.util.ApiUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LandingPageTemplateParamModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(LandingPageTemplateParamModelService.class);

    @Autowired
    private LandingPageTemplateModelService landingPageTemplateModelService;

    @Autowired
    private LandingPageTemplateParamService paramService;

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
    private ApiUtil util;



    public LandingPageTemplateParam getParam(String uid) {
        LandingPageTemplateParam param = paramService.fetchByUid(uid);

        if (param == null) {
            throw new NotFoundException("LandingPageTemplateParam not found for uid: " + uid);
        }

        return param;
    }


    public LandingPageTemplateParam getParam(Long paramId) {
        LandingPageTemplateParam param = paramService.fetchById(paramId);

        if (param == null) {
            throw new NotFoundException("LandingPageTemplateParam not found for id: " + paramId);
        }

        return param;
    }
    

    public LandingPageTemplateParam getParam(LandingPageTemplateParamModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("LandingPageTemplateParam not found for model: " + model);
        }

        return getParam(uid);
    }
   


    public LandingPageTemplateParamModel toModel(LandingPageTemplateParam param, String... includeKeys) {
        if (param == null) return null;

        LandingPageTemplateParamModel model = new LandingPageTemplateParamModel();
        
        model.fromBean(param);

        if (param.getTemplateId() != null) {
            LandingPageTemplate template = landingPageTemplateModelService.getTemplate(param.getTemplateId());
            model.setTemplate(LandingPageTemplateModel.from(template));
        }

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }



    public List<LandingPageTemplateParamModel> toModelList(List<LandingPageTemplateParam> params, String... includeKeys) {
        List<LandingPageTemplateParamModel> modelList = new ArrayList<>();

        for (LandingPageTemplateParam param : params) {
            modelList.add(toModel(param, includeKeys));
        }

        return modelList;
    }



    public LandingPageTemplateParam toEntity(LandingPageTemplateParamModel model) {
        LandingPageTemplateParam param = new LandingPageTemplateParam();

        return mergeEntity(param, model);
    }

    


    public LandingPageTemplateParam mergeEntity(LandingPageTemplateParam param, LandingPageTemplateParamModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, param);

        for (String key : model.initializedKeys()) {

            switch (key) {

                case "template":
                    LandingPageTemplate template = landingPageTemplateModelService.getTemplate(model.getTemplate());
                    param.setTemplateId(template == null ? null : template.getId());
                    break;
            }
        }

        return param;
    }

}
