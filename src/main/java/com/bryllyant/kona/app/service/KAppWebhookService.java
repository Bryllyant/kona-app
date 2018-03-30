/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAppWebhook;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

/**
 * The client side stub for the RPC service.
 */
public interface KAppWebhookService<T extends KAppWebhook> extends KService, KEntityService<T> {
	public static final String SERVICE_PATH = "rpc/kona/AppWebhookService";
    
    public T fetchByUid(String uid);
    
    public T fetchByAppIdAndSlug(Long appId, String slug);
}
