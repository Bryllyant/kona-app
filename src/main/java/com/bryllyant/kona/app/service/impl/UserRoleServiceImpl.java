/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.UserRoleMapper;
import com.bryllyant.kona.app.entity.AuthRole;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserRole;
import com.bryllyant.kona.app.entity.UserRoleExample;
import com.bryllyant.kona.app.service.AuthRoleService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.UserRoleService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(UserRoleService.SERVICE_PATH)
public class UserRoleServiceImpl
		extends KAbstractService<UserRole,UserRoleExample,UserRoleMapper>
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
    public void validate(UserRole userRole) {
        if (userRole.getCreatedDate() == null) {
            userRole.setCreatedDate(new Date());
        }

        userRole.setUpdatedDate(new Date());

        if (userRole.getUid() == null) {
            userRole.setUid(uuid());
        }
    }

    @Override
    public List<UserRole> fetchByRoleId(Long roleId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("roleId", roleId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<UserRole> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(filter);
    }

    public UserRole fetchByUserIdAndRoleId(Long userId, Long roleId) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("userId", userId)
                .and("roleId", roleId)
                .build();

        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    public List<AuthRole> fetchRolesByUser(User user) {
        List<UserRole> userRoles = fetchByUserId(user.getId());

        List<AuthRole> result = new ArrayList<>();

        for (UserRole userRole : userRoles) {
            AuthRole role = authRoleService.fetchById(userRole.getRoleId());
            result.add(role);
        }

        return result;
    }


    public UserRole create(User user, AuthRole role) {
        UserRole userRole = fetchByUserIdAndRoleId(user.getId(), role.getId());

        if (userRole == null) {
            userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(role.getId());
            userRole = add(userRole);
        }

        return userRole;
    }


    public void remove(User user, AuthRole role) {
        UserRole userRole = fetchByUserIdAndRoleId(user.getId(), role.getId());

        if (userRole != null) {
            remove(userRole);
        }
    }

}
