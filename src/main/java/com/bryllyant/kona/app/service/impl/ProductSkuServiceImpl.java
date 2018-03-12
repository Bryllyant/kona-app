/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.ProductSkuMapper;
import com.bryllyant.kona.app.entity.ProductSku;
import com.bryllyant.kona.app.entity.ProductSkuExample;
import com.bryllyant.kona.app.service.KAbstractProductSkuService;
import com.bryllyant.kona.app.service.ProductSkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(ProductSkuService.SERVICE_PATH)
public class ProductSkuServiceImpl extends KAbstractProductSkuService<
        ProductSku,
        ProductSkuExample,
        ProductSkuMapper>
		implements ProductSkuService {
	
	private static Logger logger = LoggerFactory.getLogger(ProductSkuServiceImpl.class);
    
    @Autowired
    private ProductSkuMapper productSkuMapper;
    

    @Override @SuppressWarnings("unchecked")
    protected ProductSkuMapper getMapper() {
        return productSkuMapper;
    }

    protected ProductSku getNewObject() {
        return new ProductSku();
    }

    @Override
    protected ProductSkuExample getEntityExampleObject() {
        return new ProductSkuExample();
    }

}
