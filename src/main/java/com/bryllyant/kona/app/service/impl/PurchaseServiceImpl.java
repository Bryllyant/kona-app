/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.app.dao.PurchaseMapper;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.Product;
import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.app.entity.Purchase;
import com.bryllyant.kona.app.entity.PurchaseExample;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.AppService;
import com.bryllyant.kona.app.service.KAbstractPurchaseService;
import com.bryllyant.kona.app.service.ProductService;
import com.bryllyant.kona.app.service.PromoService;
import com.bryllyant.kona.app.service.PurchaseService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.app.util.KCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service(PurchaseService.SERVICE_PATH)
public class PurchaseServiceImpl
        extends KAbstractPurchaseService<
        Purchase,
        PurchaseExample,
        PurchaseMapper,
        Promo,
        User>
        implements PurchaseService {

    private static Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private KConfig config;

    @Autowired
    private AppService appService;

    @Autowired
    private SystemService system;

    @Autowired
    private PromoService promoService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;


    @Override @SuppressWarnings("unchecked")
    protected PurchaseMapper getMapper() {
        return purchaseMapper;
    }

    @Override
    protected Purchase getNewObject() {
        return new Purchase();
    }

    @Override @SuppressWarnings("unchecked")
    protected PromoService getPromoService() {
        return promoService;
    }

    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }

    @Override
    protected PurchaseExample getEntityExampleObject() { return new PurchaseExample(); }

    protected void sendPendingProductExpirationEmail(Purchase purchase, int days) {
        Account account = accountService.fetchById(purchase.getAccountId());

        User user = userService.fetchById(account.getOwnerId());

        Product product = productService.fetchById(purchase.getProductId());

        App app = appService.getSystemApp();

        String templateName = "email.templates.sales.pendingProductExpiration";

        String from = config.getString("system.mail.from");
        String to = user.getEmail();
        String replyTo = from;

        String subject = "Product Expiration";
        subject = "[" + app.getName() + "] " + subject;

        Map<String,Object> params = new HashMap<String,Object>();

        params.put("user", user);
        params.put("purchase", purchase);
        params.put("product", product);
        params.put("expireDays", days);

        system.sendEmail(templateName, params, subject, from, replyTo, to, null, new KCallback<Email>() {
            @Override
            public void success(Email data) {

            }

            @Override
            public void error(Throwable t) {
                logger.error("Error sending email: " + t.getMessage(), t);
                system.alert("salesAlert: sendMail() Error", t);
            }
        });

    }

}
