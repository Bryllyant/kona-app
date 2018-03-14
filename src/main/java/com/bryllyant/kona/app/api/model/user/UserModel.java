/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.model.user;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.account.AccountModel;
import com.bryllyant.kona.app.api.model.geo.position.PositionModel;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.data.model.KEntityModel;

import java.util.Date;
import java.util.Set;

public class UserModel extends PersonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;
    
    private String uid;
    
    private String username;

    @RestdocsNotExpanded
    private UserModel parent;

    @RestdocsNotExpanded
    private AccountModel account;

    private Set<String> roles;

    private KUser.Presence presence;

    @RestdocsNotExpanded
    private PositionModel position;

    private Boolean enabled;

    private Boolean emailVerified;

    private Boolean mobileVerified;

    private Double distance;

    private Date createdDate;

    private Date deletedDate;


    public static UserModel from(PersonModel person) {
        UserModel model = new UserModel();
        model.setFirstName(person.getFirstName());
        model.setLastName(person.getLastName());
        model.setDisplayName(person.getDisplayName());
        model.setPhotoUrl(person.getPhotoUrl());
        model.setThumbnailUrl(person.getThumbnailUrl());
        model.setGender(person.getGender());
        model.setBirthDate(person.getBirthDate());
        model.setLocale(person.getLocale());
        model.setTimeZone(person.getTimeZone());
        model.setMobileNumber(person.getMobileNumber());
        model.setEmail(person.getEmail());
        model.setSocialHandles(person.getSocialHandles());
        return model;
    }

    public static UserModel from(User user) {
        UserModel model = new UserModel();
        model.setUid(user.getUid());
        model.setDisplayName(user.getDisplayName());
        return model;
    }

    public static UserModel create(String uid) {
        return create(uid, null, null, null, null);
    }


    public static UserModel create(String uid, String username, String firstName, String lastName, String photoUrl) {
        UserModel model = new UserModel();
        model.setUid(uid);
        model.setUsername(username);
        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setPhotoUrl(photoUrl);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.set("username", username);
    }

    public UserModel getParent() {
        return parent;
    }

    public void setParent(UserModel parent) {
        this.set("parent", parent);
    }

    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(AccountModel account) {
        this.set("account", account);
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.set("roles", roles);
    }

    public KUser.Presence getPresence() {
        return presence;
    }

    public void setPresence(KUser.Presence presence) {
        this.set("presence", presence);
    }

    public PositionModel getPosition() {
        return position;
    }

    public void setPosition(PositionModel position) {
        this.set("position", position);
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.set("enabled", enabled);
    }

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

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.set("distance", distance);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.set("deletedDate", deletedDate);
    }
}
