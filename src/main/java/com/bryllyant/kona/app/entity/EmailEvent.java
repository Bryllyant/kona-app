package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BaseEmailEvent.Type;
import java.io.Serializable;
import java.util.Date;

public class EmailEvent extends BaseEmailEvent implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_event.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_event.email_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long emailId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_event.user_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_event.email_campaign_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long emailCampaignId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_event.type
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Type type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_event.target
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String target;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_event.error
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String error;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_event.hostname
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String hostname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_event.user_agent
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String userAgent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_event.event_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date eventDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_event.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email_event.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__email_event
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_event.uid
     *
     * @return the value of kona__email_event.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_event.uid
     *
     * @param uid the value for kona__email_event.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_event.email_id
     *
     * @return the value of kona__email_event.email_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getEmailId() {
        return emailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_event.email_id
     *
     * @param emailId the value for kona__email_event.email_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_event.user_id
     *
     * @return the value of kona__email_event.user_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_event.user_id
     *
     * @param userId the value for kona__email_event.user_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_event.email_campaign_id
     *
     * @return the value of kona__email_event.email_campaign_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getEmailCampaignId() {
        return emailCampaignId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_event.email_campaign_id
     *
     * @param emailCampaignId the value for kona__email_event.email_campaign_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setEmailCampaignId(Long emailCampaignId) {
        this.emailCampaignId = emailCampaignId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_event.type
     *
     * @return the value of kona__email_event.type
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_event.type
     *
     * @param type the value for kona__email_event.type
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_event.target
     *
     * @return the value of kona__email_event.target
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getTarget() {
        return target;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_event.target
     *
     * @param target the value for kona__email_event.target
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_event.error
     *
     * @return the value of kona__email_event.error
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getError() {
        return error;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_event.error
     *
     * @param error the value for kona__email_event.error
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setError(String error) {
        this.error = error == null ? null : error.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_event.hostname
     *
     * @return the value of kona__email_event.hostname
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_event.hostname
     *
     * @param hostname the value for kona__email_event.hostname
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_event.user_agent
     *
     * @return the value of kona__email_event.user_agent
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_event.user_agent
     *
     * @param userAgent the value for kona__email_event.user_agent
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_event.event_date
     *
     * @return the value of kona__email_event.event_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_event.event_date
     *
     * @param eventDate the value for kona__email_event.event_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_event.created_date
     *
     * @return the value of kona__email_event.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_event.created_date
     *
     * @param createdDate the value for kona__email_event.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email_event.updated_date
     *
     * @return the value of kona__email_event.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email_event.updated_date
     *
     * @param updatedDate the value for kona__email_event.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}