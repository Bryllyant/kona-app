/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.util.List;

import com.bryllyant.kona.app.entity.KDevice;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.entity.KUserDevice;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

/**
 * The client side stub for the RPC service.
 */
public interface KUserDeviceService<USER_DEVICE extends KUserDevice, USER extends KUser, DEVICE extends KDevice> extends KService, KEntityService<USER_DEVICE> {
    void remove(USER_DEVICE userDevice, boolean removeDeviceIfNotReferenced);
	
    USER_DEVICE fetchByUid(String uid);

    USER_DEVICE fetchByUserIdAndDeviceId(Long userId, Long deviceId);

    USER_DEVICE fetchByUserIdAndSlug(Long userId, String slug);

    List<USER_DEVICE> fetchByUserId(Long userId);

    List<USER_DEVICE> fetchByDeviceId(Long deviceId);

    USER_DEVICE create(USER user, DEVICE device, String displayName);

    USER_DEVICE update(USER_DEVICE userDevice, DEVICE device, String displayName);

    /**
     * Create a simple USER_DEVICE object.
     * This object is not fully populated with the DEVICE properties.
     * Used mainly by KAbstractPositionService.
     * 
     * @param userId
     * @param deviceId
     * @param name
     * @return simple USER_DEVICE object
     */
    USER_DEVICE create(Long userId, Long deviceId, String name);
}
