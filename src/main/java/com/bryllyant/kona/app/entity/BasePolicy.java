package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BasePolicy extends KBaseEntity implements Serializable {
    public enum Type {
        TERMS,
        PRIVACY
    }
}