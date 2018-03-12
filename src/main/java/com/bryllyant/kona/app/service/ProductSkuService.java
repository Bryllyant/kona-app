/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.ProductSku;
import com.bryllyant.kona.remote.service.KService;

public interface ProductSkuService extends KService, KProductSkuService<ProductSku> {
	String SERVICE_PATH = "rpc/ProductSkuService";
	
}
