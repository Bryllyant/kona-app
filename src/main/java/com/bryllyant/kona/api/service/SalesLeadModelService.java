package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.marketing.campaign.CampaignAnalyticsModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignTargetModel;
import com.bryllyant.kona.api.model.sales.saleslead.SalesLeadModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignChannelModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignGroupModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.entity.SalesLead;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.SalesLeadService;
import com.bryllyant.kona.util.AppUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesLeadModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(SalesLeadModelService.class);
    
    @Autowired
    private SalesLeadService salesLeadService;
    
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
    private AppUtil util;



    public SalesLead getSalesLead(String uid) {
        SalesLead salesLead = salesLeadService.fetchByUid(uid);

        if (salesLead == null) {
            throw new NotFoundException("SalesLead not found for uid: " + uid);
        }

        return salesLead;
    }


    public SalesLead getSalesLead(Long salesLeadId) {
        SalesLead salesLead = salesLeadService.fetchById(salesLeadId);

        if (salesLead == null) {
            throw new NotFoundException("SalesLead not found for id: " + salesLeadId);
        }

        return salesLead;
    }
    

    public SalesLead getSalesLead(SalesLeadModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("SalesLead not found for model: " + model);
        }

        return getSalesLead(uid);
    }
   

    public SalesLeadModel toModel(SalesLead salesLead, String... includeKeys) {
        if (salesLead == null) return null;

        SalesLeadModel model = new SalesLeadModel();
        
        model.fromBean(salesLead);

        if (salesLead.getCampaignId() != null) {
            Campaign campaign = campaignModelService.getCampaign(salesLead.getCampaignId());
            model.setCampaign(CampaignModel.from(campaign));
        }

        if (salesLead.getGroupId() != null) {
            CampaignGroup group = campaignGroupModelService.getCampaignGroup(salesLead.getGroupId());
            model.setGroup(CampaignGroupModel.from(group));
        }

        if (salesLead.getChannelId() != null) {
            CampaignChannel channel = campaignChannelModelService.getCampaignChannel(salesLead.getChannelId());
            model.setChannel(CampaignChannelModel.from(channel));
        }

        if (salesLead.getTargetId() != null) {
            CampaignTarget target = campaignTargetModelService.getCampaignTarget(salesLead.getTargetId());
            model.setTarget(CampaignTargetModel.from(target));
        }

        if (salesLead.getAnalyticsId() != null) {
            CampaignAnalytics analytics = campaignAnalyticsModelService.getCampaignAnalytics(salesLead.getAnalyticsId());
            model.setAnalytics(CampaignAnalyticsModel.from(analytics));
        }

        if (salesLead.getReferredById() != null) {
            User referredBy = userModelService.getUser(salesLead.getReferredById());
            model.setReferredBy(UserModel.from(referredBy));
        }
        
        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }


    public List<SalesLeadModel> toModelList(List<SalesLead> salesLeads, String... includeKeys) {
        List<SalesLeadModel> modelList = new ArrayList<>();

        for (SalesLead salesLead : salesLeads) {
            modelList.add(toModel(salesLead, includeKeys));
        }

        return modelList;
    }


    public SalesLead toEntity(SalesLeadModel model) {
        SalesLead salesLead = new SalesLead();

        return mergeEntity(salesLead, model);
    }

    
    public SalesLead mergeEntity(SalesLead salesLead, SalesLeadModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, salesLead);

        for (String key : model.initializedKeys()) {
            switch (key) {
                case "campaign":
                    Campaign campaign = campaignModelService.getCampaign(model.getCampaign());
                    salesLead.setCampaignId(campaign == null ? null : campaign.getId());
                    break;

                case "group":
                    CampaignGroup group = campaignGroupModelService.getCampaignGroup(model.getGroup());
                    salesLead.setGroupId(group == null ? null : group.getId());
                    break;

                case "channel":
                    CampaignChannel channel = campaignChannelModelService.getCampaignChannel(model.getChannel());
                    salesLead.setChannelId(channel == null ? null : channel.getId());
                    break;

                case "target":
                    CampaignTarget target = campaignTargetModelService.getCampaignTarget(model.getTarget());
                    salesLead.setTargetId(target == null ? null : target.getId());
                    break;

                case "analytics":
                    CampaignAnalytics analytics = campaignAnalyticsModelService.getCampaignAnalytics(model.getAnalytics());
                    salesLead.setAnalyticsId(analytics == null ? null : analytics.getId());
                    break;

                case "referredBy":
                    User referredBy = userModelService.getUser(model.getReferredBy());
                    salesLead.setReferredById(referredBy == null ? null : referredBy.getId());
                    break;
            }

        }

        return salesLead;
    }
}
