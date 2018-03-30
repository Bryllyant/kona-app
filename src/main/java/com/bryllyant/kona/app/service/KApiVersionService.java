/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KApiVersion;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;


/**
 * The client side stub for the RPC service.
 */
public interface KApiVersionService<A extends KApiVersion> extends KService, KEntityService<A> {
    public static final String SERVICE_PATH = "rpc/kona/ApiVersionService";
    
    public A fetchByName(String name);

    public A fetchLatest();
    
}
