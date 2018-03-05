package com.bryllyant.kona.app.api.model.device;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.user.UserModel;

public class UserDeviceModel extends DeviceModel {

    private static final long serialVersionUID = 1L;



    @RestdocsNotExpanded
    private UserModel user;

    private String name;



    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.set("user", user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.set("name", name);
    }



}

