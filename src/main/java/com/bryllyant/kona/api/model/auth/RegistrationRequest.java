package com.bryllyant.kona.api.model.auth;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.api.model.user.PersonModel;
import com.bryllyant.kona.api.model.user.PersonModel;
import com.bryllyant.kona.api.model.user.UserModel;

public class RegistrationRequest extends PersonModel {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    @RestdocsNotExpanded
    private RegistrationMeta meta;
    

    public RegistrationRequest() {
        this.meta = new RegistrationMeta();
    }


    public UserModel toUserModel() {
        return UserModel.from(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.set("username", username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.set("password", password);
    }

    public RegistrationMeta getMeta() {
        return meta;
    }

    public void setMeta(RegistrationMeta meta) {
        this.set("meta", meta);
    }
}

