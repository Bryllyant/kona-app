/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.ProductMapper;
import com.bryllyant.kona.app.entity.Product;
import com.bryllyant.kona.app.entity.ProductExample;
import com.bryllyant.kona.app.service.KAbstractProductService;
import com.bryllyant.kona.app.service.ProductService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(ProductService.SERVICE_PATH)
public class ProductServiceImpl 
		extends KAbstractProductService<Product,ProductExample> 
		implements ProductService {
	
	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    
    @Autowired
    private ProductMapper productDao;




    @Override @SuppressWarnings("unchecked")
    protected ProductMapper getDao() {
        return productDao;
    }
    


    @Override
    protected ProductExample getEntityExampleObject() { return new ProductExample(); }


}
