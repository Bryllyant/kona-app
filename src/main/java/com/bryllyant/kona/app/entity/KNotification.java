package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KNotification extends KEntityObject {
    enum Channel {
        IN_APP,
        EMAIL,
        SMS,
        PUSH
    }

    @Override
	Long getId();

	void setId(Long id);

	String getUid();

	void setUid(String uid);

	Long getUserId();

	void setUserId(Long userId);

	String getEvent();

	void setEvent(String event);

	Date getLastViewedDate();

	void setLastViewedDate(Date lastViewedDate);

	Date getEventDate();

	void setEventDate(Date eventDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

	String toString();

}