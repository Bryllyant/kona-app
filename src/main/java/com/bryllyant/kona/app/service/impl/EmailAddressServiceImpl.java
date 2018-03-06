/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EmailAddressMapper;
import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailAddressExample;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.entity.EmailGroupAddress;
import com.bryllyant.kona.app.service.EmailAddressService;
import com.bryllyant.kona.app.service.EmailGroupAddressService;
import com.bryllyant.kona.app.service.EmailGroupService;
import com.bryllyant.kona.app.service.KAbstractEmailAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(EmailAddressService.SERVICE_PATH)
public class EmailAddressServiceImpl
        extends KAbstractEmailAddressService<EmailAddress, EmailAddressExample, EmailAddressMapper,
        EmailGroup,
        EmailGroupAddress>
        implements EmailAddressService {

    private static Logger logger = LoggerFactory.getLogger(EmailAddressServiceImpl.class);

    @Autowired
    private EmailAddressMapper emailAddressMapper;

    @Autowired
    private EmailGroupService emailGroupService;

    @Autowired
    private EmailGroupAddressService emailGroupAddressService;


    @Override
    protected EmailAddressMapper getMapper() {
        return emailAddressMapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected EmailGroupService getEmailGroupService() {
        return emailGroupService;
    }


    @Override
    @SuppressWarnings("unchecked")
    protected EmailGroupAddressService getEmailGroupAddressService() {
        return emailGroupAddressService;
    }



    @Override
    protected EmailAddress getNewObject() {
        return new EmailAddress();
    }



    @Override
    protected EmailAddressExample getEntityExampleObject() { return new EmailAddressExample(); }




    @Override
    protected List<EmailAddress> daoFetchRandom(Long count, List<String> sourceList, List<Long> excludeGroupIds) {
        return emailAddressMapper.fetchRandom(count, sourceList, excludeGroupIds);
    }

}
