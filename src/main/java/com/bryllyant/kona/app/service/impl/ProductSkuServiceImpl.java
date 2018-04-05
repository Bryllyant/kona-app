/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.ProductSkuMapper;
import com.bryllyant.kona.app.entity.ProductSku;
import com.bryllyant.kona.app.entity.ProductSkuExample;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.ProductSkuService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(ProductSkuService.SERVICE_PATH)
public class ProductSkuServiceImpl extends KAbstractService<ProductSku,ProductSkuExample,ProductSkuMapper>
		implements ProductSkuService {
	
	private static Logger logger = LoggerFactory.getLogger(ProductSkuServiceImpl.class);
    
    @Autowired
    private ProductSkuMapper productSkuMapper;
    
    @Override @SuppressWarnings("unchecked")
    protected ProductSkuMapper getMapper() {
        return productSkuMapper;
    }


    @Override
    public void validate(ProductSku productSku) {
        if (productSku.getCreatedDate() == null) {
            productSku.setCreatedDate(new Date());
        }

        if (productSku.getUid() == null) {
            productSku.setUid(uuid());
        }

        productSku.setUpdatedDate(new Date());
    }


    @Override
    public ProductSku fetchBySku(String sku) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("sku", sku);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public ProductSku fetchDefaultByProductId(Long productId) {
        List<ProductSku> list = fetchByProductId(productId);

        if (list != null && list.size()  > 0) {
            list.sort((ProductSku a, ProductSku b) -> {
                Long diff = a.getCreatedDate().getTime() - b.getCreatedDate().getTime();
                return diff.intValue();
            });

            return list.get(0);
        }

        return null;
    }

    @Override
    public List<ProductSku> fetchByProductId(Long productId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("productId", productId);
        return fetchByCriteria(filter);
    }


}
