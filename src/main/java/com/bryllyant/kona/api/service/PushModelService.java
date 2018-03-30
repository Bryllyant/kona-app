package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.marketing.campaign.CampaignChannelModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignGroupModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignModel;
import com.bryllyant.kona.api.model.message.PushDeviceModel;
import com.bryllyant.kona.api.model.message.PushModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignChannelModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignGroupModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignModel;
import com.bryllyant.kona.api.model.message.PushDeviceModel;
import com.bryllyant.kona.api.model.message.PushModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.app.entity.PushDevice;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.PushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushModelService extends BaseEntityModelService<PushModel,Push> {
    private static final Logger logger = LoggerFactory.getLogger(PushModelService.class);

    @Autowired
    private PushService entityService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private CampaignChannelModelService campaignChannelModelService;

    @Autowired
    private CampaignGroupModelService campaignGroupModelService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private PushDeviceModelService pushDeviceModelService;


    protected PushService getEntityService() {
        return entityService;
    }


    protected void setForeignModelProperties(PushModel model, Push entity) {
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

        if (entity.getDeviceId() != null) {
            PushDevice device = pushDeviceModelService.getEntity(entity.getDeviceId());
            model.setDevice(PushDeviceModel.from(device));
        }

        if (entity.getUserId() != null) {
            User user = userModelService.getUser(entity.getUserId());
            model.setUser(UserModel.from(user));
        }
    }

    protected void setEntityProperty(String key, PushModel model, Push entity) {
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

            case "device":
                PushDevice device = pushDeviceModelService.getEntity(model.getDevice());
                entity.setDeviceId(device == null ? null : device.getId());
                break;

            case "toUser":
                User user = userModelService.getUser(model.getUser());
                entity.setUserId(user == null ? null : user.getId());
                break;
        }

    }
}
