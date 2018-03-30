/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PaymentAccountMapper;
import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.app.entity.PaymentAccountExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.KAbstractPaymentAccountService;
import com.bryllyant.kona.app.service.PaymentAccountService;
import com.bryllyant.kona.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(PaymentAccountService.SERVICE_PATH)
public class PaymentAccountServiceImpl 
		extends KAbstractPaymentAccountService<PaymentAccount, PaymentAccountExample, PaymentAccountMapper,User>
		implements PaymentAccountService {
	
	private static Logger logger = LoggerFactory.getLogger(PaymentAccountServiceImpl.class);
    
    @Autowired
    private PaymentAccountMapper paymentAccountMapper;
    
    @Autowired
    private UserService userService;


    
    @Override
    protected PaymentAccount getNewObject() {
        return new PaymentAccount();
    }



    @Override @SuppressWarnings("unchecked")
    protected PaymentAccountMapper getMapper() {
        return paymentAccountMapper;
    }
    


    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }

    


    @Override
    protected PaymentAccountExample getEntityExampleObject() { return new PaymentAccountExample(); }


}
