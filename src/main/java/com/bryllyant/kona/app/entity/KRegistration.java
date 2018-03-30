package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KRegistration extends KEntityObject {
    @Override
    Long getId();
    void setId(Long id);

    String getUid();
    void setUid(String uid);

    Long getAppId();
    void setAppId(Long appId);

    Long getAccountId();
    void setAccountId(Long accountId);

    Long getUserId();
    void setUserId(Long userId);

    Long getDeviceId();
    void setDeviceId(Long deviceId);

    Long getCampaignChannelId();
    void setCampaignChannelId(Long campaignChannelId);

    Long getReferredById();
    void setReferredById(Long referredById);

    String getAppClientId();
    void setAppClientId(String appClientId);

    String getUsername();
    void setUsername(String username);

    String getHostname();
    void setHostname(String hostname);

    String getUserAgent();
    void setUserAgent(String userAgent);

    String getOsName();
    void setOsName(String osName);

    String getOsVersion();
    void setOsVersion(String osVersion);

    String getAppVersion();
    void setAppVersion(String appVersion);

    String getAppBuild();
    void setAppBuild(String appBuild);

    Long getSignupTime();

    void setSignupTime(Long signupTime);

    Double getLatitude();

    void setLatitude(Double latitude);

    Double getLongitude();

    void setLongitude(Double longitude);

    boolean isEmailVerified();

    void setEmailVerified(boolean emailVerified);

    boolean isEmailPending();

    void setEmailPending(boolean emailPending);

    boolean isMobileVerified();

    void setMobileVerified(boolean mobileVerified);

    boolean isMobilePending();

    void setMobilePending(boolean mobilePending);

    Date getRemindedDate();

    void setRemindedDate(Date remindedDate);

    Date getRegisteredDate();

    void setRegisteredDate(Date registeredDate);

    Date getDeletedDate();

    void setDeletedDate(Date deletedDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    boolean isVerified();

    void setVerified(boolean verified);
}
