/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AppWebhook;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

public interface AppWebhookService extends KService, KEntityService<AppWebhook> {
	String SERVICE_PATH = "rpc/AppWebhookService";

    AppWebhook fetchByAppIdAndSlug(Long appId, String slug);
}
