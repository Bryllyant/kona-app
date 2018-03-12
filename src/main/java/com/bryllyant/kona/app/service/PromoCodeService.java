/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.PromoCode;
import com.bryllyant.kona.remote.service.KService;

public interface PromoCodeService extends KService, KPromoCodeService<PromoCode> {
	String SERVICE_PATH = "rpc/PromoCodeService";
	
}
