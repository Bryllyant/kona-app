/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.remote.service.KService;

public interface TokenService extends KService, KTokenService<Token> {
	public static final String SERVICE_PATH = "rpc/TokenService";
	
}
