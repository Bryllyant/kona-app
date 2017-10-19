/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AuthCode;
import com.bryllyant.kona.remote.service.KService;

public interface AuthCodeService extends KService, KAuthCodeService<AuthCode> {
	public static final String SERVICE_PATH = "rpc/AuthCodeService";
	
}
