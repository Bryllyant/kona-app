/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import java.util.List;

import com.bryllyant.kona.app.entity.KAppCreds;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KAppCredsService<AC extends KAppCreds> extends KService, KEntityService<AC> {
    public static final String SERVICE_PATH = "rpc/kona/AppCredsService";
    
    public List<AC> fetchByAppId(Long appId);
    
    public AC fetchByClientId(String clientId);
    
    public void expireAppTokens(Long appId);
}
