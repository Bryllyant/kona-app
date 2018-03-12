/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CampaignMapper;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignExample;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.service.CampaignGroupService;
import com.bryllyant.kona.app.service.CampaignService;
import com.bryllyant.kona.app.service.KAbstractCampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(CampaignService.SERVICE_PATH)
public class CampaignServiceImpl 
		extends KAbstractCampaignService<
        Campaign,
        CampaignExample,
        CampaignMapper,
        CampaignGroup>
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

    @Override @SuppressWarnings("unchecked")
    protected CampaignGroupService getCampaignGroupService() {
        return campaignGroupService;
    }
}
