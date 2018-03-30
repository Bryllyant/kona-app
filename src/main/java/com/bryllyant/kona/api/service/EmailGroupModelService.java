package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.message.EmailGroupModel;
import com.bryllyant.kona.api.model.message.EmailGroupModel;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.service.EmailGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailGroupModelService extends BaseEntityModelService<EmailGroupModel,EmailGroup> {
    private static final Logger logger = LoggerFactory.getLogger(EmailGroupModelService.class);

    @Autowired
    private EmailGroupService entityService;

    protected EmailGroupService getEntityService() {
        return entityService;
    }


    protected void setForeignModelProperties(EmailGroupModel model, EmailGroup entity) {
    }

    protected void setEntityProperty(String key, EmailGroupModel model, EmailGroup entity) {
    }
}
