/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PushDeviceMapper;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.PushDevice;
import com.bryllyant.kona.app.entity.PushDeviceExample;
import com.bryllyant.kona.app.entity.PushProvider;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserDevice;
import com.bryllyant.kona.app.service.KAbstractPushDeviceService;
import com.bryllyant.kona.app.service.PushDeviceService;
import com.bryllyant.kona.app.service.PushProviderService;
import com.bryllyant.kona.app.service.UserDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(PushDeviceService.SERVICE_PATH)
public class PushDeviceServiceImpl
        extends KAbstractPushDeviceService<
        PushDevice,
        PushDeviceExample,
        User,
        Device,
        UserDevice,
        PushProvider>
        implements PushDeviceService {

    private static Logger logger = LoggerFactory.getLogger(PushDeviceServiceImpl.class);

    @Autowired
    private PushDeviceMapper mapper;

    @Autowired
    private PushProviderService pushProviderService;


    @Autowired
    private UserDeviceService userDeviceService;

    protected PushDevice getNewObject() {
        return new PushDevice();
    }


    @Override @SuppressWarnings("unchecked")
    protected PushDeviceMapper getDao() {
        return mapper;
    }


    @Override @SuppressWarnings("unchecked")
    protected PushProviderService getPushProviderService() {
        return pushProviderService;
    }


    @Override @SuppressWarnings("unchecked")
    protected UserDeviceService getUserDeviceService() {
        return userDeviceService;
    }


    @Override
    protected PushDeviceExample getEntityExampleObject() { return new PushDeviceExample(); }

}
