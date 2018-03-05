/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AuthRole;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserRole;
import com.bryllyant.kona.remote.service.KService;

public interface UserRoleService extends KService, KUserRoleService<UserRole,User,AuthRole> {
	String SERVICE_PATH = "rpc/UserRoleService";
}
