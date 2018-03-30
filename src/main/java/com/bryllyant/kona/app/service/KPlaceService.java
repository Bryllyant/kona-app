/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPlace;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.Date;
import java.util.List;


public interface KPlaceService<PLACE extends KPlace> extends KService, KEntityService<PLACE> {

    PLACE fetchByUid(String uid);

    PLACE fetchBySlug(String slug);

    PLACE fetchByRefPlaceId(String refPlaceId);

    List<PLACE> fetchByOwnerId(Long ownerId);

    List<PLACE> fetchByGroupId(Long groupId);

    List<PLACE> fetchProximate(
            Double latitude, 
            Double longitude, 
            Double radius, 
            Date startDate, 
            Date endDate,
            List<Long> objectIdList
    );

}
