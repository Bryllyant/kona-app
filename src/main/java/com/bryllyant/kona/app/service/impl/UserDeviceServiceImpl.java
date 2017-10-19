/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.UserDeviceMapper;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserDevice;
import com.bryllyant.kona.app.entity.UserDeviceExample;
import com.bryllyant.kona.app.service.DeviceService;
import com.bryllyant.kona.app.service.KAbstractUserDeviceService;
import com.bryllyant.kona.app.service.UserDeviceService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(UserDeviceService.SERVICE_PATH)
public class UserDeviceServiceImpl 
		extends KAbstractUserDeviceService<UserDevice,UserDeviceExample,User,Device> 
		implements UserDeviceService {
	
	private static Logger logger = LoggerFactory.getLogger(UserDeviceServiceImpl.class);

// ----------------------------------------------------------------------------

	@Autowired
	private UserDeviceMapper userDeviceDao;

	@Autowired
	private DeviceService deviceService;
	
// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected UserDeviceMapper getDao() {
		return userDeviceDao;
	}
    
	// ----------------------------------------------------------------------------

	@Override
	protected UserDevice getNewObject() {
		return new UserDevice();
	}

	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected  DeviceService getDeviceService() {
	    return deviceService;
	}

	// ----------------------------------------------------------------------------

	@Override
	protected UserDeviceExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		UserDeviceExample example = new UserDeviceExample();

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
