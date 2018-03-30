/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.util.Date;
import java.util.List;

import com.bryllyant.kona.app.entity.KAppUser;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

/**
 * The client side stub for the RPC service.
 */
public interface KAppUserService<APP_USER extends KAppUser> extends KService, KEntityService<APP_USER> {

	public APP_USER create(Long appId, Long userId, Long tokenId, String refUserId);
	
	public APP_USER fetchByAppIdAndUserId(Long appId, Long userId);

	public APP_USER fetchByAppIdAndRefUserId(Long appId, String refUserId);

	public List<APP_USER> fetchByAppId(Long appId);
	
	public List<APP_USER> fetchByUserId(Long userId);

	public List<APP_USER> fetchProximate(
			Double latitude,
			Double longitude,
			Double radius,
			Date startDate,
			Date endDate,
			List<Long> objectIdList
	);
}
