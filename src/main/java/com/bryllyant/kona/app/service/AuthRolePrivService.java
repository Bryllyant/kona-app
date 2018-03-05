/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AuthPriv;
import com.bryllyant.kona.app.entity.AuthRole;
import com.bryllyant.kona.app.entity.AuthRolePriv;
import com.bryllyant.kona.remote.service.KService;

public interface AuthRolePrivService extends KService, KAuthRolePrivService<AuthRolePriv,AuthRole,AuthPriv> {
	String SERVICE_PATH = "rpc/AuthRolePrivService";
}
