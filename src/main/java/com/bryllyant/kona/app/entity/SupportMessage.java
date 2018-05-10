package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class SupportMessage extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__support_message.uid
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__support_message.user_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__support_message.first_name
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String firstName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__support_message.last_name
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String lastName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__support_message.email
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__support_message.mobile_number
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String mobileNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__support_message.message
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String message;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__support_message.hostname
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String hostname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__support_message.user_agent
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String userAgent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__support_message.created_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__support_message.updated_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__support_message
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__support_message.uid
     *
     * @return the value of kona__support_message.uid
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__support_message.uid
     *
     * @param uid the value for kona__support_message.uid
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__support_message.user_id
     *
     * @return the value of kona__support_message.user_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__support_message.user_id
     *
     * @param userId the value for kona__support_message.user_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__support_message.first_name
     *
     * @return the value of kona__support_message.first_name
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__support_message.first_name
     *
     * @param firstName the value for kona__support_message.first_name
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__support_message.last_name
     *
     * @return the value of kona__support_message.last_name
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__support_message.last_name
     *
     * @param lastName the value for kona__support_message.last_name
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__support_message.email
     *
     * @return the value of kona__support_message.email
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__support_message.email
     *
     * @param email the value for kona__support_message.email
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__support_message.mobile_number
     *
     * @return the value of kona__support_message.mobile_number
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__support_message.mobile_number
     *
     * @param mobileNumber the value for kona__support_message.mobile_number
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber == null ? null : mobileNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__support_message.message
     *
     * @return the value of kona__support_message.message
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__support_message.message
     *
     * @param message the value for kona__support_message.message
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__support_message.hostname
     *
     * @return the value of kona__support_message.hostname
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__support_message.hostname
     *
     * @param hostname the value for kona__support_message.hostname
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__support_message.user_agent
     *
     * @return the value of kona__support_message.user_agent
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__support_message.user_agent
     *
     * @param userAgent the value for kona__support_message.user_agent
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__support_message.created_date
     *
     * @return the value of kona__support_message.created_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__support_message.created_date
     *
     * @param createdDate the value for kona__support_message.created_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__support_message.updated_date
     *
     * @return the value of kona__support_message.updated_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__support_message.updated_date
     *
     * @param updatedDate the value for kona__support_message.updated_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}