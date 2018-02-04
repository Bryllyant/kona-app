/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.PushNotificationProvider;
import com.bryllyant.kona.remote.service.KService;

public interface PushNotificationProviderService
		extends KService, KPushNotificationProviderService<PushNotificationProvider> {
	public static final String SERVICE_PATH = "rpc/PushNotificationProviderService";
	
}
