/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AppUser;
import com.bryllyant.kona.remote.service.KService;

public interface AppUserService extends KService, KAppUserService<AppUser> {
	public static final String SERVICE_PATH = "rpc/AppUserService";
	
}
