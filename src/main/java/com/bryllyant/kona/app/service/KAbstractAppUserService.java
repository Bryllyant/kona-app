/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAppUser;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KResultList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

public abstract class KAbstractAppUserService<APP_USER extends KAppUser, APP_USER_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<APP_USER, APP_USER_EXAMPLE>> extends KAbstractService<APP_USER,APP_USER_EXAMPLE,MAPPER>
implements KAppUserService<APP_USER> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractAppUserService.class);

	protected abstract APP_USER getNewObject();
	protected abstract void updateCoords(Long appUserId);

	@Override @Transactional
	public APP_USER add(APP_USER appUser) {
		appUser = super.add(appUser);
		updateCoords(appUser.getId());
		return appUser;
	}

	@Override @Transactional
	public APP_USER update(APP_USER appUser) {
		appUser = super.update(appUser);
		updateCoords(appUser.getId());
		return appUser;
	}

	@Override
	public void validate(APP_USER appUser) {
		if (appUser.getCreatedDate() == null) {
			appUser.setCreatedDate(new Date());
		}

		if (appUser.getUid() == null) {
		    appUser.setUid(uuid());
        }


        // if app doesn't provide refUserId then set it to our Uid for this user
        if (appUser.getRefUserId() == null) {
		    appUser.setRefUserId(appUser.getUid());
        }

		appUser.setUpdatedDate(new Date());
	}

    public APP_USER fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

	@Override
	public APP_USER fetchByAppIdAndUserId(Long appId, Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		filter.put("userId", userId);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

    @Override
    public APP_USER fetchByAppIdAndRefUserId(Long appId, String refUserId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        filter.put("refUserId", refUserId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

	@Override
	public KResultList<APP_USER> fetchByAppId(Long appId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	@Override
	public KResultList<APP_USER> fetchByUserId(Long userId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
		return fetchByCriteria(0, 99999, null, filter, false);
	}

	@Override
	public APP_USER create(Long appId, Long userId, Long tokenId, String refUserId) {
		APP_USER appUser = getNewObject();
		appUser.setAppId(appId);
		appUser.setUserId(userId);
		appUser.setTokenId(tokenId);
		appUser.setRefUserId(refUserId);
		appUser.setCreatedDate(new Date());
		appUser.setEnabled(true);
		return add(appUser);
	}
}

