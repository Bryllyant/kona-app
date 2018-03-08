/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AnalyticsEvent;
import com.bryllyant.kona.remote.service.KService;

public interface AnalyticsEventService extends KService, KAnalyticsEventService<AnalyticsEvent> {
	String SERVICE_PATH = "rpc/AnalyticsEventService";
	
}
