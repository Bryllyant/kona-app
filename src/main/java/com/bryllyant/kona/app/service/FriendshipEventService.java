package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.FriendshipEvent;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface FriendshipEventService extends KService, KEntityService<FriendshipEvent> {
	String SERVICE_PATH = "rpc/FriendshipEventService";

    List<FriendshipEvent> fetchByFriendshipId(Long friendshipId);
	
}
