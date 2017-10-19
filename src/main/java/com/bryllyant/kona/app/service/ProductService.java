/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Product;
import com.bryllyant.kona.remote.service.KService;

public interface ProductService extends KService, KProductService<Product> {
	public static final String SERVICE_PATH = "rpc/ProductService";
	
}
