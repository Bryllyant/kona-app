/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CampaignEventMapper;
import com.bryllyant.kona.app.entity.CampaignEvent;
import com.bryllyant.kona.app.entity.CampaignEventExample;
import com.bryllyant.kona.app.service.CampaignEventService;
import com.bryllyant.kona.app.service.KAbstractCampaignEventService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(CampaignEventService.SERVICE_PATH)
public class CampaignEventServiceImpl 
		extends KAbstractCampaignEventService<CampaignEvent, CampaignEventExample, CampaignEventMapper>
		implements CampaignEventService {
	
	private static Logger logger = LoggerFactory.getLogger(CampaignEventServiceImpl.class);
    
    @Autowired
    private CampaignEventMapper campaignEventMapper;




    @Override @SuppressWarnings("unchecked")
    protected CampaignEventMapper getMapper() {
        return campaignEventMapper;
    }
    


    @Override
    protected CampaignEventExample getEntityExampleObject() { return new CampaignEventExample(); }

    
    @Override
    protected void updateCoords(Long campaignEventId) {
        getMapper().updateCoords(campaignEventId);
    }


    @Override
    public List<CampaignEvent> fetchProximate(
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
