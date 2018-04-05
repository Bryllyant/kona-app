package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BaseScript extends KBaseEntity implements Serializable {
    public enum Language {
        GROOVY,
        JAVASCRIPT
    }

    public enum ReturnType {
        STRING,
        NUMBER,
        BOOLEAN,
        LIST,
        MAP,
        OBJECT,
        DATE
    }
}
