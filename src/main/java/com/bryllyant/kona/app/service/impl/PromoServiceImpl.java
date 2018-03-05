/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PromoMapper;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.Product;
import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.app.entity.PromoExample;
import com.bryllyant.kona.app.entity.Purchase;
import com.bryllyant.kona.app.service.KAbstractPromoService;
import com.bryllyant.kona.app.service.PromoService;
import com.bryllyant.kona.app.service.PurchaseService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(PromoService.SERVICE_PATH)
public class PromoServiceImpl 
		extends KAbstractPromoService<Promo,PromoExample,Account,Product,Purchase> 
		implements PromoService {
	
	private static Logger logger = LoggerFactory.getLogger(PromoServiceImpl.class);
    
    @Autowired
    private PromoMapper promoDao;
    
    @Autowired
    PurchaseService purchaseService;




    @Override @SuppressWarnings("unchecked")
    protected PromoMapper getDao() {
        return promoDao;
    }
    


    @Override @SuppressWarnings("unchecked")
    protected PurchaseService getPurchaseService() {
        return purchaseService;
    }
    


    @Override
    protected PromoExample getEntityExampleObject() { return new PromoExample(); }


}
