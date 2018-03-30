/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KFriendship;
import com.bryllyant.kona.app.entity.KFriendshipEvent;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractFriendshipService<
            FRIENDSHIP extends KFriendship,
            FRIENDSHIP_EXAMPLE extends KEntityExample,
            MAPPER extends KMyBatisMapper<FRIENDSHIP, FRIENDSHIP_EXAMPLE>,
            FRIENDSHIP_EVENT extends KFriendshipEvent>
        extends KAbstractService<FRIENDSHIP,FRIENDSHIP_EXAMPLE,MAPPER>
        implements KFriendshipService<FRIENDSHIP> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractFriendshipService.class);

    protected abstract FRIENDSHIP getNewFriendshipObject();

    protected abstract FRIENDSHIP_EVENT getNewFriendshipEventObject();

    protected abstract <S extends KFriendshipEventService<FRIENDSHIP_EVENT>> S getFriendshipEventService();

    protected abstract void notifyEvent(FRIENDSHIP friendship, KFriendshipEvent event);


    @Override
    public void validate(FRIENDSHIP friendship) {
        if (friendship.getCreatedDate() == null) {
            friendship.setCreatedDate(new Date());
        }

        friendship.setUpdatedDate(new Date());

        if (friendship.getUid() == null) {
            friendship.setUid(uuid());
        }
    }



    @Override
    public FRIENDSHIP fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public List<FRIENDSHIP> fetchByCircleId(Long circleId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("circleId", circleId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public List<FRIENDSHIP> fetchByUserIdAndStatus(Long userId, FRIENDSHIP.Status status) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);

        if (status != null) {
            filter.put("status", status);
        }

        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public List<FRIENDSHIP> fetchByFriendIdAndStatus(Long friendId, FRIENDSHIP.Status status) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("friendId", friendId);

        if (status != null) {
            filter.put("status", status);
        }

        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public FRIENDSHIP fetchByUserIdAndFriendId(Long userId, Long friendId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        filter.put("friendId", friendId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    private FRIENDSHIP_EVENT addEvent(FRIENDSHIP friendship, FRIENDSHIP_EVENT.Type type) {
        FRIENDSHIP_EVENT event = getNewFriendshipEventObject();
        event.setFriendshipId(friendship.getId());
        event.setUserId(friendship.getUserId());
        event.setFriendId(friendship.getFriendId());
        event.setType(type);
        event.setEventDate(new Date());
        return getFriendshipEventService().add(event);
    }



    @Override
    public FRIENDSHIP follow(Long userId, Long friendId, Long circleId) {
        return follow(userId, friendId, circleId, false, true);
    }

    private FRIENDSHIP follow(Long userId, Long friendId, Long circleId, boolean friendshipRequest, boolean notifyEvent) {
        // user following friend
        FRIENDSHIP friendship = fetchByUserIdAndFriendId(userId, friendId);
        FRIENDSHIP_EVENT event =  null;

        if (friendship == null) {
            friendship = getNewFriendshipObject();
            friendship.setUserId(userId);
            friendship.setFriendId(friendId);
            friendship.setCircleId(circleId);
            friendship.setStatus(FRIENDSHIP.Status.FOLLOWING);
            friendship.setFriendshipRequested(friendshipRequest);
            friendship = add(friendship);
            if (friendshipRequest) {
                event = addEvent(friendship, FRIENDSHIP_EVENT.Type.FRIENDSHIP_REQUEST);
            } else {
                event = addEvent(friendship, FRIENDSHIP_EVENT.Type.FOLLOW);
            }

            if (notifyEvent) {
                notifyEvent(friendship, event);
            }
        } else {
            // if friendship exists, make sure we're not blocked
            boolean dirty = false;

            switch (friendship.getStatus()) {

                case NONE:
                    friendship.setStatus(FRIENDSHIP.Status.FOLLOWING);
                    friendship.setFriendshipRequested(friendshipRequest);
                    friendship.setCircleId(circleId);
                    dirty = true;
                    break;

                case FOLLOWED:
                    friendship.setStatus(FRIENDSHIP.Status.FRIENDS);
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
                    event = addEvent(friendship, FRIENDSHIP_EVENT.Type.FRIENDSHIP_REQUEST);
                } else {
                    event = addEvent(friendship, FRIENDSHIP_EVENT.Type.FOLLOW);
                }

                if (notifyEvent) {
                    notifyEvent(friendship, event);
                }
            }
        }


        // create the mirror entry; friend followed by user
        FRIENDSHIP other = fetchByUserIdAndFriendId(friendId, userId);
        if (other == null) {
            other = getNewFriendshipObject();
            other.setUserId(friendId);
            other.setFriendId(userId);
            other.setStatus(FRIENDSHIP.Status.FOLLOWED);
            other = add(other);
        } else {
            // if friendship exists, make sure we're not blocked

            boolean dirty = false;

            switch (other.getStatus()) {
                case NONE:
                    other.setStatus(FRIENDSHIP.Status.FOLLOWED);
                    dirty = true;
                    break;

                case FOLLOWING:
                    other.setStatus(FRIENDSHIP.Status.FRIENDS);
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

                if (friendshipRequest && other.getStatus() == FRIENDSHIP.Status.FOLLOWING) {
                    event = addEvent(other, FRIENDSHIP_EVENT.Type.FRIENDSHIP_ACCEPT);

                    if (notifyEvent) {
                        notifyEvent(other, event);
                    }
                }
            }
        }

        return friendship;
    }



    @Override
    public FRIENDSHIP block(Long userId, Long friendId) {
        // user blocking friend
        FRIENDSHIP friendship = fetchByUserIdAndFriendId(userId, friendId);
        if (friendship == null) {
            friendship = getNewFriendshipObject();
            friendship.setUserId(userId);
            friendship.setFriendId(friendId);
            friendship.setStatus(FRIENDSHIP.Status.BLOCKING);
            friendship = add(friendship);
            addEvent(friendship, FRIENDSHIP_EVENT.Type.BLOCK);

        } else {
            // if friendship exists
            boolean dirty = false;

            switch (friendship.getStatus()) {
                case NONE:
                case FRIENDS:
                case FOLLOWED:
                case FOLLOWING:
                case BLOCKED:
                    friendship.setStatus(FRIENDSHIP.Status.BLOCKING);
                    dirty = true;
                    break;

                // do nothing if we're already
                case BLOCKING:
                    break;
            }

            if (dirty) {
                friendship = update(friendship);
                addEvent(friendship, FRIENDSHIP_EVENT.Type.BLOCK);
            }
        }


        // create the mirror entry; friend followed by user
        FRIENDSHIP other = fetchByUserIdAndFriendId(friendId, userId);
        if (other == null) {
            other = getNewFriendshipObject();
            other.setUserId(friendId);
            other.setFriendId(userId);
            other.setStatus(FRIENDSHIP.Status.BLOCKED);
            other = add(other);
        } else {
            // if friendship exists
            boolean dirty = false;

            switch (other.getStatus()) {
                case NONE:
                case FRIENDS:
                case FOLLOWED:
                case FOLLOWING:
                    other.setStatus(FRIENDSHIP.Status.BLOCKED);
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
    public FRIENDSHIP unfollow(Long userId, Long friendId) {
        return unfollow(userId, friendId, false);
    }

    private FRIENDSHIP unfollow(Long userId, Long friendId, boolean revokeFriendship) {
        FRIENDSHIP friendship = fetchByUserIdAndFriendId(userId, friendId);
        if (friendship == null) {
            throw new IllegalStateException("Friendship not found for userId: " + userId + "  friendId: " + friendId);
        }

        FRIENDSHIP other = fetchByUserIdAndFriendId(friendId, userId);
        if (other == null) {
            throw new IllegalStateException("Friendship not found for userId: " + friendId + "  friendId: " + userId);
        }

        // user -> friend
        boolean dirty = false;

        switch (friendship.getStatus()) {
            case FRIENDS:
                friendship.setStatus(FRIENDSHIP.Status.FOLLOWED);
                dirty = true;
                break;

            case FOLLOWING:
                friendship.setStatus(FRIENDSHIP.Status.NONE);
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
                addEvent(friendship, FRIENDSHIP_EVENT.Type.FRIENDSHIP_REVOKE);
            } else {
                addEvent(friendship, FRIENDSHIP_EVENT.Type.UNFOLLOW);
            }
        }


        // friend -> user
        switch (other.getStatus()) {
            case FRIENDS:
                other.setStatus(FRIENDSHIP.Status.FOLLOWING);
                dirty = true;
                break;

            case FOLLOWED:
                other.setStatus(FRIENDSHIP.Status.NONE);
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
    public FRIENDSHIP unblock(Long userId, Long friendId) {
        FRIENDSHIP friendship = fetchByUserIdAndFriendId(userId, friendId);
        if (friendship == null) {
            throw new IllegalStateException("Friendship not found for userId: " + userId + "  friendId: " + friendId);
        }

        FRIENDSHIP other = fetchByUserIdAndFriendId(friendId, userId);
        if (other == null) {
            throw new IllegalStateException("Friendship not found for userId: " + friendId + "  friendId: " + userId);
        }


        // user -> friend
        boolean dirty = false;

        switch (friendship.getStatus()) {
            case BLOCKING:
                friendship.setStatus(FRIENDSHIP.Status.NONE);
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
            addEvent(friendship, FRIENDSHIP_EVENT.Type.UNBLOCK);
        }


        // friend -> user
        switch (other.getStatus()) {
            case BLOCKED:
                other.setStatus(FRIENDSHIP.Status.NONE);
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
    public FRIENDSHIP requestFriendship(Long userId, Long friendId, Long circleId) {
        return follow(userId, friendId, circleId, true, true);
    }



    @Override
    public FRIENDSHIP createFriendship(Long userId, Long friendId, Long circleId, boolean notifyUser) {
        follow(userId, friendId, circleId, true, false);
        acceptFriendship(friendId, userId, notifyUser);

        return fetchByUserIdAndFriendId(userId, friendId);
    }



    @Override
    public FRIENDSHIP acceptFriendship(Long userId, Long friendId) {
        return acceptFriendship(userId, friendId, true);
    }



    private FRIENDSHIP acceptFriendship(Long userId, Long friendId, boolean notifyEvent) {
        FRIENDSHIP friendship = fetchByUserIdAndFriendId(userId, friendId);
        FRIENDSHIP_EVENT event =  null;

        if (friendship == null) {
            throw new IllegalStateException("Friendship not found for userId: " + userId + "  friendId: " + friendId);
        }

        FRIENDSHIP other = fetchByUserIdAndFriendId(friendId, userId);

        if (other == null) {
            throw new IllegalStateException("Friendship not found for userId: " + friendId + "  friendId: " + userId);
        }


        // user -> friend
        boolean dirty = false;

        switch (friendship.getStatus()) {
            case NONE:
            case FOLLOWING:
            case FOLLOWED:
                friendship.setStatus(FRIENDSHIP.Status.FRIENDS);
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
            event = addEvent(friendship, FRIENDSHIP_EVENT.Type.FRIENDSHIP_ACCEPT);

            if (notifyEvent) {
                notifyEvent(friendship, event);
            }
        }


        // friend -> user
        switch (other.getStatus()) {
            case NONE:
            case FOLLOWED:
            case FOLLOWING:
                other.setStatus(FRIENDSHIP.Status.FRIENDS);
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
    public FRIENDSHIP rejectFriendship(Long userId, Long friendId) {
        FRIENDSHIP friendship = fetchByUserIdAndFriendId(userId, friendId);
        if (friendship == null) {
            throw new IllegalStateException("Friendship not found for userId: " + userId + "  friendId: " + friendId);
        }
        addEvent(friendship, FRIENDSHIP_EVENT.Type.FRIENDSHIP_REJECT);
        return friendship;
    }



    @Override
    public List<FRIENDSHIP> fetchFollowerList(Long userId) {
        List<FRIENDSHIP> all = new ArrayList<>();

        List<FRIENDSHIP> followers = fetchByUserIdAndStatus(userId, FRIENDSHIP.Status.FOLLOWED);
        List<FRIENDSHIP> friends = fetchByUserIdAndStatus(userId, FRIENDSHIP.Status.FRIENDS);
        all.addAll(followers);
        all.addAll(friends);
        return all;
    }



    @Override
    public List<FRIENDSHIP> fetchFollowingList(Long userId) {
        List<FRIENDSHIP> all = new ArrayList<>();

        List<FRIENDSHIP> following = fetchByUserIdAndStatus(userId, FRIENDSHIP.Status.FOLLOWING);
        List<FRIENDSHIP> friends = fetchByUserIdAndStatus(userId, FRIENDSHIP.Status.FRIENDS);
        all.addAll(following);
        all.addAll(friends);
        return all;
    }



    @Override
    public List<FRIENDSHIP> fetchFriendList(Long userId) {
        List<FRIENDSHIP> all = new ArrayList<>();

        List<FRIENDSHIP> friends = fetchByUserIdAndStatus(userId, FRIENDSHIP.Status.FRIENDS);
        all.addAll(friends);
        return all;
    }



    @Override
    public FRIENDSHIP revokeFriendship(Long userId, Long friendId) {
        return unfollow(userId, friendId, true);
    }



    @Override
    public Integer getFollowerCount(Long userId) {
        List<FRIENDSHIP> followers = fetchFollowerList(userId);
        return followers.size();
    }



    @Override
    public Integer getFollowingCount(Long userId) {
        List<FRIENDSHIP> following = fetchFollowingList(userId);
        return following.size();
    }
}
