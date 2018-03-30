/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.util.List;

import com.bryllyant.kona.app.entity.KFriendshipEvent;

import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

public interface KFriendshipEventService<FRIENDSHIP_EVENT extends KFriendshipEvent>
        extends KService, KEntityService<FRIENDSHIP_EVENT> {
    public static final String SERVICE_PATH = "rpc/kona/FriendshipEventService";
    
    public List<FRIENDSHIP_EVENT> fetchByFriendshipId(Long friendshipId);
}
