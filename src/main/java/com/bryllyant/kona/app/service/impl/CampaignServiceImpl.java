/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CampaignMapper;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignExample;
import com.bryllyant.kona.app.service.CampaignService;
import com.bryllyant.kona.app.service.KAbstractCampaignService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(CampaignService.SERVICE_PATH)
public class CampaignServiceImpl 
		extends KAbstractCampaignService<Campaign, CampaignExample, CampaignMapper>
		implements CampaignService {
	
	private static Logger logger = LoggerFactory.getLogger(CampaignServiceImpl.class);
    
    @Autowired
    private CampaignMapper campaignMapper;




    @Override @SuppressWarnings("unchecked")
    protected CampaignMapper getMapper() {
        return campaignMapper;
    }
    


    @Override
    protected CampaignExample getEntityExampleObject() { return new CampaignExample(); }


}
