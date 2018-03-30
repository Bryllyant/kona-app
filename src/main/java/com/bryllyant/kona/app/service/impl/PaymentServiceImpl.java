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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(PaymentService.SERVICE_PATH)
public class PaymentServiceImpl 
		extends KAbstractPaymentService<Payment, PaymentExample, PaymentMapper,PaymentAccount,Invoice,InvoiceItem,Cart,CartItem>
		implements PaymentService {
	
	private static Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    
    @Autowired
    private PaymentMapper paymentMapper;
    
    @Autowired
    private InvoiceService invoiceService;
    


    @Override @SuppressWarnings("unchecked")
    protected PaymentMapper getMapper() {
        return paymentMapper;
    }
    

    
    @Override
    protected Payment getNewPaymentObject() {
    	return new Payment();
    }
    

    
    @Override @SuppressWarnings("unchecked")
    protected InvoiceService getInvoiceService() {
        return invoiceService;
    }
    


    @Override
    protected PaymentExample getEntityExampleObject() { return new PaymentExample(); }

    


    @Override 
    protected void updateCoords(Long paymentId) {
        getMapper().updateCoords(paymentId);
    }


    @Override
    public List<Payment> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    ) {
        return getMapper().selectProximate(latitude, longitude, radius, startDate, endDate, objectIdList);
    }

}
