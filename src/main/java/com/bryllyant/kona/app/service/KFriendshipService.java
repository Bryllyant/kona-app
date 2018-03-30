/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KFriendship;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;


public interface KFriendshipService<FRIENDSHIP extends KFriendship> extends KService, KEntityService<FRIENDSHIP> {

    List<FRIENDSHIP> fetchByUserIdAndStatus(Long userId, FRIENDSHIP.Status status);
    List<FRIENDSHIP> fetchByFriendIdAndStatus(Long friendId,FRIENDSHIP.Status status);
    
	List<FRIENDSHIP> fetchFollowerList(Long userId);
	List<FRIENDSHIP> fetchFollowingList(Long userId);
	List<FRIENDSHIP> fetchFriendList(Long userId);

	List<FRIENDSHIP> fetchByCircleId(Long circleId);
    
    FRIENDSHIP fetchByUserIdAndFriendId(Long userId, Long friendId);
    FRIENDSHIP fetchByUid(String uid);
    
    Integer getFollowingCount(Long userId);
    Integer getFollowerCount(Long userId);
    
	FRIENDSHIP follow(Long userId, Long friendId, Long circleId);
	FRIENDSHIP block(Long userId, Long friendId);
	FRIENDSHIP unfollow(Long userId, Long friendId);
	FRIENDSHIP unblock(Long userId, Long friendId);
	
	FRIENDSHIP requestFriendship(Long userId, Long friendId, Long circleId);
	FRIENDSHIP acceptFriendship(Long userId, Long friendId);
	FRIENDSHIP rejectFriendship(Long userId, Long friendId);
	FRIENDSHIP revokeFriendship(Long userId, Long friendId);
	
	// request and accept friendship in single call without notifications
	FRIENDSHIP createFriendship(Long userId, Long friendId, Long circleId, boolean notifyUser);
    
}
