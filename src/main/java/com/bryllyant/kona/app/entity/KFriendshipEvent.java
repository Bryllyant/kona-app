package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KFriendshipEvent extends KEntityObject {

    enum Type {
        FOLLOW,
        UNFOLLOW,
        BLOCK,
        UNBLOCK,
        FRIENDSHIP_REQUEST,
        FRIENDSHIP_ACCEPT,
        FRIENDSHIP_REJECT,
        FRIENDSHIP_REVOKE
    }

	Long getId();
	void setId(Long id);

	String getUid();
	void setUid(String uid);

	Type getType();
	void setType(Type type);

	Long getFriendshipId();
	void setFriendshipId(Long friendshipId);

	Long getUserId();
	void setUserId(Long userId);

	Long getFriendId();
	void setFriendId(Long friendId);

	String getEvent();
	void setEvent(String event);

	Date getEventDate();
	void setEventDate(Date eventDate);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);

}