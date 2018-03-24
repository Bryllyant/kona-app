/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.remote.service.KService;

public interface CampaignAnalyticsService extends KService, KCampaignAnalyticsService<CampaignAnalytics> {
	String SERVICE_PATH = "rpc/CampaignAnalyticsService";
	
}
