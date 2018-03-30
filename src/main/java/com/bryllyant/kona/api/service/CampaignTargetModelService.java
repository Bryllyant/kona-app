package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.marketing.campaign.CampaignChannelModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignGroupModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignTargetModel;
import com.bryllyant.kona.api.model.marketing.landingpage.LandingPageModel;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.service.CampaignTargetService;
import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignTargetModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(CampaignTargetModelService.class);
    
    @Autowired
    private CampaignTargetService campaignTargetService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private CampaignGroupModelService campaignGroupModelService;

    @Autowired
    private CampaignChannelModelService campaignChannelModelService;

    @Autowired
    private LandingPageModelService landingPageModelService;

    @Autowired
    private AppUtil util;

    public CampaignTarget getCampaignTarget(String uid) {
        CampaignTarget campaignTarget = campaignTargetService.fetchByUid(uid);

        if (campaignTarget == null) {
            throw new NotFoundException("CampaignTarget not found for uid: " + uid);
        }

        return campaignTarget;
    }

    public CampaignTarget getCampaignTarget(Long campaignTargetId) {
        CampaignTarget campaignTarget = campaignTargetService.fetchById(campaignTargetId);

        if (campaignTarget == null) {
            throw new NotFoundException("CampaignTarget not found for id: " + campaignTargetId);
        }

        return campaignTarget;
    }
    
    public CampaignTarget getCampaignTarget(CampaignTargetModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("CampaignTarget not found for model: " + model);
        }

        return getCampaignTarget(uid);
    }
   
    public CampaignTargetModel toModel(CampaignTarget campaignTarget, String... includeKeys) {
        if (campaignTarget == null) return null;

        CampaignTargetModel model = new CampaignTargetModel();
        
        model.fromBean(campaignTarget);
        
        if (campaignTarget.getCampaignId() != null) {
            Campaign campaign = campaignModelService.getCampaign(campaignTarget.getCampaignId());
            model.setCampaign(CampaignModel.from(campaign));
        }

        if (campaignTarget.getGroupId() != null) {
            CampaignGroup campaignGroup = campaignGroupModelService.getCampaignGroup(campaignTarget.getGroupId());
            model.setGroup(CampaignGroupModel.from(campaignGroup));
        }

        if (campaignTarget.getChannelId() != null) {
            CampaignChannel campaignChannel = campaignChannelModelService.getCampaignChannel(campaignTarget.getChannelId());
            model.setChannel(CampaignChannelModel.from(campaignChannel));
        }

        if (campaignTarget.getLandingPageId() != null) {
            LandingPage landingPage = landingPageModelService.getLandingPage(campaignTarget.getLandingPageId());
            model.setLandingPage(LandingPageModel.from(landingPage));
        }

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }

    public List<CampaignTargetModel> toModelList(List<CampaignTarget> campaignTargets, String... includeKeys) {
        List<CampaignTargetModel> modelList = new ArrayList<>();

        for (CampaignTarget campaignTarget : campaignTargets) {
            modelList.add(toModel(campaignTarget, includeKeys));
        }

        return modelList;
    }

    public CampaignTarget toEntity(CampaignTargetModel model) {
        CampaignTarget campaignTarget = new CampaignTarget();

        return mergeEntity(campaignTarget, model);
    }

    
    public CampaignTarget mergeEntity(CampaignTarget campaignTarget, CampaignTargetModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, campaignTarget);

        for (String key : model.initializedKeys()) {
            switch (key) {

                case "campaign":
                    Campaign campaign = campaignModelService.getCampaign(model.getCampaign());
                    campaignTarget.setCampaignId(campaign == null ? null : campaign.getId());
                    break;

                case "group":
                    CampaignGroup campaignGroup = campaignGroupModelService.getCampaignGroup(model.getGroup());
                    campaignTarget.setGroupId(campaignGroup == null ? null : campaignGroup.getId());
                    break;

                case "channel":
                    CampaignChannel campaignChannel = campaignChannelModelService.getCampaignChannel(model.getChannel());
                    campaignTarget.setChannelId(campaignChannel == null ? null : campaignChannel.getId());
                    break;

                case "landingpage":
                    LandingPage landingPage = landingPageModelService.getLandingPage(model.getLandingPage());
                    campaignTarget.setLandingPageId(landingPage == null ? null : landingPage.getId());
                    break;
            }

        }

        return campaignTarget;
    }
}
