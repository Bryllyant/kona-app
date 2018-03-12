/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.remote.service.KService;

public interface CampaignGroupService
        extends KService, KCampaignGroupService<CampaignGroup,Campaign> {

	String SERVICE_PATH = "rpc/CampaignGroupService";
}
