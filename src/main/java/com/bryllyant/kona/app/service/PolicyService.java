/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Policy;
import com.bryllyant.kona.remote.service.KService;

public interface PolicyService extends KService, KPolicyService<Policy> {
	public static final String SERVICE_PATH = "rpc/PolicyService";
	
}
