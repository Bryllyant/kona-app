/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.PreOrder;
import com.bryllyant.kona.remote.service.KService;

public interface PreOrderService extends KService, KPreOrderService<PreOrder> {
	public static final String SERVICE_PATH = "rpc/PreOrderService";
	
}
