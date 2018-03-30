package com.bryllyant.kona.app.entity;

import java.util.Date;

import com.bryllyant.kona.data.entity.KEntityObject;

public interface KAppUser extends KEntityObject {

	Long getId();
	void setId(Long id);

    String getUid();
    void setUid(String uid);

	Long getUserId();
	void setUserId(Long userId);

	Long getAppId();
	void setAppId(Long appId);

	Long getTokenId();
	void setTokenId(Long tokenId);

	String getRefUserId();
	void setRefUserId(String refUserId);

	String getPhotoUrl();
	void setPhotoUrl(String photoUrl);

	String getThumbnailUrl();
	void setThumbnailUrl(String thumbnailUrl);

	String getEmail();
	void setEmail(String email);

	String getMobileNumber();
	void setMobileNumber(String mobileNumber);

	String getFirstName();
	void setFirstName(String firstName);

	String getLastName();
	void setLastName(String lastName);

	String getDisplayName();
	void setDisplayName(String displayName);

	String getGender();
	void setGender(String gender);

	Date getBirthDate();
	void setBirthDate(Date birthDate);

	String getLocale();
	void setLocale(String locale);

	String getTimeZone();
	void setTimeZone(String timeZone);

	Double getLatitude();
	void setLatitude(Double latitude);

	Double getLongitude();
	void setLongitude(Double longitude);

	boolean isEnabled();
	void setEnabled(boolean enabled);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getRevokedDate();
	void setRevokedDate(Date revokedDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);

}
