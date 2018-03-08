/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AnalyticsEventMapper;
import com.bryllyant.kona.app.entity.AnalyticsEvent;
import com.bryllyant.kona.app.entity.AnalyticsEventExample;
import com.bryllyant.kona.app.service.AnalyticsEventService;
import com.bryllyant.kona.app.service.KAbstractAnalyticsEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(AnalyticsEventService.SERVICE_PATH)
public class AnalyticsEventServiceImpl
		extends KAbstractAnalyticsEventService<AnalyticsEvent, AnalyticsEventExample, AnalyticsEventMapper>
		implements AnalyticsEventService {
	
	private static Logger logger = LoggerFactory.getLogger(AnalyticsEventServiceImpl.class);
    
    @Autowired
    private AnalyticsEventMapper analyticsEventMapper;




    @Override @SuppressWarnings("unchecked")
    protected AnalyticsEventMapper getMapper() {
        return analyticsEventMapper;
    }
    


    @Override
    protected AnalyticsEventExample getEntityExampleObject() { return new AnalyticsEventExample(); }

    
    @Override
    protected void updateCoords(Long analyticsEventId) {
        getMapper().updateCoords(analyticsEventId);
    }


    @Override
    public List<AnalyticsEvent> fetchProximate(
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
