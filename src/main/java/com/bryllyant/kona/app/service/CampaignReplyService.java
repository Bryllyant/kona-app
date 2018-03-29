/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignReplyMessage;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.remote.service.KService;

public interface CampaignReplyService
        extends KService, KCampaignReplyService<CampaignReply,CampaignTarget,CampaignAnalytics,CampaignReplyMessage> {

	String SERVICE_PATH = "rpc/CampaignReplyService";
}
