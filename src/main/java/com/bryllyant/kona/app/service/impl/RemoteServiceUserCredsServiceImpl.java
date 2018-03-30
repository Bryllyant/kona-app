/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.RemoteServiceUserCredsMapper;
import com.bryllyant.kona.app.entity.RemoteServiceUserCreds;
import com.bryllyant.kona.app.entity.RemoteServiceUserCredsExample;
import com.bryllyant.kona.app.service.KAbstractRemoteServiceUserCredsService;
import com.bryllyant.kona.app.service.RemoteServiceUserCredsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(RemoteServiceUserCredsService.SERVICE_PATH)
public class RemoteServiceUserCredsServiceImpl 
		extends KAbstractRemoteServiceUserCredsService<RemoteServiceUserCreds, RemoteServiceUserCredsExample, RemoteServiceUserCredsMapper>
		implements RemoteServiceUserCredsService {
	
	private static Logger logger = LoggerFactory.getLogger(RemoteServiceUserCredsServiceImpl.class);

	@Autowired
	private RemoteServiceUserCredsMapper remoteServiceUserCredsMapper;
    


	@Override @SuppressWarnings("unchecked")
	protected RemoteServiceUserCredsMapper getMapper() {
		return remoteServiceUserCredsMapper;
	}
    


	 @Override
    protected RemoteServiceUserCredsExample getEntityExampleObject() { return new RemoteServiceUserCredsExample(); }

    
}
