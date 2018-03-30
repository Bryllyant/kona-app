/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.entity;

import java.util.Date;

import com.bryllyant.kona.data.entity.KEntityObject;

public interface KTokenLog extends KEntityObject {
    public Long getId();
    public void setId(Long id);
    
    public Long getAppId();
    public void setAppId(Long appId);

    public Long getUserId();
    public void setUserId(Long userId);
    
    public Long getTokenId();
    public void setTokenId(Long tokenId);

    public String getHostname();
    public void setHostname(String hostname);
    
    public Double getLatitude();
    public void setLatitude(Double latitude);
    
    public Double getLongitude();
    public void setLongitude(Double longitude);

    public String getUserAgent();
    public void setUserAgent(String userAgent);

    public String getRequestUrl();
    public void setRequestUrl(String requestUrl);

    public Date getUpdatedDate();
    public void setUpdatedDate(Date updatedDate);
}
