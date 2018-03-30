/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAuthRole;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.entity.KUserRole;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractUserRoleService<
		    USER_ROLE extends KUserRole,
            USER_ROLE_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<USER_ROLE, USER_ROLE_EXAMPLE>,
			USER extends KUser,
		    AUTH_ROLE extends KAuthRole
        >
		extends KAbstractService<USER_ROLE,USER_ROLE_EXAMPLE,MAPPER>
		implements KUserRoleService<USER_ROLE, USER, AUTH_ROLE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractUserRoleService.class);

    protected abstract USER_ROLE getNewObject();
    protected abstract <S extends KUserService<USER>> S getUserService();
    protected abstract <S extends KAuthRoleService<AUTH_ROLE>> S getAuthRoleService();

	@Override
	public void validate(USER_ROLE userRole) {
		if (userRole.getCreatedDate() == null) {
            userRole.setCreatedDate(new Date());
		}

        userRole.setUpdatedDate(new Date());

		if (userRole.getUid() == null) {
		    userRole.setUid(uuid());
        }


	}


	@Override
	public USER_ROLE fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

    @Override
    public List<USER_ROLE> fetchByRoleId(Long roleId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("roleId", roleId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<USER_ROLE> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    public USER_ROLE fetchByUserIdAndRoleId(Long userId, Long roleId) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("userId", userId)
                .and("roleId", roleId)
                .build();

        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    public List<AUTH_ROLE> fetchRolesByUser(USER user) {
        List<USER_ROLE> userRoles = fetchByUserId(user.getId());

        List<AUTH_ROLE> result = new ArrayList<>();

        for (USER_ROLE userRole : userRoles) {
            AUTH_ROLE role = getAuthRoleService().fetchById(userRole.getRoleId());
            result.add(role);
        }

        return result;
    }


    public USER_ROLE create(USER user, AUTH_ROLE role) {
        USER_ROLE userRole = fetchByUserIdAndRoleId(user.getId(), role.getId());

        if (userRole == null) {
            userRole = getNewObject();
            userRole.setUserId(user.getId());
            userRole.setRoleId(role.getId());
            userRole = add(userRole);
        }

        return userRole;
    }


    public void remove(USER user, AUTH_ROLE role) {
        USER_ROLE userRole = fetchByUserIdAndRoleId(user.getId(), role.getId());

        if (userRole != null) {
            remove(userRole);
        }
    }
}
