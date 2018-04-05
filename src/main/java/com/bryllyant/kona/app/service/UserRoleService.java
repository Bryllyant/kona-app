/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AuthRole;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserRole;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface UserRoleService extends KService, KEntityService<UserRole> {
	String SERVICE_PATH = "rpc/UserRoleService";

    UserRole fetchByUserIdAndRoleId(Long userId, Long roleId);

    List<UserRole> fetchByRoleId(Long roleId);

    List<UserRole> fetchByUserId(Long userId);

    List<AuthRole> fetchRolesByUser(User user);

    UserRole create(User user, AuthRole role);

    void remove(User user, AuthRole role);
}
