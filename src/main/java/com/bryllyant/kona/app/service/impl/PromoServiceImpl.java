/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PromoMapper;
import com.bryllyant.kona.app.entity.ProductSku;
import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.app.entity.PromoCode;
import com.bryllyant.kona.app.entity.PromoExample;
import com.bryllyant.kona.app.entity.PromoProduct;
import com.bryllyant.kona.app.entity.Purchase;
import com.bryllyant.kona.app.service.KAbstractPromoService;
import com.bryllyant.kona.app.service.ProductSkuService;
import com.bryllyant.kona.app.service.PromoCodeService;
import com.bryllyant.kona.app.service.PromoProductService;
import com.bryllyant.kona.app.service.PromoService;
import com.bryllyant.kona.app.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(PromoService.SERVICE_PATH)
public class PromoServiceImpl extends KAbstractPromoService<
        Promo,
        PromoExample,
        PromoMapper,
        PromoCode,
        PromoProduct,
        ProductSku,
        Purchase>
		implements PromoService {
	
	private static Logger logger = LoggerFactory.getLogger(PromoServiceImpl.class);
    
    @Autowired
    private PromoMapper promoMapper;
    
    @Autowired
    PurchaseService purchaseService;

    @Autowired
    PromoCodeService promoCodeService;

    @Autowired
    PromoProductService promoProductService;

    @Autowired
    ProductSkuService productSkuService;


    @Override @SuppressWarnings("unchecked")
    protected PromoMapper getMapper() {
        return promoMapper;
    }
    
    @Override @SuppressWarnings("unchecked")
    protected PurchaseService getPurchaseService() {
        return purchaseService;
    }

    @Override @SuppressWarnings("unchecked")
    protected PromoCodeService getPromoCodeService() {
        return promoCodeService;
    }

    @Override @SuppressWarnings("unchecked")
    protected PromoProductService getPromoProductService() {
        return promoProductService;
    }

    @Override @SuppressWarnings("unchecked")
    protected ProductSkuService getProductSkuService() {
        return productSkuService;
    }

    @Override
    protected PromoExample getEntityExampleObject() {
        return new PromoExample();
    }


}
