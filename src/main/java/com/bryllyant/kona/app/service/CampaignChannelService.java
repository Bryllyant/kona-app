/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface CampaignChannelService
        extends KService, KEntityService<CampaignChannel> {

	String SERVICE_PATH = "rpc/CampaignChannelService";

    CampaignChannel fetchByGroupIdAndSlug(Long groupId, String slug);

    CampaignChannel fetchBySmsNumberAndKeyword(String smsNumber, String smsKeyword);

    CampaignChannel fetchByPromoCode(String promoCode);

    List<CampaignChannel> fetchByCampaignId(Long campaignId);

    List<CampaignChannel> fetchByGroupId(Long groupId);

    @Transactional
    CampaignChannel create(CampaignGroup group, CampaignChannel channel);

    @Transactional
    CampaignChannel create(
            CampaignGroup group,
            CampaignChannel.Type type,
            CampaignChannel.TargetStrategy targetStrategy,
            CampaignChannel.ReplyStrategy replyStrategy,
            String name,
            Date startDate,
            Date endDate
    );


    CampaignTarget nextCampaignTarget(CampaignChannel channel);

    CampaignReply nextCampaignReply(CampaignChannel channel, CampaignTarget target);
}
