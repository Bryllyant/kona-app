package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class AppCreds extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app_creds.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app_creds.app_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long appId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app_creds.api_version_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long apiVersionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app_creds.name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app_creds.client_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String clientId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app_creds.client_secret
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String clientSecret;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app_creds.redirect_uri
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String redirectUri;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app_creds.scope
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String scope;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app_creds.enabled
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app_creds.access_token_timeout
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Integer accessTokenTimeout;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app_creds.refresh_token_timeout
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Integer refreshTokenTimeout;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app_creds.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app_creds.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__app_creds
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app_creds.uid
     *
     * @return the value of kona__app_creds.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app_creds.uid
     *
     * @param uid the value for kona__app_creds.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app_creds.app_id
     *
     * @return the value of kona__app_creds.app_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app_creds.app_id
     *
     * @param appId the value for kona__app_creds.app_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app_creds.api_version_id
     *
     * @return the value of kona__app_creds.api_version_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getApiVersionId() {
        return apiVersionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app_creds.api_version_id
     *
     * @param apiVersionId the value for kona__app_creds.api_version_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setApiVersionId(Long apiVersionId) {
        this.apiVersionId = apiVersionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app_creds.name
     *
     * @return the value of kona__app_creds.name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app_creds.name
     *
     * @param name the value for kona__app_creds.name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app_creds.client_id
     *
     * @return the value of kona__app_creds.client_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app_creds.client_id
     *
     * @param clientId the value for kona__app_creds.client_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app_creds.client_secret
     *
     * @return the value of kona__app_creds.client_secret
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app_creds.client_secret
     *
     * @param clientSecret the value for kona__app_creds.client_secret
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app_creds.redirect_uri
     *
     * @return the value of kona__app_creds.redirect_uri
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getRedirectUri() {
        return redirectUri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app_creds.redirect_uri
     *
     * @param redirectUri the value for kona__app_creds.redirect_uri
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri == null ? null : redirectUri.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app_creds.scope
     *
     * @return the value of kona__app_creds.scope
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getScope() {
        return scope;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app_creds.scope
     *
     * @param scope the value for kona__app_creds.scope
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app_creds.enabled
     *
     * @return the value of kona__app_creds.enabled
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app_creds.enabled
     *
     * @param enabled the value for kona__app_creds.enabled
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app_creds.access_token_timeout
     *
     * @return the value of kona__app_creds.access_token_timeout
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Integer getAccessTokenTimeout() {
        return accessTokenTimeout;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app_creds.access_token_timeout
     *
     * @param accessTokenTimeout the value for kona__app_creds.access_token_timeout
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAccessTokenTimeout(Integer accessTokenTimeout) {
        this.accessTokenTimeout = accessTokenTimeout;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app_creds.refresh_token_timeout
     *
     * @return the value of kona__app_creds.refresh_token_timeout
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Integer getRefreshTokenTimeout() {
        return refreshTokenTimeout;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app_creds.refresh_token_timeout
     *
     * @param refreshTokenTimeout the value for kona__app_creds.refresh_token_timeout
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setRefreshTokenTimeout(Integer refreshTokenTimeout) {
        this.refreshTokenTimeout = refreshTokenTimeout;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app_creds.created_date
     *
     * @return the value of kona__app_creds.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app_creds.created_date
     *
     * @param createdDate the value for kona__app_creds.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app_creds.updated_date
     *
     * @return the value of kona__app_creds.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app_creds.updated_date
     *
     * @param updatedDate the value for kona__app_creds.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}