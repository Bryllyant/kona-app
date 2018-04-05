/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PromoProductMapper;
import com.bryllyant.kona.app.entity.PromoProduct;
import com.bryllyant.kona.app.entity.PromoProductExample;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.PromoProductService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(PromoProductService.SERVICE_PATH)
public class PromoProductServiceImpl extends KAbstractService<PromoProduct,PromoProductExample,PromoProductMapper>
		implements PromoProductService {
	
	private static Logger logger = LoggerFactory.getLogger(PromoProductServiceImpl.class);
    
    @Autowired
    private PromoProductMapper promoProductMapper;
    

    @Override @SuppressWarnings("unchecked")
    protected PromoProductMapper getMapper() {
        return promoProductMapper;
    }

    @Override
    public void validate(PromoProduct promoProduct) {
        if (promoProduct.getCreatedDate() == null) {
            promoProduct.setCreatedDate(new Date());
        }

        if (promoProduct.getUid() == null) {
            promoProduct.setUid(uuid());
        }

        promoProduct.setUpdatedDate(new Date());
    }

    @Override
    public List<PromoProduct> fetchByPromoId(Long promoId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("promoId", promoId);
        return fetchByCriteria(filter);
    }


}
