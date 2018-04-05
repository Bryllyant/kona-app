/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.ProductMapper;
import com.bryllyant.kona.app.entity.Product;
import com.bryllyant.kona.app.entity.ProductExample;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.ProductService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(ProductService.SERVICE_PATH)
public class ProductServiceImpl 
		extends KAbstractService<Product, ProductExample, ProductMapper>
        implements ProductService {
	
	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    
    @Autowired
    private ProductMapper productMapper;

    @Override @SuppressWarnings("unchecked")
    protected ProductMapper getMapper() {
        return productMapper;
    }
    

    @Override
    public void validate(Product product) {
        if (product.getCreatedDate() == null) {
            product.setCreatedDate(new Date());
        }

        if (product.getUid() == null) {
            product.setUid(uuid());
        }

        product.setUpdatedDate(new Date());
    }


    /*
    @Override
    // FIXME: make sure that when adding/remove/updating products, the default
    // product is handled properly.
    public Product fetchDefault(Long appId) {
        Product defaultProduct = null;
        if (defaultProduct == null) {
            ProductExample example = new ProductExample();
            example.or()
            	.andAppIdEqualTo(appId)
            	.andDefaultPlanEqualTo(true);

                List<Product> result =
                        productDao.selectByExample(example);
                if (result != null && result.size() >0) {
                    defaultProduct = result.get(0);
                }
        }
        return defaultProduct;
    }
    */

    @Override
    public List<Product> fetchAll(Boolean enabled) {
        Map<String,Object> filter = null;

        if (enabled != null) {
            filter = KMyBatisUtil.createFilter("enabled", enabled);
        }

        return fetchByCriteria(filter);
    }

    @Override
    public Product fetchBySlug(String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

}

