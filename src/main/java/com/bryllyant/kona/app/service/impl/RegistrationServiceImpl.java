/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.RegistrationMapper;
import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.app.entity.Registration;
import com.bryllyant.kona.app.entity.RegistrationExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.KAbstractRegistrationService;
import com.bryllyant.kona.app.service.RegistrationService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(RegistrationService.SERVICE_PATH)
public class RegistrationServiceImpl 
		extends KAbstractRegistrationService<Registration,RegistrationExample,User> implements RegistrationService {
	private static Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	@Autowired
	private RegistrationMapper registrationDao;
	

	@Override
	protected Registration getNewObject() {
		return new Registration();
	}

	@Override @SuppressWarnings("unchecked")
	protected RegistrationMapper getDao() {
		return registrationDao;
	}


    
    @Override 
    protected void updateCoords(Long registrationId) {
        getDao().updateCoords(registrationId);
    }


	 @Override
    protected RegistrationExample getEntityExampleObject() { return new RegistrationExample(); }

	
	@Override
	public List<Registration> fetchProximate(
			Double latitude,
			Double longitude,
			Double radius,
			Date startDate,
			Date endDate,
			List<Long> objectIdList
	) {
		return getDao().selectProximate(latitude, longitude, radius, startDate, endDate, objectIdList);
	}
}
