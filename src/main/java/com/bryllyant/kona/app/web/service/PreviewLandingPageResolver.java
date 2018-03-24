package com.bryllyant.kona.app.web.service;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.service.LandingPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreviewLandingPageResolver extends LandingPageResolver{
    private static final Logger logger = LoggerFactory.getLogger(PreviewLandingPageResolver.class);

    @Autowired
    KConfig config;

    @Autowired
    LandingPageService landingPageService;

    @Override
    protected String getBaseUrlPath() {
        return config.getString("landingPage.previewUrlPath");
    }

    @Override
    protected LandingPage getLandingPage(String baseResourcePath) {
        return landingPageService.fetchByUid(baseResourcePath);
    }
}
