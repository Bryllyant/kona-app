package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Notification;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.Date;
import java.util.List;

public interface NotificationService extends KService, KEntityService<Notification> {
    String SERVICE_PATH = "rpc/NotificationService";

    List<Notification> fetchByUserIdSinceUid(Long userId, String uid, Integer limit);

    void notifyEvent(Long userId, String event, Date eventDate);
}
