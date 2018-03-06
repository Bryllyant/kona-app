/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.LandingPageMapper;
import com.bryllyant.kona.app.entity.LandingPage;
import com.bryllyant.kona.app.entity.LandingPageExample;
import com.bryllyant.kona.app.service.KAbstractLandingPageService;
import com.bryllyant.kona.app.service.LandingPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(LandingPageService.SERVICE_PATH)
public class LandingPageServiceImpl
		extends KAbstractLandingPageService<LandingPage, LandingPageExample, LandingPageMapper>
		implements LandingPageService {
	
	private static Logger logger = LoggerFactory.getLogger(LandingPageServiceImpl.class);

	@Autowired
	private LandingPageMapper mapper;

	@Override
    protected LandingPage getNewObject() {
	    return new LandingPage();
    }

	@Override @SuppressWarnings("unchecked")
	protected LandingPageMapper getMapper() {
		return mapper;
	}
	

	 @Override
    protected LandingPageExample getEntityExampleObject() { return new LandingPageExample(); }
}