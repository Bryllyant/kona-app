package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCampaign;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface KCampaignService<CAMPAIGN extends KCampaign>
        extends KService, KEntityService<CAMPAIGN> {

	CAMPAIGN fetchByUid(String uid);

	CAMPAIGN fetchBySlug(String slug);

	List<CAMPAIGN> fetchByOwnerId(Long ownerId);

    @Transactional
    CAMPAIGN create(CAMPAIGN campaign);

    @Transactional
    CAMPAIGN create(
            Long ownerId,
            String name,
            CAMPAIGN.Goal goal,
            CAMPAIGN.KPI kpi,
            Integer conversionTarget,
            Date startDate,
            Date endDate
    );
}
