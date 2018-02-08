/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.PushNotificationDelivery;
import com.bryllyant.kona.app.entity.PushNotificationDevice;
import com.bryllyant.kona.app.entity.PushNotificationMessage;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.remote.service.KService;

public interface PushNotificationDeliveryService extends
        KService,
        KPushNotificationDeliveryService<PushNotificationDelivery,PushNotificationDevice,PushNotificationMessage> {

	public static final String SERVICE_PATH = "rpc/PushNotificationDeliveryService";
}
