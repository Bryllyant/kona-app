package com.bryllyant.kona.app.api.model.sales.campaign;

import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.app.entity.PushProvider;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class PushProviderModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private Push.Platform platform;
    private String serverKey;
    private String serverSecret;
    private String endpoint;
    private Boolean sandbox;
    private Date createdDate;
    private Date updatedDate;


    public static PushProviderModel from(PushProvider provider) {
        PushProviderModel model = new PushProviderModel();
        model.setUid(provider.getUid());
        return model;
    }

    public static PushProviderModel create(String uid) {
        PushProviderModel model = new PushProviderModel();
        model.setUid(uid);
        return model;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.set("uid", uid);
    }

    public Push.Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Push.Platform platform) {
        this.set("platform", platform);
    }

    public String getServerKey() {
        return serverKey;
    }

    public void setServerKey(String serverKey) {
        this.set("serverKey", serverKey);
    }

    public String getServerSecret() {
        return serverSecret;
    }

    public void setServerSecret(String serverSecret) {
        this.set("serverSecret", serverSecret);
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.set("endpoint", endpoint);
    }

    public Boolean getSandbox() {
        return sandbox;
    }

    public void setSandbox(Boolean sandbox) {
        this.set("sandbox", sandbox);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.set("updatedDate", updatedDate);
    }
}

