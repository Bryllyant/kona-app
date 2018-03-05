/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.remote.service.KService;

public interface LandingPageTemplateService extends KService, KLandingPageTemplateService<LandingPageTemplate> {
	String SERVICE_PATH = "rpc/LandingPageTemplateService";
	
}
