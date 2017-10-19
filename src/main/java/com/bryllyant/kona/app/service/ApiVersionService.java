/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.ApiVersion;
import com.bryllyant.kona.remote.service.KService;

public interface ApiVersionService extends KService, KApiVersionService<ApiVersion> {
	public static final String SERVICE_PATH = "rpc/ApiVersionService";
	
}
