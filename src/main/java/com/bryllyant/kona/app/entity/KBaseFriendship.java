/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseFriendship extends KBaseEntity implements KFriendship {
    private Long id;
    private String uid;
    private Long userId;
    private Long friendId;
    private Status status;
    private Long circleId;
    private boolean friendshipRequested;
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
	public Long getFriendId() {
        return friendId;
    }

    @Override
	public void setFriendId(Long friendId) {
        this.friendId = friendId;
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
	public Long getCircleId() {
        return circleId;
    }

    @Override
    public void setCircleId(Long circleId) {
    	this.circleId = circleId;
    }

    @Override
    public boolean isFriendshipRequested() {
    	return friendshipRequested;
    }

    @Override
    public void setFriendshipRequested(boolean friendshipRequested) {
    	this.friendshipRequested = friendshipRequested;
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
