package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.model.SocialHandle;
import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;
import java.util.List;

public interface KPartner extends KEntityObject {

	Long getId();
	void setId(Long id);

	String getUid();
	void setUid(String uid);

	Long getParentId();
	void setParentId(Long parentId);

    Long getPlaceId();
    void setPlaceId(Long placeId);

	String getName();
	void setName(String name);

	String getSlug();
	void setSlug(String slug);

	String getDescription();
	void setDescription(String description);

	String getLogoUrl();
	void setLogoUrl(String logoUrl);

	String getUrl();
	void setUrl(String url);

    String getEmail();
    void setEmail(String email);

    String getPhoneNumber();
    void setPhoneNumber(String phoneNumber);

    /**
     * JSON encoded array of SocialHandle objects
     *  {
     *      network: 'FACEBOOK'|'TWITTER'|'LINKEDIN'
     *      handle: string;
     *      url: string;
     *  }
     */
    List<SocialHandle> getSocialHandles();
    void setSocialHandles(List<SocialHandle> socialHandles);

	String getContactFirstName();
	void setContactFirstName(String contactFirstName);

	String getContactLastName();
	void setContactLastName(String contactLastName);

    String getContactEmail();
    void setContactEmail(String contactEmail);

    String getContactPhoneNumber();
    void setContactPhoneNumber(String contactPhoneNumber);

    String getContactMobileNumber();
    void setContactMobileNumber(String contactMobileNumber);

	boolean isEnabled();
	void setEnabled(boolean enabled);

    /**
     * User base size
     * @return user base size
     */
    Integer getPopulation();
    void setPopulation(Integer population);

	Date getDeletedDate();
	void setDeletedDate(Date deletedDate);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);

}
