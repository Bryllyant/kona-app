package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseEmailContent extends KBaseEntity implements KEmailContent {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long ownerId;
    private String html;
    private String text;
    
    private Date createdDate;
    private Date updatedDate;
    


    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KEmailContent#getId()
     */
    @Override
    public Long getId() {
        return id;
    }
    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KEmailContent#setId(java.lang.Long)
     */
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getOwnerId() {
        return ownerId;
    }

    @Override
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KEmailContent#getUid()
     */
    @Override
    public String getUid() {
        return uid;
    }
    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KEmailContent#setUid(java.lang.String)
     */
    @Override
    public void setUid(String uid) {
        this.uid = uid;
    }
    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KEmailContent#getHtml()
     */
    @Override
    public String getHtml() {
        return html;
    }
    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KEmailContent#setHtml(java.lang.String)
     */
    @Override
    public void setHtml(String html) {
        this.html = html;
    }
    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KEmailContent#getText()
     */
    @Override
    public String getText() {
        return text;
    }
    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KEmailContent#setText(java.lang.String)
     */
    @Override
    public void setText(String text) {
        this.text = text;
    }
    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KEmailContent#getAttachment1()
     */
    
    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KEmailContent#getCreatedDate()
     */
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KEmailContent#setCreatedDate(java.util.Date)
     */
    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KEmailContent#getUpdatedDate()
     */
    @Override
    public Date getUpdatedDate() {
        return updatedDate;
    }
    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KEmailContent#setUpdatedDate(java.util.Date)
     */
    @Override
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

}
