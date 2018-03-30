package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPaymentAccount;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface KPaymentAccountService<PAYMENT_ACCOUNT extends KPaymentAccount, USER extends KUser>
        extends KService, KEntityService<PAYMENT_ACCOUNT> {

	public PAYMENT_ACCOUNT fetchByUid(String uid);
    
	public PAYMENT_ACCOUNT fetchBySlug(Long accountId, String slug);

	public PAYMENT_ACCOUNT fetchDefault(Long accountId);
    
	public List<PAYMENT_ACCOUNT> fetchByAccountId(Long accountId);

	public PAYMENT_ACCOUNT fetchByProviderCustomerId(String providerCustomerId);

	public PAYMENT_ACCOUNT addStripeAccount(Long userId, String stripeUid,
	        String cardLast4, boolean defaultAccount);
}
