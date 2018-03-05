/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.InvoiceItemMapper;
import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.InvoiceItem;
import com.bryllyant.kona.app.entity.InvoiceItemExample;
import com.bryllyant.kona.app.service.InvoiceItemService;
import com.bryllyant.kona.app.service.KAbstractInvoiceItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(InvoiceItemService.SERVICE_PATH)
public class InvoiceItemServiceImpl 
		extends KAbstractInvoiceItemService<InvoiceItem,InvoiceItemExample,Invoice,CartItem> 
		implements InvoiceItemService {
	
	private static Logger logger = LoggerFactory.getLogger(InvoiceItemServiceImpl.class);
    
    @Autowired
    private InvoiceItemMapper invoiceItemDao;
    


    @Override @SuppressWarnings("unchecked")
    protected InvoiceItemMapper getDao() {
        return invoiceItemDao;
    }
    

    
    @Override
    protected InvoiceItem getNewObject() {
    	return new InvoiceItem();
    }
    


//    @Override
//    protected InvoiceItemExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
//            Map<String, Object> filter, boolean distinct) {
//    	InvoiceItemExample example = new InvoiceItemExample();
//
//        if (sortOrder != null) {
//            example.setOrderByClause(KMyBatisUtil.getOrderByString(sortOrder));
//        }
//
//        if (startRow == null) startRow = 0;
//        if (resultSize == null) resultSize = 99999999;
//
//        example.setOffset(startRow);
//        example.setLimit(resultSize);
//        example.setDistinct(distinct);
//
//        KMyBatisUtil.buildExample(example.or().getClass(), example.or(), filter);
//        return example;
//    }

    @Override
    protected InvoiceItemExample getEntityExampleObject() {
        return new InvoiceItemExample();
    }
}
