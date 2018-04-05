/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AppCreds;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface AppCredsService extends KService, KEntityService<AppCreds> {
	String SERVICE_PATH = "rpc/AppCredsService";

    AppCreds fetchByClientId(String clientId);

    List<AppCreds> fetchByAppId(Long appId);

    void expireAppTokens(Long appId);
	
}
