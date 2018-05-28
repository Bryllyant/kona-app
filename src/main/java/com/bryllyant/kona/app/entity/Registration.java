package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Registration extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.app_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long appId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.account_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.user_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.device_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long deviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.campaign_channel_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long campaignChannelId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.referred_by_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long referredById;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.app_client_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String appClientId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.username
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.hostname
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String hostname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.user_agent
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String userAgent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.os_name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String osName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.os_version
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String osVersion;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.app_version
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String appVersion;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.app_build
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String appBuild;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.signup_time
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long signupTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.latitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Double latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.longitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Double longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.email_verified
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean emailVerified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.email_pending
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean emailPending;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.mobile_verified
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean mobileVerified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.mobile_pending
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean mobilePending;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.reminded_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date remindedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.registered_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date registeredDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.deleted_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date deletedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__registration
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.uid
     *
     * @return the value of kona__registration.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.uid
     *
     * @param uid the value for kona__registration.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.app_id
     *
     * @return the value of kona__registration.app_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.app_id
     *
     * @param appId the value for kona__registration.app_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.account_id
     *
     * @return the value of kona__registration.account_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.account_id
     *
     * @param accountId the value for kona__registration.account_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.user_id
     *
     * @return the value of kona__registration.user_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.user_id
     *
     * @param userId the value for kona__registration.user_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.device_id
     *
     * @return the value of kona__registration.device_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.device_id
     *
     * @param deviceId the value for kona__registration.device_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.campaign_channel_id
     *
     * @return the value of kona__registration.campaign_channel_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getCampaignChannelId() {
        return campaignChannelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.campaign_channel_id
     *
     * @param campaignChannelId the value for kona__registration.campaign_channel_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCampaignChannelId(Long campaignChannelId) {
        this.campaignChannelId = campaignChannelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.referred_by_id
     *
     * @return the value of kona__registration.referred_by_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getReferredById() {
        return referredById;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.referred_by_id
     *
     * @param referredById the value for kona__registration.referred_by_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setReferredById(Long referredById) {
        this.referredById = referredById;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.app_client_id
     *
     * @return the value of kona__registration.app_client_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getAppClientId() {
        return appClientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.app_client_id
     *
     * @param appClientId the value for kona__registration.app_client_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAppClientId(String appClientId) {
        this.appClientId = appClientId == null ? null : appClientId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.username
     *
     * @return the value of kona__registration.username
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.username
     *
     * @param username the value for kona__registration.username
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.hostname
     *
     * @return the value of kona__registration.hostname
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.hostname
     *
     * @param hostname the value for kona__registration.hostname
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.user_agent
     *
     * @return the value of kona__registration.user_agent
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.user_agent
     *
     * @param userAgent the value for kona__registration.user_agent
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.os_name
     *
     * @return the value of kona__registration.os_name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getOsName() {
        return osName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.os_name
     *
     * @param osName the value for kona__registration.os_name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setOsName(String osName) {
        this.osName = osName == null ? null : osName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.os_version
     *
     * @return the value of kona__registration.os_version
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getOsVersion() {
        return osVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.os_version
     *
     * @param osVersion the value for kona__registration.os_version
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion == null ? null : osVersion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.app_version
     *
     * @return the value of kona__registration.app_version
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.app_version
     *
     * @param appVersion the value for kona__registration.app_version
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.app_build
     *
     * @return the value of kona__registration.app_build
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getAppBuild() {
        return appBuild;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.app_build
     *
     * @param appBuild the value for kona__registration.app_build
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAppBuild(String appBuild) {
        this.appBuild = appBuild == null ? null : appBuild.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.signup_time
     *
     * @return the value of kona__registration.signup_time
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getSignupTime() {
        return signupTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.signup_time
     *
     * @param signupTime the value for kona__registration.signup_time
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setSignupTime(Long signupTime) {
        this.signupTime = signupTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.latitude
     *
     * @return the value of kona__registration.latitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.latitude
     *
     * @param latitude the value for kona__registration.latitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.longitude
     *
     * @return the value of kona__registration.longitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.longitude
     *
     * @param longitude the value for kona__registration.longitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.email_verified
     *
     * @return the value of kona__registration.email_verified
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isEmailVerified() {
        return emailVerified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.email_verified
     *
     * @param emailVerified the value for kona__registration.email_verified
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.email_pending
     *
     * @return the value of kona__registration.email_pending
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isEmailPending() {
        return emailPending;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.email_pending
     *
     * @param emailPending the value for kona__registration.email_pending
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setEmailPending(boolean emailPending) {
        this.emailPending = emailPending;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.mobile_verified
     *
     * @return the value of kona__registration.mobile_verified
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isMobileVerified() {
        return mobileVerified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.mobile_verified
     *
     * @param mobileVerified the value for kona__registration.mobile_verified
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setMobileVerified(boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.mobile_pending
     *
     * @return the value of kona__registration.mobile_pending
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isMobilePending() {
        return mobilePending;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.mobile_pending
     *
     * @param mobilePending the value for kona__registration.mobile_pending
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setMobilePending(boolean mobilePending) {
        this.mobilePending = mobilePending;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.reminded_date
     *
     * @return the value of kona__registration.reminded_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getRemindedDate() {
        return remindedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.reminded_date
     *
     * @param remindedDate the value for kona__registration.reminded_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setRemindedDate(Date remindedDate) {
        this.remindedDate = remindedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.registered_date
     *
     * @return the value of kona__registration.registered_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getRegisteredDate() {
        return registeredDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.registered_date
     *
     * @param registeredDate the value for kona__registration.registered_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.deleted_date
     *
     * @return the value of kona__registration.deleted_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getDeletedDate() {
        return deletedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.deleted_date
     *
     * @param deletedDate the value for kona__registration.deleted_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.created_date
     *
     * @return the value of kona__registration.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.created_date
     *
     * @param createdDate the value for kona__registration.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.updated_date
     *
     * @return the value of kona__registration.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.updated_date
     *
     * @param updatedDate the value for kona__registration.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}