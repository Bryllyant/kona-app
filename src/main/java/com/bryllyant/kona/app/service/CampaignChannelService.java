/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.remote.service.KService;

public interface CampaignChannelService extends KService, KCampaignChannelService<CampaignChannel> {
	public static final String SERVICE_PATH = "rpc/CampaignChannelService";
	
}
