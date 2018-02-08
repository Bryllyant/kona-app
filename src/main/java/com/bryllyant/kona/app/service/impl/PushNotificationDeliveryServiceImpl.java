/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PushNotificationDeliveryMapper;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.PushNotificationDelivery;
import com.bryllyant.kona.app.entity.PushNotificationDeliveryExample;
import com.bryllyant.kona.app.entity.PushNotificationDevice;
import com.bryllyant.kona.app.entity.PushNotificationMessage;
import com.bryllyant.kona.app.entity.PushNotificationProvider;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserDevice;
import com.bryllyant.kona.app.service.KAbstractPushNotificationDeliveryService;
import com.bryllyant.kona.app.service.PushNotificationDeliveryService;
import com.bryllyant.kona.app.service.PushNotificationProviderService;
import com.bryllyant.kona.app.service.PushProviderService;
import com.bryllyant.kona.app.service.UserDeviceService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(PushNotificationDeliveryService.SERVICE_PATH)
public class PushNotificationDeliveryServiceImpl
        extends KAbstractPushNotificationDeliveryService<
        PushNotificationDelivery,
        PushNotificationDeliveryExample,
        PushNotificationDevice,
        PushNotificationMessage>
        implements PushNotificationDeliveryService {

    private static Logger logger = LoggerFactory.getLogger(PushNotificationDeliveryServiceImpl.class);

    @Autowired
    private PushNotificationDeliveryMapper mapper;

    @Autowired
    private PushNotificationProviderService pushNotificationProviderService;

    @Autowired
    private PushProviderService pushProviderService;

    @Autowired
    private UserDeviceService userDeviceService;

    protected PushNotificationDelivery getNewObject() {
        return new PushNotificationDelivery();
    }


    @Override @SuppressWarnings("unchecked")
    protected PushNotificationDeliveryMapper getDao() {
        return mapper;
    }


    @Override
    protected PushNotificationDeliveryExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
                                                                     Map<String, Object> filter, boolean distinct) {
        PushNotificationDeliveryExample example = new PushNotificationDeliveryExample();

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
