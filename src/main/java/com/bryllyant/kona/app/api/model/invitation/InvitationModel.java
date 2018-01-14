package com.bryllyant.kona.app.api.model.invitation;

import com.bryllyant.kona.app.entity.KInvitationChannel;
import com.bryllyant.kona.app.entity.KInvitationStatus;
import com.bryllyant.kona.app.entity.KInvitationType;
import com.bryllyant.kona.data.model.KJsonModel;
import com.bryllyant.kona.data.model.KEntityModel;

import java.util.Date;

public class InvitationModel extends KJsonModel implements KEntityModel {

	private static final long serialVersionUID = 1L;

    // ----------------------------------------------------------------------
	
	private String uid;
	private KInvitationType type;
	private KInvitationChannel channel;
	private KInvitationStatus status;
	private Date createdDate;
	private String invitationCode;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String message;
	private Integer invitedCount;
	private Date invitedDate;
	private Date viewedDate;
	private Date acceptedDate;
	private Date registeredDate;

    // ----------------------------------------------------------------------
    
    public static InvitationModel create(String uid) {
        InvitationModel model = new InvitationModel();
        model.setUid(uid);
        return model;
    }

    // ----------------------------------------------------------------------

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.set("uid", uid);
    }

    public KInvitationType getType() {
        return type;
    }

    public void setType(KInvitationType type) {
        this.set("type", type);
    }

    public KInvitationChannel getChannel() {
        return channel;
    }

    public void setChannel(KInvitationChannel channel) {
        this.set("channel", channel);
    }

    public KInvitationStatus getStatus() {
        return status;
    }

    public void setStatus(KInvitationStatus status) {
        this.set("status", status);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.set("invitationCode", invitationCode);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.set("firstName", firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.set("lastName", lastName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.set("email", email);
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.set("mobileNumber", mobileNumber);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.set("message", message);
    }

    public Integer getInvitedCount() {
        return invitedCount;
    }

    public void setInvitedCount(Integer invitedCount) {
        this.set("invitedCount", invitedCount);
    }

    public Date getInvitedDate() {
        return invitedDate;
    }

    public void setInvitedDate(Date invitedDate) {
        this.set("invitedDate", invitedDate);
    }

    public Date getViewedDate() {
        return viewedDate;
    }

    public void setViewedDate(Date viewedDate) {
        this.set("viewedDate", viewedDate);
    }

    public Date getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(Date acceptedDate) {
        this.set("acceptedDate", acceptedDate);
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.set("registeredDate", registeredDate);
    }


    // ----------------------------------------------------------------------


}
