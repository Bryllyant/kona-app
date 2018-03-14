package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.sales.campaign.CampaignGroupModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignModel;
import com.bryllyant.kona.app.api.model.sales.partner.PartnerModel;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.Partner;
import com.bryllyant.kona.app.service.CampaignGroupService;
import com.bryllyant.kona.app.util.ApiUtil;
import com.bryllyant.kona.rest.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignGroupModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(CampaignGroupModelService.class);

    @Autowired
    private CampaignGroupService campaignGroupService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private PartnerModelService partnerModelService;

    @Autowired
    private ApiUtil util;


    public CampaignGroup getCampaignGroup(String uid) {
        CampaignGroup campaignGroup = campaignGroupService.fetchByUid(uid);

        if (campaignGroup == null) {
            throw new NotFoundException("CampaignGroup not found for uid: " + uid);
        }

        return campaignGroup;
    }

    public CampaignGroup getCampaignGroup(Long campaignGroupId) {
        CampaignGroup campaignGroup = campaignGroupService.fetchById(campaignGroupId);

        if (campaignGroup == null) {
            throw new NotFoundException("CampaignGroup not found for id: " + campaignGroupId);
        }

        return campaignGroup;
    }

    public CampaignGroup getCampaignGroup(CampaignGroupModel model) {
        if (model == null) return null;

        String uid = model.getUid();

        if (uid == null) {
            throw new NotFoundException("CampaignGroup not found for model: " + model);
        }

        return getCampaignGroup(uid);
    }

    public CampaignGroupModel toModel(CampaignGroup campaignGroup, String... includeKeys) {
        if (campaignGroup == null) return null;

        CampaignGroupModel model = new CampaignGroupModel();

        model.fromBean(campaignGroup);

        if (campaignGroup.getCampaignId() != null) {
            Campaign campaign = campaignModelService.getCampaign(campaignGroup.getCampaignId());
            model.setCampaign(CampaignModel.from(campaign));
        }

        if (campaignGroup.getPartnerId() != null) {
            Partner partner = partnerModelService.getPartner(campaignGroup.getPartnerId());
            model.setPartner(PartnerModel.from(partner));
        }

        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }

    public List<CampaignGroupModel> toModelList(List<CampaignGroup> campaignGroups, String... includeKeys) {
        List<CampaignGroupModel> modelList = new ArrayList<>();

        for (CampaignGroup campaignGroup : campaignGroups) {
            modelList.add(toModel(campaignGroup, includeKeys));
        }

        return modelList;
    }

    public CampaignGroup toEntity(CampaignGroupModel model) {
        CampaignGroup campaignGroup = new CampaignGroup();

        return mergeEntity(campaignGroup, model);
    }

    public CampaignGroup mergeEntity(CampaignGroup campaignGroup, CampaignGroupModel model) {
        logger.debug("toEntity called for model: " + model);

        util.copyModelToObject(model, campaignGroup);

        for (String key : model.initializedKeys()) {
            switch (key) {
                case "campaign":
                    Campaign campaign = campaignModelService.getCampaign(model.getCampaign());
                    campaignGroup.setCampaignId(campaign == null ? null : campaign.getId());
                    break;

                case "partner":
                    Partner partner = partnerModelService.getPartner(model.getPartner());
                    campaignGroup.setPartnerId(partner == null ? null : partner.getId());
                    break;
            }

        }

        return campaignGroup;
    }
}
