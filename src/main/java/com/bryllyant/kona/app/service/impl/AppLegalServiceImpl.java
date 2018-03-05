/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AppLegalMapper;
import com.bryllyant.kona.app.entity.AppLegal;
import com.bryllyant.kona.app.entity.AppLegalExample;
import com.bryllyant.kona.app.service.AppLegalService;
import com.bryllyant.kona.app.service.KAbstractAppLegalService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(AppLegalService.SERVICE_PATH)
public class AppLegalServiceImpl 
		extends KAbstractAppLegalService<AppLegal,AppLegalExample> 
		implements AppLegalService {
	
	private static Logger logger = LoggerFactory.getLogger(AppLegalServiceImpl.class);

	@Autowired
	private AppLegalMapper appLegalDao;
    


	@Override @SuppressWarnings("unchecked")
	protected AppLegalMapper getDao() {
		return appLegalDao;
	}
    


    @Override
    protected boolean entityHasBlobs() {
        return true;
    }
    

	
	 @Override
    protected AppLegalExample getEntityExampleObject() { return new AppLegalExample(); }




}
