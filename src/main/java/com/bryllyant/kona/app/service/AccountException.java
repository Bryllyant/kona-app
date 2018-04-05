/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Account;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class AccountException extends RuntimeException implements Serializable  {
	private Account account = null;

    private Map<String,String> errorMap = new LinkedHashMap<>();

    public AccountException() {
    }

    public AccountException(String message) {
        super(message);
    }

    public AccountException(String message, Map<String,String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public AccountException(String ex, Throwable cause) {
        super(ex, cause);
    }

    public AccountException(Throwable cause) {
        super(cause);
    }

    public Map<String,String> getErrorMap() {
        return errorMap;
    }

    public void addError(String field, String error) {
        errorMap.put(field, error);
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
