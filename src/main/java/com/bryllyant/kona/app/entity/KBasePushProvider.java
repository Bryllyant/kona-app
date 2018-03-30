package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBasePushProvider extends KBaseEntity implements KPushProvider {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private KPush.Platform platform;
    private String serverKey;
    private String serverSecret;
    private String endpoint;
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
    public KPush.Platform getPlatform() {
        return platform;
    }

    @Override
    public void setPlatform(KPush.Platform platform) {
        this.platform = platform;
    }

    @Override
    public String getServerKey() {
        return serverKey;
    }

    @Override
    public void setServerKey(String serverKey) {
        this.serverKey = serverKey;
    }

    @Override
    public String getServerSecret() {
        return serverSecret;
    }

    @Override
    public void setServerSecret(String serverSecret) {
        this.serverSecret = serverSecret;
    }

    @Override
    public String getEndpoint() {
        return endpoint;
    }

    @Override
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
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
