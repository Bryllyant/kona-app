/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.User;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * UserException.
 */

@SuppressWarnings("serial")
public class UserException extends RuntimeException
        implements Serializable {

    private User user = null;

    private Map<String,String> errorMap = 
            new LinkedHashMap<String,String>();

    public UserException() {
    }

    public UserException(String message) {
        super(message);
        addError("_SYSTEM", message);
    }

    public UserException(String message, Map<String,String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public UserException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    public Map<String,String> getErrorMap() {
        return errorMap;
    }

    public void addError(String field, String error) {
        errorMap.put(field, error);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
