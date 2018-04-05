/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface PaymentAccountService extends KService, KEntityService<PaymentAccount> {
	String SERVICE_PATH = "rpc/PaymentAccountService";

    PaymentAccount fetchBySlug(Long accountId, String slug);

    PaymentAccount fetchDefault(Long accountId);

    List<PaymentAccount> fetchByAccountId(Long accountId);

    PaymentAccount fetchByProviderCustomerId(String providerCustomerId);

    PaymentAccount addStripeAccount(
            Long userId,
            String stripeUid,
            String cardLast4,
            boolean defaultAccount
    );
	
}
