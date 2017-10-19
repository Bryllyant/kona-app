/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.remote.service.KService;

public interface PositionService extends KService, KPositionService<Position> {
	public static final String SERVICE_PATH = "rpc/PositionService";
	
}
