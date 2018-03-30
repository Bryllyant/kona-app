/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.entity;

import java.util.Date;

/**
 * KBaseEntityNameRule.
 */
public class KBaseEntityNameRule extends KBaseEntity implements KEntityNameRule {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private String pattern;
    private boolean blackListed;
    private boolean reserved;
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
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String getPattern() {
        return pattern;
    }

    @Override
    public void setPattern(String pattern) {
        this.pattern = pattern == null ? null : pattern.trim();
    }

    @Override
    public boolean isBlackListed() {
        return blackListed;
    }

    @Override
    public void setBlackListed(boolean blackListed) {
        this.blackListed = blackListed;
    }

    @Override
    public boolean isReserved() {
        return reserved;
    }

    @Override
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
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
