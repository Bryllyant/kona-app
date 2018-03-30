package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KPolicy extends KEntityObject {

    enum Type {
        TERMS,
        PRIVACY
    }

	Long getId();
	void setId(Long id);

	String getUid();
    void setUid(String uid);

    Type getType();
	void setType(Type type);

	String getContent();
	void setContent(String content);

	Integer getVersion();
	void setVersion(Integer version);

	boolean isActive();
	void setActive(boolean active);

	Date getPublishedDate();
	void setPublishedDate(Date publishedDate);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);

}
