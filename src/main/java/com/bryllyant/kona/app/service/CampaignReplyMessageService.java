/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignReplyMessage;
import com.bryllyant.kona.remote.service.KService;

public interface CampaignReplyMessageService
        extends KService, KCampaignReplyMessageService<CampaignReplyMessage,CampaignReply> {

	String SERVICE_PATH = "rpc/CampaignReplyMessageService";
}
