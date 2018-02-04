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
		extends KAbstractCampaignEventService<CampaignEvent,CampaignEventExample> 
		implements CampaignEventService {
	
	private static Logger logger = LoggerFactory.getLogger(CampaignEventServiceImpl.class);
    
    @Autowired
    private CampaignEventMapper campaignEventDao;


    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected CampaignEventMapper getDao() {
        return campaignEventDao;
    }
    
    // ----------------------------------------------------------------------------

    @Override
    protected CampaignEventExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
            Map<String, Object> filter, boolean distinct) {
    	CampaignEventExample example = new CampaignEventExample();

        if (sortOrder != null) {
            example.setOrderByClause(KMyBatisUtil.getOrderByString(sortOrder));
        }

        if (startRow == null) startRow = 0;
        if (resultSize == null) resultSize = 99999999;

        example.setOffset(startRow);
        example.setLimit(resultSize);
        example.setDistinct(distinct);

        KMyBatisUtil.buildExample(example.or().getClass(), example.or(), filter);
        return example;
    }
    
    @Override
    protected void updateCoords(Long campaignEventId) {
        getDao().updateCoords(campaignEventId);
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
        return getDao().selectProximate(latitude, longitude, radius, startDate, endDate, objectIdList);
    }
}
