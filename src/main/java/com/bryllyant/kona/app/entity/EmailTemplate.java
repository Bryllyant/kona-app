package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class EmailTemplate extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_template.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_template.owner_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long ownerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_template.name
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_template.slug
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_template.description
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_template.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_template.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date updatedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_template.text_header
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String textHeader;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_template.text_footer
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String textFooter;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_template.html_header
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String htmlHeader;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_template.html_footer
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String htmlFooter;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__email_template
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_template.uid
     *
     * @return the value of kona__email_template.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_template.uid
     *
     * @param uid the value for kona__email_template.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_template.owner_id
     *
     * @return the value of kona__email_template.owner_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_template.owner_id
     *
     * @param ownerId the value for kona__email_template.owner_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_template.name
     *
     * @return the value of kona__email_template.name
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_template.name
     *
     * @param name the value for kona__email_template.name
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_template.slug
     *
     * @return the value of kona__email_template.slug
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_template.slug
     *
     * @param slug the value for kona__email_template.slug
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_template.description
     *
     * @return the value of kona__email_template.description
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_template.description
     *
     * @param description the value for kona__email_template.description
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_template.created_date
     *
     * @return the value of kona__email_template.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_template.created_date
     *
     * @param createdDate the value for kona__email_template.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_template.updated_date
     *
     * @return the value of kona__email_template.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_template.updated_date
     *
     * @param updatedDate the value for kona__email_template.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_template.text_header
     *
     * @return the value of kona__email_template.text_header
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getTextHeader() {
        return textHeader;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_template.text_header
     *
     * @param textHeader the value for kona__email_template.text_header
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setTextHeader(String textHeader) {
        this.textHeader = textHeader == null ? null : textHeader.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_template.text_footer
     *
     * @return the value of kona__email_template.text_footer
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getTextFooter() {
        return textFooter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_template.text_footer
     *
     * @param textFooter the value for kona__email_template.text_footer
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setTextFooter(String textFooter) {
        this.textFooter = textFooter == null ? null : textFooter.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_template.html_header
     *
     * @return the value of kona__email_template.html_header
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getHtmlHeader() {
        return htmlHeader;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_template.html_header
     *
     * @param htmlHeader the value for kona__email_template.html_header
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setHtmlHeader(String htmlHeader) {
        this.htmlHeader = htmlHeader == null ? null : htmlHeader.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_template.html_footer
     *
     * @return the value of kona__email_template.html_footer
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getHtmlFooter() {
        return htmlFooter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_template.html_footer
     *
     * @param htmlFooter the value for kona__email_template.html_footer
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setHtmlFooter(String htmlFooter) {
        this.htmlFooter = htmlFooter == null ? null : htmlFooter.trim();
    }
}