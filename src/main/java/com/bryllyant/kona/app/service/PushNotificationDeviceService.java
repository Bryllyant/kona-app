/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.PushNotificationDevice;
import com.bryllyant.kona.remote.service.KService;

public interface PushNotificationDeviceService extends KService, KPushNotificationDeviceService<PushNotificationDevice> {
	public static final String SERVICE_PATH = "rpc/PushNotificationDeviceService";
	
}
