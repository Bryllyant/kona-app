/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.remote.service.KService;

public interface StripeService extends KService, KStripeService {
	public static final String SERVICE_PATH = "rpc/StripeService";
	
}
