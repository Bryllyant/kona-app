/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Policy;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface PolicyService extends KService, KEntityService<Policy> {
	String SERVICE_PATH = "rpc/PolicyService";

    List<Policy> fetchByType(Policy.Type type);

    Policy fetchByTypeAndVersion(Policy.Type type, Integer version);

    Policy fetchActive(Policy.Type type);
	
}
