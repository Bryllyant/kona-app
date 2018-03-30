package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KScript extends KEntityObject {

    enum Language {
        GROOVY,
        JAVASCRIPT
    }

    enum ReturnType {
        STRING,
        NUMBER,
        BOOLEAN,
        LIST,
        MAP,
        OBJECT,
        DATE
    }

    @Override
    Long getId();

    void setId(Long id);

    String getUid();

    void setUid(String uid);

    Language getLanguage();

    void setLanguage(Language language);

    ReturnType getReturnType();

    void setReturnType(ReturnType returnType);

    String getName();

    void setName(String name);

    String getSlug();

    void setSlug(String slug);

    String getBody();

    void setBody(String body);

    boolean isEnabled();

    void setEnabled(boolean enabled);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);




}