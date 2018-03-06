/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.model.user;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.account.AccountModel;
import com.bryllyant.kona.app.api.model.app.AppModel;
import com.bryllyant.kona.app.api.model.device.DeviceModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignModel;
import com.bryllyant.kona.app.entity.Registration;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class RegistrationModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;
    
    private String uid;

    @RestdocsNotExpanded
    private AppModel app;
    @RestdocsNotExpanded
    private AccountModel account;
    @RestdocsNotExpanded
    private UserModel user;
    @RestdocsNotExpanded
    private DeviceModel device;
    @RestdocsNotExpanded
    private CampaignModel campaign;
    @RestdocsNotExpanded
    private UserModel referredBy;

    private String appClientId;
    private String username;
    private String hostname;
    private String userAgent;
    private String osName;
    private String osVersion;
    private String appVersion;
    private String appBuild;
    private Long signupTime;
    private Double latitude;
    private Double longitude;
    private Boolean emailVerified;
    private Boolean emailPending;
    private Boolean mobileVerified;
    private Boolean mobilePending;
    private Date remindedDate;
    private Date registeredDate;
    private Date deactivatedDate;
    private Date deletedDate;
    private Date createdDate;


    public static RegistrationModel from(Registration registration) {
        RegistrationModel model = new RegistrationModel();
        model.fromBean(registration);
        return model;
    }

    public static RegistrationModel create(String uid) {
        return create(uid, null);
    }


    public static RegistrationModel create(String uid, String username) {
        RegistrationModel model = new RegistrationModel();
        model.setUid(uid);
        model.setUsername(username);
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

    public AppModel getApp() {
        return app;
    }

    public void setApp(AppModel app) {
        this.set("app", app);
    }

    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(AccountModel account) {
        this.set("account", account);
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.set("user", user);
    }

    public DeviceModel getDevice() {
        return device;
    }

    public void setDevice(DeviceModel device) {
        this.set("device", device);
    }

    public CampaignModel getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignModel campaign) {
        this.set("campaign", campaign);
    }

    public UserModel getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(UserModel referredBy) {
        this.set("referredBy", referredBy);
    }

    public String getAppClientId() {
        return appClientId;
    }

    public void setAppClientId(String appClientId) {
        this.set("appClientId", appClientId);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.set("username", username);
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.set("hostname", hostname);
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.set("userAgent", userAgent);
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.set("osName", osName);
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.set("osVersion", osVersion);
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.set("appVersion", appVersion);
    }

    public String getAppBuild() {
        return appBuild;
    }

    public void setAppBuild(String appBuild) {
        this.set("appBuild", appBuild);
    }

    public Long getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(Long signupTime) {
        this.set("signupTime", signupTime);
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.set("latitude", latitude);
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.set("longitude", longitude);
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.set("emailVerified", emailVerified);
    }

    public Boolean getEmailPending() {
        return emailPending;
    }

    public void setEmailPending(Boolean emailPending) {
        this.set("emailPending", emailPending);
    }

    public Boolean getMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(Boolean mobileVerified) {
        this.set("mobileVerified", mobileVerified);
    }

    public Boolean getMobilePending() {
        return mobilePending;
    }

    public void setMobilePending(Boolean mobilePending) {
        this.set("mobilePending", mobilePending);
    }

    public Date getRemindedDate() {
        return remindedDate;
    }

    public void setRemindedDate(Date remindedDate) {
        this.set("remindedDate", remindedDate);
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.set("registeredDate", registeredDate);
    }

    public Date getDeactivatedDate() {
        return deactivatedDate;
    }

    public void setDeactivatedDate(Date deactivatedDate) {
        this.set("deactivatedDate", deactivatedDate);
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.set("deletedDate", deletedDate);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }
}
