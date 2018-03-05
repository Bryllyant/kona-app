/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CampaignChannelMapper;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignChannelExample;
import com.bryllyant.kona.app.service.CampaignChannelService;
import com.bryllyant.kona.app.service.KAbstractCampaignChannelService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(CampaignChannelService.SERVICE_PATH)
public class CampaignChannelServiceImpl 
		extends KAbstractCampaignChannelService<CampaignChannel,CampaignChannelExample> 
		implements CampaignChannelService {
	
	private static Logger logger = LoggerFactory.getLogger(CampaignChannelServiceImpl.class);
    
    @Autowired
    private CampaignChannelMapper campaignChannelDao;




    @Override @SuppressWarnings("unchecked")
    protected CampaignChannelMapper getDao() {
        return campaignChannelDao;
    }
    


    @Override
    protected CampaignChannelExample getEntityExampleObject() { return new CampaignChannelExample(); }


}
