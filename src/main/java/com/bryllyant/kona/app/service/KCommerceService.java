package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCart;
import com.bryllyant.kona.app.entity.KInvoice;
import com.bryllyant.kona.app.entity.KPayment;
import com.bryllyant.kona.app.entity.KPaymentAccount;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;

import java.math.BigDecimal;

public interface KCommerceService<PAYMENT extends KPayment,
        PAYMENT_ACCOUNT extends KPaymentAccount,
        CART extends KCart,
        INVOICE extends KInvoice>
        extends KService {

    PAYMENT charge(
            CART cart,
            String cardToken,
            Boolean setDefaultCard,
            String paymentOption,
            KServiceClient client
    );


    // NOTE, if a payment fails while charging a cart, the generated invoice
    // is immediately closed so a payment retry does not occur.  The assumption
    // is that carts are charged for live payments and invoices are charged
    // for recurring payments.
    PAYMENT charge(
            CART cart,
            PAYMENT_ACCOUNT paymentAccount,
            String cardToken,
            KServiceClient client
    );

    PAYMENT charge(INVOICE invoice, PAYMENT_ACCOUNT paymentAccount, KServiceClient client);

    PAYMENT charge(
            INVOICE invoice,
            PAYMENT_ACCOUNT paymentAccount,
            String cardToken,
            boolean paymentRequired,
            KServiceClient client
    );

    PAYMENT charge(
            KServiceClient client,
            PAYMENT_ACCOUNT paymentAccount,
            String cardToken,
            String productName
    );

    PAYMENT externalCharge(
            CART cart,
            PAYMENT.Type paymentType,
            BigDecimal paidAmount,
            String processorRef,
            BigDecimal processorFee,
            KServiceClient client
    );

    PAYMENT externalCharge(
            INVOICE invoice,
            PAYMENT.Type paymentType,
            BigDecimal paidAmount,
            String processorRef,
            BigDecimal processorFee,
            KServiceClient client
    );

    PAYMENT inAppPurchase(
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

    PAYMENT processorCharge(
            INVOICE invoice,
            PAYMENT_ACCOUNT paymentAccount,
            String cardToken,
            KServiceClient client
    );



//	Date getSubscriptionEndDate(Account account, User user,
//			Subscription sub, Integer quantity, Promo promo);
//
//	BigDecimal getItemDiscount(Subscription subscription, Promo promo);
//	String getItemDescription(User uer, Subscription sub);
//	String getItemDiscountDescription(Promo promo);

    String getCardLast4(PAYMENT_ACCOUNT account);



    // NOTE: this method is not Transactional.  We don't want to rollback
    // payments that have already been processed.
    void renewSubscriptions();

    void retryFailedPayments();
}
