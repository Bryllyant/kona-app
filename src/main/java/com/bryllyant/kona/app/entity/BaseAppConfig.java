package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BaseAppConfig extends KBaseEntity implements Serializable {
    public enum Env {
        DEV,
        QA,
        BETA,
        TEST,
        PROD
    }
}