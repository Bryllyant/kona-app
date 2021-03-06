/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.api.model.user;

import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.model.SocialHandle;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;
import java.util.List;

public class PersonModel extends KJsonModel {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String displayName;
    private String photoUrl;
    private String thumbnailUrl;
    private User.Gender gender;
    private Date birthDate;
    private String locale;
    private String timeZone;
    private String url;
    private String email;
    private String phoneNumber;
    private String mobileNumber;
    private List<SocialHandle> socialHandles;

    public static PersonModel create(String firstName, String lastName, String photoUrl) {
        PersonModel model = new PersonModel();
        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setPhotoUrl(photoUrl);
        return model;
    }

    public PersonModel() {

    }

    public PersonModel(String firstName, String lastName, String displayName, String photoUrl,
                       String thumbnailUrl, User.Gender gender, Date birthDate, String locale,
                       String timeZone, String url, String email, String phoneNumber,
                       String mobileNumber, List<SocialHandle> socialHandles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.photoUrl = photoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.gender = gender;
        this.birthDate = birthDate;
        this.locale = locale;
        this.timeZone = timeZone;
        this.url = url;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.socialHandles = socialHandles;
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

    public User.Gender getGender() {
        return gender;
    }

    public void setGender(User.Gender gender) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.set("url", url);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.set("email", email);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.set("phoneNumber", phoneNumber);
    }


    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.set("mobileNumber", mobileNumber);
    }

    public List<SocialHandle> getSocialHandles() {
        return socialHandles;
    }

    public void setSocialHandles(List<SocialHandle> socialHandles) {
        this.set("socialHandles", socialHandles);
    }
}
