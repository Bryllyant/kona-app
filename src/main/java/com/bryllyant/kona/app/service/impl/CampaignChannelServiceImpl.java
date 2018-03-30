/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.app.dao.CampaignChannelMapper;
import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignChannelExample;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignReplyMessage;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.entity.ShortUrl;
import com.bryllyant.kona.app.service.CampaignChannelService;
import com.bryllyant.kona.app.service.CampaignReplyService;
import com.bryllyant.kona.app.service.CampaignTargetService;
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
        CampaignTarget,
        CampaignReply,
        CampaignReplyMessage,
        CampaignAnalytics,
        ShortUrl>
		implements CampaignChannelService {
	
	private static Logger logger = LoggerFactory.getLogger(CampaignChannelServiceImpl.class);

    @Autowired
    private KConfig config;
    
    @Autowired
    private CampaignChannelMapper campaignChannelMapper;

    @Autowired
    private ShortUrlService shortUrlService;

    @Autowired
    private CampaignTargetService campaignTargetService;

    @Autowired
    private CampaignReplyService campaignReplyService;

    protected String getLandingPageBaseUrl() {
        return config.getString("landingpage.baseUrl");
    }

    @Override @SuppressWarnings("unchecked")
    protected CampaignChannelMapper getMapper() {
        return campaignChannelMapper;
    }

    @Override @SuppressWarnings("unchecked")
    protected ShortUrlService getShortUrlService() {
        return shortUrlService;
    }

    @Override @SuppressWarnings("unchecked")
    protected CampaignTargetService getCampaignTargetService() {
        return campaignTargetService;
    }

    @Override @SuppressWarnings("unchecked")
    protected CampaignReplyService getCampaignReplyService() {
        return campaignReplyService;
    }
}
