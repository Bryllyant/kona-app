package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.model.SocialHandle;
import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;
import java.util.List;

public interface KContact extends KEntityObject {

	@Override
	Long getId();
	void setId(Long id);

	String getUid();
	void setUid(String uid);

	Long getOwnerId();
	void setOwnerId(Long ownerId);

	Long getRefUserId();
	void setRefUserId(Long refUserId);

	Long getPlaceId();
	void setPlaceId(Long placeId);

	Long getPhotoId();
	void setPhotoId(Long photoId);

	String getPhotoUrl();
	void setPhotoUrl(String photoUrl);

	String getThumbnailUrl();
	void setThumbnailUrl(String thumbnailUrl);

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

    String getUrl();
    void setUrl(String url);

	String getEmail();
	void setEmail(String email);

	String getMobileNumber();
	void setMobileNumber(String mobileNumber);

    /**
     * JSON encoded array of SocialHandle objects
     *  {
     *      network: 'FACEBOOK'|'TWITTER'|'LINKEDIN'
     *      handle: string;
     *      url: string;
     *  }
     */
    List<SocialHandle> getSocialHandles();
    void setSocialHandles(List<SocialHandle> SocialHandles);

	boolean isEmailVerified();
	void setEmailVerified(boolean emailVerified);

	boolean isMobileVerified();
	void setMobileVerified(boolean mobileVerified);

	Date getInvitedDate();
	void setInvitedDate(Date invitedDate);

	Date getRegisteredDate();
	void setRegisteredDate(Date registeredDate);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);

}
