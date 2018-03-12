/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PromoCodeMapper;
import com.bryllyant.kona.app.entity.PromoCode;
import com.bryllyant.kona.app.entity.PromoCodeExample;
import com.bryllyant.kona.app.service.KAbstractPromoCodeService;
import com.bryllyant.kona.app.service.PromoCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(PromoCodeService.SERVICE_PATH)
public class PromoCodeServiceImpl extends KAbstractPromoCodeService<
        PromoCode,
        PromoCodeExample,
        PromoCodeMapper>
		implements PromoCodeService {
	
	private static Logger logger = LoggerFactory.getLogger(PromoCodeServiceImpl.class);
    
    @Autowired
    private PromoCodeMapper promoCodeMapper;
    

    @Override @SuppressWarnings("unchecked")
    protected PromoCodeMapper getMapper() {
        return promoCodeMapper;
    }

    protected PromoCode getNewObject() {
        return new PromoCode();
    }

    @Override
    protected PromoCodeExample getEntityExampleObject() {
        return new PromoCodeExample();
    }


}
