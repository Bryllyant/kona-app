package com.bryllyant.kona.app.service;

import java.util.List;

import com.bryllyant.kona.app.entity.KNotificationDelivery;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

public interface KNotificationDeliveryService<NotificationDelivery extends KNotificationDelivery> 
		extends KService, KEntityService<NotificationDelivery> {
    
    public static final String SERVICE_PATH = "rpc/kona/NotificationDeliveryService";

    public List<NotificationDelivery> fetchByNotificationId(Long notificationId);
    
    public NotificationDelivery fetchByCode(String code);
}
