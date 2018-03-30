package com.bryllyant.kona.api.model.notification;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.data.model.KJsonModel;
import com.bryllyant.kona.data.model.KEntityModel;

import java.util.Date;

public class NotificationModel extends KJsonModel implements KEntityModel {
	private static final long serialVersionUID = 1L;


	
	private String uid;

	@RestdocsNotExpanded
	private UserModel user;

	private String event;
	private Date eventDate;
	private Date lastViewedDate;
	private Date createdDate;



	public static NotificationModel create(String uid) {
	    NotificationModel model = new NotificationModel();
	    model.setUid(uid);
	    return model;
	}



    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.set("uid", uid);
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.set("user", user);
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.set("event", event);
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.set("eventDate", eventDate);
    }

    public Date getLastViewedDate() {
        return lastViewedDate;
    }

    public void setLastViewedDate(Date lastViewedDate) {
        this.set("lastViewedDate", lastViewedDate);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }



}
