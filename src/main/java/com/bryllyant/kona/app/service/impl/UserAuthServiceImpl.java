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
	
	


	@Override @SuppressWarnings("unchecked")
	protected UserAuthMapper getDao() {
		return userAuthDao;
	}
    

	
	@Override @SuppressWarnings("unchecked")
	protected UserService getUserService() {
		return userService;
	}



	@Override
	protected UserAuth getNewObject() {
		return new UserAuth();
	}


    
	 @Override
    protected UserAuthExample getEntityExampleObject() { return new UserAuthExample(); }



	@Override
	protected void sendPasswordResetEmail(User user, String password) {
		// TODO Auto-generated method stub
	}



}
