/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.model.user;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.account.AccountModel;
import com.bryllyant.kona.app.api.model.position.PositionModel;
import com.bryllyant.kona.app.entity.KUserPresence;
import com.bryllyant.kona.data.model.KJsonModel;
import com.bryllyant.kona.data.model.KEntityModel;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;
    
    // ----------------------------------------------------------------------

    @NotNull
    private String uid;
    
    @NotNull
    private String username;

    private String email;
    private String mobileNumber;

    @RestdocsNotExpanded
    private UserModel parent;

    @NotNull
    @RestdocsNotExpanded
    private AccountModel account;


    private String firstName;
    private String lastName;
    private String displayName;
    private String photoUrl;
    private String thumbnailUrl;
    private String gender;
    private Date birthDate;
    private String locale;
    private String timeZone;

    private KUserPresence presence;

    @RestdocsNotExpanded
    private PositionModel position;

    private Double distance;

    @NotNull
    private Date createdDate;
    
    // ----------------------------------------------------------------------

    public static UserModel create(String uid) {
        return create(uid, null, null, null, null);
    }

    // ----------------------------------------------------------------------

    public static UserModel create(String uid, String username, String firstName, String lastName, String photoUrl) {
        UserModel model = new UserModel();
        model.setUid(uid);
        model.setUsername(username);
        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setPhotoUrl(photoUrl);
        return model;
    }


    // ----------------------------------------------------------------------


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.set("email", email);
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.set("mobileNumber", mobileNumber);
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.set("firstName", firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.set("lastName", lastName);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.set("displayName", displayName);
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.set("photoUrl", photoUrl);
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.set("thumbnailUrl", thumbnailUrl);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.set("gender", gender);
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.set("birthDate", birthDate);
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.set("locale", locale);
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.set("timeZone", timeZone);
    }

    public KUserPresence getPresence() {
        return presence;
    }

    public void setPresence(KUserPresence presence) {
        this.set("presence", presence);
    }

    public PositionModel getPosition() {
        return position;
    }

    public void setPosition(PositionModel position) {
        this.set("position", position);
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
}
