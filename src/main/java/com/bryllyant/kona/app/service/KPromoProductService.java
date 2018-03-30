package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPromoProduct;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface KPromoProductService<PROMO_PRODUCT extends KPromoProduct>
        extends KService, KEntityService<PROMO_PRODUCT> {

    PROMO_PRODUCT fetchByUid(String uid);

    List<PROMO_PRODUCT> fetchByPromoId(Long promoId);
}
