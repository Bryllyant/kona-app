/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.model.user;

import com.bryllyant.kona.app.entity.KUserRole;

import java.util.Set;

public class MeModel extends UserModel {
    private static final long serialVersionUID = 1L;

    private Boolean emailVerified;
    private Boolean mobileVerified;
    private Set<String> roles;

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.set("emailVerified", emailVerified);
    }

    public Boolean getMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(Boolean mobileVerified) {
        this.set("mobileVerified", mobileVerified);
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.set("roles", roles);
    }

}
