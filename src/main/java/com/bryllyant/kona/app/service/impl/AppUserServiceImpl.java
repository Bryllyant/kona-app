/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AppUserMapper;
import com.bryllyant.kona.app.entity.AppUser;
import com.bryllyant.kona.app.entity.AppUserExample;
import com.bryllyant.kona.app.service.AppUserService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KResultList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(AppUserService.SERVICE_PATH)
public class AppUserServiceImpl 
		extends KAbstractService<AppUser, AppUserExample, AppUserMapper>
		implements AppUserService {
	
	private static Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);

	@Autowired
	private AppUserMapper appUserMapper;
    

	@Override @SuppressWarnings("unchecked")
	protected AppUserMapper getMapper() {
		return appUserMapper;
	}
    

	protected void updateCoords(Long apiLogId) {
		getMapper().updateCoords(apiLogId);
	}

    @Override
    public void validate(AppUser appUser) {
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

    @Override @Transactional
    public AppUser add(AppUser appUser) {
        appUser = super.add(appUser);
        updateCoords(appUser.getId());
        return appUser;
    }

    @Override @Transactional
    public AppUser update(AppUser appUser) {
        appUser = super.update(appUser);
        updateCoords(appUser.getId());
        return appUser;
    }



    public AppUser fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public AppUser fetchByAppIdAndUserId(Long appId, Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        filter.put("userId", userId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public AppUser fetchByAppIdAndRefUserId(Long appId, String refUserId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        filter.put("refUserId", refUserId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public KResultList<AppUser> fetchByAppId(Long appId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("appId", appId);
        return fetchByCriteria(filter);
    }

    @Override
    public KResultList<AppUser> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(filter);
    }

    @Override
    public AppUser create(Long appId, Long userId, Long tokenId, String refUserId) {
        AppUser appUser = new AppUser();

        appUser.setAppId(appId);
        appUser.setUserId(userId);
        appUser.setTokenId(tokenId);
        appUser.setRefUserId(refUserId);
        appUser.setCreatedDate(new Date());
        appUser.setEnabled(true);

        return add(appUser);
    }

	@Override
	public List<AppUser> fetchProximate(
			Double latitude,
			Double longitude,
			Double radius,
			Date startDate,
			Date endDate,
			List<Long> objectIdList
	) {
		return getMapper().selectProximate(latitude, longitude, radius, startDate, endDate, objectIdList);
	}
}
