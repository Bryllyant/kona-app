package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Friendship;
import com.bryllyant.kona.remote.service.KService;

public interface FriendshipService extends KService, KFriendshipService<Friendship> {
	public static final String SERVICE_PATH = "rpc/FriendshipService";
	
}
