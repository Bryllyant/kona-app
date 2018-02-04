/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PushNotificationMessageMapper;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AppUser;
import com.bryllyant.kona.app.entity.PushNotificationProvider;
import com.bryllyant.kona.app.entity.PushNotificationDevice;
import com.bryllyant.kona.app.entity.PushNotificationMessage;
import com.bryllyant.kona.app.entity.PushNotificationMessageExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.AppUserService;
import com.bryllyant.kona.app.service.KAbstractPushNotificationMessageService;
import com.bryllyant.kona.app.service.PushNotificationDeviceService;
import com.bryllyant.kona.app.service.PushNotificationMessageService;
import com.bryllyant.kona.app.service.PushProviderService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(PushNotificationMessageService.SERVICE_PATH)
public class PushNotificationMessageServiceImpl 
		extends KAbstractPushNotificationMessageService<PushNotificationMessage,
													   PushNotificationMessageExample,
													   App,
													   User,
													   AppUser,
        PushNotificationProvider,
													   PushNotificationDevice> 
		implements PushNotificationMessageService {

	private static Logger logger = LoggerFactory.getLogger(PushNotificationMessageServiceImpl.class);

	@Autowired
	private PushNotificationMessageMapper mapper;

	@Autowired
	private AppService appService;

	@Autowired
	private AppUserService appUserService;

	@Autowired
	private PushNotificationDeviceService pushNotificationDeviceService;

	@Autowired
	private UserService userService;

	@Autowired
	private PushProviderService pushProviderService;
    
	// ----------------------------------------------------------------------------
	
	@Override
	protected PushNotificationMessage getNewObject() {
		return new PushNotificationMessage();
	}

	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected PushNotificationMessageMapper getDao() {
		return mapper;
	}

	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected AppService getAppService() {
		return appService;
	}

	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected PushNotificationDeviceService getPushNotificationDeviceService() {
		return pushNotificationDeviceService;
	}

	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected AppUserService getAppUserService() {
		return appUserService;
	}

	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected UserService getUserService() {
		return userService;
	}

	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected PushProviderService getPushProviderService() {
		return pushProviderService;
	}

	// ----------------------------------------------------------------------------

	@Override
	protected PushNotificationMessageExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		PushNotificationMessageExample example = new PushNotificationMessageExample();

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
