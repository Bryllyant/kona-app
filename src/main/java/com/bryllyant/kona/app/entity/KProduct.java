package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KProduct extends KEntityObject {

    @Override
    Long getId();

    void setId(Long id);

    String getUid();

    void setUid(String uid);

    String getName();

    void setName(String name);

    String getSlug();

    void setSlug(String slug);

    String getDescription();

    void setDescription(String description);

    Integer getDisplayOrder();

    void setDisplayOrder(Integer displayOrder);

    boolean isSubscription();

    void setSubscription(boolean subscription);

    boolean isEnabled();

    void setEnabled(boolean enabled);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);
}
