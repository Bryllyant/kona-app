/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AppConfig;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;
import java.util.Map;

public interface AppConfigService extends KService, KEntityService<AppConfig> {
	String SERVICE_PATH = "rpc/AppConfigService";

    List<AppConfig> fetchByAppIdAndEnv(Long appId, AppConfig.Env env);

    AppConfig fetchByAppIdAndEnvAndName(Long appId, AppConfig.Env env, String name);

    AppConfig fetchGlobalValue(String name);

    AppConfig fetchGlobalAppValue(Long appId, String name);

    AppConfig fetchGlobalEnvValue(AppConfig.Env env, String name);

    // get merged config for env
    Map<String,Object> getConfig(Long appId, AppConfig.Env env);

    // Note this method is additive.  It does not remove entries not defined in Map.
    Map<String,Object> save(Long appId, AppConfig.Env env, Map<String, Object> config);

    Map<String,Object> remove(Long appId, AppConfig.Env env, List<String> keyList);
}
