package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KNotificationDelivery extends KEntityObject {

    @Override
	Long getId();

	void setId(Long id);

	Long getNotificationId();

	void setNotificationId(Long notificationId);

	KNotification.Channel getChannel();

	void setChannel(KNotification.Channel channel);

	String getCode();

	void setCode(String code);

	Date getDeliveredDate();

	void setDeliveredDate(Date deliveredDate);

	Date getViewedDate();

	void setViewedDate(Date viewedDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

	String toString();

}