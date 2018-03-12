/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.remote.service.KService;

public interface PromoService extends KService, KPromoService<Promo> {
	String SERVICE_PATH = "rpc/PromoService";
	
}
