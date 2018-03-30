/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.entity;

import java.util.Date;

/**
 * KBaseAppCreds.
 */
public class KBaseAppCreds extends KBaseEntity implements KAppCreds {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long appId;
    private Long apiVersionId;
    private String name;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String scope;
    private boolean enabled;
    private Integer accessTokenTimeout;
    private Integer refreshTokenTimeout;
    private Date createdDate;
    private Date deletedDate;
    private Date updatedDate;

    @Override
	public Long getId() {
        return id;
    }

    @Override
	public void setId(Long id) {
        this.id = id;
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
	public Long getApiVersionId() {
        return apiVersionId;
    }

    @Override
	public void setApiVersionId(Long apiVersionId) {
        this.apiVersionId = apiVersionId;
    }

    @Override
	public String getName() {
        return name;
    }

    @Override
	public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
	public String getClientId() {
        return clientId;
    }

    @Override
	public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    @Override
	public String getClientSecret() {
        return clientSecret;
    }

    @Override
	public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
    }

    @Override
	public String getRedirectUri() {
        return redirectUri;
    }

    @Override
	public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri == null ? null : redirectUri.trim();
    }

    @Override
	public String getScope() {
        return scope;
    }

    @Override
	public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    @Override
	public boolean isEnabled() {
        return enabled;
    }

    @Override
	public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
	public Integer getAccessTokenTimeout() {
        return accessTokenTimeout;
    }

    @Override
	public void setAccessTokenTimeout(Integer accessTokenTimeout) {
        this.accessTokenTimeout = accessTokenTimeout;
    }

    @Override
	public Integer getRefreshTokenTimeout() {
        return refreshTokenTimeout;
    }

    @Override
	public void setRefreshTokenTimeout(Integer refreshTokenTimeout) {
        this.refreshTokenTimeout = refreshTokenTimeout;
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

}
