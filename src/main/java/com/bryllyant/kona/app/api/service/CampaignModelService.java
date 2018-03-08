package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.sales.campaign.CampaignModel;
import com.bryllyant.kona.app.api.model.sales.partner.PartnerModel;
import com.bryllyant.kona.app.api.model.sales.promo.PromoModel;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.Partner;
import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.app.service.CampaignService;
import com.bryllyant.kona.app.util.ApiUtil;
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
    private AppModelService appModelService;

    @Autowired
    private PromoModelService promoModelService;

    @Autowired
    private PartnerModelService partnerModelService;


    @Autowired
    private ApiUtil util;



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
        
        if (campaign.getPromoId() != null) {
            Promo promo = promoModelService.getPromo(campaign.getPromoId());
            model.setPromo(PromoModel.create(promo.getUid()));
        }

        if (campaign.getPartnerId() != null) {
            Partner partner = partnerModelService.getPartner(campaign.getPartnerId());
            model.setPartner(PartnerModel.create(partner.getUid()));
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
                case "partner":
                    Partner partner = partnerModelService.getPartner(model.getPartner());
                    campaign.setPartnerId(partner.getId());
                    break;

                case "promo":
                    Promo promo = promoModelService.getPromo(model.getPromo());
                    campaign.setPromoId(promo.getId());
                    break;
            }

        }

        return campaign;
    }
    

   
}
