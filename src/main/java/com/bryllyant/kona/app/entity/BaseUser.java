package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

public class BaseUser extends KBaseEntity {
    public enum Type {
        SYSTEM,
        USER,
        TEST,
    }

    public enum Presence {
        ONLINE,
        AWAY,
        BUSY,
        INVISIBLE,
        STREAMING,
        OFFLINE
    }

    public enum Gender {
        MALE,
        FEMALE,
        NON_BINARY,
        TRANSGENDER,
        INTERSEX,
        GENDER_NON_CONFORMING,
        OTHER,
        DECLINE
    }
}

