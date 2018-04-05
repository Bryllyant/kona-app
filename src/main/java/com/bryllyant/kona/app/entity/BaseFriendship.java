package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BaseFriendship extends KBaseEntity implements Serializable {
    public enum Status {
        NONE,
        FRIENDS,
        PENDING,
        FOLLOWING,
        FOLLOWED,
        BLOCKING,
        BLOCKED
    }
}