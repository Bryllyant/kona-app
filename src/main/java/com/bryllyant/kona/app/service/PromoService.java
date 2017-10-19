/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.Product;
import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.remote.service.KService;

public interface PromoService extends KService, KPromoService<Promo,Account,Product> {
	public static final String SERVICE_PATH = "rpc/PromoService";
	
}
