/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KRemoteServiceUserCreds;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractRemoteServiceUserCredsService<REMOTE_SERVICE_USER_CREDS extends KRemoteServiceUserCreds, REMOTE_SERVICE_USER_CREDS_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<REMOTE_SERVICE_USER_CREDS, REMOTE_SERVICE_USER_CREDS_EXAMPLE>>
		extends KAbstractService<REMOTE_SERVICE_USER_CREDS,REMOTE_SERVICE_USER_CREDS_EXAMPLE,MAPPER>
		implements KRemoteServiceUserCredsService<REMOTE_SERVICE_USER_CREDS> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractRemoteServiceUserCredsService.class);


	@Override 
	public void validate(REMOTE_SERVICE_USER_CREDS remoteServiceUserCreds) {
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
    public REMOTE_SERVICE_USER_CREDS fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0,9999, null, filter,  false));
    }

    @Override
    public REMOTE_SERVICE_USER_CREDS fetchByAccountIdAndSlug(Long accountId, String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        filter.put("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0,9999, null, filter,  false));
    }
    

    @Override
    public List<REMOTE_SERVICE_USER_CREDS> fetchByAccountId(Long accountId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        return fetchByCriteria(0,9999, null, filter,  false);
    }
    

    @Override
    public REMOTE_SERVICE_USER_CREDS create(REMOTE_SERVICE_USER_CREDS remoteServiceUserCreds) {
        remoteServiceUserCreds = add(remoteServiceUserCreds);
        return remoteServiceUserCreds;
    }
    

    @Override
    public List<REMOTE_SERVICE_USER_CREDS> fetchByRemoteServiceScreenName(Long remoteServiceId, String screenName) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("remoteServiceId", remoteServiceId);
        filter.put("remoteServiceScreenName", screenName);
        return fetchByCriteria(0,9999, null, filter,  false);
    }
}
