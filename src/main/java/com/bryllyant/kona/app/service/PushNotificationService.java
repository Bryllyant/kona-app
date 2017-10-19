/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.PushNotification;
import com.bryllyant.kona.remote.service.KService;

public interface PushNotificationService extends KService, KPushNotificationService<PushNotification> {
	public static final String SERVICE_PATH = "rpc/PushNotificationService";
	
}
