/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.service.GeocodingService;
import com.bryllyant.kona.app.service.KAbstractGeocodingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service(GeocodingService.SERVICE_PATH)
public class GeocodingServiceImpl extends KAbstractGeocodingService implements GeocodingService {

	private static Logger logger = LoggerFactory.getLogger(GeocodingService.class);

	@Autowired
	private KConfig config;


	
	protected String getGoogleApiKey() {
	    return config.getString("google.apiKey");
	}
}
