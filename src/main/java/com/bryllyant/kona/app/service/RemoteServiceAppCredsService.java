/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.RemoteServiceAppCreds;
import com.bryllyant.kona.remote.service.KService;

public interface RemoteServiceAppCredsService extends KService, KRemoteServiceAppCredsService<RemoteServiceAppCreds> {
	public static final String SERVICE_PATH = "rpc/RemoteServiceAppCredsService";
	
}
