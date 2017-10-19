/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EmailGroupAddressMapper;
import com.bryllyant.kona.app.entity.EmailGroupAddress;
import com.bryllyant.kona.app.entity.EmailGroupAddressExample;
import com.bryllyant.kona.app.service.EmailGroupAddressService;
import com.bryllyant.kona.app.service.KAbstractEmailGroupAddressService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service(EmailGroupAddressService.SERVICE_PATH)
public class EmailGroupAddressServiceImpl 
		extends KAbstractEmailGroupAddressService<EmailGroupAddress,
                                      EmailGroupAddressExample>
		implements EmailGroupAddressService {
	
	private static Logger logger = LoggerFactory.getLogger(EmailGroupAddressServiceImpl.class);

	@Autowired
	private EmailGroupAddressMapper emailGroupAddressDao;
    
	// ----------------------------------------------------------------------------

	@Override @SuppressWarnings("unchecked")
	protected EmailGroupAddressMapper getDao() {
		return emailGroupAddressDao;
	}
    
	// ----------------------------------------------------------------------------

	@Override
	protected EmailGroupAddressExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
			Map<String, Object> filter, boolean distinct) {
		EmailGroupAddressExample example = new EmailGroupAddressExample();

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
