/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AppUserMapper;
import com.bryllyant.kona.app.entity.ApiLog;
import com.bryllyant.kona.app.entity.AppUser;
import com.bryllyant.kona.app.entity.AppUserExample;
import com.bryllyant.kona.app.service.AppUserService;
import com.bryllyant.kona.app.service.KAbstractAppUserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(AppUserService.SERVICE_PATH)
public class AppUserServiceImpl 
		extends KAbstractAppUserService<AppUser, AppUserExample, AppUserMapper>
		implements AppUserService {
	
	private static Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class);

	@Autowired
	private AppUserMapper appUserMapper;
    

	@Override @SuppressWarnings("unchecked")
	protected AppUserMapper getMapper() {
		return appUserMapper;
	}
    

	 @Override
    protected AppUserExample getEntityExampleObject() { return new AppUserExample(); }

	

	@Override
	protected AppUser getNewObject() {
		return new AppUser();
	}

	@Override
	protected void updateCoords(Long apiLogId) {
		getMapper().updateCoords(apiLogId);
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
