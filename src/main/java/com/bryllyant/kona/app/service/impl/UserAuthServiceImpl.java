/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.UserAuthMapper;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserAuth;
import com.bryllyant.kona.app.entity.UserAuthExample;
import com.bryllyant.kona.app.service.KAbstractUserAuthService;
import com.bryllyant.kona.app.service.UserAuthService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(UserAuthService.SERVICE_PATH)
public class UserAuthServiceImpl 
		extends KAbstractUserAuthService<UserAuth,UserAuthExample,User> 
		implements UserAuthService {
	
	private static Logger logger = LoggerFactory.getLogger(UserAuthServiceImpl.class);

	@Autowired
	private UserAuthMapper userAuthDao;
	
	@Autowired
	private UserService userService;
	
	
// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected UserAuthMapper getDao() {
		return userAuthDao;
	}
    
	// ----------------------------------------------------------------------------
	
	@Override @SuppressWarnings("unchecked")
	protected UserService getUserService() {
		return userService;
	}

	// ----------------------------------------------------------------------------

	@Override
	protected UserAuth getNewObject() {
		return new UserAuth();
	}

	// ----------------------------------------------------------------------------
    
	@Override
	protected UserAuthExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		UserAuthExample example = new UserAuthExample();

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


	@Override
	protected void sendPasswordResetEmail(User user, String password) {
		// TODO Auto-generated method stub
	}

	// ----------------------------------------------------------------------------

}
