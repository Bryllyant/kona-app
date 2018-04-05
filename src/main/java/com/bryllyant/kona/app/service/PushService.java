/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;
import java.util.Map;

public interface PushService extends KService, KEntityService<Push> {
	String SERVICE_PATH = "rpc/PushService";

    List<Push> fetchByCampaignId(Long campaignId);

    List<Push> fetchByChannelId(Long channelId);

    List<Push> fetchByUserId(Long userId);

    Push fetchByProviderMessageId(String providerMessageId);


    void broadcast(
            String title,
            String message,
            String imageUrl,
            String actionUrl,
            Map<String, Object> filter,
            boolean sandbox
    );

    void broadcast(Push push, Map<String, Object> filter);

    void broadcast(
            Push push,
            List<Long> userIds,
            Double latitude,
            Double longitude,
            Double radius
    );

    Push send (
            Long userId,
            String title,
            String message,
            String imageUrl,
            String actionUrl,
            boolean sandbox
    );

    Push send(Push push);
}
