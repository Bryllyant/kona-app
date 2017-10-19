package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.FriendshipCircle;
import com.bryllyant.kona.remote.service.KService;

public interface FriendshipCircleService extends KService, KFriendshipCircleService<FriendshipCircle> {
	public static final String SERVICE_PATH = "rpc/FriendshipCircleService";
	
}
