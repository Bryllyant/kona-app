/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.api.model.user;

import com.bryllyant.kona.api.model.geo.place.PlaceModel;

import java.util.Date;

public class ContactModel extends PersonModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private UserModel owner;
    private UserModel refUser;
    private PlaceModel place;
    private Boolean emailVerified;
    private Boolean mobileVerified;
    private Date invitedDate;
    private Date registeredDate;
    private Date createdDate;

    public static ContactModel create(String uid) {
        return create(uid, null, null, null);
    }

    public static ContactModel create(String uid, String firstName, String lastName, String photoUrl) {
        ContactModel model = new ContactModel();
        model.setUid(uid);
        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setPhotoUrl(photoUrl);
        return model;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.set("uid", uid);
    }

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.set("owner", owner);
    }

    public UserModel getRefUser() {
        return refUser;
    }

    public void setRefUser(UserModel refUser) {
        this.set("refUser", refUser);
    }

    public PlaceModel getPlace() {
        return place;
    }

    public void setPlace(PlaceModel place) {
        this.set("place", place);
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

    public Date getInvitedDate() {
        return invitedDate;
    }

    public void setInvitedDate(Date invitedDate) {
        this.set("invitedDate", invitedDate);
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.set("registeredDate", registeredDate);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }
}
