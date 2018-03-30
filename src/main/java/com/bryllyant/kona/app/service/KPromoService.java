package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPromo;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

public interface KPromoService<PROMO extends KPromo>
        extends KService, KEntityService<PROMO> {
    
	PROMO fetchByUid(String uid);

    PROMO fetchByPromoCode(String promoCode);

    PROMO fetchAndValidateByPromoCode(
            String promoCode,
            Long accountId,
            Long productCategoryId,
            Long productId,
            Long productSkuId
    );

    // rule variables: price,
    boolean isPromoValid(
            PROMO promo,
            Long accountId,
            Long productCategoryId,
            Long productId,
            Long productSkuId
    );
}
