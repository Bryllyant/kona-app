package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class ApiVersion extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__api_version.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__api_version.name
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__api_version.description
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__api_version.enabled
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__api_version.published_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date publishedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__api_version.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__api_version.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__api_version
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__api_version.uid
     *
     * @return the value of kona__api_version.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__api_version.uid
     *
     * @param uid the value for kona__api_version.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__api_version.name
     *
     * @return the value of kona__api_version.name
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__api_version.name
     *
     * @param name the value for kona__api_version.name
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__api_version.description
     *
     * @return the value of kona__api_version.description
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__api_version.description
     *
     * @param description the value for kona__api_version.description
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__api_version.enabled
     *
     * @return the value of kona__api_version.enabled
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__api_version.enabled
     *
     * @param enabled the value for kona__api_version.enabled
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__api_version.published_date
     *
     * @return the value of kona__api_version.published_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getPublishedDate() {
        return publishedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__api_version.published_date
     *
     * @param publishedDate the value for kona__api_version.published_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__api_version.created_date
     *
     * @return the value of kona__api_version.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__api_version.created_date
     *
     * @param createdDate the value for kona__api_version.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__api_version.updated_date
     *
     * @return the value of kona__api_version.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__api_version.updated_date
     *
     * @param updatedDate the value for kona__api_version.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}