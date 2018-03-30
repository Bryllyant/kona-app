/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAppCreds;
import com.bryllyant.kona.app.entity.KToken;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractAppCredsService<APP_CREDS extends KAppCreds, APP_CREDS_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<APP_CREDS, APP_CREDS_EXAMPLE>,TOKEN extends KToken>
		extends KAbstractService<APP_CREDS,APP_CREDS_EXAMPLE,MAPPER>
		implements KAppCredsService<APP_CREDS> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAppCredsService.class);


	
	protected abstract <S extends KTokenService<TOKEN>> S getTokenService();
	

	
	@Override
	public List<APP_CREDS> fetchByAppId(Long appId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        return fetchByCriteria(0, 99999, null, filter, false);
	}
	

	
	@Override
	public APP_CREDS fetchByClientId(String clientId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("clientId", clientId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}


	
	@Override
    public void expireAppTokens(Long appId) {
        List<APP_CREDS> credsList = fetchByAppId(appId);
        for (APP_CREDS creds : credsList) {
        	getTokenService().expireByClientId(creds.getClientId());
        }
    }
	

	
	@Override
	public void validate(APP_CREDS appCreds) {
    	if (appCreds.getCreatedDate() == null) {
			appCreds.setCreatedDate(new Date());
		}
    	
    	appCreds.setUpdatedDate(new Date());
	}
}
