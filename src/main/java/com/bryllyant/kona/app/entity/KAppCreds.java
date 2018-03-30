/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.entity;

import java.util.Date;

import com.bryllyant.kona.data.entity.KEntityObject;

public interface KAppCreds extends KEntityObject {

	Long getId();

	void setId(Long id);

	Long getAppId();

	void setAppId(Long appId);

	Long getApiVersionId();

	void setApiVersionId(Long apiVersionId);

	String getName();

	void setName(String name);

	String getClientId();

	void setClientId(String clientId);

	String getClientSecret();

	void setClientSecret(String clientSecret);

	String getRedirectUri();

	void setRedirectUri(String redirectUri);

	String getScope();

	void setScope(String scope);

	boolean isEnabled();

	void setEnabled(boolean enabled);

    /**
     * 
     * @return access token timeout in seconds
     */
	Integer getAccessTokenTimeout();

    /**
     * 
     * @param accessTokenTimeout timeout in seconds
     */
	void setAccessTokenTimeout(Integer accessTokenTimeout);

    /**
     * 
     * @return refresh token timeout in seconds
     */
	Integer getRefreshTokenTimeout();

    /**
     * 
     * @param refreshTokenTimeout timeout in seconds
     */
	void setRefreshTokenTimeout(Integer refreshTokenTimeout);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getDeletedDate();

	void setDeletedDate(Date deletedDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

}