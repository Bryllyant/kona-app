/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Setting;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;
import java.util.Map;

public interface SettingService extends KService, KEntityService<Setting> {
    String SERVICE_PATH = "rpc/SettingService";

    // creates or updates a setting record
    Setting save(Setting setting);

    void save(Long userId, Long accountId, Map<String, Object> config, boolean overrideGlobal);

    List<Setting> fetchGlobal();
    List<Setting> fetchByUserId(Long userId);
    List<Setting> fetchByAccountId(Long accountId);

    Setting fetchGlobalByName(String name);
    Setting fetchByUserIdAndName(Long userId, String name);
    Setting fetchByAccountIdAndName(Long accountId, String name);

    Map<String,Object> getGlobalSettings();
    Map<String,Object> getAccountSettings(Long accountId);
    Map<String,Object> getUserSettings(User user);
}
