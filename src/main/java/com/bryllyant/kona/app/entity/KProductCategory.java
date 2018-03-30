package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KProductCategory extends KEntityObject {

    @Override
    Long getId();

    void setId(Long id);

    String getUid();

    void setUid(String uid);

    Long getParentId();

    void setParentId(Long parentId);

    String getName();

    void setName(String name);

    String getSlug();

    void setSlug(String slug);

    String getDescription();

    void setDescription(String description);

    Integer getDisplayOrder();

    void setDisplayOrder(Integer displayOrder);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);
}
