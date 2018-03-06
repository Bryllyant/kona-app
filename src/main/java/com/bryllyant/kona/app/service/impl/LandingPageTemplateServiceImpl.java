/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.LandingPageTemplateMapper;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.app.entity.LandingPageTemplateExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.service.KAbstractLandingPageTemplateService;
import com.bryllyant.kona.app.service.LandingPageTemplateService;
import com.bryllyant.kona.app.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(LandingPageTemplateService.SERVICE_PATH)
public class LandingPageTemplateServiceImpl
		extends KAbstractLandingPageTemplateService<LandingPageTemplate, LandingPageTemplateExample, LandingPageTemplateMapper,User,File>
		implements LandingPageTemplateService {
	
	private static Logger logger = LoggerFactory.getLogger(LandingPageTemplateServiceImpl.class);

	@Autowired
	private LandingPageTemplateMapper mapper;

    @Autowired
    private SystemService systemService;

    @Autowired
    private FileService fileService;

	@Override
    protected LandingPageTemplate getNewObject() {
	    return new LandingPageTemplate();
    }

    @Override
    protected File getNewFileObject() {
	    return new File();
    }

    @Override @SuppressWarnings("unchecked")
    protected User getSystemUser() {
	    return systemService.getSystemUser();
    }

    @Override @SuppressWarnings("unchecked")
    protected FileService getFileService() {
        return fileService;
    }

    @Override
	protected LandingPageTemplateMapper getMapper() {
		return mapper;
	}
	

	 @Override
    protected LandingPageTemplateExample getEntityExampleObject() { return new LandingPageTemplateExample(); }
}
