/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.PushNotificationDelivery;
import com.bryllyant.kona.app.entity.PushNotificationMessage;
import com.bryllyant.kona.remote.service.KService;

public interface PushNotificationMessageService 
		extends KService, KPushNotificationMessageService<PushNotificationMessage, PushNotificationDelivery> {
	public static final String SERVICE_PATH = "rpc/PushNotificationMessageService";
	
}
