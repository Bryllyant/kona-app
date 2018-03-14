package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.sales.campaign.CampaignChannelModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignGroupModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignModel;
import com.bryllyant.kona.app.api.model.sales.landingPage.LandingPageModel;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.service.CampaignChannelService;
import com.bryllyant.kona.app.util.ApiUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignChannelModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(CampaignChannelModelService.class);
    
    @Autowired
    private CampaignChannelService campaignChannelService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private CampaignGroupModelService campaignGroupModelService;

    @Autowired
    private LandingPageModelService landingPageModelService;

    @Autowired
    private ApiUtil util;

    public CampaignChannel getCampaignChannel(String uid) {
        CampaignChannel campaignChannel = campaignChannelService.fetchByUid(uid);

        if (campaignChannel == null) {
            throw new NotFoundException("CampaignChannel not found for uid: " + uid);
        }

        return campaignChannel;
    }

    public CampaignChannel getCampaignChannel(Long campaignChannelId) {
        CampaignChannel campaignChannel = campaignChannelService.fetchById(campaignChannelId);

        if (campaignChannel == null) {
            throw new NotFoundException("CampaignChannel not found for id: " + campaignChannelId);
        }

        return campaignChannel;
    }
    
    public CampaignChannel getCampaignChannel(CampaignChannelModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("CampaignChannel not found for model: " + model);
        }

        return getCampaignChannel(uid);
    }
   
    public CampaignChannelModel toModel(CampaignChannel campaignChannel, String... includeKeys) {
        if (campaignChannel == null) return null;

        CampaignChannelModel model = new CampaignChannelModel();
        
        model.fromBean(campaignChannel);
        
        if (campaignChannel.getCampaignId() != null) {
            Campaign campaign = campaignModelService.getCampaign(campaignChannel.getCampaignId());
            model.setCampaign(CampaignModel.from(campaign));
        }

        if (campaignChannel.getGroupId() != null) {
            CampaignGroup campaignGroup = campaignGroupModelService.getCampaignGroup(campaignChannel.getGroupId());
            model.setGroup(CampaignGroupModel.from(campaignGroup));
        }

        if (campaignChannel.getLandingPageId() != null) {
            LandingPage landingPage = landingPageModelService.getLandingPage(campaignChannel.getLandingPageId());
            model.setLandingPage(LandingPageModel.from(landingPage));
        }

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }

    public List<CampaignChannelModel> toModelList(List<CampaignChannel> campaignChannels, String... includeKeys) {
        List<CampaignChannelModel> modelList = new ArrayList<>();

        for (CampaignChannel campaignChannel : campaignChannels) {
            modelList.add(toModel(campaignChannel, includeKeys));
        }

        return modelList;
    }

    public CampaignChannel toEntity(CampaignChannelModel model) {
        CampaignChannel campaignChannel = new CampaignChannel();

        return mergeEntity(campaignChannel, model);
    }

    
    public CampaignChannel mergeEntity(CampaignChannel campaignChannel, CampaignChannelModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, campaignChannel);

        for (String key : model.initializedKeys()) {
            switch (key) {
                case "group":
                    CampaignGroup campaignGroup = campaignGroupModelService.getCampaignGroup(model.getGroup());
                    campaignChannel.setGroupId(campaignGroup == null ? null : campaignGroup.getId());
                    break;

                case "campaign":
                    Campaign campaign = campaignModelService.getCampaign(model.getCampaign());
                    campaignChannel.setCampaignId(campaign == null ? null : campaign.getId());
                    break;

                case "landingPage":
                    LandingPage landingPage = landingPageModelService.getLandingPage(model.getLandingPage());
                    campaignChannel.setLandingPageId(landingPage == null ? null : landingPage.getId());
                    break;
            }

        }

        return campaignChannel;
    }
}
