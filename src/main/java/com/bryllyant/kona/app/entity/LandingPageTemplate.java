package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class LandingPageTemplate extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template.uid
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template.owner_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Long ownerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template.file_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Long fileId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template.url_path
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String urlPath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template.name
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template.slug
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template.description
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template.created_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template.updated_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__landing_page_template
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template.uid
     *
     * @return the value of kona__landing_page_template.uid
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template.uid
     *
     * @param uid the value for kona__landing_page_template.uid
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template.owner_id
     *
     * @return the value of kona__landing_page_template.owner_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template.owner_id
     *
     * @param ownerId the value for kona__landing_page_template.owner_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template.file_id
     *
     * @return the value of kona__landing_page_template.file_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template.file_id
     *
     * @param fileId the value for kona__landing_page_template.file_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template.url_path
     *
     * @return the value of kona__landing_page_template.url_path
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getUrlPath() {
        return urlPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template.url_path
     *
     * @param urlPath the value for kona__landing_page_template.url_path
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath == null ? null : urlPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template.name
     *
     * @return the value of kona__landing_page_template.name
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template.name
     *
     * @param name the value for kona__landing_page_template.name
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template.slug
     *
     * @return the value of kona__landing_page_template.slug
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template.slug
     *
     * @param slug the value for kona__landing_page_template.slug
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template.description
     *
     * @return the value of kona__landing_page_template.description
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template.description
     *
     * @param description the value for kona__landing_page_template.description
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template.created_date
     *
     * @return the value of kona__landing_page_template.created_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template.created_date
     *
     * @param createdDate the value for kona__landing_page_template.created_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template.updated_date
     *
     * @return the value of kona__landing_page_template.updated_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template.updated_date
     *
     * @param updatedDate the value for kona__landing_page_template.updated_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}