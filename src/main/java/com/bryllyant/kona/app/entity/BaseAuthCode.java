package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BaseAuthCode extends KBaseEntity implements Serializable {
    public enum Type {
        EMAIL_CONFIRMATION,
        MOBILE_CONFIRMATION,
        PHONE_CONFIRMATION,
        PASSWORD_RESET
    }
}