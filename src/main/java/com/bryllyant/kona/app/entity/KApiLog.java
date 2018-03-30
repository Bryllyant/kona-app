package com.bryllyant.kona.app.entity;

import java.util.Date;

import com.bryllyant.kona.data.entity.KEntityObject;

public interface KApiLog extends KEntityObject {

	Long getId();

	void setId(Long id);

	String getUid();

	void setUid(String uid);

	Long getOwnerId();

	void setOwnerId(Long ownerId);

	Long getAppId();

	void setAppId(Long appId);

	Long getVersionId();

	void setVersionId(Long versionId);

	String getAppClientId();

	void setAppClientId(String appClientId);

	Long getUserId();

	void setUserId(Long userId);

	String getAccessToken();

	void setAccessToken(String accessToken);

	String getEndPoint();

	void setEndPoint(String endPoint);

	String getClassName();

	void setClassName(String className);

	String getMethodName();

	void setMethodName(String methodName);

	String getHostname();

	void setHostname(String hostname);

	String getUserAgent();

	void setUserAgent(String userAgent);

	Double getLatitude();

	void setLatitude(Double latitude);

	Double getLongitude();

	void setLongitude(Double longitude);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

}
