package com.bryllyant.kona.web.service;

import com.bryllyant.kona.api.model.marketing.campaign.CampaignTargetModel;
import com.bryllyant.kona.api.service.CampaignTargetModelService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.service.CampaignTargetService;
import com.bryllyant.kona.app.service.LandingPageService;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CampaignLandingPageResolver extends LandingPageResolver {
    private static final Logger logger = LoggerFactory.getLogger(CampaignLandingPageResolver.class);

    @Autowired
    KConfig config;

    @Autowired
    LandingPageService landingPageService;

    @Autowired
    private CampaignTargetService campaignTargetService;

    @Autowired
    private CampaignTargetModelService campaignTargetModelService;


    @Override
    protected String getBaseUrlPath() {
        return config.getString("landingpage.urlPath");
    }

    @Override
    protected LandingPage getLandingPage(String baseResourcePath) {
        LandingPage page = null;

        CampaignTarget target = campaignTargetService.fetchByUid(baseResourcePath);

        if (target != null) {
            page = landingPageService.fetchById(target.getLandingPageId());
        }

        return page;
    }

    @Override
    protected Map<String, Object> getPageConfig(LandingPage page, String baseResourcePath) {

        logger.debug("getPageConfig called: ...");

        CampaignTarget target = campaignTargetService.fetchByUid(baseResourcePath);
        CampaignTargetModel targetModel = campaignTargetModelService.toModel(target);

        Map<String, Object> campaign = new HashMap<>();
        campaign.put("campaign", targetModel.getCampaign());
        campaign.put("group", targetModel.getGroup());
        campaign.put("channel", targetModel.getChannel());
        campaign.put("target", CampaignTargetModel.from(target));

        Map<String, Object> config = super.getPageConfig(page, baseResourcePath);

        config.put("campaign", campaign);
        config.put("trackingId", target.getAnalyticsTrackingId());

        logger.debug("getPageConfig called: campaign config: " + KJsonUtil.toJson(config, 5000));

        return config;
    }

}
