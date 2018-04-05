/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AuthPriv;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

public interface AuthPrivService extends KService, KEntityService<AuthPriv> {
	String SERVICE_PATH = "rpc/AuthPrivService";

    AuthPriv fetchByName(String uid);
}
