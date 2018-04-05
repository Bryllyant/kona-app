package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BaseFriendshipEvent extends KBaseEntity implements Serializable {
    public enum Type {
        FOLLOW,
        UNFOLLOW,
        BLOCK,
        UNBLOCK,
        FRIENDSHIP_REQUEST,
        FRIENDSHIP_ACCEPT,
        FRIENDSHIP_REJECT,
        FRIENDSHIP_REVOKE
    }

}