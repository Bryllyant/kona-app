package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KPlaceTag extends KEntityObject  {

    Long getId();
    void setId(Long id);

    String getUid();
    void setUid(String uid);

    Long getPlaceId();
    void setPlaceId(Long placeId);

    String getTag();
    void setTag(String tag);

    String getSlug();
    void setSlug(String slug);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();
    void setUpdatedDate(Date updatedDate);

}