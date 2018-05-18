package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BaseInvitation.Channel;
import com.bryllyant.kona.app.entity.BaseInvitation.Status;
import com.bryllyant.kona.app.entity.BaseInvitation.Type;
import java.io.Serializable;
import java.util.Date;

public class Invitation extends BaseInvitation implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.type
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Type type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.channel
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Channel channel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.status
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Status status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.owner_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long ownerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.contact_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long contactId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.invitee_user_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long inviteeUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.first_name
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String firstName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.last_name
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String lastName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.display_name
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String displayName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.email
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.mobile_number
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String mobileNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.invitation_code
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String invitationCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.message
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String message;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.invited_count
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Integer invitedCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.invited_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date invitedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.viewed_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date viewedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.ignored_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date ignoredDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.accepted_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date acceptedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.registered_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date registeredDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__invitation.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__invitation
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.id
     *
     * @return the value of kona__invitation.id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.id
     *
     * @param id the value for kona__invitation.id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.uid
     *
     * @return the value of kona__invitation.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.uid
     *
     * @param uid the value for kona__invitation.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.type
     *
     * @return the value of kona__invitation.type
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.type
     *
     * @param type the value for kona__invitation.type
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.channel
     *
     * @return the value of kona__invitation.channel
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.channel
     *
     * @param channel the value for kona__invitation.channel
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.status
     *
     * @return the value of kona__invitation.status
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Status getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.status
     *
     * @param status the value for kona__invitation.status
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.owner_id
     *
     * @return the value of kona__invitation.owner_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.owner_id
     *
     * @param ownerId the value for kona__invitation.owner_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.contact_id
     *
     * @return the value of kona__invitation.contact_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getContactId() {
        return contactId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.contact_id
     *
     * @param contactId the value for kona__invitation.contact_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.invitee_user_id
     *
     * @return the value of kona__invitation.invitee_user_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getInviteeUserId() {
        return inviteeUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.invitee_user_id
     *
     * @param inviteeUserId the value for kona__invitation.invitee_user_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setInviteeUserId(Long inviteeUserId) {
        this.inviteeUserId = inviteeUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.first_name
     *
     * @return the value of kona__invitation.first_name
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.first_name
     *
     * @param firstName the value for kona__invitation.first_name
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.last_name
     *
     * @return the value of kona__invitation.last_name
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.last_name
     *
     * @param lastName the value for kona__invitation.last_name
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.display_name
     *
     * @return the value of kona__invitation.display_name
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.display_name
     *
     * @param displayName the value for kona__invitation.display_name
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.email
     *
     * @return the value of kona__invitation.email
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.email
     *
     * @param email the value for kona__invitation.email
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.mobile_number
     *
     * @return the value of kona__invitation.mobile_number
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.mobile_number
     *
     * @param mobileNumber the value for kona__invitation.mobile_number
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber == null ? null : mobileNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.invitation_code
     *
     * @return the value of kona__invitation.invitation_code
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getInvitationCode() {
        return invitationCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.invitation_code
     *
     * @param invitationCode the value for kona__invitation.invitation_code
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode == null ? null : invitationCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.message
     *
     * @return the value of kona__invitation.message
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.message
     *
     * @param message the value for kona__invitation.message
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.invited_count
     *
     * @return the value of kona__invitation.invited_count
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Integer getInvitedCount() {
        return invitedCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.invited_count
     *
     * @param invitedCount the value for kona__invitation.invited_count
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setInvitedCount(Integer invitedCount) {
        this.invitedCount = invitedCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.invited_date
     *
     * @return the value of kona__invitation.invited_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getInvitedDate() {
        return invitedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.invited_date
     *
     * @param invitedDate the value for kona__invitation.invited_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setInvitedDate(Date invitedDate) {
        this.invitedDate = invitedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.viewed_date
     *
     * @return the value of kona__invitation.viewed_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getViewedDate() {
        return viewedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.viewed_date
     *
     * @param viewedDate the value for kona__invitation.viewed_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setViewedDate(Date viewedDate) {
        this.viewedDate = viewedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.ignored_date
     *
     * @return the value of kona__invitation.ignored_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getIgnoredDate() {
        return ignoredDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.ignored_date
     *
     * @param ignoredDate the value for kona__invitation.ignored_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setIgnoredDate(Date ignoredDate) {
        this.ignoredDate = ignoredDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.accepted_date
     *
     * @return the value of kona__invitation.accepted_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getAcceptedDate() {
        return acceptedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.accepted_date
     *
     * @param acceptedDate the value for kona__invitation.accepted_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.registered_date
     *
     * @return the value of kona__invitation.registered_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getRegisteredDate() {
        return registeredDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.registered_date
     *
     * @param registeredDate the value for kona__invitation.registered_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.created_date
     *
     * @return the value of kona__invitation.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.created_date
     *
     * @param createdDate the value for kona__invitation.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__invitation.updated_date
     *
     * @return the value of kona__invitation.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__invitation.updated_date
     *
     * @param updatedDate the value for kona__invitation.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}