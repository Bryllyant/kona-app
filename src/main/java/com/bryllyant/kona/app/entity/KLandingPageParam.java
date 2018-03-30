package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KLandingPageParam extends KEntityObject {
    @Override
    Long getId();
    void setId(Long id);

    String getUid();
    void setUid(String uid);

    Long getLandingPageId();
    void setLandingPageId(Long landing_page_id);

    Long getTemplateParamId();
    void setTemplateParamId(Long templateParamId);

    Long getFileId();
    void setFileId(Long fileId);

    String getValue();
    void setValue(String value);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();
    void setUpdatedDate(Date updatedDate);
}
