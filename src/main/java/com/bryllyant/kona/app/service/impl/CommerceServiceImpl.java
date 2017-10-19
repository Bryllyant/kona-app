/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.InvoiceItem;
import com.bryllyant.kona.app.entity.Payment;
import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.app.entity.Product;
import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.app.entity.Purchase;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.CartItemService;
import com.bryllyant.kona.app.service.CartService;
import com.bryllyant.kona.app.service.CommerceService;
import com.bryllyant.kona.app.service.GooglePlayService;
import com.bryllyant.kona.app.service.InvoiceItemService;
import com.bryllyant.kona.app.service.InvoiceService;
import com.bryllyant.kona.app.service.KAbstractCommerceService;
import com.bryllyant.kona.app.service.KEmailException;
import com.bryllyant.kona.app.service.PaymentAccountService;
import com.bryllyant.kona.app.service.PaymentService;
import com.bryllyant.kona.app.service.ProductService;
import com.bryllyant.kona.app.service.PromoService;
import com.bryllyant.kona.app.service.PurchaseService;
import com.bryllyant.kona.app.service.StripeService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service(CommerceService.SERVICE_PATH)
public class CommerceServiceImpl 
        extends KAbstractCommerceService<App,
                                        User,
                                        File,
                                        Account,
                                        Cart,
                                        CartItem,
                                        Invoice,
                                        InvoiceItem,
                                        Product,
                                        Promo,
                                        Payment,
                                        PaymentAccount,
                                        Purchase> 
        implements CommerceService {
    
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
    ProductService productService;
    
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
    SystemService system;

    @Autowired
    PaymentAccountService paymentAccountService;

    // ----------------------------------------------------------------------------

    @Override
    protected Payment getNewPaymentObject() {
        return new Payment();
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    protected Purchase getNewPurchaseObject() {
        return new Purchase();
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    protected String getGooglePlayPackageName(Long appId) {
        return null;
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    protected String getAppleVerifyReceiptUrl(Long appId) {
        return null;
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    protected String getAppleVerifyReceiptSandboxUrl(Long appId) {
        return null;
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    protected String getAppleAppSharedSecret(Long appId) {
        return null;
    }
    
    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected PurchaseService getPurchaseService() {
        return purchaseService;
    }
    
    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected PromoService getPromoService() {
        return promoService;
    }
    
    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected PaymentService getPaymentService() {
        return paymentService;
    }

    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected PaymentAccountService getPaymentAccountService() {
        return paymentAccountService;
    } 

    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected ProductService getProductService() {
        return productService;
    }
    
    // ----------------------------------------------------------------------------
    
    @Override @SuppressWarnings("unchecked")
    protected InvoiceItemService getInvoiceItemService() {
        return invoiceItemService;
    }
    
    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected InvoiceService getInvoiceService() {
        return invoiceService;
    }
    
    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected CartService getCartService() {
        return cartService;
    }
    
    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected CartItemService getCartItemService() {
        return cartItemService;
    }
    
    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }
    
    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected AccountService getAccountService() {
        return accountService;
    }
    
    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected GooglePlayService getGooglePlayService() {
        return googlePlayService;
    }
    
    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected StripeService getStripeService() {
        return stripeService;
    }
    
    // ----------------------------------------------------------------------------

    @Override @SuppressWarnings("unchecked")
    protected SystemService getSystemService() {
        return system;
    }
    
    
    // ----------------------------------------------------------------------------
    
    @Override
    protected void sendReceiptEmail(Invoice invoice, boolean externalPayment) {
        User user = userService.fetchById(invoice.getUserId());
        
        App app = null;
        
        if (invoice.getAppId() != null) {
            app = appService.fetchById(invoice.getAppId());
        } else {
            app = system.getSystemApp();
        }

        try {
            String templateName = "email.templates.sales.paymentReceipt";
            
            String from = config.getString("system.mail.from");
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

            system.sendEmail(templateName, params, subject, from, replyTo, to, null);
        } catch (KEmailException e) {
            logger.error("Error sending email: " + e.getMessage());
        }
        
    }
    
    // ----------------------------------------------------------------------------
    
    @Override
    protected void sendInvalidCardEmail(Invoice invoice) {
        User user = userService.fetchById(invoice.getUserId());
        
        App app = null;
        
        if (invoice.getAppId() != null) {
            app = appService.fetchById(invoice.getAppId());
        } else {
            app = system.getSystemApp();
        }
        
        try {
            String templateName = "email.templates.sales.invalidCard";
            
            String from = config.getString("system.mail.from");
            String to = user.getEmail();
            String replyTo = from;
            
            String subject = "Payment Error";
            subject = "[" + app.getName() + "] " + subject;

            Map<String,Object> params = new HashMap<String,Object>();

            params.put("user", user);
            params.put("invoice", invoice);
            params.put("itemList", invoiceItemService.getInvoiceItemList(invoice));

            system.sendEmail(templateName, params, subject, from, replyTo, to, null);
        } catch (KEmailException e) {
            logger.error("Error sending email: " + e.getMessage(), e);
        }
    }
}
