/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.remote.service.KService;

public interface LandingPageService extends KService, KLandingPageService<LandingPage> {
	public static final String SERVICE_PATH = "rpc/LandingPageService";
	
}
