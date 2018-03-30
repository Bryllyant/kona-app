package com.bryllyant.kona.app.entity;

import java.util.Date;

import com.bryllyant.kona.data.entity.KEntityObject;

public interface KEmailContent extends KEntityObject  {

    /**
     * @return the id
     */
    @Override
    Long getId();

    /**
     * @param id the id to set
     */
    void setId(Long id);


    Long getOwnerId();
    void setOwnerId(Long ownerId);


    /**
     * @return the uid
     */
    String getUid();

    /**
     * @param uid the uid to set
     */
    void setUid(String uid);

    /**
     * @return the html
     */
    String getHtml();

    /**
     * @param html the html to set
     */
    void setHtml(String html);

    /**
     * @return the text
     */
    String getText();

    /**
     * @param text the text to set
     */
    void setText(String text);

    
    /**
     * @return the createdDate
     */
    Date getCreatedDate();

    /**
     * @param createdDate the createdDate to set
     */
    void setCreatedDate(Date createdDate);

    /**
     * @return the updatedDate
     */
    Date getUpdatedDate();

    /**
     * @param updatedDate the updatedDate to set
     */
    void setUpdatedDate(Date updatedDate);
}
