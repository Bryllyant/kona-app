package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;
import java.util.Map;

public interface KPlacePlan extends KEntityObject  {

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

    Map<String,Object> getPlan();
    void setPlan(Map<String,Object> plan);

    Integer getFloor();
    void setFloor(Integer floor);

    String getPerimeter();
    void setPerimeter(String perimeter);

    String getEncodedPerimeter();
    void setEncodedPerimeter(String encodedPerimeter);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();
    void setUpdatedDate(Date updatedDate);

}