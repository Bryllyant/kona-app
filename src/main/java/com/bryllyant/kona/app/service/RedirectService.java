/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Redirect;
import com.bryllyant.kona.remote.service.KService;

public interface RedirectService extends KService, KRedirectService<Redirect> {
	public static final String SERVICE_PATH = "rpc/RedirectService";
	
}
