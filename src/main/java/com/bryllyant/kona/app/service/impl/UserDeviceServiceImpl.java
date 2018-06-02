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
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.UserDeviceService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import com.bryllyant.kona.util.KResultList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(UserDeviceService.SERVICE_PATH)
public class UserDeviceServiceImpl 
		extends KAbstractService<UserDevice,UserDeviceExample,UserDeviceMapper>
		implements UserDeviceService {
	
	private static Logger logger = LoggerFactory.getLogger(UserDeviceServiceImpl.class);

	@Autowired
	private UserDeviceMapper userDeviceMapper;

	@Autowired
	private DeviceService deviceService;
	
	@Override @SuppressWarnings("unchecked")
	protected UserDeviceMapper getMapper() {
		return userDeviceMapper;
	}
    
    @Override @Transactional
    public void remove(UserDevice userDevice, boolean removeDeviceIfNotReferenced) {
        super.remove(userDevice);

        List<UserDevice> others = fetchByDeviceId(userDevice.getDeviceId());

        if (others.size() == 0 && removeDeviceIfNotReferenced) {
            deviceService.removeById(userDevice.getDeviceId());
        }
    }

    @Override
    public List<UserDevice> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<UserDevice> fetchByDeviceId(Long deviceId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("deviceId", deviceId);
        return fetchByCriteria(filter);
    }

    @Override
    public UserDevice fetchByUserIdAndDeviceId(Long userId, Long deviceId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("deviceId", deviceId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public UserDevice fetchByUserIdAndSlug(Long userId, String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public UserDevice fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override @Transactional(readOnly=true)
    public KResultList<UserDevice> fetchByCriteria(Integer startRow, Integer resultSize,
                                                   String[] sortOrder, Map<String, Object> filter, boolean distinct) {

        List<UserDevice> userDeviceList = super.fetchByCriteria(startRow, resultSize, sortOrder, filter, distinct);

        //KResultList<UserDevice> result = new KResultList<UserDevice>(userDeviceList.size());
        KResultList<UserDevice> result = new KResultList<UserDevice>(userDeviceList.size());

        for (UserDevice userDevice : userDeviceList) {
            Device device = deviceService.fetchById(userDevice.getDeviceId());

            UserDevice _userDevice = new UserDevice();

            // first copy device object to new user device object
            copyBean(device, _userDevice, true);

            // next copy original UserDevice object to new object to retain id, createdDate, etc.
            copyBean(userDevice, _userDevice, true);

            result.add(_userDevice);
        }

        return result;
    }

    @Override
    public void validate(UserDevice userDevice) {
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
    public UserDevice create(Long userId, Long deviceId, String name) {

        UserDevice userDevice = new UserDevice();
        userDevice.setUserId(userId);
        userDevice.setDeviceId(deviceId);
        userDevice.setName(name);

        return add(userDevice);
    }



    @Override @Transactional
    public UserDevice create(User user, Device device, String name) {

        UserDevice userDevice = new UserDevice();

        device = getOrCreateDevice(device);

        userDevice.setUserId(user.getId());
        userDevice.setDeviceId(device.getId());
        userDevice.setName(name);

        userDevice = add(userDevice);

        UserDevice _userDevice = new UserDevice();
        // first copy device object to new user device object
        copyBean(device, _userDevice, true);

        // next copy original UserDevice object to new object to retain id, createdDate, etc.
        copyBean(userDevice, _userDevice, true);

        return _userDevice;

    }

    @Override @Transactional
    public UserDevice update(UserDevice userDevice, Device device, String name) {
        Device _device = deviceService.fetchById(userDevice.getDeviceId());

        copyBean(device, _device, true);

        device = deviceService.save(_device);

        if (name != null) {
            userDevice.setSlug(null);
            userDevice.setName(name);
        }

        userDevice = update(userDevice);

        UserDevice _userDevice = new UserDevice();

        // first copy device object to new user device object
        copyBean(device, _userDevice, true);

        // next copy original UserDevice object to new object to retain id, createdDate, etc.
        copyBean(userDevice, _userDevice, true);

        return _userDevice;
    }

    @Transactional
    private Device getOrCreateDevice(Device device) {
        // if we have a id then just update this device
        if (device.getId() != null) {
            return deviceService.save(device);
        }

        // check uid
        if (device.getUid() != null) {
            Device _device = deviceService.fetchByUid(device.getUid());

            if (_device != null) {
                copyBean(device, _device, true);

                return deviceService.save(_device);
            }
        }

        // check advertiserId
        if (device.getAdvertiserId() != null && !device.getAdvertiserId().startsWith("00000000")) {
            Device _device = deviceService.fetchByAdvertiserId(device.getAdvertiserId());

            if (_device != null) {
                copyBean(device, _device, true);

                return deviceService.save(_device);
            }
        }

        // check deviceUuid
        if (device.getDeviceUuid() != null) {
            Device _device = deviceService.fetchByDeviceUuid(device.getDeviceUuid());

            if (_device != null) {

                copyBean(device, _device, true);

                return deviceService.save(_device);
            }
        }

        // ok, this is a new device so create a new object
        return deviceService.save(device);
    }

}
