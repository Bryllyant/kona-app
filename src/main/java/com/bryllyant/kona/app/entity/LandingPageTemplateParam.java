package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BaseLandingPageTemplateParam.Type;
import java.io.Serializable;
import java.util.Date;

public class LandingPageTemplateParam extends BaseLandingPageTemplateParam implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template_param.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template_param.template_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long templateId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template_param.name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template_param.slug
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template_param.display_name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String displayName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template_param.type
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Type type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template_param.required
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean required;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template_param.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_template_param.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__landing_page_template_param
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template_param.uid
     *
     * @return the value of kona__landing_page_template_param.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template_param.uid
     *
     * @param uid the value for kona__landing_page_template_param.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template_param.template_id
     *
     * @return the value of kona__landing_page_template_param.template_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getTemplateId() {
        return templateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template_param.template_id
     *
     * @param templateId the value for kona__landing_page_template_param.template_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template_param.name
     *
     * @return the value of kona__landing_page_template_param.name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template_param.name
     *
     * @param name the value for kona__landing_page_template_param.name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template_param.slug
     *
     * @return the value of kona__landing_page_template_param.slug
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template_param.slug
     *
     * @param slug the value for kona__landing_page_template_param.slug
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template_param.display_name
     *
     * @return the value of kona__landing_page_template_param.display_name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template_param.display_name
     *
     * @param displayName the value for kona__landing_page_template_param.display_name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template_param.type
     *
     * @return the value of kona__landing_page_template_param.type
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template_param.type
     *
     * @param type the value for kona__landing_page_template_param.type
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template_param.required
     *
     * @return the value of kona__landing_page_template_param.required
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template_param.required
     *
     * @param required the value for kona__landing_page_template_param.required
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template_param.created_date
     *
     * @return the value of kona__landing_page_template_param.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template_param.created_date
     *
     * @param createdDate the value for kona__landing_page_template_param.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_template_param.updated_date
     *
     * @return the value of kona__landing_page_template_param.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_template_param.updated_date
     *
     * @param updatedDate the value for kona__landing_page_template_param.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}