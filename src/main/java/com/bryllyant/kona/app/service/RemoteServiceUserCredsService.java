/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.RemoteServiceUserCreds;
import com.bryllyant.kona.remote.service.KService;

public interface RemoteServiceUserCredsService extends KService, KRemoteServiceUserCredsService<RemoteServiceUserCreds> {
	public static final String SERVICE_PATH = "rpc/RemoteServiceUserCredsService";
	
}
