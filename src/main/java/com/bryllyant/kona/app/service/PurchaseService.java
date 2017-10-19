/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Purchase;
import com.bryllyant.kona.remote.service.KService;

public interface PurchaseService extends KService, KPurchaseService<Purchase> {
	public static final String SERVICE_PATH = "rpc/PurchaseService";
	
}
