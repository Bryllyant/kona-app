/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.PromoProduct;
import com.bryllyant.kona.remote.service.KService;

public interface PromoProductService extends KService, KPromoProductService<PromoProduct> {
	String SERVICE_PATH = "rpc/PromoProductService";
	
}
