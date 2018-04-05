package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.FriendshipCircle;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface FriendshipCircleService extends KService, KEntityService<FriendshipCircle> {
	String SERVICE_PATH = "rpc/FriendshipCircleService";

    FriendshipCircle fetchByUserIdAndSlug(Long userId, String slug);

    List<FriendshipCircle> fetchByUserId(Long userId);

    FriendshipCircle fetchDefaultByUserId(Long userId);
}
