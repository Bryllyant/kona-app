package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KAuthCode extends KEntityObject {
    enum Type {
        EMAIL_CONFIRMATION,
        MOBILE_CONFIRMATION,
        PHONE_CONFIRMATION,
        PASSWORD_RESET
    }

	Long getId();

	void setId(Long id);

	Type getType();

	void setType(Type type);

	Long getUserId();

	void setUserId(Long userId);

	String getCode();

	void setCode(String code);

	boolean isValid();

	void setValid(boolean valid);

	Integer getUseCount();

	void setUseCount(Integer useCount);

	Integer getMaxUseCount();

	void setMaxUseCount(Integer maxUseCount);

	Date getExpirationDate();

	void setExpirationDate(Date expirationDate);

	Date getLastAccessedDate();

	void setLastAccessedDate(Date lastAccessedDate);

	Date getConfirmedDate();

    void setConfirmedDate(Date confirmedDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

}
