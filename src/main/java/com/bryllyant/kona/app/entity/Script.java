package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BaseScript.Language;
import com.bryllyant.kona.app.entity.BaseScript.ReturnType;
import java.io.Serializable;
import java.util.Date;

public class Script extends BaseScript implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__script.uid
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__script.language
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Language language;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__script.return_type
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private ReturnType returnType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__script.name
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__script.slug
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__script.enabled
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__script.created_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__script.updated_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Date updatedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__script.body
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String body;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__script
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__script.uid
     *
     * @return the value of kona__script.uid
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__script.uid
     *
     * @param uid the value for kona__script.uid
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__script.language
     *
     * @return the value of kona__script.language
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__script.language
     *
     * @param language the value for kona__script.language
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__script.return_type
     *
     * @return the value of kona__script.return_type
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public ReturnType getReturnType() {
        return returnType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__script.return_type
     *
     * @param returnType the value for kona__script.return_type
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setReturnType(ReturnType returnType) {
        this.returnType = returnType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__script.name
     *
     * @return the value of kona__script.name
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__script.name
     *
     * @param name the value for kona__script.name
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__script.slug
     *
     * @return the value of kona__script.slug
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__script.slug
     *
     * @param slug the value for kona__script.slug
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__script.enabled
     *
     * @return the value of kona__script.enabled
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__script.enabled
     *
     * @param enabled the value for kona__script.enabled
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__script.created_date
     *
     * @return the value of kona__script.created_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__script.created_date
     *
     * @param createdDate the value for kona__script.created_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__script.updated_date
     *
     * @return the value of kona__script.updated_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__script.updated_date
     *
     * @param updatedDate the value for kona__script.updated_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__script.body
     *
     * @return the value of kona__script.body
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getBody() {
        return body;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__script.body
     *
     * @param body the value for kona__script.body
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }
}