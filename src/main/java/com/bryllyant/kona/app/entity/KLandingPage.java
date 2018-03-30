package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KLandingPage extends KEntityObject {

    @Override
    Long getId();

    void setId(Long id);

    String getUid();

    void setUid(String uid);

    Long getTemplateId();

    void setTemplateId(Long templateId);

    String getName();

    void setName(String name);

    String getSlug();

    void setSlug(String slug);

    String getDescription();

    void setDescription(String description);

    boolean isEnabled();

    void setEnabled(boolean enabled);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);
}
