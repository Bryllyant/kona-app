/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.RemoteService;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

public interface RemoteServiceService extends KService, KEntityService<RemoteService> {
	String SERVICE_PATH = "rpc/RemoteServiceService";

    RemoteService fetchBySlug(String slug);
}
