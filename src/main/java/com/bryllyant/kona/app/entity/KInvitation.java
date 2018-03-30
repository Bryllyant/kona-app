package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KInvitation extends KEntityObject {
    enum Type {
        JOIN,
        FRIEND
    }

    enum Status {
        PENDING,
        ACCEPTED,
        DECLINED,
        IGNORED
    }

    enum Channel {
        IN_APP,
        EMAIL,
        SMS,
        TWITTER,
        FACEBOOK
    }

	@Override
	Long getId();
	void setId(Long id);

	String getUid();
	void setUid(String uid);

	Type getType();
	void setType(Type type);

	Channel getChannel();
	void setChannel(Channel channel);

	Status getStatus();
	void setStatus(Status status);

	Long getOwnerId();
	void setOwnerId(Long ownerId);

	Long getContactId();
	void setContactId(Long contactId);

	Long getInviteeUserId();
	void setInviteeUserId(Long inviteeUserId);

	String getFirstName();
	void setFirstName(String firstName);

	String getLastName();
	void setLastName(String lastName);

	String getDisplayName();
	void setDisplayName(String displayName);

	String getEmail();
	void setEmail(String email);

	String getMobileNumber();
	void setMobileNumber(String mobileNumber);

	String getInvitationCode();
	void setInvitationCode(String invitationCode);

	String getMessage();
	void setMessage(String message);

	Integer getInvitedCount();
	void setInvitedCount(Integer invitedCount);

	Date getInvitedDate();
	void setInvitedDate(Date invitedDate);

	Date getViewedDate();
	void setViewedDate(Date viewedDate);

	Date getIgnoredDate();
	void setIgnoredDate(Date ignoredDate);

	Date getAcceptedDate();
	void setAcceptedDate(Date acceptedDate);

	Date getRegisteredDate();
	void setRegisteredDate(Date registeredDate);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);
}
