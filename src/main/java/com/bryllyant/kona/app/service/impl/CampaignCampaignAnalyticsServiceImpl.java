/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CampaignAnalyticsMapper;
import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.app.entity.CampaignAnalyticsExample;
import com.bryllyant.kona.app.service.CampaignCampaignAnalyticsService;
import com.bryllyant.kona.app.service.KAbstractCampaignAnalyticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(CampaignCampaignAnalyticsService.SERVICE_PATH)
public class CampaignCampaignAnalyticsServiceImpl
		extends KAbstractCampaignAnalyticsService<CampaignAnalytics, CampaignAnalyticsExample, CampaignAnalyticsMapper>
		implements CampaignCampaignAnalyticsService {
	
	private static Logger logger = LoggerFactory.getLogger(CampaignCampaignAnalyticsServiceImpl.class);
    
    @Autowired
    private CampaignAnalyticsMapper campaignAnalyticsMapper;


    @Override @SuppressWarnings("unchecked")
    protected CampaignAnalyticsMapper getMapper() {
        return campaignAnalyticsMapper;
    }
    

    @Override
    protected void updateCoords(Long campaignAnalyticsId) {
        getMapper().updateCoords(campaignAnalyticsId);
    }


    @Override
    public List<CampaignAnalytics> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    ) {
        return getMapper().selectProximate(latitude, longitude, radius, startDate, endDate, objectIdList);
    }
}
