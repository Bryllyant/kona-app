/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.LandingPageMapper;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.entity.LandingPageExample;
import com.bryllyant.kona.app.entity.LandingPageParam;
import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.app.entity.LandingPageTemplateParam;
import com.bryllyant.kona.app.service.KAbstractLandingPageService;
import com.bryllyant.kona.app.service.LandingPageParamService;
import com.bryllyant.kona.app.service.LandingPageService;
import com.bryllyant.kona.app.service.LandingPageTemplateParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(LandingPageService.SERVICE_PATH)
public class LandingPageServiceImpl
		extends KAbstractLandingPageService<
        LandingPage,
        LandingPageExample,
        LandingPageMapper,
        LandingPageParam,
        LandingPageTemplate,
        LandingPageTemplateParam,
        File>
		implements LandingPageService {
	
	private static Logger logger = LoggerFactory.getLogger(LandingPageServiceImpl.class);

	@Autowired
	private LandingPageMapper mapper;

    @Autowired
    private LandingPageTemplateParamService templateParamService;

    @Autowired
    private LandingPageParamService landingPageParamService;

	@Override @SuppressWarnings("unchecked")
	protected LandingPageMapper getMapper() {
		return mapper;
	}

    @Override @SuppressWarnings("unchecked")
    protected LandingPageTemplateParamService getTemplateParamService() {
        return templateParamService;
    }

    @Override @SuppressWarnings("unchecked")
    protected LandingPageParamService getLandingPageParamService() {
        return landingPageParamService;
    }
}
