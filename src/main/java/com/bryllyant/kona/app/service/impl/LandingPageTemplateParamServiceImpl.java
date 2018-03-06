/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.LandingPageTemplateParamMapper;
import com.bryllyant.kona.app.entity.LandingPageTemplateParam;
import com.bryllyant.kona.app.entity.LandingPageTemplateParamExample;
import com.bryllyant.kona.app.service.KAbstractLandingPageTemplateParamService;
import com.bryllyant.kona.app.service.LandingPageTemplateParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(LandingPageTemplateParamService.SERVICE_PATH)
public class LandingPageTemplateParamServiceImpl
		extends KAbstractLandingPageTemplateParamService<LandingPageTemplateParam, LandingPageTemplateParamExample, LandingPageTemplateParamMapper>
		implements LandingPageTemplateParamService {
	
	private static Logger logger = LoggerFactory.getLogger(LandingPageTemplateParamServiceImpl.class);

	@Autowired
	private LandingPageTemplateParamMapper mapper;

	
	@Override @SuppressWarnings("unchecked")
	protected LandingPageTemplateParamMapper getMapper() {
		return mapper;
	}

	@Override
    protected LandingPageTemplateParam getNewObject() {
	    return new LandingPageTemplateParam();
    }
	

	 @Override
    protected LandingPageTemplateParamExample getEntityExampleObject() { return new LandingPageTemplateParamExample(); }
}
