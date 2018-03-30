/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.util.Map;
import java.util.LinkedHashMap;

import java.io.Serializable;
import com.bryllyant.kona.app.entity.KAccount;

/**
 * KAccountException.
 */

@SuppressWarnings("serial")
public class KAccountException extends RuntimeException 
        implements Serializable  {
	private KAccount account = null;

    private Map<String,String> errorMap = 
            new LinkedHashMap<String,String>();

    public KAccountException() {
    }

    public KAccountException(String message) {
        super(message);
        addError("_SYSTEM", message);
    }

    public KAccountException(String message, Map<String,String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public KAccountException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public KAccountException(Throwable cause) {
        super(cause);
    }

    public Map<String,String> getErrorMap() {
        return errorMap;
    }

    public void addError(String field, String error) {
        errorMap.put(field, error);
    }

    public void setAccount(KAccount account) {
        this.account = account;
    }

    public KAccount getAccount() {
        return account;
    }
}
