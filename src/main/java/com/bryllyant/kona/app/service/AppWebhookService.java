/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AppWebhook;
import com.bryllyant.kona.remote.service.KService;

public interface AppWebhookService extends KService, KAppWebhookService<AppWebhook> {
	public static final String SERVICE_PATH = "rpc/AppWebhookService";
	
}
