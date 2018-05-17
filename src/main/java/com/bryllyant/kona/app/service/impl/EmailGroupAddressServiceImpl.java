/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EmailGroupAddressMapper;
import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailGroupAddress;
import com.bryllyant.kona.app.entity.EmailGroupAddressExample;
import com.bryllyant.kona.app.service.EmailAddressService;
import com.bryllyant.kona.app.service.EmailGroupAddressService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.util.KResultList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service(EmailGroupAddressService.SERVICE_PATH)
public class EmailGroupAddressServiceImpl 
		extends KAbstractService<EmailGroupAddress, EmailGroupAddressExample, EmailGroupAddressMapper>
		implements EmailGroupAddressService {
	
	private static Logger logger = LoggerFactory.getLogger(EmailGroupAddressServiceImpl.class);

	@Autowired
	private EmailGroupAddressMapper emailGroupAddressMapper;

    @Autowired
    private EmailAddressService emailAddressService;


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
    public List<EmailAddress> fetchByGroupId(
            Long groupId,
            Integer startRow,
            Integer resultSize,
            String[] sortOrder,
            Map<String, Object> filter,
            boolean distinct) {

        Map<String,Object> emailGroupFilter = KMyBatisUtil.createFilter("groupId", groupId);

        KResultList<EmailGroupAddress> emailGroupAddresses =
                fetchByCriteria(startRow, resultSize, null, emailGroupFilter, false);

        KResultList<EmailAddress> result = new KResultList<>();

        if (emailGroupAddresses.size() > 0) {
            List<Long> emailAddressIdList = new ArrayList<>();

            for (EmailGroupAddress emailGroupAddress : emailGroupAddresses) {
                emailAddressIdList.add(emailGroupAddress.getAddressId());
            }


            if (filter == null) {
                filter = new HashMap<>();
            }

            filter.put("|id", emailAddressIdList);

            result = emailAddressService.fetchByCriteria(0, resultSize, sortOrder, filter, distinct);
        }

        result.setStartIndex(emailGroupAddresses.getStartIndex());
        result.setEndIndex(emailGroupAddresses.getEndIndex());
        result.setTotalSize(emailGroupAddresses.getTotalSize());
        result.setPageSize(emailGroupAddresses.getPageSize());

        return result;
    }


    @Override
    public List<EmailGroupAddress> fetchByAddressId(Long addressId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("addressId", addressId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }
}
