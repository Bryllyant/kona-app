/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.LandingPageTemplateParam;
import com.bryllyant.kona.remote.service.KService;

public interface LandingPageTemplateParamService extends KService, KLandingPageTemplateParamService<LandingPageTemplateParam> {
	public static final String SERVICE_PATH = "rpc/LandingPageTemplateParamService";
	
}
