package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseAppConfig extends KBaseEntity implements KAppConfig {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long parentId;
    private Long appId;
    private Env env;
    private String name;
    private String value;
    private boolean overrideGlobal;
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
    public Long getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public Long getAppId() {
        return appId;
    }

    @Override
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    @Override
    public Env getEnv() {
        return env;
    }

    @Override
    public void setEnv(Env env) {
        this.env = env;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
    public boolean isOverrideGlobal() {
        return overrideGlobal;
    }

    @Override
    public void setOverrideGlobal(boolean overrideGlobal) {
        this.overrideGlobal = overrideGlobal;
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
