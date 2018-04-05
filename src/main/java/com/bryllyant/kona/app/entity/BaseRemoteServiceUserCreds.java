package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BaseRemoteServiceUserCreds extends KBaseEntity implements Serializable {
    public enum AuthType {
        OAUTH,
        OAUTH2,
        CREDENTIALS
    }
}