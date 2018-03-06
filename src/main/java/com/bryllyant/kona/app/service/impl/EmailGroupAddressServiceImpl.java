/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EmailGroupAddressMapper;
import com.bryllyant.kona.app.entity.EmailGroupAddress;
import com.bryllyant.kona.app.entity.EmailGroupAddressExample;
import com.bryllyant.kona.app.service.EmailGroupAddressService;
import com.bryllyant.kona.app.service.KAbstractEmailGroupAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(EmailGroupAddressService.SERVICE_PATH)
public class EmailGroupAddressServiceImpl 
		extends KAbstractEmailGroupAddressService<EmailGroupAddress, EmailGroupAddressExample, EmailGroupAddressMapper>
		implements EmailGroupAddressService {
	
	private static Logger logger = LoggerFactory.getLogger(EmailGroupAddressServiceImpl.class);

	@Autowired
	private EmailGroupAddressMapper emailGroupAddressMapper;
    


	@Override @SuppressWarnings("unchecked")
	protected EmailGroupAddressMapper getMapper() {
		return emailGroupAddressMapper;
	}
    


	 @Override
    protected EmailGroupAddressExample getEntityExampleObject() { return new EmailGroupAddressExample(); }



	
    
}
