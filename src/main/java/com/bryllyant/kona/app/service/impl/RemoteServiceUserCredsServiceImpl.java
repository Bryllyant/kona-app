/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.RemoteServiceUserCredsMapper;
import com.bryllyant.kona.app.entity.RemoteServiceUserCreds;
import com.bryllyant.kona.app.entity.RemoteServiceUserCredsExample;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.RemoteServiceUserCredsService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(RemoteServiceUserCredsService.SERVICE_PATH)
public class RemoteServiceUserCredsServiceImpl 
		extends KAbstractService<RemoteServiceUserCreds, RemoteServiceUserCredsExample, RemoteServiceUserCredsMapper>
		implements RemoteServiceUserCredsService {
	
	private static Logger logger = LoggerFactory.getLogger(RemoteServiceUserCredsServiceImpl.class);

	@Autowired
	private RemoteServiceUserCredsMapper remoteServiceUserCredsMapper;
    

	@Override @SuppressWarnings("unchecked")
	protected RemoteServiceUserCredsMapper getMapper() {
		return remoteServiceUserCredsMapper;
	}
    

    @Override
    public void validate(RemoteServiceUserCreds remoteServiceUserCreds) {
        if (remoteServiceUserCreds.getCreatedDate() == null) {
            remoteServiceUserCreds.setCreatedDate(new Date());
        }

        remoteServiceUserCreds.setUpdatedDate(new Date());

        if (remoteServiceUserCreds.getUid() == null) {
            remoteServiceUserCreds.setUid(uuid());
        }

        if (remoteServiceUserCreds.getName() != null)  {
            String slug = KInflector.getInstance().slug(remoteServiceUserCreds.getName());
            remoteServiceUserCreds.setSlug(slug);
        }
    }



    @Override
    public RemoteServiceUserCreds fetchByAccountIdAndSlug(Long accountId, String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        filter.put("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0,9999, null, filter,  false));
    }


    @Override
    public List<RemoteServiceUserCreds> fetchByAccountId(Long accountId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        return fetchByCriteria(0,9999, null, filter,  false);
    }


    @Override
    public RemoteServiceUserCreds create(RemoteServiceUserCreds remoteServiceUserCreds) {
        remoteServiceUserCreds = add(remoteServiceUserCreds);
        return remoteServiceUserCreds;
    }


    @Override
    public List<RemoteServiceUserCreds> fetchByRemoteServiceScreenName(Long remoteServiceId, String screenName) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("remoteServiceId", remoteServiceId);
        filter.put("remoteServiceScreenName", screenName);
        return fetchByCriteria(0,9999, null, filter,  false);
    }
}
