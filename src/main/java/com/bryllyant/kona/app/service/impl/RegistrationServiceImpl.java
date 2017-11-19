/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.RegistrationMapper;
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

import java.util.Map;

@Service(RegistrationService.SERVICE_PATH)
public class RegistrationServiceImpl 
		extends KAbstractRegistrationService<Registration,RegistrationExample,User> implements RegistrationService {
	private static Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	@Autowired
	private RegistrationMapper registrationDao;
	
	// ----------------------------------------------------------------------------
	
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

	// ----------------------------------------------------------------------------
	
	@Override
	protected RegistrationExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		RegistrationExample example = new RegistrationExample();

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
