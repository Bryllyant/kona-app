package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BaseEmailEvent extends KBaseEntity implements Serializable {
    public enum Type {
        ATTEMPTED,
        FAILED,
        DELIVERED,
        BOUNCED,
        COMPLAINED,
        UNSUBSCRIBED,
        OPENED,
        FORWARDED,
        PRINTED,
        CLICKED
    }
}