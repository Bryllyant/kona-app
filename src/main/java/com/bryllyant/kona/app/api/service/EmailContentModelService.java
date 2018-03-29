package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.sales.campaign.EmailContentModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.EmailContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailContentModelService extends BaseEntityModelService<EmailContentModel,EmailContent> {
    private static final Logger logger = LoggerFactory.getLogger(EmailContentModelService.class);

    @Autowired
    private EmailContentService entityService;

    @Autowired
    private UserModelService userModelService;

    protected EmailContentService getEntityService() {
        return entityService;
    }


    protected void setForeignModelProperties(EmailContentModel model, EmailContent entity) {
        if (entity.getOwnerId() != null) {
            User owner = userModelService.getUser(entity.getOwnerId());
            model.setOwner(UserModel.from(owner));
        }
    }

    protected void setEntityProperty(String key, EmailContentModel model, EmailContent entity) {
        switch (key) {

            case "owner":
                User owner = userModelService.getUser(model.getOwner());
                entity.setOwnerId(owner == null ? null : owner.getId());
                break;
        }

    }
}
