/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PushNotificationProviderMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.PushNotificationProvider;
import com.bryllyant.kona.app.entity.PushNotificationProviderExample;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.KAbstractPushNotificationProviderService;
import com.bryllyant.kona.app.service.PushNotificationProviderService;
import com.bryllyant.kona.app.service.PushProviderService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(PushNotificationProviderService.SERVICE_PATH)
public class PushNotificationProviderServiceImpl
		extends KAbstractPushNotificationProviderService<PushNotificationProvider,PushNotificationProviderExample,App>
		implements PushNotificationProviderService {
	
	private static Logger logger = LoggerFactory.getLogger(PushNotificationProviderServiceImpl.class);

	@Autowired
	private PushNotificationProviderMapper mapper;
	
	@Autowired
	private AppService appService;
	
	@Autowired
	private PushProviderService pushProviderService;

	@Override
	protected PushNotificationProvider getNewObject() {
	    return new PushNotificationProvider();
    }

	@Override @SuppressWarnings("unchecked")
	protected PushNotificationProviderMapper getDao() {
		return mapper;
	}
	

	@Override @SuppressWarnings("unchecked")
	protected AppService getAppService() {
		return appService;
	}
	

	@Override @SuppressWarnings("unchecked")
	protected PushProviderService getPushProviderService() {
		return pushProviderService;
	}
    

	@Override
	protected PushNotificationProviderExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		PushNotificationProviderExample example = new PushNotificationProviderExample();

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
