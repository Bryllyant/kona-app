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
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(AccountService.SERVICE_PATH)
public class AccountServiceImpl
        extends KAbstractService<Account, AccountExample, AccountMapper>
        implements AccountService {

    private static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    UserService userService;

    @Autowired
    EntityNameRuleService entityNameRuleService;


    @Override
    @SuppressWarnings("unchecked")
    protected AccountMapper getMapper() {
        return accountMapper;
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


    @Override
    public void validate(Account account) {
        if (account.getCreatedDate() == null) {
            account.setCreatedDate(new Date());
        }

        if (account.getUid() == null) {
            account.setUid(uuid());
        }

        if (account.getName() == null) {
            account.setName(account.getUid());
        }

        account.setUpdatedDate(new Date());

        String slug = KInflector.getInstance().slug(account.getName());
        account.setSlug(slug);
    }



    @Override @Transactional
    public Account createAccount(String name) {

        String uid = uuid();

        if (name == null) {
            name = uid;
        }

        // clean up displayName as much as possible before
        name = name.trim().replaceAll("\\p{Punct}", "");

        if (!isAccountNameAvailable(name)) {
            throw new IllegalArgumentException("Account name already exists: " + name);
        }

        String slug = KInflector.getInstance().slug(name);

        Account account = new Account();
        account.setUid(uid);
        account.setOwnerId(null);
        account.setName(name);
        account.setSlug(slug);
        account.setEnabled(true);
        account.setVerified(false);
        account.setCreatedDate(new Date());

		/* -- STRIPE --
        String email = uid;
        // If Stripe is enabled
        if (email != null && !account.getName().equalsIgnoreCase("guest")) {
            KCustomer customer = stripeService.addCustomer(email,
                    "Name: " + account.getDisplayName());
            String stripeUid = customer.getId();
            account.setStripeUid(stripeUid);
            account.setPaymentTypeId(PaymentType.CARD.getId());
        }
		 */

        return add(account);
    }



    @Override
    public Account fetchBySlug(String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public Account fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public Account fetchByOwnerId(Long ownerId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }




    @Override @Transactional
    public Account retire(Account account) {
        // fetch fresh object
        account = fetchById(account.getId());

        // return account if already retired
        if (account == null || account.getDeletedDate() != null) {
            return account;
        }

        // first check that all users associated with this account are also retired
        List<User> userList = userService.fetchByAccountId(account.getId());

        boolean haveActiveUser = false;

        for (User user : userList) {
            if (user.getDeletedDate() != null) {
                haveActiveUser = true;
            }
        }

        if (haveActiveUser) {
            throw new IllegalStateException("Cannot retire account with active (non-retired) users: accountId: " + account.getId());
        }



        // NOTE: we need uuid here in case multiple apps with the same name are deleted.
        // first app called test is deleted, then second app created called test gets deleted.
        String prefix = "$RETIRED_" + uuid() + "_";
        account.setSlug(prefix + account.getSlug());;
        account.setEnabled(false);
        account.setDeletedDate(new Date());
        account = update(account);

        return account;
    }
}
