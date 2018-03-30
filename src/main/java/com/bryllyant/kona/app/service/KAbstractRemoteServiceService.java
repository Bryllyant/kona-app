/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KRemoteService;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

public abstract class KAbstractRemoteServiceService<REMOTE_SERVICE extends KRemoteService, REMOTE_SERVICE_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<REMOTE_SERVICE, REMOTE_SERVICE_EXAMPLE>> extends KAbstractService<REMOTE_SERVICE,REMOTE_SERVICE_EXAMPLE,MAPPER>
		implements KRemoteServiceService<REMOTE_SERVICE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractRemoteServiceService.class);



	@Override 
	public void validate(REMOTE_SERVICE remoteService) {
		if (remoteService.getCreatedDate() == null) {
			remoteService.setCreatedDate(new Date());
		}

		remoteService.setUpdatedDate(new Date());

		if (remoteService.getName() != null) {
			String slug = KInflector.getInstance().slug(remoteService.getName());
			remoteService.setSlug(slug);
		}

		if (remoteService.getUid() == null) {
			remoteService.setUid(uuid());
		}
	}

    @Override
    public REMOTE_SERVICE fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0,9999, null, filter,  false));
    }

	@Override
    public REMOTE_SERVICE fetchBySlug(String slug) {
        slug = slug.trim().toLowerCase();
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0,9999, null, filter,  false));
    }


	
}
