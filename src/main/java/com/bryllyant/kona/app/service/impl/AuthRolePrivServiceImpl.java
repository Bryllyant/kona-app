/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AuthRoleMapper;
import com.bryllyant.kona.app.dao.AuthRolePrivMapper;
import com.bryllyant.kona.app.entity.AuthPriv;
import com.bryllyant.kona.app.entity.AuthRole;
import com.bryllyant.kona.app.entity.AuthRoleExample;
import com.bryllyant.kona.app.entity.AuthRolePriv;
import com.bryllyant.kona.app.entity.AuthRolePrivExample;
import com.bryllyant.kona.app.service.AuthPrivService;
import com.bryllyant.kona.app.service.AuthRolePrivService;
import com.bryllyant.kona.app.service.AuthRoleService;
import com.bryllyant.kona.app.service.KAbstractAuthRolePrivService;
import com.bryllyant.kona.app.service.KAbstractAuthRoleService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(AuthRolePrivService.SERVICE_PATH)
public class AuthRolePrivServiceImpl
		extends KAbstractAuthRolePrivService<AuthRolePriv, AuthRolePrivExample, AuthRolePrivMapper,AuthRole,AuthPriv>
		implements AuthRolePrivService {

	private static Logger logger = LoggerFactory.getLogger(AuthRolePrivServiceImpl.class);

	@Autowired
	private AuthRolePrivMapper authRolePrivMapper;

    @Autowired
    private AuthRoleService authRoleService;

    @Autowired
    private AuthPrivService authPrivService;


	@Override
	@SuppressWarnings("unchecked")
	protected AuthRolePrivMapper getMapper() {
		return authRolePrivMapper;
	}

	@Override
	protected AuthRolePriv getNewObject() {
	    return new AuthRolePriv();
    }

    @Override  @SuppressWarnings("unchecked")
    protected AuthRoleService getAuthRoleService() {
	    return authRoleService;
    }

    @Override  @SuppressWarnings("unchecked")
    protected AuthPrivService getAuthPrivService() {
        return authPrivService;
    }


	 @Override
    protected AuthRolePrivExample getEntityExampleObject() { return new AuthRolePrivExample(); }


}
