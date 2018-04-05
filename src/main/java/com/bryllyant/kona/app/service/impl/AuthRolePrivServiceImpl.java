/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AuthRolePrivMapper;
import com.bryllyant.kona.app.entity.AuthPriv;
import com.bryllyant.kona.app.entity.AuthRole;
import com.bryllyant.kona.app.entity.AuthRolePriv;
import com.bryllyant.kona.app.entity.AuthRolePrivExample;
import com.bryllyant.kona.app.service.AuthPrivService;
import com.bryllyant.kona.app.service.AuthRolePrivService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(AuthRolePrivService.SERVICE_PATH)
public class AuthRolePrivServiceImpl
		extends KAbstractService<AuthRolePriv, AuthRolePrivExample, AuthRolePrivMapper>
		implements AuthRolePrivService {

	private static Logger logger = LoggerFactory.getLogger(AuthRolePrivServiceImpl.class);

	@Autowired
	private AuthRolePrivMapper authRolePrivMapper;

    @Autowired
    private AuthPrivService authPrivService;


	@Override
	@SuppressWarnings("unchecked")
	protected AuthRolePrivMapper getMapper() {
		return authRolePrivMapper;
	}


    @Override
    public void validate(AuthRolePriv rolePriv) {
        if (rolePriv.getCreatedDate() == null) {
            rolePriv.setCreatedDate(new Date());
        }

        rolePriv.setUpdatedDate(new Date());

        if (rolePriv.getUid() == null) {
            rolePriv.setUid(uuid());
        }
    }


    @Override
    public List<AuthRolePriv> fetchByRoleId(Long roleId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("roleId", roleId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<AuthRolePriv> fetchByPrivId(Long privId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("privId", privId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    public AuthRolePriv fetchByRoleIdAndPrivId(Long roleId, Long privId) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("roleId", roleId)
                .and("privId", privId)
                .build();

        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    public List<AuthPriv> fetchPrivsByRole(AuthRole role) {
        List<AuthRolePriv> rolePrivs = fetchByRoleId(role.getId());

        List<AuthPriv> result = new ArrayList<>();

        for (AuthRolePriv rolePriv : rolePrivs) {
            AuthPriv priv = authPrivService.fetchById(rolePriv.getPrivId());
            result.add(priv);
        }

        return result;
    }


    public AuthRolePriv create(AuthRole role, AuthPriv priv) {
        AuthRolePriv rolePriv = fetchByRoleIdAndPrivId(role.getId(), priv.getId());

        if (rolePriv == null) {
            rolePriv = new AuthRolePriv();
            rolePriv.setRoleId(role.getId());
            rolePriv.setPrivId(priv.getId());
            rolePriv = add(rolePriv);
        }

        return rolePriv;
    }


    public void remove(AuthRole role, AuthPriv priv) {
        AuthRolePriv rolePriv = fetchByRoleIdAndPrivId(role.getId(), priv.getId());
        if (rolePriv != null) {
            remove(rolePriv);
        }
    }
}
