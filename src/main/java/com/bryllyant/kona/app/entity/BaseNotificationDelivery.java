package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

public class BaseNotificationDelivery extends KBaseEntity {
    public enum Channel {
        IN_APP,
        Email,
        Sms,
        Push
    }
}