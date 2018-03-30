package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KAuthRolePriv extends KEntityObject {

	Long getId();
	void setId(Long id);

	String getUid();
	void setUid(String uid);

	Long getRoleId();
	void setRoleId(Long roleId);

    Long getPrivId();
    void setPrivId(Long privId);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);
}
