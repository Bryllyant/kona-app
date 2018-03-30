package com.bryllyant.kona.app.model;

import com.bryllyant.kona.data.model.KBaseModel;

import java.io.Serializable;

public class SocialHandle extends KBaseModel implements Serializable {

    public enum Network {
            FACEBOOK,
            TWITTER,
            INSTAGRAM,
            SNAPCHAT,
            LINKEDIN,
    }

    private Network network;
    private String handle;
    private String url;

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
