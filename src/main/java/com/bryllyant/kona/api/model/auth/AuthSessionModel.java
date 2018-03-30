package com.bryllyant.kona.api.model.auth;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.data.model.KJsonModel;

import javax.validation.constraints.NotNull;

public class AuthSessionModel extends KJsonModel {
    private static final long serialVersionUID = 1L;



    /**
     * User object
     */
    @NotNull
    @RestdocsNotExpanded
    private UserModel user;

    /**
     * Token object
     */
    @NotNull
    @RestdocsNotExpanded
    private TokenModel token;





    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.set("user", user);
    }

    public TokenModel getToken() {
        return token;
    }

    public void setToken(TokenModel token) {
        this.set("token", token);
    }
}
