/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.CampaignChannelMapper;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignChannelExample;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.ShortUrl;
import com.bryllyant.kona.app.service.CampaignChannelService;
import com.bryllyant.kona.app.service.KAbstractCampaignChannelService;
import com.bryllyant.kona.app.service.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(CampaignChannelService.SERVICE_PATH)
public class CampaignChannelServiceImpl 
		extends KAbstractCampaignChannelService<
        CampaignChannel,
        CampaignChannelExample,
        CampaignChannelMapper,
        CampaignGroup,
        ShortUrl>
		implements CampaignChannelService {
	
	private static Logger logger = LoggerFactory.getLogger(CampaignChannelServiceImpl.class);

    @Autowired
    private KConfig config;
    
    @Autowired
    private CampaignChannelMapper campaignChannelMapper;

    @Autowired
    private ShortUrlService shortUrlService;

    protected String getLandingPageBaseUrl() {
        return config.getString("landingPage.baseUrl");
    }

    @Override @SuppressWarnings("unchecked")
    protected CampaignChannelMapper getMapper() {
        return campaignChannelMapper;
    }

    @Override @SuppressWarnings("unchecked")
    protected ShortUrlService getShortUrlService() {
        return shortUrlService;
    }
}
