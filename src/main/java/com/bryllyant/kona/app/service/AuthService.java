/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.remote.service.KService;

public interface AuthService extends KService, KAuthService<User,Token> {
	public static final String SERVICE_PATH = "rpc/AuthService";
	
}
