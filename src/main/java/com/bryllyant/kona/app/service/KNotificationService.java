package com.bryllyant.kona.app.service;


import java.util.Date;
import java.util.List;

import com.bryllyant.kona.app.entity.KNotification;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

public interface KNotificationService<Notification extends KNotification> extends KService, KEntityService<Notification> {
    public static final String SERVICE_PATH = "rpc/kona/NotificationService";

    public Notification fetchByUid(String uid);

	public List<Notification> fetchByUserIdSinceUid(Long userId, String uid, Integer limit);

	public void notifyEvent(Long userId, String event, Date eventDate);
}
