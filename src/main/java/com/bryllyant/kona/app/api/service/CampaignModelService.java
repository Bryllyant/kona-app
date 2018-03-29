package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.sales.campaign.CampaignModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.CampaignService;
import com.bryllyant.kona.app.util.AppUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(CampaignModelService.class);
    
    @Autowired
    private CampaignService campaignService;
    
    @Autowired
    private UserModelService userModelService;

    @Autowired
    private AppUtil util;



    public Campaign getCampaign(String uid) {
        Campaign campaign = campaignService.fetchByUid(uid);

        if (campaign == null) {
            throw new NotFoundException("Campaign not found for uid: " + uid);
        }

        return campaign;
    }


    public Campaign getCampaign(Long campaignId) {
        Campaign campaign = campaignService.fetchById(campaignId);

        if (campaign == null) {
            throw new NotFoundException("Campaign not found for id: " + campaignId);
        }

        return campaign;
    }
    

    public Campaign getCampaign(CampaignModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("Campaign not found for model: " + model);
        }

        return getCampaign(uid);
    }
   

    public CampaignModel toModel(Campaign campaign, String... includeKeys) {
        if (campaign == null) return null;

        CampaignModel model = new CampaignModel();
        
        model.fromBean(campaign);

        if (campaign.getOwnerId() != null) {
            User owner = userModelService.getUser(campaign.getOwnerId());
            model.setOwner(UserModel.from(owner));
        }
        
        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }


    public List<CampaignModel> toModelList(List<Campaign> campaigns, String... includeKeys) {
        List<CampaignModel> modelList = new ArrayList<>();

        for (Campaign campaign : campaigns) {
            modelList.add(toModel(campaign, includeKeys));
        }

        return modelList;
    }


    public Campaign toEntity(CampaignModel model) {
        Campaign campaign = new Campaign();

        return mergeEntity(campaign, model);
    }

    
    public Campaign mergeEntity(Campaign campaign, CampaignModel model) {
        logger.debug("toEntity called for model: " + model);
        
        util.copyModelToObject(model, campaign);

        for (String key : model.initializedKeys()) {
            switch (key) {
                case "owner":
                    User owner = userModelService.getUser(model.getOwner());
                    campaign.setOwnerId(owner == null ? null : owner.getId());
                    break;
            }

        }

        return campaign;
    }
}
