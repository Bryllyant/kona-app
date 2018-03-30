package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KSetting extends KEntityObject {

	Long getId();
	void setId(Long id);

    String getUid();
    void setUid(String uid);

	Long getUserId();
	void setUserId(Long userId);

	Long getAccountId();
	void setAccountId(Long accountId);

	Long getParentId();
	void setParentId(Long parentId);

	String getName();
	void setName(String name);

	String getValue();
	void setValue(String value);

	boolean isOverrideGlobal();
	void setOverrideGlobal(boolean overrideGlobal);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);
}