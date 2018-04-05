/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Product;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface ProductService extends KService, KEntityService<Product> {
	String SERVICE_PATH = "rpc/ProductService";

    Product fetchBySlug(String slug);

    List<Product> fetchAll(Boolean enabled);
	
}
