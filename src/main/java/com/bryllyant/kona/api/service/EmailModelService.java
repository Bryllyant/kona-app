package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.marketing.campaign.CampaignChannelModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignGroupModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignModel;
import com.bryllyant.kona.api.model.message.EmailAddressModel;
import com.bryllyant.kona.api.model.message.EmailContentModel;
import com.bryllyant.kona.api.model.message.EmailGroupModel;
import com.bryllyant.kona.api.model.message.EmailModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignChannelModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignGroupModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignModel;
import com.bryllyant.kona.api.model.message.EmailAddressModel;
import com.bryllyant.kona.api.model.message.EmailContentModel;
import com.bryllyant.kona.api.model.message.EmailGroupModel;
import com.bryllyant.kona.api.model.message.EmailModel;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailModelService extends BaseEntityModelService<EmailModel,Email> {
    private static final Logger logger = LoggerFactory.getLogger(EmailModelService.class);

    @Autowired
    private EmailService entityService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private CampaignChannelModelService campaignChannelModelService;

    @Autowired
    private CampaignGroupModelService campaignGroupModelService;

    @Autowired
    private EmailGroupModelService emailGroupModelService;

    @Autowired
    private EmailAddressModelService emailAddressModelService;

    @Autowired
    private EmailContentModelService emailContentModelService;


    protected EmailService getEntityService() {
        return entityService;
    }


    protected void setForeignModelProperties(EmailModel model, Email entity) {
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

        if (entity.getEmailAddressId() != null) {
            EmailAddress address = emailAddressModelService.getEntity(entity.getEmailAddressId());
            model.setEmailAddress(EmailAddressModel.from(address));
        }

        if (entity.getEmailContentId() != null) {
            EmailContent content = emailContentModelService.getEntity(entity.getEmailContentId());
            model.setEmailContent(EmailContentModel.from(content));
        }
    }

    protected void setEntityProperty(String key, EmailModel model, Email entity) {
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

            case "emailAddress":
                EmailAddress emailAddress = emailAddressModelService.getEntity(model.getEmailAddress());
                entity.setEmailAddressId(emailAddress == null ? null : emailAddress.getId());
                break;

            case "emailContent":
                EmailContent emailContent = emailContentModelService.getEntity(model.getEmailContent());
                entity.setEmailContentId(emailContent == null ? null : emailContent.getId());
                break;
        }

    }
}
