/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseFriendshipEvent extends KBaseEntity implements KFriendshipEvent {
    private Long id;
    private String uid;
    private Type type;
    private Long friendshipId;
    private Long userId;
    private Long friendId;
    private String event;
    private Date eventDate;
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
	public Long getFriendshipId() {
        return friendshipId;
    }

    @Override
	public void setFriendshipId(Long friendshipId) {
        this.friendshipId = friendshipId;
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
	public Long getFriendId() {
        return friendId;
    }

    @Override
	public void setFriendId(Long friendId) {
        this.friendId = friendId;
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
