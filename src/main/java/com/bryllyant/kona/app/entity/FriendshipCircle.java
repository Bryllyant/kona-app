package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class FriendshipCircle extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_circle.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_circle.user_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_circle.name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_circle.slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_circle.default_circle
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private boolean defaultCircle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_circle.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__friendship_circle.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__friendship_circle
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_circle.uid
     *
     * @return the value of kona__friendship_circle.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_circle.uid
     *
     * @param uid the value for kona__friendship_circle.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_circle.user_id
     *
     * @return the value of kona__friendship_circle.user_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_circle.user_id
     *
     * @param userId the value for kona__friendship_circle.user_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_circle.name
     *
     * @return the value of kona__friendship_circle.name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_circle.name
     *
     * @param name the value for kona__friendship_circle.name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_circle.slug
     *
     * @return the value of kona__friendship_circle.slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_circle.slug
     *
     * @param slug the value for kona__friendship_circle.slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_circle.default_circle
     *
     * @return the value of kona__friendship_circle.default_circle
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public boolean isDefaultCircle() {
        return defaultCircle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_circle.default_circle
     *
     * @param defaultCircle the value for kona__friendship_circle.default_circle
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setDefaultCircle(boolean defaultCircle) {
        this.defaultCircle = defaultCircle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_circle.created_date
     *
     * @return the value of kona__friendship_circle.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_circle.created_date
     *
     * @param createdDate the value for kona__friendship_circle.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__friendship_circle.updated_date
     *
     * @return the value of kona__friendship_circle.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__friendship_circle.updated_date
     *
     * @param updatedDate the value for kona__friendship_circle.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}