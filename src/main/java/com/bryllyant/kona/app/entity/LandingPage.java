package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class LandingPage extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page.template_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Long templateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page.name
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page.slug
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page.description
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page.enabled
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__landing_page
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page.uid
     *
     * @return the value of kona__landing_page.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page.uid
     *
     * @param uid the value for kona__landing_page.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page.template_id
     *
     * @return the value of kona__landing_page.template_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Long getTemplateId() {
        return templateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page.template_id
     *
     * @param templateId the value for kona__landing_page.template_id
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page.name
     *
     * @return the value of kona__landing_page.name
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page.name
     *
     * @param name the value for kona__landing_page.name
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page.slug
     *
     * @return the value of kona__landing_page.slug
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page.slug
     *
     * @param slug the value for kona__landing_page.slug
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page.description
     *
     * @return the value of kona__landing_page.description
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page.description
     *
     * @param description the value for kona__landing_page.description
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page.enabled
     *
     * @return the value of kona__landing_page.enabled
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page.enabled
     *
     * @param enabled the value for kona__landing_page.enabled
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page.created_date
     *
     * @return the value of kona__landing_page.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page.created_date
     *
     * @param createdDate the value for kona__landing_page.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page.updated_date
     *
     * @return the value of kona__landing_page.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page.updated_date
     *
     * @param updatedDate the value for kona__landing_page.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}