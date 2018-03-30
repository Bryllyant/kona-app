/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAppConfig;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;
import java.util.Map;

public interface KAppConfigService<APP_CONFIG extends KAppConfig>
        extends KService, KEntityService<APP_CONFIG> {

    List<APP_CONFIG> fetchByAppIdAndEnv(Long appId, KAppConfig.Env env);

    APP_CONFIG fetchByAppIdAndEnvAndName(Long appId, KAppConfig.Env env, String name);

    APP_CONFIG fetchGlobalValue(String name);

    APP_CONFIG fetchGlobalAppValue(Long appId, String name);

    APP_CONFIG fetchGlobalEnvValue(KAppConfig.Env env, String name);

    // get merged config for env
	Map<String,Object> getConfig(Long appId, KAppConfig.Env env);

    // Note this method is additive.  It does not remove entries not defined in Map.
    Map<String,Object> save(Long appId, KAppConfig.Env env, Map<String, Object> config);

    Map<String,Object> remove(Long appId, KAppConfig.Env env, List<String> keyList);
}
