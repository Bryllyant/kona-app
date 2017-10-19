/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EmailGroupMapper;
import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.entity.EmailGroupAddress;
import com.bryllyant.kona.app.entity.EmailGroupExample;
import com.bryllyant.kona.app.service.EmailAddressService;
import com.bryllyant.kona.app.service.EmailGroupAddressService;
import com.bryllyant.kona.app.service.EmailGroupService;
import com.bryllyant.kona.app.service.KAbstractEmailGroupService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service(EmailGroupService.SERVICE_PATH)
public class EmailGroupServiceImpl 
		extends KAbstractEmailGroupService<EmailGroup,
                                      EmailGroupExample,
                                      EmailAddress,
                                      EmailGroupAddress> 
		implements EmailGroupService {
	
	private static Logger logger = LoggerFactory.getLogger(EmailGroupServiceImpl.class);

	@Autowired
	private EmailGroupMapper emailGroupDao;
    
	@Autowired
	private EmailAddressService emailAddressService;

	@Autowired
	private EmailGroupAddressService emailGroupAddressService;
    
	
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected EmailGroupMapper getDao() {
		return emailGroupDao;
	}
    
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected EmailAddressService getEmailAddressService() {
		return emailAddressService;
	}

	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected EmailGroupAddressService getEmailGroupAddressService() {
		return emailGroupAddressService;
	}

	// ----------------------------------------------------------------------------

	@Override
	protected EmailGroup getNewEmailGroupObject() {
		return new EmailGroup();
	}


	// ----------------------------------------------------------------------------

	@Override
	protected EmailAddress getNewEmailAddressObject() {
		return new EmailAddress();
	}


	// ----------------------------------------------------------------------------

	@Override
	protected EmailGroupAddress getNewEmailGroupAddressObject() {
		return new EmailGroupAddress();
	}

	// ----------------------------------------------------------------------------

	@Override
	protected EmailGroupExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		EmailGroupExample example = new EmailGroupExample();

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
	
    
}
