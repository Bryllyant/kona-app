package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Account extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__account.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__account.owner_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long ownerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__account.name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__account.slug
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__account.enabled
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__account.verified
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean verified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__account.deleted_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date deletedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__account.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__account.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__account
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__account.uid
     *
     * @return the value of kona__account.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__account.uid
     *
     * @param uid the value for kona__account.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__account.owner_id
     *
     * @return the value of kona__account.owner_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__account.owner_id
     *
     * @param ownerId the value for kona__account.owner_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__account.name
     *
     * @return the value of kona__account.name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__account.name
     *
     * @param name the value for kona__account.name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__account.slug
     *
     * @return the value of kona__account.slug
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__account.slug
     *
     * @param slug the value for kona__account.slug
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__account.enabled
     *
     * @return the value of kona__account.enabled
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__account.enabled
     *
     * @param enabled the value for kona__account.enabled
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__account.verified
     *
     * @return the value of kona__account.verified
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__account.verified
     *
     * @param verified the value for kona__account.verified
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__account.deleted_date
     *
     * @return the value of kona__account.deleted_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getDeletedDate() {
        return deletedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__account.deleted_date
     *
     * @param deletedDate the value for kona__account.deleted_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__account.created_date
     *
     * @return the value of kona__account.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__account.created_date
     *
     * @param createdDate the value for kona__account.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__account.updated_date
     *
     * @return the value of kona__account.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__account.updated_date
     *
     * @param updatedDate the value for kona__account.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}