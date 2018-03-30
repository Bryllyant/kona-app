/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPlacePlan;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;


public interface KPlacePlanService<PLACE_PLAN extends KPlacePlan> extends KService, KEntityService<PLACE_PLAN> {

    PLACE_PLAN fetchByUid(String uid);

    PLACE_PLAN fetchByPlaceIdAndSlug(Long placeId, String slug);

    List<PLACE_PLAN> fetchByPlaceId(Long placeId);

}
