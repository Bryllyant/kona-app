/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AppLegal;
import com.bryllyant.kona.remote.service.KService;

public interface AppLegalService extends KService, KAppLegalService<AppLegal> {
	public static final String SERVICE_PATH = "rpc/AppLegalService";
	
}
