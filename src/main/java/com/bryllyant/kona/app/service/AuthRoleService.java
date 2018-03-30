/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AuthRole;
import com.bryllyant.kona.remote.service.KService;

public interface AuthRoleService extends KService, KAuthRoleService<AuthRole> {
	public static final String SERVICE_PATH = "rpc/AuthRoleService";
}
