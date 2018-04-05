/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.ApiLog;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.Date;
import java.util.List;

public interface ApiLogService extends KService, KEntityService<ApiLog> {
	String SERVICE_PATH = "rpc/ApiLogService";

    List<ApiLog> fetchByOwnerId(Long ownerId);

    List<ApiLog> fetchByUserId(Long userId);

    List<ApiLog> fetchByAppId(Long appId);

    List<ApiLog> fetchByClientId(String clientId);

    List<ApiLog> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    );
	
}
