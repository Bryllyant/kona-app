package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class RemoteService extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service.name
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service.slug
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service.authorize_uri
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String authorizeUri;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service.token_uri
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String tokenUri;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service.scope
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String scope;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service.client_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String clientId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service.client_secret
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String clientSecret;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service.redirect_uri
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String redirectUri;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service.namespace
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String namespace;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service.region
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String region;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__remote_service.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__remote_service
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service.uid
     *
     * @return the value of kona__remote_service.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service.uid
     *
     * @param uid the value for kona__remote_service.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service.name
     *
     * @return the value of kona__remote_service.name
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service.name
     *
     * @param name the value for kona__remote_service.name
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service.slug
     *
     * @return the value of kona__remote_service.slug
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service.slug
     *
     * @param slug the value for kona__remote_service.slug
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service.authorize_uri
     *
     * @return the value of kona__remote_service.authorize_uri
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getAuthorizeUri() {
        return authorizeUri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service.authorize_uri
     *
     * @param authorizeUri the value for kona__remote_service.authorize_uri
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setAuthorizeUri(String authorizeUri) {
        this.authorizeUri = authorizeUri == null ? null : authorizeUri.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service.token_uri
     *
     * @return the value of kona__remote_service.token_uri
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getTokenUri() {
        return tokenUri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service.token_uri
     *
     * @param tokenUri the value for kona__remote_service.token_uri
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setTokenUri(String tokenUri) {
        this.tokenUri = tokenUri == null ? null : tokenUri.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service.scope
     *
     * @return the value of kona__remote_service.scope
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getScope() {
        return scope;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service.scope
     *
     * @param scope the value for kona__remote_service.scope
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service.client_id
     *
     * @return the value of kona__remote_service.client_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service.client_id
     *
     * @param clientId the value for kona__remote_service.client_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service.client_secret
     *
     * @return the value of kona__remote_service.client_secret
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service.client_secret
     *
     * @param clientSecret the value for kona__remote_service.client_secret
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service.redirect_uri
     *
     * @return the value of kona__remote_service.redirect_uri
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getRedirectUri() {
        return redirectUri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service.redirect_uri
     *
     * @param redirectUri the value for kona__remote_service.redirect_uri
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri == null ? null : redirectUri.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service.namespace
     *
     * @return the value of kona__remote_service.namespace
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service.namespace
     *
     * @param namespace the value for kona__remote_service.namespace
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setNamespace(String namespace) {
        this.namespace = namespace == null ? null : namespace.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service.region
     *
     * @return the value of kona__remote_service.region
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getRegion() {
        return region;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service.region
     *
     * @param region the value for kona__remote_service.region
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service.created_date
     *
     * @return the value of kona__remote_service.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service.created_date
     *
     * @param createdDate the value for kona__remote_service.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__remote_service.updated_date
     *
     * @return the value of kona__remote_service.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__remote_service.updated_date
     *
     * @param updatedDate the value for kona__remote_service.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}