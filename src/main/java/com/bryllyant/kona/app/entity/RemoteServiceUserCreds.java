package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BaseRemoteServiceUserCreds.AuthType;
import java.io.Serializable;
import java.util.Date;

public class RemoteServiceUserCreds extends BaseRemoteServiceUserCreds implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.uid
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.account_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Long accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.user_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.remote_service_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Long remoteServiceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.name
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.slug
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.remote_service_user_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String remoteServiceUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.remote_service_screen_name
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String remoteServiceScreenName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.auth_type
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private AuthType authType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.access_token
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String accessToken;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.refresh_token
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String refreshToken;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.token_secret
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String tokenSecret;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.expire_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Date expireDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.connected_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Date connectedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.created_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service_user_creds.updated_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__remote_service_user_creds
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.uid
     *
     * @return the value of kona__remote_service_user_creds.uid
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.uid
     *
     * @param uid the value for kona__remote_service_user_creds.uid
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.account_id
     *
     * @return the value of kona__remote_service_user_creds.account_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.account_id
     *
     * @param accountId the value for kona__remote_service_user_creds.account_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.user_id
     *
     * @return the value of kona__remote_service_user_creds.user_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.user_id
     *
     * @param userId the value for kona__remote_service_user_creds.user_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.remote_service_id
     *
     * @return the value of kona__remote_service_user_creds.remote_service_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Long getRemoteServiceId() {
        return remoteServiceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.remote_service_id
     *
     * @param remoteServiceId the value for kona__remote_service_user_creds.remote_service_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setRemoteServiceId(Long remoteServiceId) {
        this.remoteServiceId = remoteServiceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.name
     *
     * @return the value of kona__remote_service_user_creds.name
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.name
     *
     * @param name the value for kona__remote_service_user_creds.name
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.slug
     *
     * @return the value of kona__remote_service_user_creds.slug
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.slug
     *
     * @param slug the value for kona__remote_service_user_creds.slug
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.remote_service_user_id
     *
     * @return the value of kona__remote_service_user_creds.remote_service_user_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getRemoteServiceUserId() {
        return remoteServiceUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.remote_service_user_id
     *
     * @param remoteServiceUserId the value for kona__remote_service_user_creds.remote_service_user_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setRemoteServiceUserId(String remoteServiceUserId) {
        this.remoteServiceUserId = remoteServiceUserId == null ? null : remoteServiceUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.remote_service_screen_name
     *
     * @return the value of kona__remote_service_user_creds.remote_service_screen_name
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getRemoteServiceScreenName() {
        return remoteServiceScreenName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.remote_service_screen_name
     *
     * @param remoteServiceScreenName the value for kona__remote_service_user_creds.remote_service_screen_name
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setRemoteServiceScreenName(String remoteServiceScreenName) {
        this.remoteServiceScreenName = remoteServiceScreenName == null ? null : remoteServiceScreenName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.auth_type
     *
     * @return the value of kona__remote_service_user_creds.auth_type
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public AuthType getAuthType() {
        return authType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.auth_type
     *
     * @param authType the value for kona__remote_service_user_creds.auth_type
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.access_token
     *
     * @return the value of kona__remote_service_user_creds.access_token
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.access_token
     *
     * @param accessToken the value for kona__remote_service_user_creds.access_token
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.refresh_token
     *
     * @return the value of kona__remote_service_user_creds.refresh_token
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.refresh_token
     *
     * @param refreshToken the value for kona__remote_service_user_creds.refresh_token
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken == null ? null : refreshToken.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.token_secret
     *
     * @return the value of kona__remote_service_user_creds.token_secret
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getTokenSecret() {
        return tokenSecret;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.token_secret
     *
     * @param tokenSecret the value for kona__remote_service_user_creds.token_secret
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret == null ? null : tokenSecret.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.expire_date
     *
     * @return the value of kona__remote_service_user_creds.expire_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.expire_date
     *
     * @param expireDate the value for kona__remote_service_user_creds.expire_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.connected_date
     *
     * @return the value of kona__remote_service_user_creds.connected_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Date getConnectedDate() {
        return connectedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.connected_date
     *
     * @param connectedDate the value for kona__remote_service_user_creds.connected_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setConnectedDate(Date connectedDate) {
        this.connectedDate = connectedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.created_date
     *
     * @return the value of kona__remote_service_user_creds.created_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.created_date
     *
     * @param createdDate the value for kona__remote_service_user_creds.created_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service_user_creds.updated_date
     *
     * @return the value of kona__remote_service_user_creds.updated_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service_user_creds.updated_date
     *
     * @param updatedDate the value for kona__remote_service_user_creds.updated_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}