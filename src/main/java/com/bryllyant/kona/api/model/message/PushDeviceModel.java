package com.bryllyant.kona.api.model.message;

import com.bryllyant.kona.api.model.device.DeviceModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.app.entity.PushDevice;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class PushDeviceModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private PushProviderModel provider;
    private UserModel user;
    private DeviceModel device;
    private Push.Platform platform;
    private String token;
    private String endpoint;
    private Boolean enabled;
    private Boolean sandbox;
    private Date createdDate;
    private Date updatedDate;


    public static PushDeviceModel from(PushDevice device) {
        PushDeviceModel model = new PushDeviceModel();
        model.setUid(device.getUid());
        return model;
    }

    public static PushDeviceModel create(String uid) {
        PushDeviceModel model = new PushDeviceModel();
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

    public PushProviderModel getProvider() {
        return provider;
    }

    public void setProvider(PushProviderModel provider) {
        this.set("provider", provider);
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.set("user", user);
    }

    public DeviceModel getDevice() {
        return device;
    }

    public void setDevice(DeviceModel device) {
        this.set("device", device);
    }

    public Push.Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Push.Platform platform) {
        this.set("platform", platform);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.set("token", token);
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.set("endpoint", endpoint);
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.set("enabled", enabled);
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
