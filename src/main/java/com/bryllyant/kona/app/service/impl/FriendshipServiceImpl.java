package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.FriendshipMapper;
import com.bryllyant.kona.app.entity.Friendship;
import com.bryllyant.kona.app.entity.FriendshipEvent;
import com.bryllyant.kona.app.entity.FriendshipExample;
import com.bryllyant.kona.app.service.FriendshipEventService;
import com.bryllyant.kona.app.service.FriendshipService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(FriendshipService.SERVICE_PATH)
public class FriendshipServiceImpl 
		extends KAbstractService<Friendship,FriendshipExample,FriendshipMapper>
		implements FriendshipService {
	
	private static Logger logger = LoggerFactory.getLogger(FriendshipServiceImpl.class);

	@Autowired
	private FriendshipMapper friendshipMapper;
	
	@Autowired
	FriendshipEventService friendshipEventService;
	
	@Autowired
	//NotificationService notificationService;
	

	@Override @SuppressWarnings("unchecked")
	protected FriendshipMapper getMapper() {
		return friendshipMapper;
	}


	protected void notifyEvent(Friendship friendship, FriendshipEvent event) {
		logger.debug("notifyEvent called: friendship: {}  friendshipEvent: {}", friendship, event);
		
		switch (event.getType()) {
		case FOLLOW:
			break;
			
		case FRIENDSHIP_ACCEPT:
			break;
			
		case FRIENDSHIP_REQUEST:
			break;
			
		case BLOCK:
		case UNFOLLOW:
		case FRIENDSHIP_REJECT:
		case FRIENDSHIP_REVOKE:
		case UNBLOCK:
			break;
		default:
			break;
			
		}
	}


    @Override
    public void validate(Friendship friendship) {
        if (friendship.getCreatedDate() == null) {
            friendship.setCreatedDate(new Date());
        }

        friendship.setUpdatedDate(new Date());

        if (friendship.getUid() == null) {
            friendship.setUid(uuid());
        }
    }


    @Override
    public List<Friendship> fetchByCircleId(Long circleId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("circleId", circleId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public List<Friendship> fetchByUserIdAndStatus(Long userId, Friendship.Status status) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);

        if (status != null) {
            filter.put("status", status);
        }

        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public List<Friendship> fetchByFriendIdAndStatus(Long friendId, Friendship.Status status) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("friendId", friendId);

        if (status != null) {
            filter.put("status", status);
        }

        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public Friendship fetchByUserIdAndFriendId(Long userId, Long friendId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("friendId", friendId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    private FriendshipEvent addEvent(Friendship friendship, FriendshipEvent.Type type) {
        FriendshipEvent event = new FriendshipEvent();
        event.setFriendshipId(friendship.getId());
        event.setUserId(friendship.getUserId());
        event.setFriendId(friendship.getFriendId());
        event.setType(type);
        event.setEventDate(new Date());
        return friendshipEventService.add(event);
    }



    @Override
    public Friendship follow(Long userId, Long friendId, Long circleId) {
        return follow(userId, friendId, circleId, false, true);
    }

    private Friendship follow(Long userId, Long friendId, Long circleId, boolean friendshipRequest, boolean notifyEvent) {
        // user following friend
        Friendship friendship = fetchByUserIdAndFriendId(userId, friendId);
        FriendshipEvent event =  null;

        if (friendship == null) {
            friendship = new Friendship();
            friendship.setUserId(userId);
            friendship.setFriendId(friendId);
            friendship.setCircleId(circleId);
            friendship.setStatus(Friendship.Status.FOLLOWING);
            friendship.setFriendshipRequested(friendshipRequest);
            friendship = add(friendship);
            if (friendshipRequest) {
                event = addEvent(friendship, FriendshipEvent.Type.FRIENDSHIP_REQUEST);
            } else {
                event = addEvent(friendship, FriendshipEvent.Type.FOLLOW);
            }

            if (notifyEvent) {
                notifyEvent(friendship, event);
            }
        } else {
            // if friendship exists, make sure we're not blocked
            boolean dirty = false;

            switch (friendship.getStatus()) {

                case NONE:
                    friendship.setStatus(Friendship.Status.FOLLOWING);
                    friendship.setFriendshipRequested(friendshipRequest);
                    friendship.setCircleId(circleId);
                    dirty = true;
                    break;

                case FOLLOWED:
                    friendship.setStatus(Friendship.Status.FRIENDS);
                    friendship.setFriendshipRequested(friendshipRequest);
                    friendship.setCircleId(circleId);
                    dirty = true;
                    break;

                // do nothing if we're already
                case FRIENDS:
                case FOLLOWING:
                case BLOCKING:
                case BLOCKED:
                    break;
            }

            if (dirty) {
                friendship = update(friendship);

                if (friendshipRequest) {
                    event = addEvent(friendship, FriendshipEvent.Type.FRIENDSHIP_REQUEST);
                } else {
                    event = addEvent(friendship, FriendshipEvent.Type.FOLLOW);
                }

                if (notifyEvent) {
                    notifyEvent(friendship, event);
                }
            }
        }


        // create the mirror entry; friend followed by user
        Friendship other = fetchByUserIdAndFriendId(friendId, userId);
        if (other == null) {
            other = new Friendship();
            other.setUserId(friendId);
            other.setFriendId(userId);
            other.setStatus(Friendship.Status.FOLLOWED);
            other = add(other);
        } else {
            // if friendship exists, make sure we're not blocked

            boolean dirty = false;

            switch (other.getStatus()) {
                case NONE:
                    other.setStatus(Friendship.Status.FOLLOWED);
                    dirty = true;
                    break;

                case FOLLOWING:
                    other.setStatus(Friendship.Status.FRIENDS);
                    dirty = true;
                    break;

                // do nothing if we're already
                case FRIENDS:
                case FOLLOWED:
                case BLOCKING:
                case BLOCKED:
                    break;
            }

            if (dirty) {
                other = update(other);

                if (friendshipRequest && other.getStatus() == Friendship.Status.FOLLOWING) {
                    event = addEvent(other, FriendshipEvent.Type.FRIENDSHIP_ACCEPT);

                    if (notifyEvent) {
                        notifyEvent(other, event);
                    }
                }
            }
        }

        return friendship;
    }



    @Override
    public Friendship block(Long userId, Long friendId) {
        // user blocking friend
        Friendship friendship = fetchByUserIdAndFriendId(userId, friendId);
        if (friendship == null) {
            friendship = new Friendship();
            friendship.setUserId(userId);
            friendship.setFriendId(friendId);
            friendship.setStatus(Friendship.Status.BLOCKING);
            friendship = add(friendship);
            addEvent(friendship, FriendshipEvent.Type.BLOCK);

        } else {
            // if friendship exists
            boolean dirty = false;

            switch (friendship.getStatus()) {
                case NONE:
                case FRIENDS:
                case FOLLOWED:
                case FOLLOWING:
                case BLOCKED:
                    friendship.setStatus(Friendship.Status.BLOCKING);
                    dirty = true;
                    break;

                // do nothing if we're already
                case BLOCKING:
                    break;
            }

            if (dirty) {
                friendship = update(friendship);
                addEvent(friendship, FriendshipEvent.Type.BLOCK);
            }
        }


        // create the mirror entry; friend followed by user
        Friendship other = fetchByUserIdAndFriendId(friendId, userId);
        if (other == null) {
            other = new Friendship();
            other.setUserId(friendId);
            other.setFriendId(userId);
            other.setStatus(Friendship.Status.BLOCKED);
            other = add(other);
        } else {
            // if friendship exists
            boolean dirty = false;

            switch (other.getStatus()) {
                case NONE:
                case FRIENDS:
                case FOLLOWED:
                case FOLLOWING:
                    other.setStatus(Friendship.Status.BLOCKED);
                    dirty = true;
                    break;

                // do nothing if we're already
                case BLOCKED:
                case BLOCKING:
                    break;
            }

            if (dirty) {
                other = update(other);
            }
        }

        return friendship;
    }




    @Override
    public Friendship unfollow(Long userId, Long friendId) {
        return unfollow(userId, friendId, false);
    }

    private Friendship unfollow(Long userId, Long friendId, boolean revokeFriendship) {
        Friendship friendship = fetchByUserIdAndFriendId(userId, friendId);
        if (friendship == null) {
            throw new IllegalStateException("Friendship not found for userId: " + userId + "  friendId: " + friendId);
        }

        Friendship other = fetchByUserIdAndFriendId(friendId, userId);
        if (other == null) {
            throw new IllegalStateException("Friendship not found for userId: " + friendId + "  friendId: " + userId);
        }

        // user -> friend
        boolean dirty = false;

        switch (friendship.getStatus()) {
            case FRIENDS:
                friendship.setStatus(Friendship.Status.FOLLOWED);
                dirty = true;
                break;

            case FOLLOWING:
                friendship.setStatus(Friendship.Status.NONE);
                dirty = true;
                break;

            // do nothing if we're already
            case NONE:
            case FOLLOWED:
            case BLOCKING:
            case BLOCKED:
                break;
        }

        if (dirty) {
            friendship = update(friendship);
            if (revokeFriendship) {
                addEvent(friendship, FriendshipEvent.Type.FRIENDSHIP_REVOKE);
            } else {
                addEvent(friendship, FriendshipEvent.Type.UNFOLLOW);
            }
        }


        // friend -> user
        switch (other.getStatus()) {
            case FRIENDS:
                other.setStatus(Friendship.Status.FOLLOWING);
                dirty = true;
                break;

            case FOLLOWED:
                other.setStatus(Friendship.Status.NONE);
                dirty = true;
                break;

            case NONE:
            case FOLLOWING:
            case BLOCKED:
            case BLOCKING:
                break;
        }

        if (dirty) {
            other = update(other);
        }

        return friendship;
    }



    @Override
    public Friendship unblock(Long userId, Long friendId) {
        Friendship friendship = fetchByUserIdAndFriendId(userId, friendId);
        if (friendship == null) {
            throw new IllegalStateException("Friendship not found for userId: " + userId + "  friendId: " + friendId);
        }

        Friendship other = fetchByUserIdAndFriendId(friendId, userId);
        if (other == null) {
            throw new IllegalStateException("Friendship not found for userId: " + friendId + "  friendId: " + userId);
        }


        // user -> friend
        boolean dirty = false;

        switch (friendship.getStatus()) {
            case BLOCKING:
                friendship.setStatus(Friendship.Status.NONE);
                dirty = true;
                break;

            // do nothing if we're already
            case NONE:
            case FRIENDS:
            case FOLLOWING:
            case FOLLOWED:
            case BLOCKED:
                break;
        }

        if (dirty) {
            friendship = update(friendship);
            addEvent(friendship, FriendshipEvent.Type.UNBLOCK);
        }


        // friend -> user
        switch (other.getStatus()) {
            case BLOCKED:
                other.setStatus(Friendship.Status.NONE);
                dirty = true;
                break;

            case NONE:
            case FRIENDS:
            case FOLLOWED:
            case FOLLOWING:
            case BLOCKING:
                break;
        }

        if (dirty) {
            other = update(other);
        }

        return friendship;
    }



    @Override
    public Friendship requestFriendship(Long userId, Long friendId, Long circleId) {
        return follow(userId, friendId, circleId, true, true);
    }



    @Override
    public Friendship createFriendship(Long userId, Long friendId, Long circleId, boolean notifyUser) {
        follow(userId, friendId, circleId, true, false);
        acceptFriendship(friendId, userId, notifyUser);

        return fetchByUserIdAndFriendId(userId, friendId);
    }



    @Override
    public Friendship acceptFriendship(Long userId, Long friendId) {
        return acceptFriendship(userId, friendId, true);
    }



    private Friendship acceptFriendship(Long userId, Long friendId, boolean notifyEvent) {
        Friendship friendship = fetchByUserIdAndFriendId(userId, friendId);
        FriendshipEvent event =  null;

        if (friendship == null) {
            throw new IllegalStateException("Friendship not found for userId: " + userId + "  friendId: " + friendId);
        }

        Friendship other = fetchByUserIdAndFriendId(friendId, userId);

        if (other == null) {
            throw new IllegalStateException("Friendship not found for userId: " + friendId + "  friendId: " + userId);
        }


        // user -> friend
        boolean dirty = false;

        switch (friendship.getStatus()) {
            case NONE:
            case FOLLOWING:
            case FOLLOWED:
                friendship.setStatus(Friendship.Status.FRIENDS);
                dirty = true;
                break;

            // do nothing if we're already
            case FRIENDS:
            case BLOCKING:
            case BLOCKED:
                break;
        }

        if (dirty) {
            friendship = update(friendship);
            event = addEvent(friendship, FriendshipEvent.Type.FRIENDSHIP_ACCEPT);

            if (notifyEvent) {
                notifyEvent(friendship, event);
            }
        }


        // friend -> user
        switch (other.getStatus()) {
            case NONE:
            case FOLLOWED:
            case FOLLOWING:
                other.setStatus(Friendship.Status.FRIENDS);
                dirty = true;
                break;

            case FRIENDS:
            case BLOCKED:
            case BLOCKING:
                break;
        }

        if (dirty) {
            other = update(other);
        }

        return friendship;
    }



    @Override
    public Friendship rejectFriendship(Long userId, Long friendId) {
        Friendship friendship = fetchByUserIdAndFriendId(userId, friendId);
        if (friendship == null) {
            throw new IllegalStateException("Friendship not found for userId: " + userId + "  friendId: " + friendId);
        }
        addEvent(friendship, FriendshipEvent.Type.FRIENDSHIP_REJECT);
        return friendship;
    }



    @Override
    public List<Friendship> fetchFollowerList(Long userId) {
        List<Friendship> all = new ArrayList<>();

        List<Friendship> followers = fetchByUserIdAndStatus(userId, Friendship.Status.FOLLOWED);
        List<Friendship> friends = fetchByUserIdAndStatus(userId, Friendship.Status.FRIENDS);
        all.addAll(followers);
        all.addAll(friends);
        return all;
    }



    @Override
    public List<Friendship> fetchFollowingList(Long userId) {
        List<Friendship> all = new ArrayList<>();

        List<Friendship> following = fetchByUserIdAndStatus(userId, Friendship.Status.FOLLOWING);
        List<Friendship> friends = fetchByUserIdAndStatus(userId, Friendship.Status.FRIENDS);
        all.addAll(following);
        all.addAll(friends);
        return all;
    }



    @Override
    public List<Friendship> fetchFriendList(Long userId) {
        List<Friendship> all = new ArrayList<>();

        List<Friendship> friends = fetchByUserIdAndStatus(userId, Friendship.Status.FRIENDS);
        all.addAll(friends);
        return all;
    }



    @Override
    public Friendship revokeFriendship(Long userId, Long friendId) {
        return unfollow(userId, friendId, true);
    }



    @Override
    public Integer getFollowerCount(Long userId) {
        List<Friendship> followers = fetchFollowerList(userId);
        return followers.size();
    }



    @Override
    public Integer getFollowingCount(Long userId) {
        List<Friendship> following = fetchFollowingList(userId);
        return following.size();
    }
}
