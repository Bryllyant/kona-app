/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface AppService extends KService, KEntityService<App> {
	String SERVICE_PATH = "rpc/AppService";

    App create(App app);

    App create(App app, String apiVersion, String redirectUri, String scope);

    App create(App app, String apiVersion, String redirectUri, String scope,
               String clientId, String clientSecret);

    App update(App app, String clientId, String apiVersion, String redirectUri, String scope);

    App fetchBySlug(String slug);

    App retire(App app);

    App getSystemApp();

    List<App> fetchByUserId(Long userId);

    boolean isAppNameAvailable(String name);
	
}
