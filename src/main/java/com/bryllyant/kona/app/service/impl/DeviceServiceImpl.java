/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.DeviceMapper;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.DeviceExample;
import com.bryllyant.kona.app.service.DeviceService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service(DeviceService.SERVICE_PATH)
public class DeviceServiceImpl 
		extends KAbstractService<Device, DeviceExample, DeviceMapper>
		implements DeviceService {
	
	private static Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

	@Autowired
	private DeviceMapper deviceMapper;
	
	@Override @SuppressWarnings("unchecked")
	protected DeviceMapper getMapper() {
		return deviceMapper;
	}
	

    @Override
    public void validate(Device device) {
        if (device.getCreatedDate() == null) {
            device.setCreatedDate(new Date());
        }

        if (device.getUid() == null) {
            device.setUid(uuid());
        }

        device.setUpdatedDate(new Date());

        if (device.getAdvertiserId() != null && device.getAdvertiserId().startsWith("00000000")) {
            device.setAdvertiserId(null);
        }
    }


    @Override
    public Device fetchByAdvertiserId(String advertiserId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("advertiserId", advertiserId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public Device fetchByDeviceUuid(String deviceUuid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("deviceUuid", deviceUuid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

}
