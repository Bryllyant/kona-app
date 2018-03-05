/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AuthRoleMapper;
import com.bryllyant.kona.app.entity.AuthRole;
import com.bryllyant.kona.app.entity.AuthRoleExample;
import com.bryllyant.kona.app.service.AuthRoleService;
import com.bryllyant.kona.app.service.KAbstractAuthRoleService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(AuthRoleService.SERVICE_PATH)
public class AuthRoleServiceImpl
		extends KAbstractAuthRoleService<AuthRole,AuthRoleExample>
		implements AuthRoleService {

	private static Logger logger = LoggerFactory.getLogger(AuthRoleServiceImpl.class);

	@Autowired
	private AuthRoleMapper authRoleDao;


	@Override
	@SuppressWarnings("unchecked")
	protected AuthRoleMapper getDao() {
		return authRoleDao;
	}

	 @Override
    protected AuthRoleExample getEntityExampleObject() { return new AuthRoleExample(); }


}
