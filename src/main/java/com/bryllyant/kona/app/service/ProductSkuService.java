/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.ProductSku;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface ProductSkuService extends KService, KEntityService<ProductSku> {
	String SERVICE_PATH = "rpc/ProductSkuService";

    ProductSku fetchBySku(String sku);

    ProductSku fetchDefaultByProductId(Long productId);

    List<ProductSku> fetchByProductId(Long productId);
}
