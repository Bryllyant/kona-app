package com.bryllyant.kona.app.model;

import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.data.model.KBaseModel;

import java.util.Date;

public class AuthSession extends KBaseModel {
    private static final long serialVersionUID = 1L;

    private User user;
    private Token token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public boolean isExpired() {
        if (token == null || token.getAccessExpirationDate() == null) return false;
        return token.getAccessExpirationDate().getTime() < new Date().getTime();
    }
}
