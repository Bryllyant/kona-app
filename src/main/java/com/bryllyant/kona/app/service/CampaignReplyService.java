/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignReplyMessage;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface CampaignReplyService extends KService, KEntityService<CampaignReply> {

	String SERVICE_PATH = "rpc/CampaignReplyService";

    CampaignReply fetchByTargetIdAndSlug(Long targetId, String slug);

    List<CampaignReply> fetchByCampaignId(Long campaignId);

    List<CampaignReply> fetchByGroupId(Long groupId);

    List<CampaignReply> fetchByChannelId(Long channelId);

    List<CampaignReply> fetchByTargetId(Long targetId);


    @Transactional
    CampaignReply create(CampaignTarget target, CampaignReply reply);

    @Transactional
    CampaignReply create(
            CampaignTarget target,
            String name,
            CampaignReply.Type type,
            String content,
            Date startDate,
            Date endDate
    );

    @Transactional
    CampaignReplyMessage execute(CampaignReply reply, CampaignAnalytics analytics);
}
