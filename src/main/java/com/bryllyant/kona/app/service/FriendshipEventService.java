package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.FriendshipEvent;
import com.bryllyant.kona.remote.service.KService;

public interface FriendshipEventService extends KService, KFriendshipEventService<FriendshipEvent> {
	public static final String SERVICE_PATH = "rpc/FriendshipEventService";
	
}
