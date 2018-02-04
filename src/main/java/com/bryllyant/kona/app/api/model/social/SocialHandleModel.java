package com.bryllyant.kona.app.api.model.social;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class SocialHandleModel extends KJsonModel {
    private static final long serialVersionUID = 1L;

    public enum Network {
        FACEBOOK,
        TWITTER,
        INSTAGRAM,
        SNAPCHAT,
        LINKEDIN
    }

    private Network network;
    private String handle;
    private String url;

    public static SocialHandleModel from(Network network, String handle, String url) {
        return new SocialHandleModel(network, handle, url);
    }

    public SocialHandleModel() {
    }


    public SocialHandleModel(Network network, String handle, String url) {
        this.network = network;
        this.handle = handle;
        this.url = url;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.set("network", network);
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.set("handle", handle);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.set("url", url);
    }
}
