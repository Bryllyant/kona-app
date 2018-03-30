package com.bryllyant.kona.app.model;

import com.bryllyant.kona.app.entity.KToken;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.model.KBaseModel;

import java.util.Date;

public class KAuthSession<USER extends KUser, TOKEN extends KToken> extends KBaseModel {
    private static final long serialVersionUID = 1L;

    private USER user;
    private TOKEN token;

    public USER getUser() {
        return user;
    }

    public void setUser(USER user) {
        this.user = user;
    }

    public TOKEN getToken() {
        return token;
    }

    public void setToken(TOKEN token) {
        this.token = token;
    }

    public boolean isExpired() {
        if (token == null || token.getAccessExpirationDate() == null) return false;
        return token.getAccessExpirationDate().getTime() < new Date().getTime();
    }

}
