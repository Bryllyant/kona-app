/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.remote.service.KService;

public interface CampaignTargetService
        extends KService, KCampaignTargetService<CampaignTarget,CampaignChannel> {

	String SERVICE_PATH = "rpc/CampaignTargetService";
}
