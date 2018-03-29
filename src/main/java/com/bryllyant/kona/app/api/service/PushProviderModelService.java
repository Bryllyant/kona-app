package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.sales.campaign.PushProviderModel;
import com.bryllyant.kona.app.entity.PushProvider;
import com.bryllyant.kona.app.service.PushProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushProviderModelService extends BaseEntityModelService<PushProviderModel,PushProvider> {
    private static final Logger logger = LoggerFactory.getLogger(PushProviderModelService.class);

    @Autowired
    private PushProviderService entityService;


    protected PushProviderService getEntityService() {
        return entityService;
    }


    protected void setForeignModelProperties(PushProviderModel model, PushProvider entity) {
    }

    protected void setEntityProperty(String key, PushProviderModel model, PushProvider entity) {
    }
}
