package com.bryllyant.kona.app.entity;

import java.util.Date;

import com.bryllyant.kona.data.entity.KEntityObject;


public interface KEmailAttachment extends KEntityObject {
    /**
     * @return the id
     */
    @Override
    Long getId();

    /**
     * @param id the id to set
     */
    void setId(Long id);

    String getUid();
    void setUid(String uid);

    Long getContentId();
    void setContentId(Long contentId);

    Long getFileId();
    void setFileId(Long fileId);

    String getName();
    void setName(String name);

    String getContentType();
    void setContentType(String contentType);

    byte[] getData();
    void setData(byte[] data);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();
    void setUpdatedDate(Date updatedDate);

}
