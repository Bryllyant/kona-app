/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PushNotificationMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.PushNotification;
import com.bryllyant.kona.app.entity.PushNotificationExample;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.KAbstractPushNotificationService;
import com.bryllyant.kona.app.service.PushNotificationService;
import com.bryllyant.kona.app.service.PushProviderService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(PushNotificationService.SERVICE_PATH)
public class PushNotificationServiceImpl 
		extends KAbstractPushNotificationService<PushNotification,PushNotificationExample,App> 
		implements PushNotificationService {
	
	private static Logger logger = LoggerFactory.getLogger(PushNotificationServiceImpl.class);

	@Autowired
	private PushNotificationMapper mapper;
	
	@Autowired
	private AppService appService;
	
	@Autowired
	private PushProviderService pushProviderService;
    
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected PushNotificationMapper getDao() {
		return mapper;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override @SuppressWarnings("unchecked")
	protected AppService getAppService() {
		return appService;
	}
	
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected PushProviderService getPushProviderService() {
		return pushProviderService;
	}
    
	// ----------------------------------------------------------------------------
	
	@Override
	protected PushNotificationExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		PushNotificationExample example = new PushNotificationExample();

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


	// ----------------------------------------------------------------------------

}
