/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface CampaignTargetService
        extends KService, KEntityService<CampaignTarget> {

	String SERVICE_PATH = "rpc/CampaignTargetService";

    CampaignTarget fetchByChannelIdAndSlug(Long channelId, String slug);

    List<CampaignTarget> fetchByCampaignId(Long campaignId);

    List<CampaignTarget> fetchByGroupId(Long groupId);

    List<CampaignTarget> fetchByChannelId(Long channelId);

    List<CampaignTarget> fetchByUrl(String url);

    CampaignTarget fetchByShortUrl(String shortUrl);

    List<CampaignTarget> fetchByLandingPageId(Long landingPageId);

    @Transactional
    CampaignTarget create(CampaignChannel channel, CampaignTarget target);

    @Transactional
    CampaignTarget create(
            CampaignChannel channel,
            String name,
            CampaignTarget.Type type,
            Long landingPageId,
            Date startDate,
            Date endDate
    );

    @Transactional
    CampaignTarget create(
            CampaignChannel channel,
            String name,
            CampaignTarget.Type type,
            String url,
            Date startDate,
            Date endDate
    );
}
