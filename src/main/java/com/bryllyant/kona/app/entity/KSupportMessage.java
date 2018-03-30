package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KSupportMessage extends KEntityObject {

	Long getId();
	void setId(Long id);

	String getUid();
    void setUid(String uid);

	Long getUserId();
	void setUserId(Long userId);

	String getFirstName();
	void setFirstName(String firstName);

	String getLastName();
	void setLastName(String lastName);

	String getEmail();
	void setEmail(String email);

	String getMobileNumber();
	void setMobileNumber(String mobileNumber);

	String getMessage();
	void setMessage(String message);

	String getHostname();
	void setHostname(String hostname);

	String getUserAgent();
	void setUserAgent(String userAgent);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);

}
