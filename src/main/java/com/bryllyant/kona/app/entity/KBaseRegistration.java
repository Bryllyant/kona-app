/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseRegistration extends KBaseEntity implements KRegistration {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long appId;
    private Long accountId;
    private Long userId;
    private Long deviceId;
    private Long campaignChannelId;
    private Long referredById;
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
    private boolean emailVerified;
    private boolean emailPending;
    private boolean mobileVerified;
    private boolean mobilePending;
    private Date remindedDate;
    private Date registeredDate;
    private Date deletedDate;
    private Date updatedDate;
    private Date createdDate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public Long getAppId() {
        return appId;
    }

    @Override
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    @Override
    public Long getAccountId() {
        return accountId;
    }

    @Override
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Long getDeviceId() {
        return deviceId;
    }

    @Override
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public Long getCampaignChannelId() {
        return campaignChannelId;
    }

    @Override
    public void setCampaignChannelId(Long campaignChannelId) {
        this.campaignChannelId = campaignChannelId;
    }

    @Override
    public Long getReferredById() {
        return referredById;
    }

    @Override
    public void setReferredById(Long referredById) {
        this.referredById = referredById;
    }

    @Override
    public String getAppClientId() {
        return appClientId;
    }

    @Override
    public void setAppClientId(String appClientId) {
        this.appClientId = appClientId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String getOsName() {
        return osName;
    }

    @Override
    public void setOsName(String osName) {
        this.osName = osName;
    }

    @Override
    public String getOsVersion() {
        return osVersion;
    }

    @Override
    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    @Override
    public String getAppVersion() {
        return appVersion;
    }

    @Override
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    @Override
    public String getAppBuild() {
        return appBuild;
    }

    @Override
    public void setAppBuild(String appBuild) {
        this.appBuild = appBuild;
    }

    @Override
    public Long getSignupTime() {
        return signupTime;
    }

    @Override
    public void setSignupTime(Long signupTime) {
        this.signupTime = signupTime;
    }

    @Override
    public Double getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public Double getLongitude() {
        return longitude;
    }

    @Override
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean isEmailVerified() {
        return emailVerified;
    }

    @Override
    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @Override
    public boolean isEmailPending() {
        return emailPending;
    }

    @Override
    public void setEmailPending(boolean emailPending) {
        this.emailPending = emailPending;
    }

    @Override
    public boolean isMobileVerified() {
        return mobileVerified;
    }

    @Override
    public void setMobileVerified(boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    @Override
    public boolean isMobilePending() {
        return mobilePending;
    }

    @Override
    public void setMobilePending(boolean mobilePending) {
        this.mobilePending = mobilePending;
    }

    @Override
    public Date getRemindedDate() {
        return remindedDate;
    }

    @Override
    public void setRemindedDate(Date remindedDate) {
        this.remindedDate = remindedDate;
    }

    @Override
    public Date getRegisteredDate() {
        return registeredDate;
    }

    @Override
    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    @Override
    public Date getDeletedDate() {
        return deletedDate;
    }

    @Override
    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Override
    public Date getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean isVerified() {
        return isMobileVerified() && isEmailVerified();
    }
    
    @Override
    public void setVerified(boolean verified) {
        setEmailVerified(verified);
        setMobileVerified(verified);
        if (verified) {
        	setEmailPending(false);
        	setMobilePending(false);
        }
    }

}
