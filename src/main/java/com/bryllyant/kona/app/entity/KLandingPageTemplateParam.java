package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KLandingPageTemplateParam extends KEntityObject {
    enum Type {
        HTML,
        TEXT,
        URL,
        MEDIA
    }

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

    String getDisplayName();
    void setDisplayName(String displayName);

    Type getType();

    void setType(Type type);

    boolean isRequired();

    void setRequired(boolean required);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);
}
