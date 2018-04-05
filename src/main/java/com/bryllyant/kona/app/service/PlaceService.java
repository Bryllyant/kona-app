/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Place;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.Date;
import java.util.List;

public interface PlaceService extends KService, KEntityService<Place> {
	String SERVICE_PATH = "rpc/PlaceService";

    Place fetchBySlug(String slug);

    Place fetchByRefPlaceId(String refPlaceId);

    List<Place> fetchByOwnerId(Long ownerId);

    List<Place> fetchByGroupId(Long groupId);

    List<Place> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    );
}
