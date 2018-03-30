/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KApp extends KEntityObject {

    enum Type {
        INTERNAL,
        PARTNER,
        PUBLIC
    }

    Long getId();
    void setId(Long id);
    
    /** 
     * Unique id that can be exported or referenced externally.
     */
    String getUid(); 
    void setUid(String uid);

	Type getType();
	void setType(Type type);

	Long getUserId();
	void setUserId(Long userId);

	Long getLogoId();
	void setLogoId(Long logoId);

	String getLogoUrlPath();
	void setLogoUrlPath(String logoUrlPath);

	String getName();
	void setName(String name);

	String getSlug();
	void setSlug(String slug);

	String getDescription();
	void setDescription(String description);

	String getAppUrl();
	void setAppUrl(String appUrl);

	String getCompanyName();
	void setCompanyName(String companyName);

	String getCompanyUrl();
	void setCompanyUrl(String companyUrl);

	String getPrivacyUrl();
	void setPrivacyUrl(String privacyUrl);

	boolean isEnabled();
	void setEnabled(boolean enabled);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getDeletedDate();
	void setDeletedDate(Date deletedDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);
}
