/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAuthPriv;
import com.bryllyant.kona.app.entity.KAuthRole;
import com.bryllyant.kona.app.entity.KAuthRolePriv;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;


public interface KAuthRolePrivService<
            AUTH_ROLE_PRIV extends KAuthRolePriv,
            AUTH_ROLE extends KAuthRole,
            AUTH_PRIV extends KAuthPriv
        >
        extends KService, KEntityService<AUTH_ROLE_PRIV> {

    AUTH_ROLE_PRIV fetchByUid(String uid);

    AUTH_ROLE_PRIV fetchByRoleIdAndPrivId(Long roleId, Long privId);

    List<AUTH_ROLE_PRIV> fetchByRoleId(Long roleId);

    List<AUTH_ROLE_PRIV> fetchByPrivId(Long privId);

    List<AUTH_PRIV> fetchPrivsByRole(AUTH_ROLE role);

    AUTH_ROLE_PRIV create(AUTH_ROLE role, AUTH_PRIV priv);

    void remove(AUTH_ROLE role, AUTH_PRIV priv);

}
