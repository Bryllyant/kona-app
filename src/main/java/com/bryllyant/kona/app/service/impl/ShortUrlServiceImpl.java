/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.ShortUrlMapper;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.entity.Script;
import com.bryllyant.kona.app.entity.ShortUrl;
import com.bryllyant.kona.app.entity.ShortUrlExample;
import com.bryllyant.kona.app.service.CampaignChannelService;
import com.bryllyant.kona.app.service.CampaignTargetService;
import com.bryllyant.kona.app.service.KAbstractShortUrlService;
import com.bryllyant.kona.app.service.ScriptService;
import com.bryllyant.kona.app.service.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(ShortUrlService.SERVICE_PATH)
public class ShortUrlServiceImpl 
		extends KAbstractShortUrlService<
        ShortUrl,
        ShortUrlExample,
        ShortUrlMapper,
        Script,
        Campaign,
        CampaignGroup,
        CampaignChannel,
        CampaignTarget,
        CampaignReply>
		implements ShortUrlService {
	
	private static Logger logger = LoggerFactory.getLogger(ShortUrlServiceImpl.class);

	@Autowired
	private ShortUrlMapper shortUrlMapper;
    
	@Autowired
	private KConfig config;

    @Autowired
    private CampaignChannelService campaignChannelService;

    @Autowired
    private CampaignTargetService campaignTargetService;

    @Autowired
    private ScriptService scriptService;

	@Override @SuppressWarnings("unchecked")
	protected ShortUrlMapper getMapper() {
		return shortUrlMapper;
	}



    @Override @SuppressWarnings("unchecked")
    protected CampaignTargetService getCampaignTargetService() {
	    return campaignTargetService;
    }


    @Override @SuppressWarnings("unchecked")
    protected CampaignChannelService getCampaignChannelService() {
        return campaignChannelService;
    }

    @Override @SuppressWarnings("unchecked")
    protected ScriptService getScriptService() {
        return scriptService;
    }

	@Override
	protected String getDefaultVanityDomain() {
		return config.getString("shortUrl.domain");
	}
    
	@Override
	protected boolean useHttps() {
        return config.getBoolean("shortUrl.https", false);
	}

	@Override
    protected String getLandingPageBaseUrl() {
        return config.getString("landingPage.baseUrl");
    }
}
