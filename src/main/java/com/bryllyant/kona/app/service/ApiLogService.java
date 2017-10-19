/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.ApiLog;
import com.bryllyant.kona.remote.service.KService;

public interface ApiLogService extends KService, KApiLogService<ApiLog> {
	public static final String SERVICE_PATH = "rpc/ApiLogService";
	
}
