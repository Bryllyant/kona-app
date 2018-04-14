/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PaymentAccountMapper;
import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.app.entity.PaymentAccountExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.PaymentAccountService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(PaymentAccountService.SERVICE_PATH)
public class PaymentAccountServiceImpl 
		extends KAbstractService<PaymentAccount, PaymentAccountExample, PaymentAccountMapper>
		implements PaymentAccountService {
	
	private static Logger logger = LoggerFactory.getLogger(PaymentAccountServiceImpl.class);
    
    @Autowired
    private PaymentAccountMapper paymentAccountMapper;
    
    @Autowired
    private UserService userService;


    @Override @SuppressWarnings("unchecked")
    protected PaymentAccountMapper getMapper() {
        return paymentAccountMapper;
    }
    

    @Override
    public void validate(PaymentAccount paymentAccount) {
        if (paymentAccount.getCreatedDate() == null) {
            paymentAccount.setCreatedDate(new Date());
        }

        if (paymentAccount.getUid() == null) {
            paymentAccount.setUid(uuid());
        }

        if (paymentAccount.isDefaultAccount()) {
            unsetDefaultAccount(paymentAccount);
        }

        if (paymentAccount.getName() != null) {
            String slug = KInflector.getInstance().slug(paymentAccount.getName());
            paymentAccount.setSlug(slug);
        }

        if (paymentAccount.getProviderName() != null) {
            String slug = KInflector.getInstance().slug(paymentAccount.getProviderName());
            paymentAccount.setProviderSlug(slug);
        }
    }


    private void unsetDefaultAccount(PaymentAccount current) {
        PaymentAccount paymentAccount = fetchDefault(current.getAccountId());

        if (paymentAccount != null) {
            if (current.getId() == null || !current.getId().equals(paymentAccount.getId())) {
                paymentAccount.setDefaultAccount(false);
                getMapper().updateByPrimaryKey(paymentAccount);
            }
        }
    }

    @Override
    public PaymentAccount fetchBySlug(Long accountId, String slug) {
        Map<String, Object> filter = KMyBatisUtil.createFilter();
        filter.put("accountId", accountId);
        filter.put("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public PaymentAccount fetchDefault(Long accountId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter();
        filter.put("accountId", accountId);
        filter.put("defaultAccount", true);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public List<PaymentAccount> fetchByAccountId(Long accountId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public PaymentAccount fetchByProviderCustomerId(String providerName, String providerCustomerId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("providerCustomerId", providerCustomerId);
        filter.put("providerName", providerName);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public PaymentAccount fetchByProviderAccountNumber(String providerName, String accountNumber) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("accountNumber", accountNumber);
        filter.put("providerName", providerName);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    @Override
    public PaymentAccount addStripeAccount(User user, String stripeUid, String cardLast4, boolean defaultAccount) {
        String providerName = "Stripe";
        String providerSlug = KInflector.getInstance().slug("Stripe");

        PaymentAccount paymentAccount = fetchByProviderCustomerId(providerSlug, stripeUid);

        // if we have an existing account for this stripeUid then just update the cardInfo

        if (paymentAccount != null) {
            // sanity checks
            if (!paymentAccount.getProviderName().equals("stripe")) {
                throw new IllegalStateException("Payment account is not 'stripe'");
            }

            if (!paymentAccount.getType().equals(PaymentAccount.Type.CREDIT_CARD)) {
                throw new IllegalStateException("Payment account type is not 'Credit Card'");
            }
        }


        if (paymentAccount == null) {
            paymentAccount = new PaymentAccount();
            paymentAccount.setAccountId(user.getAccountId());
            paymentAccount.setType(PaymentAccount.Type.CREDIT_CARD);
            paymentAccount.setProviderName(providerName);
            paymentAccount.setProviderSlug(providerSlug);
        }

        paymentAccount.setOwnerId(user.getId());
        paymentAccount.setProviderCustomerId(stripeUid);
        paymentAccount.setCardLast4(cardLast4);
        paymentAccount.setDefaultAccount(defaultAccount);

        return save(paymentAccount);
    }

    @Override
    public PaymentAccount create(User user, PaymentAccount.Type type, String providerName, String accountNumber) {
        PaymentAccount paymentAccount = new PaymentAccount();
        paymentAccount.setOwnerId(user.getId());
        paymentAccount.setAccountId(user.getAccountId());
        paymentAccount.setType(type);
        paymentAccount.setProviderName(providerName);
        paymentAccount.setAccountNumber(accountNumber);

        return save(paymentAccount);
    }
}
