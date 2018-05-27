package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class EmailAttachment extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_attachment.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_attachment.content_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Long contentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_attachment.file_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Long fileId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_attachment.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_attachment.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__email_attachment
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_attachment.uid
     *
     * @return the value of kona__email_attachment.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_attachment.uid
     *
     * @param uid the value for kona__email_attachment.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_attachment.content_id
     *
     * @return the value of kona__email_attachment.content_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Long getContentId() {
        return contentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_attachment.content_id
     *
     * @param contentId the value for kona__email_attachment.content_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_attachment.file_id
     *
     * @return the value of kona__email_attachment.file_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_attachment.file_id
     *
     * @param fileId the value for kona__email_attachment.file_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_attachment.created_date
     *
     * @return the value of kona__email_attachment.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_attachment.created_date
     *
     * @param createdDate the value for kona__email_attachment.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_attachment.updated_date
     *
     * @return the value of kona__email_attachment.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_attachment.updated_date
     *
     * @param updatedDate the value for kona__email_attachment.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}