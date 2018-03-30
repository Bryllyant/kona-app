/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.util.List;

import com.bryllyant.kona.app.entity.KFriendshipCircle;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;


public interface KFriendshipCircleService<CIRCLE extends KFriendshipCircle> extends KService, KEntityService<CIRCLE> {
	public static final String SERVICE_PATH = "rpc/kona/FriendshipCircleService";
	
    public CIRCLE fetchByUid(String uid);

    public CIRCLE fetchByUserIdAndSlug(Long userId, String slug);

    public List<CIRCLE> fetchByUserId(Long userId);

    public CIRCLE fetchDefaultByUserId(Long userId);
}
