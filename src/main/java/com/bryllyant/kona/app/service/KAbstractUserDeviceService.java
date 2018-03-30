/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KDevice;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.entity.KUserDevice;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import com.bryllyant.kona.util.KResultList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractUserDeviceService<
            USER_DEVICE extends KUserDevice,
            USER_DEVICE_EXAMPLE extends KEntityExample,
            MAPPER extends KMyBatisMapper<USER_DEVICE, USER_DEVICE_EXAMPLE>,
            USER extends KUser,
            DEVICE extends KDevice>
        extends KAbstractService<USER_DEVICE,USER_DEVICE_EXAMPLE,MAPPER>
        implements KUserDeviceService<USER_DEVICE, USER, DEVICE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractUserDeviceService.class);



	protected abstract USER_DEVICE getNewObject();

	protected abstract <S extends KDeviceService<DEVICE>> S getDeviceService();



	@Override @Transactional
	public void remove(USER_DEVICE userDevice, boolean removeDeviceIfNotReferenced) {
	    super.remove(userDevice);

	    List<USER_DEVICE> others = fetchByDeviceId(userDevice.getDeviceId());

	    if (others.size() == 0 && removeDeviceIfNotReferenced) {
	        getDeviceService().removeById(userDevice.getDeviceId());
	    }
	}



	@Override
	public List<USER_DEVICE> fetchByUserId(Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}



	@Override
	public List<USER_DEVICE> fetchByDeviceId(Long deviceId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("deviceId", deviceId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}



	@Override
	public USER_DEVICE fetchByUserIdAndDeviceId(Long userId, Long deviceId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		filter.put("deviceId", deviceId);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}



	@Override
	public USER_DEVICE fetchByUserIdAndSlug(Long userId, String slug) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		filter.put("slug", slug);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}



	@Override
	public USER_DEVICE fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}



	@Override @Transactional(readOnly=true)
	public KResultList<USER_DEVICE> fetchByCriteria(Integer startRow, Integer resultSize,
                                                    String[] sortOrder, Map<String, Object> filter, boolean distinct) {

	    List<USER_DEVICE> userDeviceList = super.fetchByCriteria(startRow, resultSize, sortOrder, filter, distinct);

		//KResultList<USER_DEVICE> result = new KResultList<USER_DEVICE>(userDeviceList.size());
		KResultList<USER_DEVICE> result = new KResultList<USER_DEVICE>(userDeviceList.size());

	    for (USER_DEVICE userDevice : userDeviceList) {
	        DEVICE device = getDeviceService().fetchById(userDevice.getDeviceId());

	        USER_DEVICE _userDevice = getNewObject();

	        // first copy device object to new user device object
	        copyBean(device, _userDevice, true);

	        // next copy original UserDevice object to new object to retain id, createdDate, etc.
	        copyBean(userDevice, _userDevice, true);

	        result.add(_userDevice);
	    }

	    return result;
	}



	@Override
	public void validate(USER_DEVICE userDevice) {
		if (userDevice.getCreatedDate() == null) {
			userDevice.setCreatedDate(new Date());
		}

		userDevice.setUpdatedDate(new Date());

		if (userDevice.getSlug() == null && userDevice.getName() != null)  {
			String slug = KInflector.getInstance().slug(userDevice.getName());
			userDevice.setSlug(slug);
		}

		if (userDevice.getSlug() == null) {
			userDevice.setSlug("device-" + uuid());
		}
	}



	@Override @Transactional
	public USER_DEVICE create(Long userId, Long deviceId, String name) {

	    USER_DEVICE userDevice = getNewObject();

	    userDevice.setUserId(userId);

	    userDevice.setDeviceId(deviceId);

	    userDevice.setName(name);

	    return add(userDevice);
	}


	
	@Override @Transactional
	public USER_DEVICE create(USER user, DEVICE device, String name) {

	    USER_DEVICE userDevice = getNewObject();

	    device = getOrCreateDevice(device);

	    userDevice.setUserId(user.getId());
	    userDevice.setDeviceId(device.getId());
	    userDevice.setName(name);

	    userDevice = add(userDevice);

	    USER_DEVICE _userDevice = getNewObject();
	    // first copy device object to new user device object
	    copyBean(device, _userDevice, true);

	    // next copy original UserDevice object to new object to retain id, createdDate, etc.
	    copyBean(userDevice, _userDevice, true);

	    return _userDevice;

	}
	


	@Override @Transactional
	public USER_DEVICE update(USER_DEVICE userDevice, DEVICE device, String name) {
	    DEVICE _device = getDeviceService().fetchById(userDevice.getDeviceId());

	    copyBean(device, _device, true);

	    device = getDeviceService().save(_device);

	    if (name != null) {
	        userDevice.setSlug(null);
	        userDevice.setName(name);
	    }

	    userDevice = update(userDevice);

	    USER_DEVICE _userDevice = getNewObject();

	    // first copy device object to new user device object
	    copyBean(device, _userDevice, true);

	    // next copy original UserDevice object to new object to retain id, createdDate, etc.
	    copyBean(userDevice, _userDevice, true);

	    return _userDevice;
	}



	@Transactional
	private DEVICE getOrCreateDevice(DEVICE device) {
	    // if we have a id then just update this device
	    if (device.getId() != null) {
	        return getDeviceService().save(device);
	    }

	    // check uid
	    if (device.getUid() != null) {
	        DEVICE _device = getDeviceService().fetchByUid(device.getUid());

	        if (_device != null) {
	            copyBean(device, _device, true);

	            return getDeviceService().save(_device);
	        }
	    }

	    // check advertiserId
	    if (device.getAdvertiserId() != null && !device.getAdvertiserId().startsWith("00000000")) {
	        DEVICE _device = getDeviceService().fetchByAdvertiserId(device.getAdvertiserId());

	        if (_device != null) {
	            copyBean(device, _device, true);

	            return getDeviceService().save(_device);
	        }
	    }

	    // check deviceUuid
	    if (device.getDeviceUuid() != null) {
	        DEVICE _device = getDeviceService().fetchByDeviceUuid(device.getDeviceUuid());

	        if (_device != null) {

	            copyBean(device, _device, true);

	            return getDeviceService().save(_device);
	        }
	    }

	    // ok, this is a new device so create a new object
	    return getDeviceService().save(device);
	}
}
