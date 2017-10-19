/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.CampaignEvent;
import com.bryllyant.kona.remote.service.KService;

public interface CampaignEventService extends KService, KCampaignEventService<CampaignEvent> {
	public static final String SERVICE_PATH = "rpc/CampaignEventService";
	
}
