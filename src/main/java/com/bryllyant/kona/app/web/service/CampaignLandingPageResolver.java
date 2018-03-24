package com.bryllyant.kona.app.web.service;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.service.LandingPageService;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CampaignLandingPageResolver extends LandingPageResolver {
    private static final Logger logger = LoggerFactory.getLogger(CampaignLandingPageResolver.class);

    @Autowired
    KConfig config;

    @Autowired
    LandingPageService landingPageService;


    @Override
    protected String getBaseUrlPath() {
        return config.getString("landingPage.urlPath");
    }

    @Override
    protected LandingPage getLandingPage(String baseResourcePath) {
        LandingPage page = null;

        CampaignChannel channel = campaignChannelService.fetchByUid(baseResourcePath);

        if (channel != null) {
            page = landingPageService.fetchById(channel.getLandingPageId());
        }

        return page;
    }

    @Override
    protected Map<String, Object> getPageConfig(LandingPage page, String baseResourcePath) {

        logger.debug("getPageConfig called: ...");

        Map<String, Object> config = super.getPageConfig(page, baseResourcePath);

        CampaignChannel channel = campaignChannelService.fetchByUid(baseResourcePath);

        config.put("trackingId", channel.getAnalyticsTrackingId());

        logger.debug("getPageConfig called: campaign config: " + KJsonUtil.toJson(config, 5000));

        return config;
    }

}
