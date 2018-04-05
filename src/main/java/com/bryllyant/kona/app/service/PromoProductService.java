/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.PromoProduct;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface PromoProductService extends KService, KEntityService<PromoProduct> {
	String SERVICE_PATH = "rpc/PromoProductService";

    List<PromoProduct> fetchByPromoId(Long promoId);
	
}
