/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.remote.service.KService;

public interface CampaignService extends KService, KCampaignService<Campaign> {
	public static final String SERVICE_PATH = "rpc/CampaignService";
	
}
