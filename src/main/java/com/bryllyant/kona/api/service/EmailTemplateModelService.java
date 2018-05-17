package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.message.EmailTemplateModel;
import com.bryllyant.kona.app.entity.EmailTemplate;
import com.bryllyant.kona.app.service.EmailTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailTemplateModelService extends BaseEntityModelService<EmailTemplateModel,EmailTemplate> {
    private static final Logger logger = LoggerFactory.getLogger(EmailTemplateModelService.class);

    @Autowired
    private EmailTemplateService entityService;

    protected EmailTemplateService getEntityService() {
        return entityService;
    }


    protected void setForeignModelProperties(EmailTemplateModel model, EmailTemplate entity) {
    }

    protected void setEntityProperty(String key, EmailTemplateModel model, EmailTemplate entity) {
    }
}
