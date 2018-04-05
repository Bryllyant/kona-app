package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.NotificationDelivery;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface NotificationDeliveryService extends KService, KEntityService<NotificationDelivery> {
    String SERVICE_PATH = "rpc/NotificationDeliveryService";

    List<NotificationDelivery> fetchByNotificationId(Long notificationId);
}
