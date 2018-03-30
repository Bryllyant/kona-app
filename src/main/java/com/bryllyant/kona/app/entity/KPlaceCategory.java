package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KPlaceCategory extends KEntityObject  {

    Long getId();
    void setId(Long id);

    String getUid();
    void setUid(String uid);

    Long getPlaceId();
    void setPlaceId(Long placeId);

    String getName();
    void setName(String name);

    String getSlug();
    void setSlug(String slug);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();
    void setUpdatedDate(Date updatedDate);

}