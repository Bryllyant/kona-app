/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.SmsMapper;
import com.bryllyant.kona.app.entity.Sms;
import com.bryllyant.kona.app.entity.SmsExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.KAbstractSmsService;
import com.bryllyant.kona.app.service.SmsService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(SmsService.SERVICE_PATH)
public class SmsServiceImpl extends KAbstractSmsService<Sms,SmsExample,User> implements SmsService {
    
    private static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
    
    @Autowired
    private KConfig config;
    
    @Autowired
    private SmsMapper smsDao;

    @Autowired
    private UserService userService;
    
    // ----------------------------------------------------------------------------

    @Override
    protected String getAccountSid() {
        String accountSid = config.getString("twilio.accountSid");
        return accountSid;
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    protected String getAuthToken() {
        String authToken = config.getString("twilio.authToken");
        return authToken;
        
    }
    
    // ----------------------------------------------------------------------------

    @Override
    public String getMessageStatusCallbackUrl() {
        return config.getString("twilio.messageStatusCallbackUrl");
    }
    
    // ----------------------------------------------------------------------------

    @Override
    public String getFromPhoneNumber() {
        return config.getString("sms.fromPhoneNumber");
    }
    
    // ----------------------------------------------------------------------------

    @Override
    public List<String> getTestPhoneNumberPrefixList() {
        List<String> list = new ArrayList<String>();
        
        String prefix = config.getString("sms.testPhoneNumberPrefix");
        
        if (prefix != null) {
            list.add(prefix);
        }
        
        return list;
    }
    
    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected SmsMapper getDao() {
        return smsDao;
    }
    
    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }

    // ----------------------------------------------------------------------------

    @Override
    protected Sms getNewObject() {
        return new Sms();
    } 
    
    // ----------------------------------------------------------------------------

    @Override
    protected SmsExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
            Map<String, Object> filter, boolean distinct) {
        SmsExample example = new SmsExample();

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


    
}
