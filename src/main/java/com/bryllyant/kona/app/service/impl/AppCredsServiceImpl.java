/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AppCredsMapper;
import com.bryllyant.kona.app.entity.AppCreds;
import com.bryllyant.kona.app.entity.AppCredsExample;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.service.AppCredsService;
import com.bryllyant.kona.app.service.KAbstractAppCredsService;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(AppCredsService.SERVICE_PATH)
public class AppCredsServiceImpl 
		extends KAbstractAppCredsService<AppCreds, AppCredsExample, AppCredsMapper,Token>
		implements AppCredsService {
	
	private static Logger logger = LoggerFactory.getLogger(AppCredsServiceImpl.class);

	@Autowired
	private AppCredsMapper appCredsMapper;
    
	@Autowired
    private TokenService tokenService;
	


	@Override @SuppressWarnings("unchecked")
	protected AppCredsMapper getMapper() {
		return appCredsMapper;
	}
    
	@Override @SuppressWarnings("unchecked")
	protected TokenService getTokenService() {
        return tokenService;
	}


	
	 @Override
    protected AppCredsExample getEntityExampleObject() { return new AppCredsExample(); }




}
