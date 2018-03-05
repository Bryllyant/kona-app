/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AuthPriv;
import com.bryllyant.kona.remote.service.KService;

public interface AuthPrivService extends KService, KAuthPrivService<AuthPriv> {
	public static final String SERVICE_PATH = "rpc/AuthPrivService";
}
