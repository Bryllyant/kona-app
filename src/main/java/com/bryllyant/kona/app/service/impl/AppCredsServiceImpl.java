/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AppCredsMapper;
import com.bryllyant.kona.app.entity.AppCreds;
import com.bryllyant.kona.app.entity.AppCredsExample;
import com.bryllyant.kona.app.service.AppCredsService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(AppCredsService.SERVICE_PATH)
public class AppCredsServiceImpl 
		extends KAbstractService<AppCreds, AppCredsExample, AppCredsMapper>
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
    

	 @Override
    protected AppCredsExample getEntityExampleObject() { return new AppCredsExample(); }


    @Override
    public List<AppCreds> fetchByAppId(Long appId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        return fetchByCriteria(filter);
    }


    @Override
    public AppCreds fetchByClientId(String clientId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("clientId", clientId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public void expireAppTokens(Long appId) {
        List<AppCreds> credsList = fetchByAppId(appId);
        for (AppCreds creds : credsList) {
            tokenService.expireByClientId(creds.getClientId());
        }
    }


    @Override
    public void validate(AppCreds appCreds) {
        if (appCreds.getCreatedDate() == null) {
            appCreds.setCreatedDate(new Date());
        }

        appCreds.setUpdatedDate(new Date());

        if (appCreds.getUid() == null) {
            appCreds.setUid(uuid());
        }
    }
}
