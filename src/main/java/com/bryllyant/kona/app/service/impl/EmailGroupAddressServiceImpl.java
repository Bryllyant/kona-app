/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EmailGroupAddressMapper;
import com.bryllyant.kona.app.entity.EmailGroupAddress;
import com.bryllyant.kona.app.entity.EmailGroupAddressExample;
import com.bryllyant.kona.app.service.EmailGroupAddressService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(EmailGroupAddressService.SERVICE_PATH)
public class EmailGroupAddressServiceImpl 
		extends KAbstractService<EmailGroupAddress, EmailGroupAddressExample, EmailGroupAddressMapper>
		implements EmailGroupAddressService {
	
	private static Logger logger = LoggerFactory.getLogger(EmailGroupAddressServiceImpl.class);

	@Autowired
	private EmailGroupAddressMapper emailGroupAddressMapper;
    
	@Override @SuppressWarnings("unchecked")
	protected EmailGroupAddressMapper getMapper() {
		return emailGroupAddressMapper;
	}
    

    @Override
    public void validate(EmailGroupAddress emailGroupAddress) {
        if (emailGroupAddress.getCreatedDate() == null) {
            emailGroupAddress.setCreatedDate(new Date());
        }

        if (emailGroupAddress.getUid() == null) {
            emailGroupAddress.setUid(uuid());
        }

        emailGroupAddress.setUpdatedDate(new Date());
    }


    @Override
    public EmailGroupAddress fetchByGroupIdAndAddressId(Long groupId, Long addressId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
        filter.put("addressId", addressId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public List<EmailGroupAddress> fetchByGroupId(Long groupId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public List<EmailGroupAddress> fetchByAddressId(Long addressId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("addressId", addressId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }
}
