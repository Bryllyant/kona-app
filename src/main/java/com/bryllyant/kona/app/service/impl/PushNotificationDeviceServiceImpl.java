/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PushNotificationDeviceMapper;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.PushNotificationProvider;
import com.bryllyant.kona.app.entity.PushNotificationDevice;
import com.bryllyant.kona.app.entity.PushNotificationDeviceExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserDevice;
import com.bryllyant.kona.app.service.KAbstractPushNotificationDeviceService;
import com.bryllyant.kona.app.service.PushNotificationDeviceService;
import com.bryllyant.kona.app.service.PushNotificationProviderService;
import com.bryllyant.kona.app.service.PushProviderService;
import com.bryllyant.kona.app.service.UserDeviceService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(PushNotificationDeviceService.SERVICE_PATH)
public class PushNotificationDeviceServiceImpl
        extends KAbstractPushNotificationDeviceService<
        PushNotificationDevice,
        PushNotificationDeviceExample,
        User,
        Device,
        UserDevice,
        PushNotificationProvider>
        implements PushNotificationDeviceService {

    private static Logger logger = LoggerFactory.getLogger(PushNotificationDeviceServiceImpl.class);

    @Autowired
    private PushNotificationDeviceMapper mapper;

    @Autowired
    private PushNotificationProviderService pushNotificationProviderService;

    @Autowired
    private PushProviderService pushProviderService;

    @Autowired
    private UserDeviceService userDeviceService;

    protected PushNotificationDevice getNewObject() {
        return new PushNotificationDevice();
    }


    @Override @SuppressWarnings("unchecked")
    protected PushNotificationDeviceMapper getDao() {
        return mapper;
    }


    @Override @SuppressWarnings("unchecked")
    protected PushNotificationProviderService getPushNotificationProviderService() {
        return pushNotificationProviderService;
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
    protected PushNotificationDeviceExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
                                                                     Map<String, Object> filter, boolean distinct) {
        PushNotificationDeviceExample example = new PushNotificationDeviceExample();

        if (sortOrder != null) {
            example.setOrderByClause(KMyBatisUtil.getOrderByString(sortOrder));
        }

        if (startRow == null) startRow = 0;
        if (resultSize == null) resultSize = 99999999;

        example.setOffset(startRow);
        example.setLimit(resultSize);
        example.setDistinct(distinct);

        KMyBatisUtil.buildExample(example.or().getClass(), example.or(), filter);

        return example;
    }
}
