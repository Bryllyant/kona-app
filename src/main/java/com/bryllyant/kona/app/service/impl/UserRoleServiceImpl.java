/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.UserRoleMapper;
import com.bryllyant.kona.app.entity.AuthPriv;
import com.bryllyant.kona.app.entity.AuthRole;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserRole;
import com.bryllyant.kona.app.entity.UserRoleExample;
import com.bryllyant.kona.app.service.AuthPrivService;
import com.bryllyant.kona.app.service.UserRoleService;
import com.bryllyant.kona.app.service.AuthRoleService;
import com.bryllyant.kona.app.service.KAbstractUserRoleService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(UserRoleService.SERVICE_PATH)
public class UserRoleServiceImpl
		extends KAbstractUserRoleService<UserRole, UserRoleExample, UserRoleMapper,User,AuthRole>
		implements UserRoleService {

	private static Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

	@Autowired
	private UserRoleMapper userRoleMapper;

    @Autowired
    private AuthRoleService authRoleService;

    @Autowired
    private UserService userService;


	@Override
	@SuppressWarnings("unchecked")
	protected UserRoleMapper getMapper() {
		return userRoleMapper;
	}

	@Override
	protected UserRole getNewObject() {
	    return new UserRole();
    }

    @Override  @SuppressWarnings("unchecked")
    protected AuthRoleService getAuthRoleService() {
	    return authRoleService;
    }

    @Override  @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }


	 @Override
    protected UserRoleExample getEntityExampleObject() { return new UserRoleExample(); }


}
