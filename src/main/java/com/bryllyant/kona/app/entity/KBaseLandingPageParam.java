/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.entity;

import java.util.Date;


public class KBaseLandingPageParam extends KBaseEntity implements KLandingPageParam {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long landingPageId;
    private Long templateParamId;
    private Long fileId;
    private String value;
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
    public Long getLandingPageId() {
        return landingPageId;
    }

    @Override
    public void setLandingPageId(Long landingPageId) {
        this.landingPageId = landingPageId;
    }

    @Override
    public Long getTemplateParamId() {
        return templateParamId;
    }

    @Override
    public void setTemplateParamId(Long templateParamId) {
        this.templateParamId = templateParamId;
    }

    @Override
    public Long getFileId() {
        return fileId;
    }

    @Override
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
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
