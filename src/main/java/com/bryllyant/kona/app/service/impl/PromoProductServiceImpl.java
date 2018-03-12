/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PromoProductMapper;
import com.bryllyant.kona.app.entity.PromoProduct;
import com.bryllyant.kona.app.entity.PromoProductExample;
import com.bryllyant.kona.app.service.KAbstractPromoProductService;
import com.bryllyant.kona.app.service.PromoProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(PromoProductService.SERVICE_PATH)
public class PromoProductServiceImpl extends KAbstractPromoProductService<
        PromoProduct,
        PromoProductExample,
        PromoProductMapper>
		implements PromoProductService {
	
	private static Logger logger = LoggerFactory.getLogger(PromoProductServiceImpl.class);
    
    @Autowired
    private PromoProductMapper promoProductMapper;
    

    @Override @SuppressWarnings("unchecked")
    protected PromoProductMapper getMapper() {
        return promoProductMapper;
    }

    protected PromoProduct getNewObject() {
        return new PromoProduct();
    }

    @Override
    protected PromoProductExample getEntityExampleObject() {
        return new PromoProductExample();
    }


}
