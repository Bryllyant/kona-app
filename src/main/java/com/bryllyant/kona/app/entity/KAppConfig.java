package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KAppConfig extends KEntityObject {
	enum Env {
        DEV,
        QA,
        BETA,
        TEST,
        PROD
    }

	Long getId();
	void setId(Long id);

    String getUid();
    void setUid(String uid);

    Long getParentId();
    void setParentId(Long parentId);

	Long getAppId();
	void setAppId(Long appId);

	Env getEnv();
	void setEnv(Env env);

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