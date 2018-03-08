/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.remote.service.KService;

public interface AppService extends KService, KAppService<App> {
	String SERVICE_PATH = "rpc/AppService";
	
}
