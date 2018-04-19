package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class PlacePlan extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__place_plan.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__place_plan.place_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Long placeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__place_plan.name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__place_plan.slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__place_plan.floor
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Integer floor;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__place_plan.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__place_plan.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date updatedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__place_plan.plan
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Map plan;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__place_plan.perimeter
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String perimeter;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__place_plan.encoded_perimeter
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String encodedPerimeter;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__place_plan
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__place_plan.uid
     *
     * @return the value of kona__place_plan.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__place_plan.uid
     *
     * @param uid the value for kona__place_plan.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__place_plan.place_id
     *
     * @return the value of kona__place_plan.place_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Long getPlaceId() {
        return placeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__place_plan.place_id
     *
     * @param placeId the value for kona__place_plan.place_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__place_plan.name
     *
     * @return the value of kona__place_plan.name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__place_plan.name
     *
     * @param name the value for kona__place_plan.name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__place_plan.slug
     *
     * @return the value of kona__place_plan.slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__place_plan.slug
     *
     * @param slug the value for kona__place_plan.slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__place_plan.floor
     *
     * @return the value of kona__place_plan.floor
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Integer getFloor() {
        return floor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__place_plan.floor
     *
     * @param floor the value for kona__place_plan.floor
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__place_plan.created_date
     *
     * @return the value of kona__place_plan.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__place_plan.created_date
     *
     * @param createdDate the value for kona__place_plan.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__place_plan.updated_date
     *
     * @return the value of kona__place_plan.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__place_plan.updated_date
     *
     * @param updatedDate the value for kona__place_plan.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__place_plan.plan
     *
     * @return the value of kona__place_plan.plan
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Map getPlan() {
        return plan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__place_plan.plan
     *
     * @param plan the value for kona__place_plan.plan
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setPlan(Map plan) {
        this.plan = plan;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__place_plan.perimeter
     *
     * @return the value of kona__place_plan.perimeter
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getPerimeter() {
        return perimeter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__place_plan.perimeter
     *
     * @param perimeter the value for kona__place_plan.perimeter
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setPerimeter(String perimeter) {
        this.perimeter = perimeter == null ? null : perimeter.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__place_plan.encoded_perimeter
     *
     * @return the value of kona__place_plan.encoded_perimeter
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getEncodedPerimeter() {
        return encodedPerimeter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__place_plan.encoded_perimeter
     *
     * @param encodedPerimeter the value for kona__place_plan.encoded_perimeter
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setEncodedPerimeter(String encodedPerimeter) {
        this.encodedPerimeter = encodedPerimeter == null ? null : encodedPerimeter.trim();
    }
}