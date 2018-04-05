/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserDevice;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface UserDeviceService extends KService, KEntityService<UserDevice> {
	String SERVICE_PATH = "rpc/UserDeviceService";

    void remove(UserDevice userDevice, boolean removeDeviceIfNotReferenced);

    UserDevice fetchByUserIdAndDeviceId(Long userId, Long deviceId);

    UserDevice fetchByUserIdAndSlug(Long userId, String slug);

    List<UserDevice> fetchByUserId(Long userId);

    List<UserDevice> fetchByDeviceId(Long deviceId);

    UserDevice create(User user, Device device, String displayName);

    UserDevice update(UserDevice userDevice, Device device, String displayName);

    /**
     * Create a simple UserDevice object.
     * This object is not fully populated with the Device properties.
     * Used mainly by PositionService.
     *
     * @param userId
     * @param deviceId
     * @param name
     * @return simple UserDevice object
     */
    UserDevice create(Long userId, Long deviceId, String name);
	
}
