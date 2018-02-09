/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.PushProvider;
import com.bryllyant.kona.remote.service.KService;

public interface PushProviderService
		extends KService, KPushProviderService<PushProvider> {
	public static final String SERVICE_PATH = "rpc/PushProviderService";
	
}
