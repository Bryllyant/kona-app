/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.RemoteService;
import com.bryllyant.kona.remote.service.KService;

public interface RemoteServiceService extends KService, KRemoteServiceService<RemoteService> {
	public static final String SERVICE_PATH = "rpc/RemoteServiceService";
	
}
