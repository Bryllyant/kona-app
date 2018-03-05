/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.entity.AppCreds;
import com.bryllyant.kona.app.entity.KTokenType;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserAuth;
import com.bryllyant.kona.app.service.AppCredsService;
import com.bryllyant.kona.app.service.AuthService;
import com.bryllyant.kona.app.service.KAbstractAuthService;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.app.service.UserAuthService;
import com.bryllyant.kona.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(AuthService.SERVICE_PATH)
public class AuthServiceImpl 
		extends KAbstractAuthService<User,UserAuth,Token,AppCreds> 
		implements AuthService {
	
	private static Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAuthService userAuthService;
	
	@Autowired
	private AppCredsService appCredsService;
	

	@Override
	public Long getLoginTokenTypeId() {
		return KTokenType.BEARER.getId();
	}
	

	@Override @SuppressWarnings("unchecked")
	protected TokenService getTokenService() {
		return tokenService;
	}
	

	@Override @SuppressWarnings("unchecked")
	protected UserService getUserService() {
		return userService;
	}


	@Override @SuppressWarnings("unchecked")
	protected UserAuthService getUserAuthService() {
		return userAuthService;
	}
	

	@Override @SuppressWarnings("unchecked")
	protected AppCredsService getAppCredsService() {
		return appCredsService;
	}


	@Override
	protected Token getNewObject() {
		return new Token();
	}

}
