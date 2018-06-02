/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CampaignMapper;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignExample;
import com.bryllyant.kona.app.service.CampaignGroupService;
import com.bryllyant.kona.app.service.CampaignService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(CampaignService.SERVICE_PATH)
public class CampaignServiceImpl 
		extends KAbstractService<
                Campaign,
                CampaignExample,
                CampaignMapper>
		implements CampaignService {
	
	private static Logger logger = LoggerFactory.getLogger(CampaignServiceImpl.class);
    
    @Autowired
    private CampaignMapper campaignMapper;

    @Autowired
    private CampaignGroupService campaignGroupService;

    @Override @SuppressWarnings("unchecked")
    protected CampaignMapper getMapper() {
        return campaignMapper;
    }

    @Override
    public void validate(Campaign campaign) {
        if (campaign.getCreatedDate() == null) {
            campaign.setCreatedDate(new Date());
        }

        campaign.setUpdatedDate(new Date());

        if (campaign.getUid() == null) {
            campaign.setUid(uuid());
        }

        if (campaign.getConversionCount() == null) {
            campaign.setConversionCount(0);
        }

        String slug = KInflector.getInstance().slug(campaign.getName());
        campaign.setSlug(slug);
    }



    @Override
    public Campaign fetchBySlug(String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<Campaign> fetchByOwnerId(Long ownerId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);
        return fetchByCriteria(filter);
    }


    // create() has side effects, add() does not.
    @Override @Transactional
    public Campaign create(Campaign campaign) {
        return create(
                campaign.getOwnerId(),
                campaign.getName(),
                campaign.getGoal(),
                campaign.getKpi(),
                campaign.getConversionTarget(),
                campaign.getStartDate(),
                campaign.getEndDate()
        );
    }


    @Override @Transactional
    public Campaign create(
            Long ownerId,
            String name,
            Campaign.Goal goal,
            Campaign.KPI kpi,
            Integer conversionTarget,
            Date startDate,
            Date endDate
    ) {
        String slug = KInflector.getInstance().slug(name);

        Campaign campaign = fetchBySlug(slug);

        if (campaign != null) {
            throw new KServiceException("Campaign name already exists: " + name);
        }

        campaign = getEntityObject();
        campaign.setOwnerId(ownerId);
        campaign.setName(name);
        campaign.setSlug(slug);
        campaign.setGoal(goal);
        campaign.setKpi(kpi);
        campaign.setConversionTarget(conversionTarget);
        campaign.setEnabled(true);
        campaign.setStartDate(startDate);
        campaign.setEndDate(endDate);

        campaign = add(campaign);

        // create default campaign group
        campaignGroupService.create(campaign, null, null, null, null, null);

        return campaign;
    }
}
