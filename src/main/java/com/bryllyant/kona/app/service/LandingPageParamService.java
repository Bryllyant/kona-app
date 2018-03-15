/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.entity.LandingPageParam;
import com.bryllyant.kona.remote.service.KService;

public interface LandingPageParamService extends KService, KLandingPageParamService<LandingPageParam,LandingPage,File> {
	String SERVICE_PATH = "rpc/LandingPageParamService";
	
}
