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
		extends KAbstractAppCredsService<AppCreds,AppCredsExample,Token> 
		implements AppCredsService {
	
	private static Logger logger = LoggerFactory.getLogger(AppCredsServiceImpl.class);

	@Autowired
	private AppCredsMapper appCredsDao;
    
	@Autowired
    private TokenService tokenService;
	
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected AppCredsMapper getDao() {
		return appCredsDao;
	}
    
	@Override @SuppressWarnings("unchecked")
	protected TokenService getTokenService() {
        return tokenService;
	}

	// ----------------------------------------------------------------------------
	
	@Override
	protected AppCredsExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		AppCredsExample example = new AppCredsExample();

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
