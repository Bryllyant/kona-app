package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.sales.campaign.CampaignChannelModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignGroupModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignModel;
import com.bryllyant.kona.app.api.model.sales.campaign.SmsModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.Sms;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsModelService extends BaseEntityModelService<SmsModel,Sms> {
    private static final Logger logger = LoggerFactory.getLogger(SmsModelService.class);

    @Autowired
    private SmsService entityService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private CampaignChannelModelService campaignChannelModelService;

    @Autowired
    private CampaignGroupModelService campaignGroupModelService;

    @Autowired
    private UserModelService userModelService;


    protected SmsService getEntityService() {
        return entityService;
    }


    protected void setForeignModelProperties(SmsModel model, Sms entity) {
        if (entity.getCampaignId() != null) {
            Campaign campaign = campaignModelService.getCampaign(entity.getCampaignId());
            model.setCampaign(CampaignModel.from(campaign));
        }

        if (entity.getCampaignGroupId() != null) {
            CampaignGroup campaignGroup = campaignGroupModelService.getCampaignGroup(entity.getCampaignGroupId());
            model.setCampaignGroup(CampaignGroupModel.from(campaignGroup));
        }

        if (entity.getCampaignChannelId() != null) {
            CampaignChannel channel = campaignChannelModelService.getCampaignChannel(entity.getCampaignChannelId());
            model.setCampaignChannel(CampaignChannelModel.from(channel));
        }

        if (entity.getToUserId() != null) {
            User user = userModelService.getUser(entity.getToUserId());
            model.setToUser(UserModel.from(user));
        }
    }

    protected void setEntityProperty(String key, SmsModel model, Sms entity) {
        switch (key) {

            case "campaign":
                Campaign campaign = campaignModelService.getCampaign(model.getCampaign());
                entity.setCampaignId(campaign == null ? null : campaign.getId());
                break;

            case "campaignGroup":
                CampaignGroup campaignGroup = campaignGroupModelService.getCampaignGroup(model.getCampaignGroup());
                entity.setCampaignGroupId(campaignGroup == null ? null : campaignGroup.getId());
                break;

            case "campaignChannel":
                CampaignChannel campaignChannel = campaignChannelModelService.getCampaignChannel(model.getCampaignChannel());
                entity.setCampaignChannelId(campaignChannel == null ? null : campaignChannel.getId());
                break;

            case "toUser":
                User user = userModelService.getUser(model.getToUser());
                entity.setToUserId(user == null ? null : user.getId());
                break;
        }

    }
}
