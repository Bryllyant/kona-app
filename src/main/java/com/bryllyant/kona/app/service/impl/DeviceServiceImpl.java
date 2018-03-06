/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.DeviceMapper;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.DeviceExample;
import com.bryllyant.kona.app.service.DeviceService;
import com.bryllyant.kona.app.service.KAbstractDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(DeviceService.SERVICE_PATH)
public class DeviceServiceImpl 
		extends KAbstractDeviceService<Device, DeviceExample, DeviceMapper>
		implements DeviceService {
	
	private static Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

	@Autowired
	private DeviceMapper deviceMapper;
	
	@Override @SuppressWarnings("unchecked")
	protected DeviceMapper getMapper() {
		return deviceMapper;
	}
	
	 @Override
    protected DeviceExample getEntityExampleObject() { return new DeviceExample(); }
}
