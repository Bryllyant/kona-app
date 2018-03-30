package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KEmailEvent extends KEntityObject {

    enum Type {
        ATTEMPTED,
        FAILED,
        DELIVERED,
        BOUNCED,
        COMPLAINED,
        UNSUBSCRIBED,
        OPENED,
        FORWARDED,
        PRINTED,
        CLICKED
    }

	@Override
	Long getId();
	void setId(Long id);

	String getUid();
	void setUid(String uid);

	Type getType();
	void setType(Type type);

	Long getEmailId();
	void setEmailId(Long emailId);

	String getTarget();
	void setTarget(String target);

	String getError();
	void setError(String error);

	String getHostname();
	void setHostname(String hostname);

	String getUserAgent();
	void setUserAgent(String userAgent);

	Date getEventDate();
	void setEventDate(Date eventDate);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);

}
