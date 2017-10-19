/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AppCreds;
import com.bryllyant.kona.remote.service.KService;

public interface AppCredsService extends KService, KAppCredsService<AppCreds> {
	public static final String SERVICE_PATH = "rpc/AppCredsService";
	
}
