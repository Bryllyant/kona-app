package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BasePush extends KBaseEntity implements Serializable {
    public enum Platform {
        // Apple Push Notification Service
        APNS,

        // Sandbox version of Apple Push Notification Service
        APNS_SANDBOX,

        // Amazon Device Messaging
        ADM,

        // Google Cloud Messaging
        GCM,

        // Baidu CloudMessaging Service
        BAIDU,

        // Windows Notification Service
        WNS,

        // Microsoft Push Notificaion Service
        MPNS;
    }
}