/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AppConfigMapper;
import com.bryllyant.kona.app.entity.AppConfig;
import com.bryllyant.kona.app.entity.AppConfigExample;
import com.bryllyant.kona.app.service.AppConfigService;
import com.bryllyant.kona.app.service.KAbstractAppConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(AppConfigService.SERVICE_PATH)
public class AppConfigServiceImpl
        extends KAbstractAppConfigService<AppConfig, AppConfigExample, AppConfigMapper>
        implements AppConfigService {

    private static Logger logger = LoggerFactory.getLogger(AppConfigServiceImpl.class);

    @Autowired
    private AppConfigMapper appConfigMapper;

    @Override
    protected AppConfig getNewObject() {
        return new AppConfig();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected AppConfigMapper getMapper() {
        return appConfigMapper;
    }

    @Override
    protected AppConfigExample getEntityExampleObject() { return new AppConfigExample(); }

}
