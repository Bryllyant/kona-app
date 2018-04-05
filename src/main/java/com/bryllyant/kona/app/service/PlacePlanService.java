/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.PlacePlan;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;


public interface PlacePlanService extends KService, KEntityService<PlacePlan> {
    String SERVICE_PATH = "rpc/PlacePlanService";

    PlacePlan fetchByPlaceIdAndSlug(Long placeId, String slug);

    List<PlacePlan> fetchByPlaceId(Long placeId);
}
