package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KFriendship extends KEntityObject {
    enum Status {
        NONE,
        FRIENDS,
        PENDING,
        FOLLOWING,
        FOLLOWED,
        BLOCKING,
        BLOCKED
    }

	Long getId();

	void setId(Long id);

	String getUid();

	void setUid(String uid);

	Long getUserId();

	void setUserId(Long userId);

	Long getFriendId();

	void setFriendId(Long friendId);

	Status getStatus();

	void setStatus(Status status);

	Long getCircleId();

	void setCircleId(Long circleId);

	/** Flag to indicate if a friend request was made by this user to the friend.  If false, the user simply followed the friend. */
	boolean isFriendshipRequested();

	void setFriendshipRequested(boolean friendshipRequested);
	
	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);



}