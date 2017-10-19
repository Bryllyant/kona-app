package com.bryllyant.kona.app.api.model.auth;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.user.MeModel;
import com.bryllyant.kona.rest.model.KBaseModel;

import javax.validation.constraints.NotNull;

public class AuthSessionModel extends KBaseModel {
    private static final long serialVersionUID = 1L;

    // ---------------------------------------------------------------

    /**
     * User object
     */
    @NotNull
    @RestdocsNotExpanded
    private MeModel user;

    /**
     * Token object
     */
    @NotNull
    @RestdocsNotExpanded
    private TokenModel token;


    // ---------------------------------------------------------------


    public MeModel getUser() {
        return user;
    }

    public void setUser(MeModel user) {
        this.set("user", user);
    }

    public TokenModel getToken() {
        return token;
    }

    public void setToken(TokenModel token) {
        this.set("token", token);
    }
}
