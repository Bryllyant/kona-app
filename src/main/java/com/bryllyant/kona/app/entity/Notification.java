package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Notification extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__notification.uid
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__notification.user_id
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__notification.event_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    private Date eventDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__notification.last_viewed_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    private Date lastViewedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__notification.created_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__notification.updated_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    private Date updatedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__notification.event
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    private String event;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__notification
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__notification.uid
     *
     * @return the value of kona__notification.uid
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__notification.uid
     *
     * @param uid the value for kona__notification.uid
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__notification.user_id
     *
     * @return the value of kona__notification.user_id
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__notification.user_id
     *
     * @param userId the value for kona__notification.user_id
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__notification.event_date
     *
     * @return the value of kona__notification.event_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__notification.event_date
     *
     * @param eventDate the value for kona__notification.event_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__notification.last_viewed_date
     *
     * @return the value of kona__notification.last_viewed_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public Date getLastViewedDate() {
        return lastViewedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__notification.last_viewed_date
     *
     * @param lastViewedDate the value for kona__notification.last_viewed_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public void setLastViewedDate(Date lastViewedDate) {
        this.lastViewedDate = lastViewedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__notification.created_date
     *
     * @return the value of kona__notification.created_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__notification.created_date
     *
     * @param createdDate the value for kona__notification.created_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__notification.updated_date
     *
     * @return the value of kona__notification.updated_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__notification.updated_date
     *
     * @param updatedDate the value for kona__notification.updated_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__notification.event
     *
     * @return the value of kona__notification.event
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public String getEvent() {
        return event;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__notification.event
     *
     * @param event the value for kona__notification.event
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }
}