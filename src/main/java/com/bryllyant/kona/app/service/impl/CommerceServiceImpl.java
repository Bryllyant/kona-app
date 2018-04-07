/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.InvoiceItem;
import com.bryllyant.kona.app.entity.Payment;
import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.app.entity.ProductSku;
import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.app.entity.PromoCode;
import com.bryllyant.kona.app.entity.Purchase;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.CampaignChannelService;
import com.bryllyant.kona.app.service.CartItemService;
import com.bryllyant.kona.app.service.CartService;
import com.bryllyant.kona.app.service.CommerceService;
import com.bryllyant.kona.app.service.GooglePlayService;
import com.bryllyant.kona.app.service.InvoiceItemService;
import com.bryllyant.kona.app.service.InvoiceService;
import com.bryllyant.kona.app.service.PaymentException;
import com.bryllyant.kona.app.service.PaymentAccountService;
import com.bryllyant.kona.app.service.PaymentService;
import com.bryllyant.kona.app.service.ProductSkuService;
import com.bryllyant.kona.app.service.PromoCodeService;
import com.bryllyant.kona.app.service.PromoService;
import com.bryllyant.kona.app.service.PurchaseService;
import com.bryllyant.kona.app.service.StripeService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.stripe.entity.KCharge;
import com.bryllyant.kona.stripe.entity.KStripeException;
import com.bryllyant.kona.util.Callback;
import com.bryllyant.kona.util.KClassUtil;
import com.bryllyant.kona.util.KDateUtil;
import com.bryllyant.kona.util.KJsonUtil;
import com.bryllyant.kona.util.KSystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(CommerceService.SERVICE_PATH)
public class CommerceServiceImpl implements CommerceService {
    
    private static Logger logger = LoggerFactory.getLogger(CommerceServiceImpl.class);
    
    @Autowired
    private KConfig config;
      
    @Autowired
    AppService appService;
       
    @Autowired
    AccountService accountService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    PurchaseService purchaseService;
    
    @Autowired
    PromoService promoService;
    
    @Autowired
    PaymentService paymentService;
    
    @Autowired
    ProductSkuService productSkuService;
    
    @Autowired
    CartService cartService;
    
    @Autowired
    CartItemService cartItemService;
    
    @Autowired
    InvoiceService invoiceService;
    
    @Autowired
    InvoiceItemService invoiceItemService;
  
    @Autowired
    GooglePlayService googlePlayService;
    
    @Autowired
    StripeService stripeService;
    
    @Autowired
    SystemService systemService;

    @Autowired
    PaymentAccountService paymentAccountService;

    @Autowired
    CampaignChannelService campaignChannelService;

    @Autowired
    PromoCodeService promoCodeService;

    protected String getGooglePlayPackageName() {
        return null;
    }

    protected String getAppleVerifyReceiptUrl() {
        return null;
    }

    protected String getAppleVerifyReceiptSandboxUrl() {
        return null;
    }

    protected String getAppleAppSharedSecret() {
        return null;
    }

    protected void sendReceiptEmail(Invoice invoice, boolean externalPayment) {
        User user = userService.fetchById(invoice.getUserId());
        
        App app = systemService.getSystemApp();;
        
            String templateName = "email.templates.sales.paymentReceipt";
            
            String from = config.getString("systemService.mail.from");
            String to = user.getEmail();
            String replyTo = from;
            
            String subject = "Payment Receipt";
            subject = "[" + app.getName() + "] " + subject;

            if (to == null) {
                logger.warn("Unable to send receipt for invoice: " + KJsonUtil.toJson(invoice));
                return;
            }

            Map<String,Object> params = new HashMap<String,Object>();
            params.put("user", user);
            params.put("invoice", invoice);
            params.put("itemList", invoiceItemService.getInvoiceItemList(invoice));
            params.put("externalPayment", externalPayment);

            systemService.sendEmail(templateName, params, subject, from, replyTo, to, null, new Callback<Email>() {
                @Override
                public void success(Email data) {

                }

                @Override
                public void error(Throwable t) {
                    logger.error("Error sending email: " + t.getMessage());

                }
            });

    }
    

    
    protected void sendInvalidCardEmail(Invoice invoice) {
        User user = userService.fetchById(invoice.getUserId());
        
        App app = systemService.getSystemApp();
        
            String templateName = "email.templates.sales.invalidCard";
            
            String from = config.getString("systemService.mail.from");
            String to = user.getEmail();
            String replyTo = from;
            
            String subject = "Payment Error";
            subject = "[" + app.getName() + "] " + subject;

            Map<String,Object> params = new HashMap<String,Object>();

            params.put("user", user);
            params.put("invoice", invoice);
            params.put("itemList", invoiceItemService.getInvoiceItemList(invoice));

            systemService.sendEmail(templateName, params, subject, from, replyTo, to, null, new Callback<Email>() {
                @Override
                public void success(Email data) {

                }

                @Override
                public void error(Throwable t) {

                    logger.error("Error sending email: " + t.getMessage(), t);
                }
            });
    }




    protected Account getAccountByUserId(Long userId) {
        if (userId == null) return null;

        Account account = null;

        User user = userService.fetchById(userId);

        if (user != null && user.getAccountId() != null) {
            account = accountService.fetchById(user.getAccountId());
        }

        return account;
    }


    private String getStripeUid(Account account) {
        PaymentAccount paymentAccount = paymentAccountService.fetchDefault(account.getId());
        return getStripeUid(paymentAccount);
    }


    private String getStripeUid(User user) {
        PaymentAccount paymentAccount = paymentAccountService.fetchDefault(user.getAccountId());
        return getStripeUid(paymentAccount);
    }

    private String getStripeUid(PaymentAccount paymentAccount) {
        String stripeUid = null;

        if (paymentAccount != null) {
            stripeUid = paymentAccount.getProviderCustomerId();
        }

        return stripeUid;
    }


    @Override
    public Payment charge(
            Cart cart,
            String cardToken,
            Boolean setDefaultCard,
            String paymentOption,
            KServiceClient client
    ) {
        Long userId = cart.getUserId();

        User user = userService.fetchById(userId);

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
                stripeService.addCustomerCard(getStripeUid(user), cardToken);
                cardToken = null;
            }
        }

        PaymentAccount paymentAccount = paymentAccountService.fetchDefault(user.getAccountId());

        return charge(cart, paymentAccount, cardToken, client);
    }



    @Override
    // NOTE, if a payment fails while charging a cart, the generated invoice
    // is immediately closed so a payment retry does not occur.  The assumption
    // is that carts are charged for live payments and invoices are charged
    // for recurring payments.
    public Payment charge(
            Cart cart,
            PaymentAccount paymentAccount,
            String cardToken,
            KServiceClient client
    ) {
        if (cart == null) {
            throw new PaymentException("Cart is empty");
        }

        boolean paymentRequired = false;

        if (cart.getTotal() != null &&
                cart.getTotal().compareTo(new BigDecimal(0))>0) {
            paymentRequired = true;
        }

        Invoice invoice = invoiceService.createInvoice(cart);

        Payment payment = charge(invoice, paymentAccount, cardToken, paymentRequired, client);

        if (paymentRequired && payment != null) {

            if (payment.getStatus() != Payment.Status.SUCCESS || !payment.isPaid()) {
                invoiceService.closeInvoice(invoice, false, null, null, null, null);
            }
        }

        return payment;
    }



    @Override
    public Payment charge(Invoice invoice, PaymentAccount paymentAccount, KServiceClient client) {
        return charge(invoice, paymentAccount, null, true, client);
    }



    @Override
    public Payment charge(
            KServiceClient client,
            PaymentAccount paymentAccount,
            String cardToken,
            String productName
    ) {
        logger.debug("CommerceService: client: " + client + " paymentAccount: " + paymentAccount + " productName: " + productName);

        Invoice invoice = invoiceService.createInvoice(paymentAccount.getAccountId(), productName, null);

        Payment payment = charge(invoice, paymentAccount, cardToken, true, client);

        if (payment == null
                || payment.getStatus() == null
                || !payment.getStatus().equals(Payment.Status.SUCCESS)) {

            String notes = payment.getProcessorError();

            invoiceService.closeInvoice(invoice, false, null, null, null, notes);
        }

        return payment;
    }

    Promo getPromoByCampaignChannelId(Long campaignChannelId) {
        if (campaignChannelId == null) return null;

        Promo promo = null;

        PromoCode promoCode = promoCodeService.fetchByCampaignChannelId(campaignChannelId);

        if (promoCode != null) {
            promo = promoService.fetchById(promoCode.getPromoId());
        }

        return promo;
    }


    @Override
    public Payment charge(
            Invoice invoice,
            PaymentAccount paymentAccount,
            String cardToken,
            boolean paymentRequired,
            KServiceClient client
    ) {
        Payment payment = null;

        Long promoId = null;

        Long campaignChannelId = getCampaignChannelIdByInvoice(invoice);

        Promo promo = getPromoByCampaignChannelId(campaignChannelId);

        if (promo != null) {
            promoId = promo.getId();
        }

        if (paymentRequired) {
            payment = processorCharge(invoice, paymentAccount, cardToken, client);
        } else {
            BigDecimal paidAmount = new BigDecimal(0);
            String notes = "Invoice paid internally. User not directly charged.";
            invoiceService.closeInvoice(invoice, true, paidAmount, "INTERNAL", null, notes);

            //FIXME: assume for now that if payment is not required
            // it is because of a promoCode

            Date now = new Date();

            payment = new Payment();
            payment.setCampaignChannelId(campaignChannelId);
            payment.setPromoId(promoId);
            payment.setType(Payment.Type.PROMO);
            payment.setStatus(Payment.Status.SUCCESS);
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
            payment = paymentService.add(payment);
        }

        if (!paymentRequired || payment.isPaid()) {
            //FIXME: if you purchase more than 1 subscription, then the
            // combined value of that purchase should be applied to your
            // account.

            List<InvoiceItem> invoiceItemList = invoiceItemService.getInvoiceItemList(invoice);
            logger.debug("invoice item list size: " + invoiceItemList.size());

            Long userId = invoice.getUserId();

            Account account = getAccountByUserId(userId);

            for (InvoiceItem item : invoiceItemList) {
                boolean autoRenew = false;

                Payment.Type paymentType = Payment.Type.PROMO;

                if (paymentRequired) {
                    autoRenew = true;
                    paymentType = Payment.Type.CARD;
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
                systemService.alert("Email Error: Payment Receipt", body, e);
            }
        }

        return payment;
    }



    private Purchase addPurchase(
            Account account,
            Invoice invoice,
            InvoiceItem item,
            Payment.Type paymentType,
            boolean autoRenew
    ) {
        ProductSku productSku = productSkuService.fetchById(item.getProductSkuId());

        Purchase purchase = new Purchase();
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

        return purchaseService.save(purchase);
    }



    @Override
    public Payment externalCharge(
            Cart cart,
            Payment.Type paymentType,
            BigDecimal paidAmount,
            String processorRef,
            BigDecimal processorFee,
            KServiceClient client
    ) {
        Invoice invoice = invoiceService.createInvoice(cart);

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
    public Payment externalCharge(
            Invoice invoice,
            Payment.Type paymentType,
            BigDecimal paidAmount,
            String processorRef,
            BigDecimal processorFee,
            KServiceClient client
    ) {
        Payment payment = null;

        if (processorRef == null) {
            processorRef = "EXTERNAL Payment";
        }

        if (processorFee == null) {
            processorFee = new BigDecimal(0);
        }

        if (paymentType == null) {
            paymentType = Payment.Type.EXTERNAL;
        }

        boolean paid = false;

        if (invoice.getAmountDue() != null && paidAmount != null &&
                invoice.getAmountDue().compareTo(paidAmount) == 0) {
            paid = true;
        }

        invoiceService.closeInvoice(
                invoice,
                paid,
                paidAmount,
                processorRef,
                null,
                null
        );


        Long promoId = null;

        Long campaignChannelId = getCampaignChannelIdByInvoice(invoice);

        Promo promo = getPromoByCampaignChannelId(campaignChannelId);

        if (promo != null) {
            promoId = promo.getId();
        }

        Date now = new Date();

        payment = new Payment();
        payment.setCampaignChannelId(campaignChannelId);
        payment.setPromoId(promoId);
        payment.setType(paymentType);
        payment.setStatus(Payment.Status.SUCCESS);
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
        payment = paymentService.add(payment);

        Long userId = invoice.getUserId();

        Account account = getAccountByUserId(userId);

        for (InvoiceItem item : invoiceItemService.getInvoiceItemList(invoice)) {
            addPurchase(account, invoice, item, paymentType, true);
            incPromoUseCount(promo);
        }

        try {
            sendReceiptEmail(invoice, true);
        } catch (Exception e) {
            String body = "\ninvoice: " + KClassUtil.toString(invoice);
            systemService.alert("Email Error: Payment Receipt", body, e);
        }

        return payment;
    }

    private void incPromoUseCount(Promo promo) {
        if (promo == null) return;

        Integer useCount = promo.getUseCount();

        if (useCount == null) useCount = 0;

        useCount += 1;

        promo.setUseCount(useCount);

        promoService.update(promo);
    }

    private Long getCampaignChannelIdByInvoice(Invoice invoice) {
        List<InvoiceItem> items = invoiceItemService.fetchByInvoiceId(invoice.getId());

        Long campaignChannelId = null;

        for (InvoiceItem item : items) {

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

    private Long getPromoIdByInvoice(Invoice invoice) {
        List<InvoiceItem> items = invoiceItemService.fetchByInvoiceId(invoice.getId());

        Long promoId = null;

        for (InvoiceItem item : items) {

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
    private Payment chargeCustomer(String cardToken, Invoice invoice)
    		throws PaymentException {
        Account account = getAccount(invoice.getAccountId());

        if (account == null) {
            throw new PaymentException(
                "Account not found for invoice: " + toString(invoice));
        }

        // see if we have a stripeUid for this customer
        if (cardToken == null && account.getStripeUid() == null) {
            throw new PaymentException(
                "StripeUid is null for accountId: " + account.getId());
        }

        BigDecimal total = invoice.getAmountDue();
        if (total == null || total.compareTo(new BigDecimal(0))<=0) {
            throw new PaymentException(
            		"Invoice amount due is null or negative: " + total);
        }

        return processorCharge(account, cardToken, invoice);
    }
    */



    @Override
    public String getCardLast4(PaymentAccount paymentAccount) {
        if (paymentAccount == null) return null;
        return paymentAccount.getCardLast4();
    }



    @Override
    public Payment processorCharge(
            Invoice invoice,
            PaymentAccount paymentAccount,
            String cardToken,
            KServiceClient client
    ) {

        Payment payment = null;
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

        invoiceService.updateInvoice(invoice);

        Long userId = invoice.getUserId();

        User user = userService.fetchById(userId);

        Account account = accountService.fetchById(user.getAccountId());

        Payment.Type type = Payment.Type.CARD;

        Payment.Status status = null;

        String error = null;

        if (account == null) {
            status = Payment.Status.ACCOUNT_INVALID;

            error = "Account not found for invoice: " + KClassUtil.toString(invoice);

            return paymentService.createPayment(
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
            status = Payment.Status.ACCOUNT_DISABLED;

            error = "Account disabled. accountId: " + account.getId();

            return paymentService.createPayment(
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
            status = Payment.Status.CARD_MISSING;

            error = "StripeUid is null for accountId: " + account.getId();

            return paymentService.createPayment(
                    type,
                    status,
                    cardToken,
                    paymentAccount,
                    invoice,
                    error,
                    client
            );

        } else if (cardToken == null) {
            String cardLast4 = stripeService.getPrimaryCardLast4(invoice.getUserId());

            if (cardLast4 == null) {
                status = Payment.Status.CARD_MISSING;

                error = "User does not have a default card. userId: " + userId;

                return paymentService.createPayment(
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
            status = Payment.Status.AMOUNT_INVALID;

            error = "Invoice amount due is null, zero or negative: " + total;

            return paymentService.createPayment(
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
                chargeItem = stripeService.chargeCustomer(
                        userId,
                        invoice.getAmountDue(),
                        chargeDescription,
                        receiptEmail,
                        metadata,
                        shipping
                );
            } else {
                chargeItem = stripeService.chargeCard(
                        cardToken,
                        invoice.getAmountDue(),
                        chargeDescription,
                        receiptEmail,
                        metadata,
                        shipping
                );
            }

            payment = paymentService.createPayment(
                    Payment.Type.CARD,
                    cardToken,
                    paymentAccount,
                    invoice,
                    chargeItem,
                    client
            );

        } catch (KStripeException e) {
            PaymentException ex = new PaymentException(e.getMessage(), e);

            // FIXME: parse exception and properly set PaymentStatus
            payment = paymentService.createPayment(
                    Payment.Type.CARD,
                    cardToken,
                    paymentAccount,
                    invoice,
                    ex,
                    client
            );
        }

        return payment;
    }



    private String getChargeDescription(Invoice invoice) {
        String username = userService.fetchById(invoice.getUserId()).getUsername();

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

        List<Purchase> subscriptionList = purchaseService.fetchSubscriptionsByExpirationDate(
                startDate, endDate, true);

        List<Long> accountIdList = new ArrayList<>();

        // only renew subscriptions for which PaymentType is CARD
        // assumes the account has a StripeID associated with a  default card.
        List<Purchase> subscriptionRenewList = new ArrayList<>();

        for (Purchase purchase : subscriptionList) {
            Payment.Type paymentType = purchase.getPaymentType();

            // make sure account is still active. if not expire this subscription
            Account account = accountService.fetchById(purchase.getAccountId());

            if (account.getDeletedDate() != null) {
                purchase.setEnabled(false);
                purchase.setAutoRenew(false);
                purchase.setExpirationDate(new Date());
                purchaseService.update(purchase);
                continue;
            }

            if (purchase.isEnabled() && purchase.isAutoRenew()
                    && purchase.getProductId() != null
                    && paymentType != null && paymentType == Payment.Type.CARD) {
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
            List<Invoice> invoiceList = invoiceService.fetchOpenByAccountId(accountId);

            if (invoiceList != null && invoiceList.size()>0) {
                skipList.add(accountId);
                continue;
            }

            Account account = accountService.fetchById(accountId);

            User user = userService.fetchById(account.getOwnerId());

            Long userId = user.getId();

            Cart cart = cartService.createCart(userId,null);

            for (Purchase purchase : subscriptionRenewList) {
                if (purchase.getAccountId().equals(accountId)) {
                    Long productSkuId = purchase.getProductSkuId();

                    if (productSkuId != null) {
                        cartItemService.addCartItem(cart, userId, productSkuId, null);
                    }
                }
            }

            if (cart.getTotal().compareTo(new BigDecimal(0)) > 0) {
                PaymentAccount paymentAccount = paymentAccountService.fetchDefault(account.getId());

                try {
                    Invoice invoice = invoiceService.createInvoice(cart);
                    Payment payment = charge(invoice, paymentAccount, null);

                    if (payment.isPaid()) {
                        autoRenewTotal = autoRenewTotal.add(payment.getAmount());
                        summary += "\n\nPayment: " + KClassUtil.toString(payment);
                    } else {
                        Date nextAttempt = KDateUtil.addDays(new Date(), 3);
                        invoice.setNextPaymentAttemptDate(nextAttempt);
                        invoiceService.updateInvoice(invoice);

                        processPaymentFailure(invoice, payment);
                    }

                    // sleep 2s to throttle payment processor calls
                    KSystemUtil.sleep(2000L);
                } catch (Exception e) {
                    String s = "Cart: " + KClassUtil.toString(cart);
                    systemService.alert("SYSTEM ERROR: Auto Renew Subscription", s, e);
                }
            }
        }

        summary = "Total Payments: " + autoRenewTotal
                + "\nSkipped Accounts: " + KClassUtil.toString(skipList)
                + "\n" + summary;

        if (autoRenewTotal.compareTo(new BigDecimal(0))> 0) {
            systemService.alert("Auto-Renew Summary", summary);
        }

        logger.info("Auto-Renew Summary:\n" + summary);
    }



    private void processPaymentFailure(Invoice invoice, Payment payment) {
        Payment.Status status = null;

        if (payment != null) {
            status = payment.getStatus();
        }

        String s = "\nInvoice: " + KClassUtil.toString(invoice)
                + "\n\nPayment: " + KClassUtil.toString(payment);

        systemService.alert("Payment ERROR: Auto Renew Subscription", s);

        if (status != null && status == Payment.Status.CARD_MISSING) {
            try {
                sendInvalidCardEmail(invoice);
            } catch (Exception e) {
                String body = "\ninvoice: " + KClassUtil.toString(invoice);
                systemService.alert("Email Error: Invalid Card Notice", body, e);
            }
        }
    }



    @Override
    public void retryFailedPayments() {
        String summary = "";
        BigDecimal autoRenewTotal = new BigDecimal(0);
        List<Long> skipList = new ArrayList<>();

        List<Invoice> invoiceList = invoiceService.fetchAllOpen();

        for (Invoice invoice : invoiceList) {
            Integer attemptCount = invoice.getPaymentAttemptCount();

            if (attemptCount != null && attemptCount >= 3) {
                String notes = "Max payment attempts reached";

                invoiceService.closeInvoice(invoice, false, null, null, null, notes);

                systemService.alert("Failed Invoice", KJsonUtil.toJson(invoice, 255, 5000));

                continue;
            }

            Date now = new Date();

            Date lastAttempt = invoice.getLastPaymentAttemptDate();

            if (lastAttempt == null) {
                systemService.alert(
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
            Account account = accountService.fetchById(invoice.getAccountId());

            if (account.getDeletedDate() != null) {
                String notes = "User account is not active";
                invoiceService.closeInvoice(invoice, false, null, null, null, notes);
                systemService.alert("Failed Invoice: account has been retired", KJsonUtil.toJson(invoice));
                continue;
            }

            // only retry payment if account has a default CARD
            List<Payment> prevPayments = paymentService.fetchByInvoiceId(invoice.getId());

            Payment.Type paymentType = null;

            if (prevPayments != null && prevPayments.size() >0) {
                Payment prevPayment = prevPayments.get(0);
                paymentType = prevPayment.getType();
            }

            PaymentAccount paymentAccount = paymentAccountService.fetchDefault(account.getId());

            Payment payment = null;

            try {
                if (paymentType != null && paymentType == Payment.Type.CARD) {
                    payment = charge(invoice, paymentAccount, null);
                }
            } catch (Exception e) {
                String s = "Invoice: " + KClassUtil.toString(invoice);
                systemService.alert("SYSTEM ERROR: Auto Renew Subscription", s, e);
            }

            if (payment != null && payment.isPaid()) {
                autoRenewTotal = autoRenewTotal.add(payment.getAmount());
                summary += "\n\nPayment: " + KClassUtil.toString(payment);
            } else {
                nextAttempt = KDateUtil.addDays(now, 3);
                invoice.setNextPaymentAttemptDate(nextAttempt);
                invoiceService.updateInvoice(invoice);
                processPaymentFailure(invoice, payment);
            }

            // sleep 5s to throttle payment processor calls
            KSystemUtil.sleep(5000L);
        }

        summary = "Total Payments: " + autoRenewTotal
                + "\nSkipped Invoices: " + KClassUtil.toString(skipList)
                + "\n" + summary;

        if (autoRenewTotal.compareTo(new BigDecimal(0))> 0) {
            systemService.alert("Retry Failed Payments", summary);
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
    public Payment inAppPurchase(
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
            throw new PaymentException("Payment.inAppPurchase: productSkuId is null.");
        }

        if (paidAmount == null) {
            throw new PaymentException("Payment.inAppPurchase: paidAmount is null.");
        }

        paidAmount = paidAmount.setScale(2, BigDecimal.ROUND_HALF_UP);

        if (processorRef != null) {
            Payment payment = paymentService.fetchByProcessRef(processorRef);

            // if we already have a processorRef, most likely we're processing a purchase
            // that was already made (typical in iOS apps).  do a sanity check then return the payment
            if (payment != null) {
                if (payment.getAmount().compareTo(paidAmount) != 0) {
                    systemService.alert("InAppPurchase Error: Payment amount mismatch", "Paid amount: " + paidAmount
                            + "\n\nExisting Payment: " + payment.toString());
                    throw new PaymentException("InAppPurchase Error: Payment amount mismatch");
                }

                if (payment.getInvoiceId() == null) {
                    systemService.alert("InAppPurchase Error: Invoice not found for Payment", payment.toString());
                    throw new PaymentException("InAppPurchase Error: Invoice not found for Payment");
                }

                if (!payment.getUserId().equals(userId)) {
                    systemService.alert("InAppPurchase Error: Payment user mismatch", "User ID: " + userId
                            + "\n\nExisting Payment: " + payment.toString());
                    throw new PaymentException("InAppPurchase Error: Payment user mismatch");
                }

                Invoice invoice = invoiceService.fetchById(payment.getInvoiceId());

                List<InvoiceItem> items = invoiceItemService.fetchByInvoiceId(invoice.getId());

                boolean foundItem = false;

                for (InvoiceItem item : items) {
                    if (item.getProductSkuId().equals(productSkuId)) {
                        foundItem = true;
                    }
                }

                if (!foundItem) {
                    systemService.alert("InAppPurchase Error: Product SKU not found for payment", "Product SKU ID: " + productSkuId
                            + "\n\nExisting Invoice Items: " + KJsonUtil.toJson(items));
                    throw new PaymentException("InAppPurchase Error: product item not found for payment");
                }

                return payment;
            }

        }

        Long promoId = null;

        if (promoCode != null) {
            Promo promo = getValidPromo(promoCode, userId, productSkuId);

            if (promo != null) {
                promoId = promo.getId();
            }
        }

        Cart cart = cartService.getCart(userId, client);

        CartItem cartItem = cartItemService.addCartItem(
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

                ProductSku productSku = productSkuService.fetchById(productSkuId);

                String googleProductId = productSku.getSku();

                try {
                    Purchase purchase = googlePlayService.getSubscription(packageName, googleProductId, receipt);

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
                cartItemService.updateCartItem(cartItem);
            }
        }

        BigDecimal processorFee = null;

        Payment.Type paymentType = null;

        if (apple) {
            //BigDecimal percent = getAppStorePercentFee();
            BigDecimal percent = new BigDecimal(0.3);
            processorFee = cart.getTotal().multiply(percent);
            paymentType = Payment.Type.APPLE_APPSTORE;
        } else if (google) {
            //BigDecimal percent = getGooglePlayPercentFee();
            BigDecimal percent = new BigDecimal(0.3);
            processorFee = cart.getTotal().multiply(percent);
            paymentType = Payment.Type.GOOGLE_PLAY;
        }

        processorFee = processorFee.setScale(2, BigDecimal.ROUND_HALF_UP);
        //paidAmount = paidAmount.setScale(2, BigDecimal.ROUND_HALF_UP);

        Payment payment = externalCharge(cart, paymentType, paidAmount,
                processorRef, processorFee, client);

        // FIXME: all receipt processing should be moved to CommerceService
        // Save the receipt since it can be used to periodically query Apple AppStore
        // for updates to the account.  (For example, will allow us to know if they
        // cancelled a subscription.
        if (receipt != null) {
            payment.setProcessorReceipt(receipt);
            paymentService.update(payment);
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
    private Promo getValidPromo(String promoCode, Long userId, Long productSkuId) {
        if (promoCode == null) return null;

        Account account = accountService.fetchByOwnerId(userId);

        return promoService.fetchAndValidateByPromoCode(
                promoCode, account.getId(),
                null,
                null,
                productSkuId);
    }
}
