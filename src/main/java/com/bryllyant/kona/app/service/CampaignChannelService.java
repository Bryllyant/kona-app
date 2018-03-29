/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.remote.service.KService;

public interface CampaignChannelService
        extends KService, KCampaignChannelService<CampaignChannel,CampaignGroup,CampaignTarget,CampaignReply> {

	String SERVICE_PATH = "rpc/CampaignChannelService";
}
