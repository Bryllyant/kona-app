/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.InvoiceItem;
import com.bryllyant.kona.app.entity.Payment;
import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.app.entity.ProductSku;
import com.bryllyant.kona.app.entity.Purchase;
import com.bryllyant.kona.app.service.GooglePlayService;
import com.bryllyant.kona.app.service.InvoiceService;
import com.bryllyant.kona.app.service.KAbstractGooglePlayService;
import com.bryllyant.kona.app.service.PaymentService;
import com.bryllyant.kona.app.service.ProductSkuService;
import com.bryllyant.kona.app.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(GooglePlayService.SERVICE_PATH)
public class GooglePlayServiceImpl
        extends KAbstractGooglePlayService<
        Purchase,
        Cart,
        CartItem,
        Payment,
        PaymentAccount,
        Invoice,
        InvoiceItem,
        ProductSku>
        implements GooglePlayService {

    private static Logger logger = LoggerFactory.getLogger(GooglePlayServiceImpl.class);

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    ProductSkuService productSkuService;

    @Autowired
    InvoiceService invoiceService;

    @Override
    protected Purchase getNewPurchaseObject() {
        return new Purchase();
    }

    @Override
    protected String getGooglePlayPackageName() {
        return null;
    }

    @Override
    protected String getGooglePlayAppName()  {
        return null;
    }

    @Override
    protected String getGooglePlayServiceAccountPrivateKey() {
        return null;
    }

    @Override
    protected String getGooglePlayServiceAccountEmail() {
        return null;
    }

    @Override @SuppressWarnings("unchecked")
    protected PurchaseService getPurchaseService() {
        return purchaseService;
    }

    @Override @SuppressWarnings("unchecked")
    protected PaymentService getPaymentService() {
        return paymentService;
    }


    @Override @SuppressWarnings("unchecked")
    protected ProductSkuService getProductSkuService() {
        return productSkuService;
    }

    @Override @SuppressWarnings("unchecked")
    protected InvoiceService getInvoiceService() {
        return invoiceService;
    }
}
