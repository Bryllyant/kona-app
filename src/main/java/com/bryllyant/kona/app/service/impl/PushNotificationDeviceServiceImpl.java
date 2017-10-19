/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PushNotificationDeviceMapper;
import com.bryllyant.kona.app.entity.PushNotification;
import com.bryllyant.kona.app.entity.PushNotificationDevice;
import com.bryllyant.kona.app.entity.PushNotificationDeviceExample;
import com.bryllyant.kona.app.service.KAbstractPushNotificationDeviceService;
import com.bryllyant.kona.app.service.PushNotificationDeviceService;
import com.bryllyant.kona.app.service.PushNotificationService;
import com.bryllyant.kona.app.service.PushProviderService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(PushNotificationDeviceService.SERVICE_PATH)
public class PushNotificationDeviceServiceImpl 
		extends KAbstractPushNotificationDeviceService<PushNotificationDevice,PushNotificationDeviceExample,PushNotification> 
		implements PushNotificationDeviceService {
	
	private static Logger logger = LoggerFactory.getLogger(PushNotificationDeviceServiceImpl.class);

	@Autowired
	private PushNotificationDeviceMapper mapper;
	
	@Autowired
	private PushNotificationService pushNotificationService;
	
	@Autowired
	private PushProviderService pushProviderService;
    
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected PushNotificationDeviceMapper getDao() {
		return mapper;
	}
	
	// ----------------------------------------------------------------------------
	
	@Override @SuppressWarnings("unchecked")
	protected PushNotificationService getPushNotificationService() {
		return pushNotificationService;
	}
	
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected PushProviderService getPushProviderService() {
		return pushProviderService;
	}
	
	// ----------------------------------------------------------------------------
	
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
	
	// ----------------------------------------------------------------------------

	@Override
	protected List<PushNotificationDevice> fetchByUserIds(List<Long> userIdList, boolean sandbox) {
		return mapper.fetchByUserIds(userIdList, sandbox);
	}

	// ----------------------------------------------------------------------------

}
