/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.RegistrationMapper;
import com.bryllyant.kona.app.entity.Registration;
import com.bryllyant.kona.app.entity.RegistrationExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.RegistrationService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(RegistrationService.SERVICE_PATH)
public class RegistrationServiceImpl 
		extends KAbstractService<Registration, RegistrationExample, RegistrationMapper>
        implements RegistrationService {

	private static Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	@Autowired
	private RegistrationMapper registrationMapper;
	
	@Override @SuppressWarnings("unchecked")
	protected RegistrationMapper getMapper() {
		return registrationMapper;
	}


    protected void updateCoords(Long registrationId) {
        getMapper().updateCoords(registrationId);
    }


	@Override
	public List<Registration> fetchProximate(
			Double latitude,
			Double longitude,
			Double radius,
			Date startDate,
			Date endDate,
			List<Long> objectIdList
	) {
		return getMapper().selectProximate(latitude, longitude, radius, startDate, endDate, objectIdList);
	}


    @Override @Transactional
    public Registration add(Registration registration) {
        registration = super.add(registration);
        updateCoords(registration.getId());
        return registration;
    }


    @Override @Transactional
    public Registration update(Registration registration) {
        registration = super.update(registration);
        updateCoords(registration.getId());
        return registration;
    }

    @Override
    public Registration createRegistration(User user, KServiceClient client, Long signupTime) {

        Registration reg = new Registration();

        reg.setAppId(client.getAppId());
        reg.setUserId(user.getId());
        reg.setDeviceId(client.getDeviceId());
        reg.setAccountId(user.getAccountId());
        reg.setUsername(user.getUsername());
        reg.setHostname(client.getHostname());
        reg.setUserAgent(client.getUserAgent());
        reg.setAppClientId(client.getAppClientId());
        reg.setSignupTime(signupTime);
        reg.setRegisteredDate(new Date());
        reg.setCreatedDate(new Date());

        return add(reg);
    }

    @Override
    public void validate(Registration registration) {
        if (registration.getCreatedDate() == null) {
            registration.setCreatedDate(new Date());
        }

        if (registration.getUid() == null) {
            registration.setUid(uuid());
        }

        registration.setUpdatedDate(new Date());
    }


    @Override
    public Registration fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
}
