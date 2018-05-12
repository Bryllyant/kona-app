package com.bryllyant.kona.app.entity;

import java.io.Serializable;
import java.util.Date;

public class UserDevice extends BaseUserDevice implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user_device.id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user_device.user_id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user_device.device_id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private Long deviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user_device.name
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user_device.slug
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user_device.created_date
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__user_device.updated_date
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__user_device
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user_device.id
     *
     * @return the value of kona__user_device.id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user_device.id
     *
     * @param id the value for kona__user_device.id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user_device.user_id
     *
     * @return the value of kona__user_device.user_id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user_device.user_id
     *
     * @param userId the value for kona__user_device.user_id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user_device.device_id
     *
     * @return the value of kona__user_device.device_id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user_device.device_id
     *
     * @param deviceId the value for kona__user_device.device_id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user_device.name
     *
     * @return the value of kona__user_device.name
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user_device.name
     *
     * @param name the value for kona__user_device.name
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user_device.slug
     *
     * @return the value of kona__user_device.slug
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user_device.slug
     *
     * @param slug the value for kona__user_device.slug
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user_device.created_date
     *
     * @return the value of kona__user_device.created_date
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user_device.created_date
     *
     * @param createdDate the value for kona__user_device.created_date
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__user_device.updated_date
     *
     * @return the value of kona__user_device.updated_date
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__user_device.updated_date
     *
     * @param updatedDate the value for kona__user_device.updated_date
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}