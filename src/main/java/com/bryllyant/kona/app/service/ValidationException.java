/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.util.Map;
import java.util.LinkedHashMap;

import java.io.Serializable;

/**
 * ValidationException.
 */

@SuppressWarnings("serial")
public class ValidationException extends RuntimeException
        implements Serializable {

    private Map<String,String> errorMap = 
            new LinkedHashMap<String,String>();

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
        addError("_SYSTEM", message);
    }

    public ValidationException(String message, Map<String,String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public ValidationException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public Map<String,String> getErrorMap() {
        return errorMap;
    }

    public void addError(String field, String error) {
        errorMap.put(field, error);
    }
}
