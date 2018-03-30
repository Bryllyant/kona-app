package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAccount;
import com.bryllyant.kona.app.entity.KApp;
import com.bryllyant.kona.app.entity.KCampaignChannel;
import com.bryllyant.kona.app.entity.KCampaignGroup;
import com.bryllyant.kona.app.entity.KCampaignReply;
import com.bryllyant.kona.app.entity.KCampaignTarget;
import com.bryllyant.kona.app.entity.KCart;
import com.bryllyant.kona.app.entity.KCartItem;
import com.bryllyant.kona.app.entity.KEmail;
import com.bryllyant.kona.app.entity.KFile;
import com.bryllyant.kona.app.entity.KInvoice;
import com.bryllyant.kona.app.entity.KInvoiceItem;
import com.bryllyant.kona.app.entity.KPayment;
import com.bryllyant.kona.app.entity.KPaymentAccount;
import com.bryllyant.kona.app.entity.KProductSku;
import com.bryllyant.kona.app.entity.KPromo;
import com.bryllyant.kona.app.entity.KPromoCode;
import com.bryllyant.kona.app.entity.KPurchase;
import com.bryllyant.kona.app.entity.KPush;
import com.bryllyant.kona.app.entity.KSms;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.stripe.entity.KCharge;
import com.bryllyant.kona.stripe.entity.KStripeException;
import com.bryllyant.kona.util.KClassUtil;
import com.bryllyant.kona.util.KDateUtil;
import com.bryllyant.kona.util.KJsonUtil;
import com.bryllyant.kona.util.KSystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class KAbstractCommerceService<
        APP extends KApp,
        USER extends KUser,
        FILE extends KFile,
        ACCOUNT extends KAccount,
        CART extends KCart,
        CART_ITEM extends KCartItem,
        INVOICE extends KInvoice,
        INVOICE_ITEM extends KInvoiceItem,
        PRODUCT_SKU extends KProductSku,
        CAMPAIGN_CHANNEL extends KCampaignChannel,
        CAMPAIGN_GROUP extends KCampaignGroup,
        CAMPAIGN_TARGET extends KCampaignTarget,
        CAMPAIGN_REPLY extends KCampaignReply,
        PROMO extends KPromo,
        PROMO_CODE extends KPromoCode,
        PAYMENT extends KPayment,
        PAYMENT_ACCOUNT extends KPaymentAccount,
        PURCHASE extends KPurchase,
        EMAIL extends KEmail,
        SMS extends KSms,
        PUSH extends KPush>
        implements KCommerceService<PAYMENT,PAYMENT_ACCOUNT,CART,INVOICE> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractCommerceService.class);

    protected abstract PAYMENT getNewPaymentObject();

    protected abstract PURCHASE getNewPurchaseObject();

    protected abstract String getGooglePlayPackageName();

    protected abstract String getAppleVerifyReceiptUrl();

    protected abstract String getAppleVerifyReceiptSandboxUrl();

    protected abstract String getAppleAppSharedSecret();

    protected abstract void sendReceiptEmail(INVOICE invoice, boolean externalPayment);

    protected abstract void sendInvalidCardEmail(INVOICE invoice);

    protected abstract <S extends KAccountService<ACCOUNT>> S getAccountService();

    protected abstract <S extends KUserService<USER>> S getUserService();

    protected abstract <S extends KCartService<CART>> S getCartService();

    protected abstract <S extends KCartItemService<CART_ITEM,CART>> S getCartItemService();

    protected abstract <S extends KInvoiceService<INVOICE,INVOICE_ITEM,CART,CART_ITEM>> S getInvoiceService();

    protected abstract <S extends KInvoiceItemService<INVOICE_ITEM,INVOICE,CART_ITEM>> S getInvoiceItemService();

    protected abstract <S extends KPromoService<PROMO>> S getPromoService();

    protected abstract <S extends KPromoCodeService<PROMO_CODE>> S getPromoCodeService();

    protected abstract <S extends KCampaignChannelService<
            CAMPAIGN_CHANNEL,
            CAMPAIGN_GROUP,
            CAMPAIGN_TARGET,
            CAMPAIGN_REPLY>> S getCampaignChannelService();

    protected abstract <S extends KProductSkuService<PRODUCT_SKU>> S getProductSkuService();

    protected abstract <S extends KPurchaseService<PURCHASE>> S getPurchaseService();

    protected abstract <S extends KPaymentService<PAYMENT,PAYMENT_ACCOUNT,INVOICE>> S getPaymentService();

    protected abstract <S extends KPaymentAccountService<PAYMENT_ACCOUNT,USER>> S getPaymentAccountService();

    protected abstract <S extends KStripeService> S getStripeService();

    protected abstract <S extends KGooglePlayService<PURCHASE>> S getGooglePlayService();

    protected abstract <S extends KSystemService<APP,ACCOUNT,USER,FILE,EMAIL,SMS,PUSH>> S getSystemService();



    protected ACCOUNT getAccountByUserId(Long userId) {
        if (userId == null) return null;

        ACCOUNT account = null;

        USER user = getUserService().fetchById(userId);

        if (user != null && user.getAccountId() != null) {
            account = getAccountService().fetchById(user.getAccountId());
        }

        return account;
    }


    private String getStripeUid(ACCOUNT account) {
        PAYMENT_ACCOUNT paymentAccount = getPaymentAccountService().fetchDefault(account.getId());
        return getStripeUid(paymentAccount);
    }


    private String getStripeUid(USER user) {
        PAYMENT_ACCOUNT paymentAccount = getPaymentAccountService().fetchDefault(user.getAccountId());
        return getStripeUid(paymentAccount);
    }

    private String getStripeUid(PAYMENT_ACCOUNT paymentAccount) {
        String stripeUid = null;

        if (paymentAccount != null) {
            stripeUid = paymentAccount.getProviderCustomerId();
        }

        return stripeUid;
    }


    @Override
    public PAYMENT charge(
            CART cart,
            String cardToken,
            Boolean setDefaultCard,
            String paymentOption,
            KServiceClient client
    ) {
        Long userId = cart.getUserId();

        USER user = getUserService().fetchById(userId);

        // paymentOption: defaultCard | newCard
        // we always charge the default (active) card associated
        // with the customer.  The only situation we need to account
        // for is a paymentOption=newCard setDefaultCard=false
        //
        // To keep things simple, we always charge the new card
        // if cardToken is not null.  Therefore, we set cardToken
        // to null after setting it as the customer's default card
        // to indicate that the customer should be charged.
        if (paymentOption != null && paymentOption.equals("newCard")) {
            if (setDefaultCard) {
                getStripeService().addCustomerCard(getStripeUid(user), cardToken);
                cardToken = null;
            }
        }

        PAYMENT_ACCOUNT paymentAccount = getPaymentAccountService().fetchDefault(user.getAccountId());

        return charge(cart, paymentAccount, cardToken, client);
    }



    @Override
    // NOTE, if a payment fails while charging a cart, the generated invoice
    // is immediately closed so a payment retry does not occur.  The assumption
    // is that carts are charged for live payments and invoices are charged
    // for recurring payments.
    public PAYMENT charge(
            CART cart,
            PAYMENT_ACCOUNT paymentAccount,
            String cardToken,
            KServiceClient client
    ) {
        if (cart == null) {
            throw new KPaymentException("Cart is empty");
        }

        boolean paymentRequired = false;

        if (cart.getTotal() != null &&
                cart.getTotal().compareTo(new BigDecimal(0))>0) {
            paymentRequired = true;
        }

        INVOICE invoice = getInvoiceService().createInvoice(cart);

        PAYMENT payment = charge(invoice, paymentAccount, cardToken, paymentRequired, client);

        if (paymentRequired && payment != null) {

            if (payment.getStatus() != PAYMENT.Status.SUCCESS || !payment.isPaid()) {
                getInvoiceService().closeInvoice(invoice, false, null, null, null, null);
            }
        }

        return payment;
    }



    @Override
    public PAYMENT charge(INVOICE invoice, PAYMENT_ACCOUNT paymentAccount, KServiceClient client) {
        return charge(invoice, paymentAccount, null, true, client);
    }



    @Override
    public PAYMENT charge(
            KServiceClient client,
            PAYMENT_ACCOUNT paymentAccount,
            String cardToken,
            String productName
    ) {
        logger.debug("KAbstractCommerceService: client: " + client + " paymentAccount: " + paymentAccount + " productName: " + productName);

        INVOICE invoice = getInvoiceService().createInvoice(paymentAccount.getAccountId(), productName, null);

        PAYMENT payment = charge(invoice, paymentAccount, cardToken, true, client);

        if (payment == null
                || payment.getStatus() == null
                || !payment.getStatus().equals(PAYMENT.Status.SUCCESS)) {

            String notes = payment.getProcessorError();

            getInvoiceService().closeInvoice(invoice, false, null, null, null, notes);
        }

        return payment;
    }

    PROMO getPromoByCampaignChannelId(Long campaignChannelId) {
        if (campaignChannelId == null) return null;

        PROMO promo = null;

        PROMO_CODE promoCode = getPromoCodeService().fetchByCampaignChannelId(campaignChannelId);

        if (promoCode != null) {
            promo = getPromoService().fetchById(promoCode.getPromoId());
        }

        return promo;
    }


    @Override
    public PAYMENT charge(
            INVOICE invoice,
            PAYMENT_ACCOUNT paymentAccount,
            String cardToken,
            boolean paymentRequired,
            KServiceClient client
    ) {
        PAYMENT payment = null;

        Long promoId = null;

        Long campaignChannelId = getCampaignChannelIdByInvoice(invoice);

        PROMO promo = getPromoByCampaignChannelId(campaignChannelId);

        if (promo != null) {
            promoId = promo.getId();
        }

        if (paymentRequired) {
            payment = processorCharge(invoice, paymentAccount, cardToken, client);
        } else {
            BigDecimal paidAmount = new BigDecimal(0);
            String notes = "Invoice paid internally. User not directly charged.";
            getInvoiceService().closeInvoice(invoice, true, paidAmount, "INTERNAL", null, notes);

            //FIXME: assume for now that if payment is not required
            // it is because of a promoCode

            Date now = new Date();

            payment = getNewPaymentObject();
            payment.setCampaignChannelId(campaignChannelId);
            payment.setPromoId(promoId);
            payment.setType(PAYMENT.Type.PROMO);
            payment.setStatus(PAYMENT.Status.SUCCESS);
            payment.setCurrencyId(invoice.getCurrencyId());
            payment.setUserId(invoice.getUserId());
            payment.setAccountId(invoice.getAccountId());
            payment.setInvoiceId(invoice.getId());
            payment.setTokenId(client.getTokenId());
            payment.setHostname(client.getHostname());
            payment.setUserAgent(client.getUserAgent());
            payment.setLatitude(client.getLatitude());
            payment.setLongitude(client.getLongitude());
            payment.setCardToken(cardToken);
            payment.setCardLast4(null);
            payment.setAmount(new BigDecimal(0));
            payment.setProcessorRef(null);
            payment.setProcessorError(null);
            payment.setProcessorFee(new BigDecimal(0));
            payment.setPaid(true);
            payment.setFailed(false);
            payment.setPaidDate(now);
            payment.setCreatedDate(now);
            payment = getPaymentService().add(payment);
        }

        if (!paymentRequired || payment.isPaid()) {
            //FIXME: if you purchase more than 1 subscription, then the
            // combined value of that purchase should be applied to your
            // account.

            List<INVOICE_ITEM> invoiceItemList = getInvoiceItemService().getInvoiceItemList(invoice);
            logger.debug("invoice item list size: " + invoiceItemList.size());

            Long userId = invoice.getUserId();

            ACCOUNT account = getAccountByUserId(userId);

            for (INVOICE_ITEM item : invoiceItemList) {
                boolean autoRenew = false;

                PAYMENT.Type paymentType = PAYMENT.Type.PROMO;

                if (paymentRequired) {
                    autoRenew = true;
                    paymentType = PAYMENT.Type.CARD;
                }

                addPurchase(account, invoice, item, paymentType, autoRenew);

                incPromoUseCount(promo);
            }

            try {
                sendReceiptEmail(invoice, false);
                    /*
                    if (paymentRequired) {
                    	sendReceiptEmail(invoice);
                    }
                    */
            } catch (Exception e) {
                String body = "\ninvoice: " + KClassUtil.toString(invoice);
                getSystemService().alert("Email Error: Payment Receipt", body, e);
            }
        }

        return payment;
    }



    private PURCHASE addPurchase(
            ACCOUNT account,
            INVOICE invoice,
            INVOICE_ITEM item,
            PAYMENT.Type paymentType,
            boolean autoRenew
    ) {
        PRODUCT_SKU productSku = getProductSkuService().fetchById(item.getProductSkuId());

        PURCHASE purchase = getNewPurchaseObject();
        purchase.setAccountId(account.getId());
        purchase.setUserId(invoice.getUserId());
        purchase.setProductId(productSku.getProductId());
        purchase.setProductSkuId(item.getProductSkuId());
        purchase.setQuantity(item.getQuantity());

        // ignore value of autoRenew if product is not a subscription
        if (!productSku.isSubscription()) {
            autoRenew = false;
        }

        purchase.setCampaignChannelId(item.getCampaignChannelId());
        purchase.setPromoId(item.getPromoId());
        purchase.setAutoRenew(autoRenew);
        purchase.setInvoiceId(invoice.getId());
        purchase.setPaymentType(paymentType);
        purchase.setEnabled(true);
        purchase.setExpirationDate(item.getSubscriptionEndDate());

        return getPurchaseService().save(purchase);
    }



    @Override
    public PAYMENT externalCharge(
            CART cart,
            PAYMENT.Type paymentType,
            BigDecimal paidAmount,
            String processorRef,
            BigDecimal processorFee,
            KServiceClient client
    ) {
        INVOICE invoice = getInvoiceService().createInvoice(cart);

        return externalCharge(
                invoice,
                paymentType,
                paidAmount,
                processorRef,
                processorFee,
                client
        );
    }



    @Override
    public PAYMENT externalCharge(
            INVOICE invoice,
            PAYMENT.Type paymentType,
            BigDecimal paidAmount,
            String processorRef,
            BigDecimal processorFee,
            KServiceClient client
    ) {
        PAYMENT payment = null;

        if (processorRef == null) {
            processorRef = "EXTERNAL PAYMENT";
        }

        if (processorFee == null) {
            processorFee = new BigDecimal(0);
        }

        if (paymentType == null) {
            paymentType = PAYMENT.Type.EXTERNAL;
        }

        boolean paid = false;

        if (invoice.getAmountDue() != null && paidAmount != null &&
                invoice.getAmountDue().compareTo(paidAmount) == 0) {
            paid = true;
        }

        getInvoiceService().closeInvoice(
                invoice,
                paid,
                paidAmount,
                processorRef,
                null,
                null
        );


        Long promoId = null;

        Long campaignChannelId = getCampaignChannelIdByInvoice(invoice);

        PROMO promo = getPromoByCampaignChannelId(campaignChannelId);

        if (promo != null) {
            promoId = promo.getId();
        }

        Date now = new Date();

        payment = getNewPaymentObject();
        payment.setCampaignChannelId(campaignChannelId);
        payment.setPromoId(promoId);
        payment.setType(paymentType);
        payment.setStatus(PAYMENT.Status.SUCCESS);
        payment.setCurrencyId(invoice.getCurrencyId());
        payment.setUserId(invoice.getUserId());
        payment.setAccountId(invoice.getAccountId());
        payment.setInvoiceId(invoice.getId());
        payment.setTokenId(client.getTokenId());
        payment.setHostname(client.getHostname());
        payment.setUserAgent(client.getUserAgent());
        payment.setLatitude(client.getLatitude());
        payment.setLongitude(client.getLongitude());
        payment.setCardToken(null);
        payment.setCardLast4(null);
        payment.setAmount(paidAmount);
        payment.setProcessorRef(processorRef);
        payment.setProcessorError(null);
        payment.setProcessorFee(processorFee);
        payment.setPaid(true);
        payment.setFailed(false);
        payment.setPaidDate(now);
        payment.setCreatedDate(now);
        payment = getPaymentService().add(payment);

        Long userId = invoice.getUserId();

        ACCOUNT account = getAccountByUserId(userId);

        for (INVOICE_ITEM item : getInvoiceItemService().getInvoiceItemList(invoice)) {
            addPurchase(account, invoice, item, paymentType, true);
            incPromoUseCount(promo);
        }

        try {
            sendReceiptEmail(invoice, true);
        } catch (Exception e) {
            String body = "\ninvoice: " + KClassUtil.toString(invoice);
            getSystemService().alert("Email Error: Payment Receipt", body, e);
        }

        return payment;
    }

    private void incPromoUseCount(PROMO promo) {
        if (promo == null) return;

        Integer useCount = promo.getUseCount();

        if (useCount == null) useCount = 0;

        useCount += 1;

        promo.setUseCount(useCount);

        getPromoService().update(promo);
    }

    private Long getCampaignChannelIdByInvoice(INVOICE invoice) {
        List<INVOICE_ITEM> items = getInvoiceItemService().fetchByInvoiceId(invoice.getId());

        Long campaignChannelId = null;

        for (INVOICE_ITEM item : items) {

            Long id = item.getCampaignChannelId();

            if (campaignChannelId == null) {
                campaignChannelId = id;
                continue;
            }

            if (id != null && !id.equals(campaignChannelId)) {
                return null;
            }
        }

        return campaignChannelId;
    }

    private Long getPromoIdByInvoice(INVOICE invoice) {
        List<INVOICE_ITEM> items = getInvoiceItemService().fetchByInvoiceId(invoice.getId());

        Long promoId = null;

        for (INVOICE_ITEM item : items) {

            Long id = item.getPromoId();

            if (promoId == null) {
                promoId = id;
                continue;
            }

            if (id != null && !id.equals(promoId)) {
                return null;
            }
        }

        return promoId;
    }

    
    /*
    @Override
    private PAYMENT chargeCustomer(String cardToken, INVOICE invoice)
    		throws KPaymentException {
        ACCOUNT account = getAccount(invoice.getAccountId());

        if (account == null) {
            throw new KPaymentException(
                "Account not found for invoice: " + toString(invoice));
        }
        
        // see if we have a stripeUid for this customer
        if (cardToken == null && account.getStripeUid() == null) {
            throw new KPaymentException(
                "StripeUid is null for accountId: " + account.getId());
        }
        
        BigDecimal total = invoice.getAmountDue();
        if (total == null || total.compareTo(new BigDecimal(0))<=0) {
            throw new KPaymentException(
            		"Invoice amount due is null or negative: " + total);
        }
        
        return processorCharge(account, cardToken, invoice); 
    }
    */



    @Override
    public String getCardLast4(PAYMENT_ACCOUNT paymentAccount) {
        if (paymentAccount == null) return null;
        return paymentAccount.getCardLast4();
    }



    @Override
    public PAYMENT processorCharge(
            INVOICE invoice,
            PAYMENT_ACCOUNT paymentAccount,
            String cardToken,
            KServiceClient client
    ) {

        PAYMENT payment = null;
        KCharge chargeItem = null;
        String chargeDescription = getChargeDescription(invoice);

        invoice.setPaymentAttempted(true);

        Integer attemptCount = invoice.getPaymentAttemptCount();

        if (attemptCount == null) {
            attemptCount = 0;
        }

        attemptCount += 1;

        invoice.setPaymentAttemptCount(attemptCount);

        invoice.setLastPaymentAttemptDate(new Date());

        getInvoiceService().updateInvoice(invoice);

        Long userId = invoice.getUserId();

        USER user = getUserService().fetchById(userId);

        ACCOUNT account = getAccountService().fetchById(user.getAccountId());

        PAYMENT.Type type = PAYMENT.Type.CARD;

        PAYMENT.Status status = null;

        String error = null;

        if (account == null) {
            status = PAYMENT.Status.ACCOUNT_INVALID;

            error = "Account not found for invoice: " + KClassUtil.toString(invoice);

            return getPaymentService().createPayment(
                    type,
                    status,
                    cardToken,
                    paymentAccount,
                    invoice,
                    error,
                    client
            );
        }

        if (!account.isEnabled()) {
            status = PAYMENT.Status.ACCOUNT_DISABLED;

            error = "Account disabled. accountId: " + account.getId();

            return getPaymentService().createPayment(
                    type,
                    status,
                    cardToken,
                    paymentAccount,
                    invoice,
                    error,
                    client
            );
        }

        String stripeUid = null;

        if (paymentAccount != null) {
            stripeUid = getStripeUid(paymentAccount);
        } else  {
            stripeUid = getStripeUid(user);
        }

        // see if we have a stripeUid for this customer
        if (cardToken == null && stripeUid == null) {
            status = PAYMENT.Status.CARD_MISSING;

            error = "StripeUid is null for accountId: " + account.getId();

            return getPaymentService().createPayment(
                    type,
                    status,
                    cardToken,
                    paymentAccount,
                    invoice,
                    error,
                    client
            );

        } else if (cardToken == null) {
            String cardLast4 = getStripeService().getPrimaryCardLast4(invoice.getUserId());

            if (cardLast4 == null) {
                status = PAYMENT.Status.CARD_MISSING;

                error = "User does not have a default card. userId: " + userId;

                return getPaymentService().createPayment(
                        type,
                        status,
                        cardToken,
                        paymentAccount,
                        invoice,
                        error,
                        client
                );
            }
        }

        BigDecimal total = invoice.getAmountDue();

        if (total == null || total.compareTo(new BigDecimal(0)) <= 0) {
            status = PAYMENT.Status.AMOUNT_INVALID;

            error = "Invoice amount due is null, zero or negative: " + total;

            return getPaymentService().createPayment(
                    type,
                    status,
                    cardToken,
                    paymentAccount,
                    invoice,
                    error,
                    client
            );
        }

        try {
            String receiptEmail = null;
            Map<String,Object> metadata = null;
            Map<String,Object> shipping = null;

            if (cardToken == null) {
                chargeItem = getStripeService().chargeCustomer(
                        userId,
                        invoice.getAmountDue(),
                        chargeDescription,
                        receiptEmail,
                        metadata,
                        shipping
                );
            } else {
                chargeItem = getStripeService().chargeCard(
                        cardToken,
                        invoice.getAmountDue(),
                        chargeDescription,
                        receiptEmail,
                        metadata,
                        shipping
                );
            }

            payment = getPaymentService().createPayment(
                    PAYMENT.Type.CARD,
                    cardToken,
                    paymentAccount,
                    invoice,
                    chargeItem,
                    client
            );

        } catch (KStripeException e) {
            KPaymentException ex = new KPaymentException(e.getMessage(), e);

            // FIXME: parse exception and properly set PaymentStatus
            payment = getPaymentService().createPayment(
                    PAYMENT.Type.CARD,
                    cardToken,
                    paymentAccount,
                    invoice,
                    ex,
                    client
            );
        }

        return payment;
    }



    private String getChargeDescription(INVOICE invoice) {
        String username = getUserService().fetchById(invoice.getUserId()).getUsername();

        StringBuffer sb = new StringBuffer();
        sb.append("invoiceId: " + invoice.getId());
        sb.append("\naccountId: " + invoice.getAccountId());
        sb.append("\nusername: " + username);
        return sb.toString();
    }



    // NOTE: this method is not Transactional.  We don't want to rollback
    // payments that have already been processed.

    // FIXME: don't renew subscriptions if payment was made
    // through external processor.  Check if account has stripeUId??
    @Override
    public void renewSubscriptions() {
        logger.debug("calling renewSubscriptions()");

        // We want to get a list of all accounts that will expire in
        // 5 days or less and have autoRenew set to true.
        Date startDate = new Date();
        Date endDate = KDateUtil.addDays(startDate, 2);

        // in case we were down for some reason, check all past
        // subscriptions for the past 7 days
        startDate = KDateUtil.addDays(startDate, -7);

        List<PURCHASE> subscriptionList = getPurchaseService().fetchSubscriptionsByExpirationDate(
                startDate, endDate, true);

        List<Long> accountIdList = new ArrayList<>();

        // only renew subscriptions for which PaymentType is CARD
        // assumes the account has a StripeID associated with a  default card.
        List<PURCHASE> subscriptionRenewList = new ArrayList<>();

        for (PURCHASE purchase : subscriptionList) {
            PAYMENT.Type paymentType = purchase.getPaymentType();

            // make sure account is still active. if not expire this subscription
            ACCOUNT account = getAccountService().fetchById(purchase.getAccountId());

            if (account.getDeletedDate() != null) {
                purchase.setEnabled(false);
                purchase.setAutoRenew(false);
                purchase.setExpirationDate(new Date());
                getPurchaseService().update(purchase);
                continue;
            }

            if (purchase.isEnabled() && purchase.isAutoRenew()
                    && purchase.getProductId() != null
                    && paymentType != null && paymentType == PAYMENT.Type.CARD) {
                subscriptionRenewList.add(purchase);

                if (!accountIdList.contains(purchase.getAccountId())) {
                    accountIdList.add(purchase.getAccountId());
                }
            }
        }

        String summary = "";
        BigDecimal autoRenewTotal = new BigDecimal(0);

        List<Long> skipList = new ArrayList<>();

        for (Long accountId : accountIdList) {
            List<INVOICE> invoiceList = getInvoiceService().fetchOpenByAccountId(accountId);

            if (invoiceList != null && invoiceList.size()>0) {
                skipList.add(accountId);
                continue;
            }

            ACCOUNT account = getAccountService().fetchById(accountId);

            USER user = getUserService().fetchById(account.getOwnerId());

            Long userId = user.getId();

            CART cart = getCartService().createCart(userId,null);

            for (PURCHASE purchase : subscriptionRenewList) {
                if (purchase.getAccountId().equals(accountId)) {
                    Long productSkuId = purchase.getProductSkuId();

                    if (productSkuId != null) {
                        getCartItemService().addCartItem(cart, userId, productSkuId, null);
                    }
                }
            }

            if (cart.getTotal().compareTo(new BigDecimal(0)) > 0) {
                PAYMENT_ACCOUNT paymentAccount = getPaymentAccountService().fetchDefault(account.getId());

                try {
                    INVOICE invoice = getInvoiceService().createInvoice(cart);
                    PAYMENT payment = charge(invoice, paymentAccount, null);

                    if (payment.isPaid()) {
                        autoRenewTotal = autoRenewTotal.add(payment.getAmount());
                        summary += "\n\nPayment: " + KClassUtil.toString(payment);
                    } else {
                        Date nextAttempt = KDateUtil.addDays(new Date(), 3);
                        invoice.setNextPaymentAttemptDate(nextAttempt);
                        getInvoiceService().updateInvoice(invoice);

                        processPaymentFailure(invoice, payment);
                    }

                    // sleep 2s to throttle payment processor calls 
                    KSystemUtil.sleep(2000L);
                } catch (Exception e) {
                    String s = "Cart: " + KClassUtil.toString(cart);
                    getSystemService().alert("SYSTEM ERROR: Auto Renew Subscription", s, e);
                }
            }
        }

        summary = "Total Payments: " + autoRenewTotal
                + "\nSkipped Accounts: " + KClassUtil.toString(skipList)
                + "\n" + summary;

        if (autoRenewTotal.compareTo(new BigDecimal(0))> 0) {
            getSystemService().alert("Auto-Renew Summary", summary);
        }

        logger.info("Auto-Renew Summary:\n" + summary);
    }



    private void processPaymentFailure(INVOICE invoice, PAYMENT payment) {
        PAYMENT.Status status = null;

        if (payment != null) {
            status = payment.getStatus();
        }

        String s = "\nInvoice: " + KClassUtil.toString(invoice)
                + "\n\nPayment: " + KClassUtil.toString(payment);

        getSystemService().alert("PAYMENT ERROR: Auto Renew Subscription", s);

        if (status != null && status == PAYMENT.Status.CARD_MISSING) {
            try {
                sendInvalidCardEmail(invoice);
            } catch (Exception e) {
                String body = "\ninvoice: " + KClassUtil.toString(invoice);
                getSystemService().alert("Email Error: Invalid Card Notice", body, e);
            }
        }
    }



    @Override
    public void retryFailedPayments() {
        String summary = "";
        BigDecimal autoRenewTotal = new BigDecimal(0);
        List<Long> skipList = new ArrayList<>();

        List<INVOICE> invoiceList = getInvoiceService().fetchAllOpen();

        for (INVOICE invoice : invoiceList) {
            Integer attemptCount = invoice.getPaymentAttemptCount();

            if (attemptCount != null && attemptCount >= 3) {
                String notes = "Max payment attempts reached";

                getInvoiceService().closeInvoice(invoice, false, null, null, null, notes);

                getSystemService().alert("Failed Invoice", KJsonUtil.toJson(invoice, 255, 5000));

                continue;
            }

            Date now = new Date();

            Date lastAttempt = invoice.getLastPaymentAttemptDate();

            if (lastAttempt == null) {
                getSystemService().alert(
                        "Possible Invoice Sync Error",
                        "retryFailedPayments: processing invoice with lastAttempt null: "
                                + KJsonUtil.toJson(invoice, 255, 5000)
                );

                continue;
            }

            Date nextAttempt = invoice.getNextPaymentAttemptDate();

            if (nextAttempt != null && now.before(nextAttempt)) continue;

            if (nextAttempt == null) {
                if (KDateUtil.diffDays(now, lastAttempt) < 3) continue;
            }

            // at this point we should have an invoice that has been charged
            // fewer than 3 times and which was last charged at least 3 days 
            // ago.

            // make sure account is still active
            ACCOUNT account = getAccountService().fetchById(invoice.getAccountId());

            if (account.getDeletedDate() != null) {
                String notes = "User account is not active";
                getInvoiceService().closeInvoice(invoice, false, null, null, null, notes);
                getSystemService().alert("Failed Invoice: account has been retired", KJsonUtil.toJson(invoice));
                continue;
            }

            // only retry payment if account has a default CARD 
            List<PAYMENT> prevPayments = getPaymentService().fetchByInvoiceId(invoice.getId());

            PAYMENT.Type paymentType = null;

            if (prevPayments != null && prevPayments.size() >0) {
                PAYMENT prevPayment = prevPayments.get(0);
                paymentType = prevPayment.getType();
            }

            PAYMENT_ACCOUNT paymentAccount = getPaymentAccountService().fetchDefault(account.getId());

            PAYMENT payment = null;

            try {
                if (paymentType != null && paymentType == PAYMENT.Type.CARD) {
                    payment = charge(invoice, paymentAccount, null);
                }
            } catch (Exception e) {
                String s = "Invoice: " + KClassUtil.toString(invoice);
                getSystemService().alert("SYSTEM ERROR: Auto Renew Subscription", s, e);
            }

            if (payment != null && payment.isPaid()) {
                autoRenewTotal = autoRenewTotal.add(payment.getAmount());
                summary += "\n\nPayment: " + KClassUtil.toString(payment);
            } else {
                nextAttempt = KDateUtil.addDays(now, 3);
                invoice.setNextPaymentAttemptDate(nextAttempt);
                getInvoiceService().updateInvoice(invoice);
                processPaymentFailure(invoice, payment);
            }

            // sleep 5s to throttle payment processor calls
            KSystemUtil.sleep(5000L);
        }

        summary = "Total Payments: " + autoRenewTotal
                + "\nSkipped Invoices: " + KClassUtil.toString(skipList)
                + "\n" + summary;

        if (autoRenewTotal.compareTo(new BigDecimal(0))> 0) {
            getSystemService().alert("Retry Failed Payments", summary);
        }

        logger.info("Retry Failed Payments:\n" + summary);

    }






    // One step to add item to cart and record payment for it for it
    // map should contain at least:
    //	productId
    // 	paidAmount

    // NOTE: 
    //  store: apple | google
    //
    // 	for apple:
    //		processorRef = transactionId
    //
    //  for google:
    //		processorRef = orderId
    //		receipt = purchaseToken
    @Override
    public PAYMENT inAppPurchase(
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
    ) {

        if (productSkuId == null) {
            throw new KPaymentException("Payment.inAppPurchase: productSkuId is null.");
        }

        if (paidAmount == null) {
            throw new KPaymentException("Payment.inAppPurchase: paidAmount is null.");
        }

        paidAmount = paidAmount.setScale(2, BigDecimal.ROUND_HALF_UP);

        if (processorRef != null) {
            PAYMENT payment = getPaymentService().fetchByProcessRef(processorRef);

            // if we already have a processorRef, most likely we're processing a purchase
            // that was already made (typical in iOS apps).  do a sanity check then return the payment
            if (payment != null) {
                if (payment.getAmount().compareTo(paidAmount) != 0) {
                    getSystemService().alert("InAppPurchase Error: Payment amount mismatch", "Paid amount: " + paidAmount
                            + "\n\nExisting Payment: " + payment.toString());
                    throw new KPaymentException("InAppPurchase Error: Payment amount mismatch");
                }

                if (payment.getInvoiceId() == null) {
                    getSystemService().alert("InAppPurchase Error: Invoice not found for Payment", payment.toString());
                    throw new KPaymentException("InAppPurchase Error: Invoice not found for Payment");
                }

                if (!payment.getUserId().equals(userId)) {
                    getSystemService().alert("InAppPurchase Error: Payment user mismatch", "User ID: " + userId
                            + "\n\nExisting Payment: " + payment.toString());
                    throw new KPaymentException("InAppPurchase Error: Payment user mismatch");
                }

                INVOICE invoice = getInvoiceService().fetchById(payment.getInvoiceId());

                List<INVOICE_ITEM> items = getInvoiceItemService().fetchByInvoiceId(invoice.getId());

                boolean foundItem = false;

                for (INVOICE_ITEM item : items) {
                    if (item.getProductSkuId().equals(productSkuId)) {
                        foundItem = true;
                    }
                }

                if (!foundItem) {
                    getSystemService().alert("InAppPurchase Error: Product SKU not found for payment", "Product SKU ID: " + productSkuId
                            + "\n\nExisting Invoice Items: " + KJsonUtil.toJson(items));
                    throw new KPaymentException("InAppPurchase Error: product item not found for payment");
                }

                return payment;
            }

        }

        Long promoId = null;

        if (promoCode != null) {
            PROMO promo = getValidPromo(promoCode, userId, productSkuId);

            if (promo != null) {
                promoId = promo.getId();
            }
        }

        CART cart = getCartService().getCart(userId, client);

        CART_ITEM cartItem = getCartItemService().addCartItem(
                cart,
                userId,
                productSkuId,
                quantity,
                promoId
        );

        boolean google = false;
        boolean apple = false;

        if (store != null && store.equalsIgnoreCase("apple")) {
            apple = true;
        } else if (store != null && store.equalsIgnoreCase("google")) {
            google = true;
        }


        if (receipt != null && processorRef != null && store != null) {
            Date subEndDate = null;
            if (store.equalsIgnoreCase("apple")) {
                Map<String,Object> decodedReceipt = decodeAppStoreReceipt(receipt, processorRef, false);
                if (decodedReceipt != null) {
                    logger.debug("inAppPurchase: got receipt: " + KJsonUtil.toJson(decodedReceipt));
                    if (decodedReceipt.get("expires_date_ms") != null) {
                        Long expiresDate = Long.valueOf(decodedReceipt.get("expires_date_ms").toString());
                        subEndDate = KDateUtil.getDate(expiresDate);
                    }
                }
            } else if (store.equalsIgnoreCase("google")) {
                String packageName = getGooglePlayPackageName();

                PRODUCT_SKU productSku = getProductSkuService().fetchById(productSkuId);

                String googleProductId = productSku.getSku();

                try {
                    PURCHASE purchase = getGooglePlayService().getSubscription(packageName, googleProductId, receipt);

                    if (purchase == null) {
                        logger.warn("Google Service subscription service returned null"
                                + "\ngoogle productId: " + googleProductId
                                + "\ngoogle token: " + receipt);
                    } else {
                        subEndDate = purchase.getExpirationDate();
                    }
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }

            if (subEndDate != null) {
                logger.debug("inAppPurchase: setting subscription end date to: " + subEndDate);
                cartItem.setSubscriptionEndDate(subEndDate);
                getCartItemService().updateCartItem(cartItem);
            }
        }

        BigDecimal processorFee = null;

        PAYMENT.Type paymentType = null;

        if (apple) {
            //BigDecimal percent = getAppStorePercentFee();
            BigDecimal percent = new BigDecimal(0.3);
            processorFee = cart.getTotal().multiply(percent);
            paymentType = PAYMENT.Type.APPLE_APPSTORE;
        } else if (google) {
            //BigDecimal percent = getGooglePlayPercentFee();
            BigDecimal percent = new BigDecimal(0.3);
            processorFee = cart.getTotal().multiply(percent);
            paymentType = PAYMENT.Type.GOOGLE_PLAY;
        }

        processorFee = processorFee.setScale(2, BigDecimal.ROUND_HALF_UP);
        //paidAmount = paidAmount.setScale(2, BigDecimal.ROUND_HALF_UP);

        PAYMENT payment = externalCharge(cart, paymentType, paidAmount,
                processorRef, processorFee, client);

        // FIXME: all receipt processing should be moved to CommerceService 
        // Save the receipt since it can be used to periodically query Apple AppStore
        // for updates to the account.  (For example, will allow us to know if they 
        // cancelled a subscription.
        if (receipt != null) {
            payment.setProcessorReceipt(receipt);
            getPaymentService().update(payment);
        }

        return payment;
    }





    // Since in app review mode, test receipts are used, the docs say:
    // 		When validating receipts on your server, your server needs to be able to handle 
    //		a production-signed app getting its receipts from Apple’s test environment. 
    //		The recommended approach is for your production server to always validate receipts 
    //		against the production App Store first. If validation fails with the error code 
    //		“Sandbox receipt used in production”, validate against the test environment instead.
    // https://developer.apple.com/library/ios/documentation/NetworkingInternet/Conceptual/StoreKitGuide/Chapters/AppReview.html#//apple_ref/doc/uid/TP40008267-CH10-SW1
    @SuppressWarnings("unchecked")
    private Map<String,Object> decodeAppStoreReceipt(String encodedReceipt, String transactionId, boolean sandbox) {
        Map<String,Object> result = null;

        String url = getAppleVerifyReceiptUrl();

        if (sandbox) {
            url = getAppleVerifyReceiptSandboxUrl();
        }

        String password = getAppleAppSharedSecret();

        Map<String,String> request = new HashMap<String,String>();
        request.put("receipt-data", encodedReceipt);
        request.put("password", password);

        try {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            template.getMessageConverters().add(new StringHttpMessageConverter());
            template.getMessageConverters().add(new ByteArrayHttpMessageConverter());
            String s = template.postForObject(url, request, String.class);
            logger.debug("######\ndecodeAppStoreReceipt: result:\n#####\n" + s);

            if (s != null) {
                result = KJsonUtil.toMap(s);
            }

            logger.debug("######\nparsed result: result:\n#####\n" + KJsonUtil.toJson(result));

            Integer status = Integer.valueOf(result.get("status").toString());
            
            /*
            https://developer.apple.com/library/ios/releasenotes/General/ValidateAppStoreReceipt/Chapters/ValidateRemotely.html#//apple_ref/doc/uid/TP40010573-CH104-SW1
            0
            	Receipt is valid.
            21000
            	The App Store could not read the JSON object you provided.
            21002
            	The data in the receipt-data property was malformed or missing.
            21003
            	The receipt could not be authenticated.
            21004
            	The shared secret you provided does not match the shared secret on file for your account.
            	Only returned for iOS 6 style transaction receipts for auto-renewable subscriptions.
            21005
            	The receipt server is not currently available.
            21006
            	This receipt is valid but the subscription has expired. When this status code is returned to your server, the receipt data is also decoded and returned as part of the response.
            	Only returned for iOS 6 style transaction receipts for auto-renewable subscriptions.
            21007
            	This receipt is from the test environment, but it was sent to the production environment for verification. Send it to the test environment instead.
            21008
            	This receipt is from the production environment, but it was sent to the test environment for verification. Send it to the production environment instead.
            */

            if (status == 21007) {
                return decodeAppStoreReceipt(encodedReceipt, transactionId, true);
            }

            if (status == 21008) {
                return decodeAppStoreReceipt(encodedReceipt, transactionId, false);
            }

            if (status == 0) {
                Map<String,Object> receipt = (Map<String,Object>)result.get("receipt");
                List<Map<String,Object>> inApp = (List<Map<String,Object>>)receipt.get("in_app");

                logger.debug("######\nin_app result:\n#####\n" + KJsonUtil.toJson(inApp));

                for (Map<String,Object> purchase : inApp) {
                    logger.debug("\n\n*************\nchecking receipt:\n***********\n" + KJsonUtil.toJson(purchase));

                    String txnId = (String) purchase.get("transaction_id");

                    logger.debug("\nchecking txnId: [" + txnId + "] = [" + transactionId + "]\n\n");

                    if (txnId != null && transactionId.equals(txnId)) {
                        return purchase;
                    }
                }

                return null;
            }

            logger.error("decodeAppStoreReceipt: Error: " + status);

            return null;
        } catch (RestClientException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    // Return null if promoCode does not exist or if the code violates
    // some type of business rule (e.g. user already previously used the promoCode)
    private PROMO getValidPromo(String promoCode, Long userId, Long productSkuId) {
        if (promoCode == null) return null;

        ACCOUNT account = getAccountService().fetchByOwnerId(userId);

        return getPromoService().fetchAndValidateByPromoCode(
                promoCode, account.getId(),
                null,
                null,
                productSkuId);
    }
}
