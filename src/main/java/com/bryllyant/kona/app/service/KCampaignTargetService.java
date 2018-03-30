package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCampaignTarget;
import com.bryllyant.kona.app.entity.KCampaignChannel;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


public interface KCampaignTargetService<
        CAMPAIGN_TARGET extends KCampaignTarget,
        CAMPAIGN_CHANNEL extends KCampaignChannel>
        extends KService, KEntityService<CAMPAIGN_TARGET> {
            
    CAMPAIGN_TARGET fetchByUid(String uid);

    CAMPAIGN_TARGET fetchByChannelIdAndSlug(Long channelId, String slug);

    List<CAMPAIGN_TARGET> fetchByCampaignId(Long campaignId);

    List<CAMPAIGN_TARGET> fetchByGroupId(Long groupId);

    List<CAMPAIGN_TARGET> fetchByChannelId(Long channelId);

    CAMPAIGN_TARGET fetchByUrl(String url);

    CAMPAIGN_TARGET fetchByShortUrl(String shortUrl);

    List<CAMPAIGN_TARGET> fetchByLandingPageId(Long landingPageId);

    @Transactional
    CAMPAIGN_TARGET create(CAMPAIGN_CHANNEL channel, CAMPAIGN_TARGET target);

    @Transactional
    CAMPAIGN_TARGET create(
            CAMPAIGN_CHANNEL channel,
            String name,
            CAMPAIGN_TARGET.Type type,
            Long landingPageId,
            Date startDate,
            Date endDate
    );
}
