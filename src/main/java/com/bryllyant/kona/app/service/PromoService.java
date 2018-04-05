/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

public interface PromoService extends KService, KEntityService<Promo> {
	String SERVICE_PATH = "rpc/PromoService";

    Promo fetchByPromoCode(String promoCode);

    Promo fetchAndValidateByPromoCode(
            String promoCode,
            Long accountId,
            Long productCategoryId,
            Long productId,
            Long productSkuId
    );

    // rule variables: price,
    boolean isPromoValid(
            Promo promo,
            Long accountId,
            Long productCategoryId,
            Long productId,
            Long productSkuId
    );
	
}
