/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AuthRoleMapper;
import com.bryllyant.kona.app.entity.AuthRole;
import com.bryllyant.kona.app.entity.AuthRoleExample;
import com.bryllyant.kona.app.service.AuthRoleService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service(AuthRoleService.SERVICE_PATH)
public class AuthRoleServiceImpl
		extends KAbstractService<AuthRole, AuthRoleExample, AuthRoleMapper>
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
    public void validate(AuthRole role) {
        if (role.getCreatedDate() == null) {
            role.setCreatedDate(new Date());
        }

        role.setUpdatedDate(new Date());

        if (role.getUid() == null) {
            role.setUid(uuid());
        }

        String slug = KInflector.getInstance().slug(role.getName());
        role.setSlug(slug);

    }

    @Override
    public AuthRole fetchByName(String name) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("name", name);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


}
