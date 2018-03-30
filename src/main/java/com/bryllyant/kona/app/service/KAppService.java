/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KApp;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface KAppService<APP extends KApp> extends KService, KEntityService<APP> {

    APP create(APP app);

    APP create(APP app, String apiVersion, String redirectUri, String scope);
    
    APP create(APP app, String apiVersion, String redirectUri, String scope,
            String clientId, String clientSecret);
    
    APP update(APP app, String clientId, String apiVersion, String redirectUri, String scope);
    
    APP fetchBySlug(String slug);
    
    APP fetchByUid(String uid);
    
    APP retire(APP app);
    
    APP getSystemApp();

    List<APP> fetchByUserId(Long userId);

    boolean isAppNameAvailable(String name);
}
