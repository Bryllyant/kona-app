package com.bryllyant.kona.app.entity;

import java.util.Date;

import com.bryllyant.kona.data.entity.KEntityObject;

public interface KEntityNameRule extends KEntityObject {

    Long getId();

    void setId(Long id);

    String getUid();

    void setUid(String uid);

    String getPattern();

    void setPattern(String pattern);

    boolean isBlackListed();

    void setBlackListed(boolean blackListed);

    boolean isReserved();

    void setReserved(boolean reserved);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);
}