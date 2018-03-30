/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseInvitation extends KBaseEntity implements KInvitation {
    private static final long serialVersionUID = 1L;

	private Long id;
	private String uid;
    private Type type;
    private Channel channel;
    private Status status;
    private Long ownerId;
    private Long contactId;
    private Long inviteeUserId;
    private String firstName;
    private String lastName;
    private String displayName;
    private String email;
    private String mobileNumber;
    private String invitationCode;
    private String message;
    private Integer invitedCount;
    private Date invitedDate;
    private Date viewedDate;
    private Date ignoredDate;
    private Date acceptedDate;
    private Date registeredDate;
    private Date createdDate;
    private Date updatedDate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Long getOwnerId() {
        return ownerId;
    }

    @Override
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public Long getContactId() {
        return contactId;
    }

    @Override
    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    @Override
    public Long getInviteeUserId() {
        return inviteeUserId;
    }

    @Override
    public void setInviteeUserId(Long inviteeUserId) {
        this.inviteeUserId = inviteeUserId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String getInvitationCode() {
        return invitationCode;
    }

    @Override
    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Integer getInvitedCount() {
        return invitedCount;
    }

    @Override
    public void setInvitedCount(Integer invitedCount) {
        this.invitedCount = invitedCount;
    }

    @Override
    public Date getInvitedDate() {
        return invitedDate;
    }

    @Override
    public void setInvitedDate(Date invitedDate) {
        this.invitedDate = invitedDate;
    }

    @Override
    public Date getViewedDate() {
        return viewedDate;
    }

    @Override
    public void setViewedDate(Date viewedDate) {
        this.viewedDate = viewedDate;
    }

    @Override
    public Date getIgnoredDate() {
        return ignoredDate;
    }

    @Override
    public void setIgnoredDate(Date ignoredDate) {
        this.ignoredDate = ignoredDate;
    }

    @Override
    public Date getAcceptedDate() {
        return acceptedDate;
    }

    @Override
    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    @Override
    public Date getRegisteredDate() {
        return registeredDate;
    }

    @Override
    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Date getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
