/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PushMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AppUser;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.PushDevice;
import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.app.entity.PushExample;
import com.bryllyant.kona.app.entity.PushProvider;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.AppUserService;
import com.bryllyant.kona.app.service.KAbstractPushService;
import com.bryllyant.kona.app.service.PushDeviceService;
import com.bryllyant.kona.app.service.PushService;
import com.bryllyant.kona.app.service.PushProviderService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(PushService.SERVICE_PATH)
public class PushServiceImpl extends KAbstractPushService<
        Push,
        PushExample,
        App,
        User,
        Device,
        AppUser,
        PushProvider,
        PushDevice>
        implements PushService {

    private static Logger logger = LoggerFactory.getLogger(PushServiceImpl.class);

    @Autowired
    private PushMapper mapper;

    @Autowired
    private AppService appService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PushDeviceService pushDeviceService;

    @Autowired
    private UserService userService;

    @Autowired
    private PushProviderService pushProviderService;


    @Override
    protected Push getNewObject() {
        return new Push();
    }

    @Override @SuppressWarnings("unchecked")
    protected PushMapper getDao() {
        return mapper;
    }

    @Override @SuppressWarnings("unchecked")
    protected AppService getAppService() {
        return appService;
    }

    @Override @SuppressWarnings("unchecked")
    protected PushDeviceService getPushDeviceService() {
        return pushDeviceService;
    }

    @Override @SuppressWarnings("unchecked")
    protected AppUserService getAppUserService() {
        return appUserService;
    }

    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }

    @Override @SuppressWarnings("unchecked")
    protected PushProviderService getPushProviderService() {
        return pushProviderService;
    }

    @Override
    protected PushExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
                                                   Map<String, Object> filter, boolean distinct) {
        PushExample example = new PushExample();

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
