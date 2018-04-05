/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.data.service.KServiceException;

import java.io.Serializable;

/**
 * AuthException.
 */

@SuppressWarnings("serial")
public class AuthException extends KServiceException
    implements Serializable {

    public enum Type implements Serializable {
        LOGIN_REQUIRED,
        INVALID_TOKEN,
        INVALID_USERNAME,
        INVALID_PASSWORD,
        MAX_LOGIN_ATTEMPTS,
        NOT_AUTHORIZED
    };

    private Type type = null;

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Type type) {
        super(message);
        this.type = type;
    }

    public AuthException(String ex, Type type, Throwable cause) {
        super(ex, cause);
        this.type = type;
    }

    public AuthException(Type type, Throwable cause) {
        super(cause);
        this.type = type;
    }

    public Type getType() {
        return (type);
    }

    public String toString() {
        return ("AuthException TYPE: " + type + "\n" + super.toString());
    }
}
