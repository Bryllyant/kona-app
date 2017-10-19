/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Purchase;
import com.bryllyant.kona.remote.service.KService;

public interface GooglePlayService extends KService, KGooglePlayService<Purchase> {
	public static final String SERVICE_PATH = "rpc/GooglePlayService";
	
}
