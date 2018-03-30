/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AuthRoleMapper;
import com.bryllyant.kona.app.entity.AuthRole;
import com.bryllyant.kona.app.entity.AuthRoleExample;
import com.bryllyant.kona.app.service.AuthRoleService;
import com.bryllyant.kona.app.service.KAbstractAuthRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(AuthRoleService.SERVICE_PATH)
public class AuthRoleServiceImpl
		extends KAbstractAuthRoleService<AuthRole, AuthRoleExample, AuthRoleMapper>
		implements AuthRoleService {

	private static Logger logger = LoggerFactory.getLogger(AuthRoleServiceImpl.class);

	@Autowired
	private AuthRoleMapper authRoleMapper;


	@Override
	@SuppressWarnings("unchecked")
	protected AuthRoleMapper getMapper() {
		return authRoleMapper;
	}

	 @Override
    protected AuthRoleExample getEntityExampleObject() { return new AuthRoleExample(); }


}
