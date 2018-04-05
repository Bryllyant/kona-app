package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BaseDevice extends KBaseEntity implements Serializable {
    public enum Type {
        PHONE,
        WATCH,
        TABLET,
        DESKTOP,
        WEARABLE,
        SENSOR,
        OTHER
    }

    public enum Capability {
        BLE,
        RFID,
        WIFI,
        CELLULAR,
        GPS,
        CAMERA,
        AUDIO,
        MICROPHONE,
        ACCELEROMETER,
        GYROSCOPE
    }
}