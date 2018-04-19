package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Product extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product.name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product.slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product.description
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product.display_order
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Integer displayOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product.subscription
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private boolean subscription;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product.enabled
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__product
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product.uid
     *
     * @return the value of kona__product.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product.uid
     *
     * @param uid the value for kona__product.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product.name
     *
     * @return the value of kona__product.name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product.name
     *
     * @param name the value for kona__product.name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product.slug
     *
     * @return the value of kona__product.slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product.slug
     *
     * @param slug the value for kona__product.slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product.description
     *
     * @return the value of kona__product.description
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product.description
     *
     * @param description the value for kona__product.description
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product.display_order
     *
     * @return the value of kona__product.display_order
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product.display_order
     *
     * @param displayOrder the value for kona__product.display_order
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product.subscription
     *
     * @return the value of kona__product.subscription
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public boolean isSubscription() {
        return subscription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product.subscription
     *
     * @param subscription the value for kona__product.subscription
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product.enabled
     *
     * @return the value of kona__product.enabled
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product.enabled
     *
     * @param enabled the value for kona__product.enabled
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product.created_date
     *
     * @return the value of kona__product.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product.created_date
     *
     * @param createdDate the value for kona__product.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product.updated_date
     *
     * @return the value of kona__product.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product.updated_date
     *
     * @param updatedDate the value for kona__product.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}