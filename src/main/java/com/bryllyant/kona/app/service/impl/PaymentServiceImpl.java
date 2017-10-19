/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PaymentMapper;
import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.InvoiceItem;
import com.bryllyant.kona.app.entity.Payment;
import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.app.entity.PaymentExample;
import com.bryllyant.kona.app.service.InvoiceService;
import com.bryllyant.kona.app.service.KAbstractPaymentService;
import com.bryllyant.kona.app.service.PaymentService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(PaymentService.SERVICE_PATH)
public class PaymentServiceImpl 
		extends KAbstractPaymentService<Payment,PaymentExample,PaymentAccount,Invoice,InvoiceItem,Cart,CartItem> 
		implements PaymentService {
	
	private static Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    
    @Autowired
    private PaymentMapper paymentDao;
    
    @Autowired
    private InvoiceService invoiceService;
    
    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected PaymentMapper getDao() {
        return paymentDao;
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    protected Payment getNewPaymentObject() {
    	return new Payment();
    }
    
    // ----------------------------------------------------------------------------
    
    @Override @SuppressWarnings("unchecked")
    protected InvoiceService getInvoiceService() {
        return invoiceService;
    }
    
    // ----------------------------------------------------------------------------

    @Override
    protected PaymentExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
            Map<String, Object> filter, boolean distinct) {
    	PaymentExample example = new PaymentExample();

        if (sortOrder != null) {
            example.setOrderByClause(KMyBatisUtil.getOrderByString(sortOrder));
        }

        if (startRow == null) startRow = 0;
        if (resultSize == null) resultSize = 99999999;

        example.setOffset(startRow);
        example.setLimit(resultSize);
        example.setDistinct(distinct);

        KMyBatisUtil.buildExample(example.or().getClass(), example.or(), filter);
        return example;
    }
    
    // ----------------------------------------------------------------------------

    @Override 
    protected void updateCoords(Long paymentId) {
        getDao().updateCoords(paymentId);
    }

    // ----------------------------------------------------------------------------

    @Override 
    public List<Payment> fetchProximate(Double latitude, Double longitude, Double radius, Date startDate, Date endDate) {
        return getDao().selectProximate(latitude, longitude, radius, startDate, endDate);
    }


}
