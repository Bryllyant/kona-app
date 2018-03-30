/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAuthRole;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.entity.KUserRole;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;


public interface KUserRoleService<
            USER_ROLE extends KUserRole,
            USER extends KUser,
            AUTH_ROLE extends KAuthRole
        >
        extends KService, KEntityService<USER_ROLE> {

    USER_ROLE fetchByUid(String uid);

    USER_ROLE fetchByUserIdAndRoleId(Long userId, Long roleId);

    List<USER_ROLE> fetchByRoleId(Long roleId);

    List<USER_ROLE> fetchByUserId(Long userId);

    List<AUTH_ROLE> fetchRolesByUser(USER user);

    USER_ROLE create(USER user, AUTH_ROLE role);

    void remove(USER user, AUTH_ROLE role);

}
