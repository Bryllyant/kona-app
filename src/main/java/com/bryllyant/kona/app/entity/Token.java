package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BaseToken.Type;
import java.io.Serializable;
import java.util.Date;

public class Token extends BaseToken implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.type
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Type type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.user_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.app_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long appId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.device_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long deviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.app_client_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String appClientId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.access_token
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String accessToken;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.refresh_token
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String refreshToken;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.scope
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String scope;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.username
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.hostname
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String hostname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.latitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Double latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.longitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Double longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.user_agent
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String userAgent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.app_version
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String appVersion;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.app_build
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String appBuild;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.active
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean active;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.approved
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean approved;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.access_count
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long accessCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.login_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date loginDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.last_login_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date lastLoginDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.logout_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date logoutDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.access_expiration_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date accessExpirationDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.refresh_expiration_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date refreshExpirationDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.expired_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date expiredDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date updatedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__token.authentication
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private byte[] authentication;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__token
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.uid
     *
     * @return the value of kona__token.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.uid
     *
     * @param uid the value for kona__token.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.type
     *
     * @return the value of kona__token.type
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.type
     *
     * @param type the value for kona__token.type
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.user_id
     *
     * @return the value of kona__token.user_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.user_id
     *
     * @param userId the value for kona__token.user_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.app_id
     *
     * @return the value of kona__token.app_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.app_id
     *
     * @param appId the value for kona__token.app_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.device_id
     *
     * @return the value of kona__token.device_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.device_id
     *
     * @param deviceId the value for kona__token.device_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.app_client_id
     *
     * @return the value of kona__token.app_client_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getAppClientId() {
        return appClientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.app_client_id
     *
     * @param appClientId the value for kona__token.app_client_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAppClientId(String appClientId) {
        this.appClientId = appClientId == null ? null : appClientId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.access_token
     *
     * @return the value of kona__token.access_token
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.access_token
     *
     * @param accessToken the value for kona__token.access_token
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.refresh_token
     *
     * @return the value of kona__token.refresh_token
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.refresh_token
     *
     * @param refreshToken the value for kona__token.refresh_token
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken == null ? null : refreshToken.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.scope
     *
     * @return the value of kona__token.scope
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getScope() {
        return scope;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.scope
     *
     * @param scope the value for kona__token.scope
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.username
     *
     * @return the value of kona__token.username
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.username
     *
     * @param username the value for kona__token.username
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.hostname
     *
     * @return the value of kona__token.hostname
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.hostname
     *
     * @param hostname the value for kona__token.hostname
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.latitude
     *
     * @return the value of kona__token.latitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.latitude
     *
     * @param latitude the value for kona__token.latitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.longitude
     *
     * @return the value of kona__token.longitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.longitude
     *
     * @param longitude the value for kona__token.longitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.user_agent
     *
     * @return the value of kona__token.user_agent
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.user_agent
     *
     * @param userAgent the value for kona__token.user_agent
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.app_version
     *
     * @return the value of kona__token.app_version
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.app_version
     *
     * @param appVersion the value for kona__token.app_version
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.app_build
     *
     * @return the value of kona__token.app_build
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getAppBuild() {
        return appBuild;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.app_build
     *
     * @param appBuild the value for kona__token.app_build
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAppBuild(String appBuild) {
        this.appBuild = appBuild == null ? null : appBuild.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.active
     *
     * @return the value of kona__token.active
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isActive() {
        return active;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.active
     *
     * @param active the value for kona__token.active
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.approved
     *
     * @return the value of kona__token.approved
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.approved
     *
     * @param approved the value for kona__token.approved
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.access_count
     *
     * @return the value of kona__token.access_count
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getAccessCount() {
        return accessCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.access_count
     *
     * @param accessCount the value for kona__token.access_count
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAccessCount(Long accessCount) {
        this.accessCount = accessCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.login_date
     *
     * @return the value of kona__token.login_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.login_date
     *
     * @param loginDate the value for kona__token.login_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.last_login_date
     *
     * @return the value of kona__token.last_login_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.last_login_date
     *
     * @param lastLoginDate the value for kona__token.last_login_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.logout_date
     *
     * @return the value of kona__token.logout_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getLogoutDate() {
        return logoutDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.logout_date
     *
     * @param logoutDate the value for kona__token.logout_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setLogoutDate(Date logoutDate) {
        this.logoutDate = logoutDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.access_expiration_date
     *
     * @return the value of kona__token.access_expiration_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getAccessExpirationDate() {
        return accessExpirationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.access_expiration_date
     *
     * @param accessExpirationDate the value for kona__token.access_expiration_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAccessExpirationDate(Date accessExpirationDate) {
        this.accessExpirationDate = accessExpirationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.refresh_expiration_date
     *
     * @return the value of kona__token.refresh_expiration_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getRefreshExpirationDate() {
        return refreshExpirationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.refresh_expiration_date
     *
     * @param refreshExpirationDate the value for kona__token.refresh_expiration_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setRefreshExpirationDate(Date refreshExpirationDate) {
        this.refreshExpirationDate = refreshExpirationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.expired_date
     *
     * @return the value of kona__token.expired_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getExpiredDate() {
        return expiredDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.expired_date
     *
     * @param expiredDate the value for kona__token.expired_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.created_date
     *
     * @return the value of kona__token.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.created_date
     *
     * @param createdDate the value for kona__token.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.updated_date
     *
     * @return the value of kona__token.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.updated_date
     *
     * @param updatedDate the value for kona__token.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__token.authentication
     *
     * @return the value of kona__token.authentication
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public byte[] getAuthentication() {
        return authentication;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__token.authentication
     *
     * @param authentication the value for kona__token.authentication
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }
}