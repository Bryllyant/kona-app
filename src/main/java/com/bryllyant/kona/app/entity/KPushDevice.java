package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KPushDevice extends KEntityObject {

	Long getId();
	void setId(Long id);

	String getUid();
	void setUid(String uid);

	Long getProviderId();
	void setProviderId(Long providerId);

    Long getUserId();
    void setUserId(Long userId);

	Long getDeviceId();
	void setDeviceId(Long deviceId);

	KPush.Platform getPlatform();
	void setPlatform(KPush.Platform platform);

	String getToken();
	void setToken(String token);

	String getEndpoint();
	void setEndpoint(String endpoint);

	boolean isEnabled();
	void setEnabled(boolean enabled);

	boolean isSandbox();
	void setSandbox(boolean sandbox);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);

}
