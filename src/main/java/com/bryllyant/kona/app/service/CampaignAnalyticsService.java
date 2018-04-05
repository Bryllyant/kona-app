/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface CampaignAnalyticsService extends KService, KEntityService<CampaignAnalytics> {
	String SERVICE_PATH = "rpc/CampaignAnalyticsService";

    List<CampaignAnalytics> fetchByCampaignId(Long campaignId, Date startDate, Date endDate);

    List<CampaignAnalytics> fetchByGroupId(Long groupId, Date startDate, Date endDate);

    List<CampaignAnalytics> fetchByChannelId(Long channelId, Date startDate, Date endDate);

    List<CampaignAnalytics> fetchByTargetId(Long targetId, Date startDate, Date endDate);

    List<CampaignAnalytics> fetchByUserId(Long userId, Date startDate, Date endDate);

    @Transactional
    CampaignAnalytics create(
            KServiceClient client,
            Long targetId,
            String action,
            String category,
            String label,
            Double value
    );

    CampaignAnalytics create(
            KServiceClient client,
            Long targetId,
            String action,
            String category,
            String label,
            Double value,
            boolean conversionEvent,
            Long conversionUserId,
            String conversionEmail,
            String conversionMobileNumber
    );

    List<CampaignAnalytics> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    );
	
}
