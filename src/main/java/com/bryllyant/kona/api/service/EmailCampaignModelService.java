package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.marketing.campaign.CampaignChannelModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignGroupModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignModel;
import com.bryllyant.kona.api.model.message.EmailCampaignModel;
import com.bryllyant.kona.api.model.message.EmailContentModel;
import com.bryllyant.kona.api.model.message.EmailGroupModel;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.EmailCampaign;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.service.EmailCampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailCampaignModelService extends BaseEntityModelService<EmailCampaignModel,EmailCampaign> {
    private static final Logger logger = LoggerFactory.getLogger(EmailCampaignModelService.class);

    @Autowired
    private EmailCampaignService entityCampaignService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private CampaignChannelModelService campaignChannelModelService;

    @Autowired
    private CampaignGroupModelService campaignGroupModelService;

    @Autowired
    private EmailGroupModelService emailGroupModelService;

    @Autowired
    private EmailContentModelService emailContentModelService;

    @Autowired
    private EmailCampaignModelService emailCampaignModelService;


    protected EmailCampaignService getEntityService() {
        return entityCampaignService;
    }


    protected void setForeignModelProperties(EmailCampaignModel model, EmailCampaign entity) {
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

        if (entity.getEmailGroupId() != null) {
            EmailGroup group = emailGroupModelService.getEntity(entity.getEmailGroupId());
            model.setEmailGroup(EmailGroupModel.from(group));
        }

        if (entity.getEmailContentId() != null) {
            EmailContent content = emailContentModelService.getEntity(entity.getEmailContentId());
            model.setEmailContent(EmailContentModel.from(content));
        }
    }

    protected void setEntityProperty(String key, EmailCampaignModel model, EmailCampaign entity) {
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

            case "emailGroup":
                EmailGroup emailGroup = emailGroupModelService.getEntity(model.getEmailGroup());
                entity.setEmailGroupId(emailGroup == null ? null : emailGroup.getId());
                break;

            case "emailContent":
                EmailContent emailContent = emailContentModelService.getEntity(model.getEmailContent());
                entity.setEmailContentId(emailContent == null ? null : emailContent.getId());
                break;
        }

    }
}
