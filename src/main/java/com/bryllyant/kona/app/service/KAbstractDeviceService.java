/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KDevice;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

public abstract class KAbstractDeviceService<DEVICE extends KDevice, DEVICE_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<DEVICE, DEVICE_EXAMPLE>> extends KAbstractService<DEVICE,DEVICE_EXAMPLE,MAPPER>
		implements KDeviceService<DEVICE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractDeviceService.class);



	@Override
	public void validate(DEVICE device) {
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
    public DEVICE fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
    


    @Override
    public DEVICE fetchByAdvertiserId(String advertiserId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("advertiserId", advertiserId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public DEVICE fetchByDeviceUuid(String deviceUuid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("deviceUuid", deviceUuid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


 
}
