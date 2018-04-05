/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AppUser;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.Date;
import java.util.List;

public interface AppUserService extends KService, KEntityService<AppUser> {
	String SERVICE_PATH = "rpc/AppUserService";

    AppUser create(Long appId, Long userId, Long tokenId, String refUserId);

    AppUser fetchByAppIdAndUserId(Long appId, Long userId);

    AppUser fetchByAppIdAndRefUserId(Long appId, String refUserId);

    List<AppUser> fetchByAppId(Long appId);

    List<AppUser> fetchByUserId(Long userId);

    List<AppUser> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    );
	
}
