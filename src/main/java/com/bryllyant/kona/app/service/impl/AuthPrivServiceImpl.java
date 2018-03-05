/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AuthPrivMapper;
import com.bryllyant.kona.app.entity.AuthPriv;
import com.bryllyant.kona.app.entity.AuthPrivExample;
import com.bryllyant.kona.app.service.AuthPrivService;
import com.bryllyant.kona.app.service.KAbstractAuthPrivService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(AuthPrivService.SERVICE_PATH)
public class AuthPrivServiceImpl
		extends KAbstractAuthPrivService<AuthPriv,AuthPrivExample>
		implements AuthPrivService {

	private static Logger logger = LoggerFactory.getLogger(AuthPrivServiceImpl.class);

	@Autowired
	private AuthPrivMapper authPrivDao;


	@Override
	@SuppressWarnings("unchecked")
	protected AuthPrivMapper getDao() {
		return authPrivDao;
	}

	 @Override
    protected AuthPrivExample getEntityExampleObject() { return new AuthPrivExample(); }


}
