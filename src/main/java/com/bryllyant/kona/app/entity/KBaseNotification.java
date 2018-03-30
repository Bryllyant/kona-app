package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseNotification extends KBaseEntity implements KNotification {
    private Long id;
    private String uid;
    private Long userId;
    private String event;
    private Date eventDate;
    private Date lastViewedDate;
    private Date createdDate;
    private Date updatedDate;

    private static final long serialVersionUID = 1L;

  
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
        this.uid = uid == null ? null : uid.trim();
    }


    @Override
	public Long getUserId() {
        return userId;
    }

   
    @Override
	public void setUserId(Long userId) {
        this.userId = userId;
    }

  
    @Override
	public String getEvent() {
        return event;
    }

  
    @Override
	public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

 
    @Override
	public Date getLastViewedDate() {
        return lastViewedDate;
    }

  
    @Override
	public void setLastViewedDate(Date lastViewedDate) {
        this.lastViewedDate = lastViewedDate;
    }

   
    @Override
	public Date getEventDate() {
        return eventDate;
    }


    @Override
	public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
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
