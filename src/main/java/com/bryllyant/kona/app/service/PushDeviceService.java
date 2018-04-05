/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.app.entity.PushDevice;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PushDeviceService extends KService, KEntityService<PushDevice> {

	String SERVICE_PATH = "rpc/PushDeviceService";

    PushDevice fetchByToken(String token);

    List<PushDevice> fetchByDeviceId(Long deviceId);

    // Different users on same device
    PushDevice fetchByUserIdAndDeviceId(Long userId, Long deviceId, boolean sandbox);

    List<PushDevice> fetchByUserId(Long userId);

    List<PushDevice> fetchByUserIds(List<Long> userIdList, boolean sandbox);

    @Transactional
    PushDevice save(User user, Device device, String token, boolean sandbox);

    @Transactional
    String createEndpoint(PushDevice device, boolean deleteIfExists);

    @Transactional
    PushDevice save(
            Long userId,
            Long deviceId,
            String token,
            Push.Platform platform,
            boolean sandbox
    );
	
}
