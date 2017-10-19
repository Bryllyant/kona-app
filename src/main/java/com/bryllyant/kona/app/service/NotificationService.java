package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Notification;
import com.bryllyant.kona.remote.service.KService;

public interface NotificationService extends KService, KNotificationService<Notification> {
    public static final String SERVICE_PATH = "rpc/NotificationService";
}
