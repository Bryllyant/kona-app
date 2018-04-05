/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface CampaignService extends KService, KEntityService<Campaign> {
	String SERVICE_PATH = "rpc/CampaignService";

    Campaign fetchBySlug(String slug);

    List<Campaign> fetchByOwnerId(Long ownerId);

    @Transactional
    Campaign create(Campaign campaign);

    @Transactional
    Campaign create(
            Long ownerId,
            String name,
            Campaign.Goal goal,
            Campaign.KPI kpi,
            Integer conversionTarget,
            Date startDate,
            Date endDate
    );
}
