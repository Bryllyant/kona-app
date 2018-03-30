package com.bryllyant.kona.app.entity;

import java.util.Date;

import com.bryllyant.kona.data.entity.KEntityObject;

public interface KEmailGroupAddress extends KEntityObject {

	Long getId();
	void setId(Long id);

	String getUid();
	void setUid(String uid);

	Long getGroupId();
	void setGroupId(Long groupId);

	Long getAddressId();
	void setAddressId(Long addressId);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);

}
