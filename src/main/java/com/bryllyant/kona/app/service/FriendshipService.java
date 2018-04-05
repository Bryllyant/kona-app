package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Friendship;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface FriendshipService extends KService, KEntityService<Friendship> {
	String SERVICE_PATH = "rpc/FriendshipService";

    List<Friendship> fetchByUserIdAndStatus(Long userId, Friendship.Status status);
    List<Friendship> fetchByFriendIdAndStatus(Long friendId,Friendship.Status status);

    List<Friendship> fetchFollowerList(Long userId);
    List<Friendship> fetchFollowingList(Long userId);
    List<Friendship> fetchFriendList(Long userId);

    List<Friendship> fetchByCircleId(Long circleId);

    Friendship fetchByUserIdAndFriendId(Long userId, Long friendId);

    Integer getFollowingCount(Long userId);
    Integer getFollowerCount(Long userId);

    Friendship follow(Long userId, Long friendId, Long circleId);
    Friendship block(Long userId, Long friendId);
    Friendship unfollow(Long userId, Long friendId);
    Friendship unblock(Long userId, Long friendId);

    Friendship requestFriendship(Long userId, Long friendId, Long circleId);
    Friendship acceptFriendship(Long userId, Long friendId);
    Friendship rejectFriendship(Long userId, Long friendId);
    Friendship revokeFriendship(Long userId, Long friendId);

    // request and accept friendship in single call without notifications
    Friendship createFriendship(Long userId, Long friendId, Long circleId, boolean notifyUser);
	
}
