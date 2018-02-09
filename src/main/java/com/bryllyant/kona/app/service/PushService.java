/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.remote.service.KService;

public interface PushService
		extends KService, KPushService<Push> {
	public static final String SERVICE_PATH = "rpc/PushService";
	
}
