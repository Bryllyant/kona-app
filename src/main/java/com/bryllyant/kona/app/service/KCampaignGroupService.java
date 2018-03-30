package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCampaign;
import com.bryllyant.kona.app.entity.KCampaignGroup;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


public interface KCampaignGroupService<
        CAMPAIGN_GROUP extends KCampaignGroup,
        CAMPAIGN extends KCampaign>
        extends KService, KEntityService<CAMPAIGN_GROUP> {
            
    CAMPAIGN_GROUP fetchByUid(String uid);

    List<CAMPAIGN_GROUP> fetchByCampaignId(Long campaignId);

    List<CAMPAIGN_GROUP> fetchByPartnerId(Long partnerId);

    @Transactional
    CAMPAIGN_GROUP create(CAMPAIGN campaign, CAMPAIGN_GROUP campaiginGroup);

    @Transactional
    CAMPAIGN_GROUP create(
            CAMPAIGN campaign,
            String name,
            Long partnerId,
            String description,
            Date startDate,
            Date endDate
    );
}
