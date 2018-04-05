/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.Payment;
import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;

import java.math.BigDecimal;

public interface CommerceService extends KService {
	String SERVICE_PATH = "rpc/CommerceService";

    Payment charge(
            Cart cart,
            String cardToken,
            Boolean setDefaultCard,
            String paymentOption,
            KServiceClient client
    );


    // NOTE, if a payment fails while charging a cart, the generated invoice
    // is immediately closed so a payment retry does not occur.  The assumption
    // is that carts are charged for live payments and invoices are charged
    // for recurring payments.
    Payment charge(
            Cart cart,
            PaymentAccount paymentAccount,
            String cardToken,
            KServiceClient client
    );

    Payment charge(Invoice invoice, PaymentAccount paymentAccount, KServiceClient client);

    Payment charge(
            Invoice invoice,
            PaymentAccount paymentAccount,
            String cardToken,
            boolean paymentRequired,
            KServiceClient client
    );

    Payment charge(
            KServiceClient client,
            PaymentAccount paymentAccount,
            String cardToken,
            String productName
    );

    Payment externalCharge(
            Cart cart,
            Payment.Type paymentType,
            BigDecimal paidAmount,
            String processorRef,
            BigDecimal processorFee,
            KServiceClient client
    );

    Payment externalCharge(
            Invoice invoice,
            Payment.Type paymentType,
            BigDecimal paidAmount,
            String processorRef,
            BigDecimal processorFee,
            KServiceClient client
    );

    Payment inAppPurchase(
            String store,
            Long userId,
            Long productSkuId,
            Integer quantity,
            String promoCode,
            BigDecimal paidAmount,
            String processorRef,
            String receipt,
            String notes,
            KServiceClient client
    );

    Payment processorCharge(
            Invoice invoice,
            PaymentAccount paymentAccount,
            String cardToken,
            KServiceClient client
    );



//	Date getSubscriptionEndDate(Account account, User user,
//			Subscription sub, Integer quantity, Promo promo);
//
//	BigDecimal getItemDiscount(Subscription subscription, Promo promo);
//	String getItemDescription(User uer, Subscription sub);
//	String getItemDiscountDescription(Promo promo);

    String getCardLast4(PaymentAccount account);



    // NOTE: this method is not Transactional.  We don't want to rollback
    // payments that have already been processed.
    void renewSubscriptions();

    void retryFailedPayments();
	
}
