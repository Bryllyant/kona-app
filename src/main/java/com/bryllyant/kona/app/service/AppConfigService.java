/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AppConfig;
import com.bryllyant.kona.remote.service.KService;

public interface AppConfigService extends KService, KAppConfigService<AppConfig> {
	public static final String SERVICE_PATH = "rpc/AppConfigService";
	
}
