package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KDevice;
import com.bryllyant.kona.app.entity.KPush;
import com.bryllyant.kona.app.entity.KPushDevice;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KPushDeviceService<
        PUSH_DEVICE extends KPushDevice,
        USER extends KUser,
        DEVICE extends KDevice
        > extends KService, KEntityService<PUSH_DEVICE> {

    PUSH_DEVICE fetchByUid(String uid);

    PUSH_DEVICE fetchByToken(String token);

    List<PUSH_DEVICE> fetchByDeviceId(Long deviceId);

    // Different users on same device
    PUSH_DEVICE fetchByUserIdAndDeviceId(Long userId, Long deviceId, boolean sandbox);

    List<PUSH_DEVICE> fetchByUserId(Long userId);

    List<PUSH_DEVICE> fetchByUserIds(List<Long> userIdList, boolean sandbox);

    @Transactional
    PUSH_DEVICE save(USER user, DEVICE device, String token, boolean sandbox);

    @Transactional
    String createEndpoint(PUSH_DEVICE device, boolean deleteIfExists);

    @Transactional
    PUSH_DEVICE save(
            Long userId,
            Long deviceId,
            String token,
            KPush.Platform platform,
            boolean sandbox
    );
}
