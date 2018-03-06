/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.RemoteServiceMapper;
import com.bryllyant.kona.app.entity.RemoteService;
import com.bryllyant.kona.app.entity.RemoteServiceExample;
import com.bryllyant.kona.app.service.KAbstractRemoteServiceService;
import com.bryllyant.kona.app.service.RemoteServiceService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(RemoteServiceService.SERVICE_PATH)
public class RemoteServiceServiceImpl 
		extends KAbstractRemoteServiceService<RemoteService, RemoteServiceExample, RemoteServiceMapper>
		implements RemoteServiceService {
	
	private static Logger logger = LoggerFactory.getLogger(RemoteServiceServiceImpl.class);

	@Autowired
	private RemoteServiceMapper remoteServiceMapper;
    


	@Override @SuppressWarnings("unchecked")
	protected RemoteServiceMapper getMapper() {
		return remoteServiceMapper;
	}
    


	 @Override
    protected RemoteServiceExample getEntityExampleObject() { return new RemoteServiceExample(); }

    
}
