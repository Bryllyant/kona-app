/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.util.Date;
import java.util.List;

import com.bryllyant.kona.app.entity.KApiLog;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;


public interface KApiLogService<API_LOG extends KApiLog> extends KService, KEntityService<API_LOG> {
    public API_LOG fetchByUid(String uid);

    public List<API_LOG> fetchByOwnerId(Long ownerId);

    public List<API_LOG> fetchByUserId(Long userId);

    public List<API_LOG> fetchByAppId(Long appId);

    public List<API_LOG> fetchByClientId(String clientId);
    
    public List<API_LOG> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    );
}
