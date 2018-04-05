/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AuthPriv;
import com.bryllyant.kona.app.entity.AuthRole;
import com.bryllyant.kona.app.entity.AuthRolePriv;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface AuthRolePrivService extends KService, KEntityService<AuthRolePriv> {
	String SERVICE_PATH = "rpc/AuthRolePrivService";

    AuthRolePriv fetchByRoleIdAndPrivId(Long roleId, Long privId);

    List<AuthRolePriv> fetchByRoleId(Long roleId);

    List<AuthRolePriv> fetchByPrivId(Long privId);

    List<AuthPriv> fetchPrivsByRole(AuthRole role);

    AuthRolePriv create(AuthRole role, AuthPriv priv);

    void remove(AuthRole role, AuthPriv priv);
}
