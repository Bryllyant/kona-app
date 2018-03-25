package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.sales.campaign.CampaignAnalyticsModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignChannelModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignGroupModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignTargetModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.CampaignAnalyticsService;
import com.bryllyant.kona.app.util.ApiUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignAnalyticsModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(CampaignAnalyticsModelService.class);
    
    @Autowired
    private CampaignAnalyticsService analyticsService;
    
    @Autowired
    private UserModelService userModelService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private CampaignGroupModelService campaignGroupModelService;

    @Autowired
    private CampaignChannelModelService campaignChannelModelService;

    @Autowired
    private CampaignTargetModelService campaignTargetModelService;

    @Autowired
    private CampaignAnalyticsModelService campaignAnalyticsModelService;

    @Autowired
    private ApiUtil util;



    public CampaignAnalytics getCampaignAnalytics(String uid) {
        CampaignAnalytics analytics = analyticsService.fetchByUid(uid);

        if (analytics == null) {
            throw new NotFoundException("CampaignAnalytics not found for uid: " + uid);
        }

        return analytics;
    }


    public CampaignAnalytics getCampaignAnalytics(Long analyticsId) {
        CampaignAnalytics analytics = analyticsService.fetchById(analyticsId);

        if (analytics == null) {
            throw new NotFoundException("CampaignAnalytics not found for id: " + analyticsId);
        }

        return analytics;
    }
    

    public CampaignAnalytics getCampaignAnalytics(CampaignAnalyticsModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("CampaignAnalytics not found for model: " + model);
        }

        return getCampaignAnalytics(uid);
    }
   

    public CampaignAnalyticsModel toModel(CampaignAnalytics analytics, String... includeKeys) {
        if (analytics == null) return null;

        CampaignAnalyticsModel model = new CampaignAnalyticsModel();
        
        model.fromBean(analytics);

        if (analytics.getCampaignId() != null) {
            Campaign campaign = campaignModelService.getCampaign(analytics.getCampaignId());
            model.setCampaign(CampaignModel.from(campaign));
        }

        if (analytics.getGroupId() != null) {
            CampaignGroup group = campaignGroupModelService.getCampaignGroup(analytics.getGroupId());
            model.setGroup(CampaignGroupModel.from(group));
        }

        if (analytics.getChannelId() != null) {
            CampaignChannel channel = campaignChannelModelService.getCampaignChannel(analytics.getChannelId());
            model.setChannel(CampaignChannelModel.from(channel));
        }

        if (analytics.getTargetId() != null) {
            CampaignTarget target = campaignTargetModelService.getCampaignTarget(analytics.getTargetId());
            model.setTarget(CampaignTargetModel.from(target));
        }


        if (analytics.getUserId() != null) {
            User user = userModelService.getUser(analytics.getUserId());
            model.setUser(UserModel.from(user));
        }
        
        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }


    public List<CampaignAnalyticsModel> toModelList(List<CampaignAnalytics> analyticss, String... includeKeys) {
        List<CampaignAnalyticsModel> modelList = new ArrayList<>();

        for (CampaignAnalytics analytics : analyticss) {
            modelList.add(toModel(analytics, includeKeys));
        }

        return modelList;
    }


    public CampaignAnalytics toEntity(CampaignAnalyticsModel model) {
        CampaignAnalytics analytics = new CampaignAnalytics();

        return mergeEntity(analytics, model);
    }

    
    public CampaignAnalytics mergeEntity(CampaignAnalytics analytics, CampaignAnalyticsModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, analytics);

        for (String key : model.initializedKeys()) {
            switch (key) {
                case "campaign":
                    Campaign campaign = campaignModelService.getCampaign(model.getCampaign());
                    analytics.setCampaignId(campaign == null ? null : campaign.getId());
                    break;

                case "group":
                    CampaignGroup group = campaignGroupModelService.getCampaignGroup(model.getGroup());
                    analytics.setGroupId(group == null ? null : group.getId());
                    break;

                case "channel":
                    CampaignChannel channel = campaignChannelModelService.getCampaignChannel(model.getChannel());
                    analytics.setChannelId(channel == null ? null : channel.getId());
                    break;

                case "target":
                    CampaignTarget target = campaignTargetModelService.getCampaignTarget(model.getTarget());
                    analytics.setTargetId(target == null ? null : target.getId());
                    break;

                case "user":
                    User user = userModelService.getUser(model.getUser());
                    analytics.setUserId(user == null ? null : user.getId());
                    break;
            }

        }

        return analytics;
    }
}
