package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPush;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;
import java.util.Map;

public interface KPushService<PUSH extends KPush>
        extends KService, KEntityService<PUSH> {

	PUSH fetchByUid(String uid);

	List<PUSH> fetchByCampaignId(Long campaignId);

    List<PUSH> fetchByChannelId(Long channelId);

    List<PUSH> fetchByUserId(Long userId);

    PUSH fetchByProviderMessageId(String providerMessageId);


	void broadcast(
            String title,
            String message,
            String imageUrl,
            String actionUrl,
            Map<String, Object> filter,
            boolean sandbox
    );

	void broadcast(PUSH push, Map<String, Object> filter);

	void broadcast(
	        PUSH push,
            List<Long> userIds,
            Double latitude,
            Double longitude,
            Double radius
    );

    PUSH send (
            Long userId,
            String title,
            String message,
            String imageUrl,
            String actionUrl,
            boolean sandbox
    );

    PUSH send(PUSH push);
}
