/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.LandingPageParamMapper;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.LandingPageParam;
import com.bryllyant.kona.app.entity.LandingPageParamExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.service.KAbstractLandingPageParamService;
import com.bryllyant.kona.app.service.LandingPageParamService;
import com.bryllyant.kona.app.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(LandingPageParamService.SERVICE_PATH)
public class LandingPageParamServiceImpl
		extends KAbstractLandingPageParamService<LandingPageParam,LandingPageParamExample,User,File>
		implements LandingPageParamService {
	
	private static Logger logger = LoggerFactory.getLogger(LandingPageParamServiceImpl.class);

	@Autowired
	private LandingPageParamMapper mapper;

    @Autowired
    private SystemService systemService;

    @Autowired
    private FileService fileService;

	@Override
    protected LandingPageParam getNewObject() {
	    return new LandingPageParam();
    }

    @Override
    protected File getNewFileObject() {
	    return new File();
    }

    @Override
    protected User getSystemUser() {
	    return systemService.getSystemUser();
    }

    @Override @SuppressWarnings("unchecked")
    protected FileService getFileService() {
        return fileService;
    }
	
	@Override @SuppressWarnings("unchecked")
	protected LandingPageParamMapper getDao() {
		return mapper;
	}
	

	 @Override
    protected LandingPageParamExample getEntityExampleObject() { return new LandingPageParamExample(); }
}
