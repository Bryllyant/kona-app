/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.AccountMapper;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.AccountExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.EntityNameRuleService;
import com.bryllyant.kona.app.service.KAbstractAccountService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(AccountService.SERVICE_PATH)
public class AccountServiceImpl
        extends KAbstractAccountService<Account, AccountExample, User>
        implements AccountService {

    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountMapper accountDao;

    @Autowired
    UserService userService;

    @Autowired
    EntityNameRuleService entityNameRuleService;



    @Override
    protected Account getNewObject() {
        return new Account();
    }



    @Override
    @SuppressWarnings("unchecked")
    protected AccountMapper getDao() {
        return accountDao;
    }



    @Override
    @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }



    @Override
    public boolean isAccountNameAvailable(String name) {
        // check if name starts with punctuation
        //  One of !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
        if (name.matches("^\\p{Punct}+.*") || name.startsWith("\\s+")) {
            return false;
        }

        // check if any "parts" of the name violate a rule
        name = KInflector.getInstance().slug(name);

        String[] parts = name.split("-");

        for (String part : parts) {
            if (!entityNameRuleService.isAcceptable(part)) {
                return false;
            }
        }

        // finally check if account name already exists
        Account account = fetchBySlug(name);

        return (account == null);
    }



//    @Override
//    protected AccountExample getExampleObjectInstance(Integer startRow, Integer resultSize, String[] sortOrder,
//                                                      Map<String, Object> filter, boolean distinct) {
//        AccountExample example = new AccountExample();
//
//        if (sortOrder != null) {
//            example.setOrderByClause(KMyBatisUtil.getOrderByString(sortOrder));
//        }
//
//        if (startRow == null) startRow = 0;
//        if (resultSize == null) resultSize = 99999999;
//
//        example.setOffset(startRow);
//        example.setLimit(resultSize);
//        example.setDistinct(distinct);
//
//        KMyBatisUtil.buildExample(example.or().getClass(), example.or(), filter);
//        return example;
//    }


    @Override
    protected AccountExample getEntityExampleObject() {
        return new AccountExample();
    }
}
