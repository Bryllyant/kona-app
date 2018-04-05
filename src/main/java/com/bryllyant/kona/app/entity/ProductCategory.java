package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class ProductCategory extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product_category.uid
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product_category.parent_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private Long parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product_category.name
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product_category.slug
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product_category.description
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product_category.display_order
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private Integer displayOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product_category.created_date
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__product_category.updated_date
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__product_category
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product_category.uid
     *
     * @return the value of kona__product_category.uid
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product_category.uid
     *
     * @param uid the value for kona__product_category.uid
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product_category.parent_id
     *
     * @return the value of kona__product_category.parent_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product_category.parent_id
     *
     * @param parentId the value for kona__product_category.parent_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product_category.name
     *
     * @return the value of kona__product_category.name
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product_category.name
     *
     * @param name the value for kona__product_category.name
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product_category.slug
     *
     * @return the value of kona__product_category.slug
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product_category.slug
     *
     * @param slug the value for kona__product_category.slug
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product_category.description
     *
     * @return the value of kona__product_category.description
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product_category.description
     *
     * @param description the value for kona__product_category.description
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product_category.display_order
     *
     * @return the value of kona__product_category.display_order
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product_category.display_order
     *
     * @param displayOrder the value for kona__product_category.display_order
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product_category.created_date
     *
     * @return the value of kona__product_category.created_date
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product_category.created_date
     *
     * @param createdDate the value for kona__product_category.created_date
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__product_category.updated_date
     *
     * @return the value of kona__product_category.updated_date
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__product_category.updated_date
     *
     * @param updatedDate the value for kona__product_category.updated_date
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}