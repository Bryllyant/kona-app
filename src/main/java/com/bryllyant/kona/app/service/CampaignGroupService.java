/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface CampaignGroupService
        extends KService, KEntityService<CampaignGroup> {

	String SERVICE_PATH = "rpc/CampaignGroupService";

    List<CampaignGroup> fetchByCampaignId(Long campaignId);

    List<CampaignGroup> fetchByPartnerId(Long partnerId);

    @Transactional
    CampaignGroup create(Campaign campaign, CampaignGroup campaiginGroup);

    @Transactional
    CampaignGroup create(
            Campaign campaign,
            String name,
            Long partnerId,
            String description,
            Date startDate,
            Date endDate
    );
}
