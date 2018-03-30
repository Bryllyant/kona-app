package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBasePushDevice extends KBaseEntity implements KPushDevice {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long providerId;
    private Long userId;
    private Long deviceId;
    private KPush.Platform platform;
    private String token;
    private String endpoint;
    private boolean enabled;
    private boolean sandbox;
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
    public Long getProviderId() {
        return providerId;
    }

    @Override
    public void setProviderId(Long providerId) {
        this.providerId = providerId;
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
    public Long getDeviceId() {
        return deviceId;
    }

    @Override
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public KPush.Platform getPlatform() {
        return platform;
    }

    @Override
    public void setPlatform(KPush.Platform platform) {
        this.platform = platform;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isSandbox() {
        return sandbox;
    }

    @Override
    public void setSandbox(boolean sandbox) {
        this.sandbox = sandbox;
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
