package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.NotificationDelivery;
import com.bryllyant.kona.remote.service.KService;

public interface NotificationDeliveryService extends KService, KNotificationDeliveryService<NotificationDelivery> {
    public static final String SERVICE_PATH = "rpc/NotificationDeliveryService";
}
