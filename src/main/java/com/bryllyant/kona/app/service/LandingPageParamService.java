/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.LandingPageParam;
import com.bryllyant.kona.remote.service.KService;

public interface LandingPageParamService extends KService, KLandingPageParamService<LandingPageParam> {
	String SERVICE_PATH = "rpc/LandingPageParamService";
	
}
