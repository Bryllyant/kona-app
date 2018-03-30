package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.message.EmailAddressModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.api.model.message.EmailAddressModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.EmailAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailAddressModelService extends BaseEntityModelService<EmailAddressModel,EmailAddress> {
    private static final Logger logger = LoggerFactory.getLogger(EmailAddressModelService.class);

    @Autowired
    private EmailAddressService entityService;

    @Autowired
    private UserModelService userModelService;

    protected EmailAddressService getEntityService() {
        return entityService;
    }


    protected void setForeignModelProperties(EmailAddressModel model, EmailAddress entity) {
        if (entity.getUserId() != null) {
            User owner = userModelService.getUser(entity.getUserId());
            model.setUser(UserModel.from(owner));
        }
    }

    protected void setEntityProperty(String key, EmailAddressModel model, EmailAddress entity) {
        switch (key) {

            case "owner":
                User owner = userModelService.getUser(model.getUser());
                entity.setUserId(owner == null ? null : owner.getId());
                break;
        }
    }
}
