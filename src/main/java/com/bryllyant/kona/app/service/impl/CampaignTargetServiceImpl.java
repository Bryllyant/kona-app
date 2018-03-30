/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.app.dao.CampaignTargetMapper;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.entity.CampaignTargetExample;
import com.bryllyant.kona.app.entity.ShortUrl;
import com.bryllyant.kona.app.service.CampaignTargetService;
import com.bryllyant.kona.app.service.KAbstractCampaignTargetService;
import com.bryllyant.kona.app.service.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(CampaignTargetService.SERVICE_PATH)
public class CampaignTargetServiceImpl
		extends KAbstractCampaignTargetService<
        CampaignTarget,
        CampaignTargetExample,
        CampaignTargetMapper,
        CampaignChannel,
        ShortUrl>
		implements CampaignTargetService {
	
	private static Logger logger = LoggerFactory.getLogger(CampaignTargetServiceImpl.class);

    @Autowired
    private KConfig config;
    
    @Autowired
    private CampaignTargetMapper campaignTargetMapper;

    @Autowired
    private ShortUrlService shortUrlService;

    protected String getLandingPageBaseUrl() {
        return config.getString("landingpage.baseUrl");
    }

    @Override @SuppressWarnings("unchecked")
    protected CampaignTargetMapper getMapper() {
        return campaignTargetMapper;
    }

    @Override @SuppressWarnings("unchecked")
    protected ShortUrlService getShortUrlService() {
        return shortUrlService;
    }
}
