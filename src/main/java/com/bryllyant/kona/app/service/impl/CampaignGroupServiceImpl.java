/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CampaignGroupMapper;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignGroupExample;
import com.bryllyant.kona.app.service.CampaignGroupService;
import com.bryllyant.kona.app.service.KAbstractCampaignGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(CampaignGroupService.SERVICE_PATH)
public class CampaignGroupServiceImpl
		extends KAbstractCampaignGroupService<
        CampaignGroup,
        CampaignGroupExample,
        CampaignGroupMapper,
        Campaign>
		implements CampaignGroupService {
	
	private static Logger logger = LoggerFactory.getLogger(CampaignGroupServiceImpl.class);
    
    @Autowired
    private CampaignGroupMapper campaignGroupMapper;


    @Override @SuppressWarnings("unchecked")
    protected CampaignGroupMapper getMapper() {
        return campaignGroupMapper;
    }
}
