package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCampaignAnalytics;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface KCampaignAnalyticsService<CAMPAIGN_ANALYTICS extends KCampaignAnalytics>
        extends KService, KEntityService<CAMPAIGN_ANALYTICS> {
    CAMPAIGN_ANALYTICS fetchByUid(String uid);

    List<CAMPAIGN_ANALYTICS> fetchByCampaignId(Long campaignId, Date startDate, Date endDate);

    List<CAMPAIGN_ANALYTICS> fetchByGroupId(Long groupId, Date startDate, Date endDate);

    List<CAMPAIGN_ANALYTICS> fetchByChannelId(Long channelId, Date startDate, Date endDate);

    List<CAMPAIGN_ANALYTICS> fetchByTargetId(Long targetId, Date startDate, Date endDate);

    List<CAMPAIGN_ANALYTICS> fetchByUserId(Long userId, Date startDate, Date endDate);

    @Transactional
    CAMPAIGN_ANALYTICS create(
            KServiceClient client,
            Long targetId,
            String action,
            String category,
            String label,
            Double value
    );

    CAMPAIGN_ANALYTICS create(
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

    List<CAMPAIGN_ANALYTICS> fetchProximate(
            Double latitude, 
            Double longitude, 
            Double radius, 
            Date startDate, 
            Date endDate,
            List<Long> objectIdList
    );
}
