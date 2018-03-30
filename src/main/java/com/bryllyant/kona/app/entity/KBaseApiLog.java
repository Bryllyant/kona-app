/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.entity;

import java.util.Date;

/**
 * KBaseApiLog
 */
public class KBaseApiLog extends KBaseEntity implements KApiLog {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String uid;
    private Long ownerId;
    private Long appId;
    private Long versionId;
    private String appClientId;
    private Long userId;
    private String accessToken;
    private String endPoint;
    private String className;
    private String methodName;
    private String hostname;
    private String userAgent;
    private Double latitude;
    private Double longitude;
    private Date createdDate;
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
	public String getUid() {
        return uid;
    }

    @Override
	public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

   
    @Override
	public Long getOwnerId() {
        return ownerId;
    }

    @Override
	public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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
	public Long getVersionId() {
        return versionId;
    }

   
    @Override
	public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

   
    @Override
	public String getAppClientId() {
        return appClientId;
    }

    
    @Override
	public void setAppClientId(String appClientId) {
        this.appClientId = appClientId == null ? null : appClientId.trim();
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
	public String getAccessToken() {
        return accessToken;
    }

   
    @Override
	public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

   
    @Override
	public String getEndPoint() {
        return endPoint;
    }

    @Override
	public void setEndPoint(String endPoint) {
        this.endPoint = endPoint == null ? null : endPoint.trim();
    }

    @Override
	public String getClassName() {
        return className;
    }

   
    @Override
	public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

   
    @Override
	public String getMethodName() {
        return methodName;
    }

   
    @Override
	public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

  
    @Override
	public String getHostname() {
        return hostname;
    }

   
    @Override
	public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    @Override
	public String getUserAgent() {
        return userAgent;
    }

   
    @Override
	public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
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
	public Date getCreatedDate() {
        return createdDate;
    }

    @Override
	public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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
