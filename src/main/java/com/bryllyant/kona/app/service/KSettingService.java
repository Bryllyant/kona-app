/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.util.List;
import java.util.Map;

import com.bryllyant.kona.app.entity.KSetting;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

public interface KSettingService<SETTING extends KSetting, USER extends KUser>
        extends KService, KEntityService<SETTING> {
	
	// creates or updates a setting record
    SETTING save(SETTING setting);

	void save(Long userId, Long accountId, Map<String, Object> config, boolean overrideGlobal);

	List<SETTING> fetchGlobal();
	List<SETTING> fetchByUserId(Long userId);
	List<SETTING> fetchByAccountId(Long accountId);

	SETTING fetchGlobalByName(String name);
	SETTING fetchByUserIdAndName(Long userId, String name);
	SETTING fetchByAccountIdAndName(Long accountId, String name);

    Map<String,Object> getGlobalSettings();
    Map<String,Object> getAccountSettings(Long accountId);
    Map<String,Object> getUserSettings(USER user);
}
