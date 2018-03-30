package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KPushProvider extends KEntityObject {

	Long getId();
	void setId(Long id);

	String getUid();
	void setUid(String uid);

	KPush.Platform getPlatform();
	void setPlatform(KPush.Platform platform);

	String getServerKey();
	void setServerKey(String serverKey);

	String getServerSecret();
	void setServerSecret(String serverSecret);

	String getEndpoint();
	void setEndpoint(String endpoint);

    boolean isSandbox();
	void setSandbox(boolean sandbox);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);

}
