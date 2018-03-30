/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KRegistration;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

public abstract class KAbstractRegistrationService<REGISTRATION extends KRegistration, REGISTRATION_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<REGISTRATION, REGISTRATION_EXAMPLE>, USER extends KUser>
		extends KAbstractService<REGISTRATION,REGISTRATION_EXAMPLE,MAPPER>
		implements KRegistrationService<REGISTRATION, USER> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractRegistrationService.class);
	

	protected abstract REGISTRATION getNewObject();
	
    protected abstract void updateCoords(Long tokenId); 


    @Override @Transactional
    public REGISTRATION add(REGISTRATION registration) {
        registration = super.add(registration);
        updateCoords(registration.getId());
        return registration;
    }
    

    @Override @Transactional
    public REGISTRATION update(REGISTRATION registration) {
        registration = super.update(registration);
        updateCoords(registration.getId());
        return registration;
    }

    @Override
	public REGISTRATION createRegistration(USER user, KServiceClient client, Long signupTime) {

		REGISTRATION reg = getNewObject();
        
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
	public void validate(REGISTRATION registration) {
    	if (registration.getCreatedDate() == null) {
			registration.setCreatedDate(new Date());
		}

		if (registration.getUid() == null) {
			registration.setUid(uuid());
		}

    	registration.setUpdatedDate(new Date());
	}
	

	@Override
	public REGISTRATION fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

	@Override
	public REGISTRATION fetchByUserId(Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}
}
