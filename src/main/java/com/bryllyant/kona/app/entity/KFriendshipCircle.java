package com.bryllyant.kona.app.entity;

import java.util.Date;

import com.bryllyant.kona.data.entity.KEntityObject;

public interface KFriendshipCircle extends KEntityObject {

	@Override
	Long getId();

	void setId(Long id);

	String getUid();

	void setUid(String uid);

	Long getUserId();

	void setUserId(Long userId);

	String getName();

	void setName(String name);

	String getSlug();

	void setSlug(String slug);

	boolean isDefaultCircle();

	void setDefaultCircle(boolean defaultCircle);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

}