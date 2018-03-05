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
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service(EmailAddressService.SERVICE_PATH)
public class EmailAddressServiceImpl
        extends KAbstractEmailAddressService<EmailAddress,
        EmailAddressExample,
        EmailGroup,
        EmailGroupAddress>
        implements EmailAddressService {

    private static Logger logger = LoggerFactory.getLogger(EmailAddressServiceImpl.class);

    @Autowired
    private EmailAddressMapper emailAddressDao;

    @Autowired
    private EmailGroupService emailGroupService;

    @Autowired
    private EmailGroupAddressService emailGroupAddressService;




    @Override
    @SuppressWarnings("unchecked")
    protected EmailAddressMapper getDao() {
        return emailAddressDao;
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
        return emailAddressDao.fetchRandom(count, sourceList, excludeGroupIds);
    }

}
