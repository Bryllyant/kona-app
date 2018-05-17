package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.message.EmailAddressModel;
import com.bryllyant.kona.api.model.message.EmailCampaignModel;
import com.bryllyant.kona.api.model.message.EmailContentModel;
import com.bryllyant.kona.api.model.message.EmailModel;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailCampaign;
import com.bryllyant.kona.app.entity.EmailContent;
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
    private EmailAddressModelService emailAddressModelService;

    @Autowired
    private EmailContentModelService emailContentModelService;

    @Autowired
    private EmailCampaignModelService emailCampaignModelService;


    protected EmailService getEntityService() {
        return entityService;
    }


    protected void setForeignModelProperties(EmailModel model, Email entity) {
        if (entity.getEmailCampaignId() != null) {
            EmailCampaign emailCampaign = emailCampaignModelService.getEntity(entity.getEmailCampaignId());
            model.setEmailCampaign(EmailCampaignModel.from(emailCampaign));
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

            case "emailCampaign":
                EmailCampaign emailCampaign = emailCampaignModelService.getEntity(model.getEmailCampaign());
                entity.setEmailCampaignId(emailCampaign == null ? null : emailCampaign.getId());
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
