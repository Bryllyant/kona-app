package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KRedirect extends KEntityObject {

	Long getId();
	void setId(Long id);

    String getUid();
    void setUid(String uid);

	Long getShortUrlId();
	void setShortUrlId(Long shortUrlId);

	String getRequestUrl();
	void setRequestUrl(String requestUrl);

	String getRedirectUrl();
	void setRedirectUrl(String redirectUrl);

	String getHostname();
	void setHostname(String hostname);

	String getUserAgent();
	void setUserAgent(String userAgent);

	String getReferer();
	void setReferer(String referer);

	String getLocale();
	void setLocale(String locale);

	Double getLatitude();
	void setLatitude(Double latitude);

	Double getLongitude();
	void setLongitude(Double longitude);

	Date getRequestedDate();
	void setRequestedDate(Date requestedDate);

	Date getRedirectedDate();
	void setRedirectedDate(Date redirectedDate);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);

}