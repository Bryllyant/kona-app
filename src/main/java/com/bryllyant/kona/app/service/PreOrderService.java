/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.PreOrder;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;
import java.util.Map;

public interface PreOrderService extends KService, KEntityService<PreOrder> {
	String SERVICE_PATH = "rpc/PreOrderService";

    List<PreOrder> fetchByAppId(Long appId);

    PreOrder create(PreOrder preOrder, Map<String,Object> metadata, boolean processPayment, boolean sendReceipt);
	
}
