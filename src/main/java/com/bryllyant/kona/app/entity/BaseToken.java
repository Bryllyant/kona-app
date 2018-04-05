package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BaseToken extends KBaseEntity implements Serializable {

    // BASIC - issued to an app (client) to identify it and allow access to public resources
    // BEARER - issued to an app (client) on behalf of a user to access user specific resources
    public enum Type {
        BASIC,
        BEARER
    }
}