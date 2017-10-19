/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Place;
import com.bryllyant.kona.remote.service.KService;

public interface PlaceService extends KService, KPlaceService<Place> {
	public static final String SERVICE_PATH = "rpc/PlaceService";

}
