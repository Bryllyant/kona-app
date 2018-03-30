/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.entity;

import java.util.Date;

/**
 * KBaseAuthCode.
 */
public class KBaseAuthCode extends KBaseEntity implements KAuthCode {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Type type;
    private Long userId;
    private String code;
    private boolean valid;
    private Integer useCount;
    private Integer maxUseCount;
    private Date expirationDate;
    private Date confirmedDate;
    private Date lastAccessedDate;
    private Date createdDate;
    private Date updatedDate;
    
    @Override
	public Long getId() {
        return id;
    }

    @Override
	public void setId(Long id) {
        this.id = id;
    }

    @Override
	public Type getType() {
        return type;
    }

    @Override
	public void setType(Type type) {
        this.type = type;
    }

    @Override
	public Long getUserId() {
        return userId;
    }

    @Override
	public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
	public String getCode() {
        return code;
    }

    @Override
	public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    @Override
	public boolean isValid() {
        return valid;
    }

    @Override
	public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
	public Integer getUseCount() {
        return useCount;
    }

    @Override
	public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    @Override
	public Integer getMaxUseCount() {
        return maxUseCount;
    }

    @Override
	public void setMaxUseCount(Integer maxUseCount) {
        this.maxUseCount = maxUseCount;
    }

    @Override
	public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
	public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public Date getConfirmedDate() {
        return confirmedDate;
    }

    @Override
    public void setConfirmedDate(Date confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    @Override
	public Date getLastAccessedDate() {
        return lastAccessedDate;
    }

    @Override
	public void setLastAccessedDate(Date lastAccessedDate) {
        this.lastAccessedDate = lastAccessedDate;
    }

    @Override
	public Date getCreatedDate() {
        return createdDate;
    }

    @Override
	public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
	public Date getUpdatedDate() {
        return updatedDate;
    }

    @Override
	public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

}
