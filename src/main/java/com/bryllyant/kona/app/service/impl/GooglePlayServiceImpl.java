/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.Payment;
import com.bryllyant.kona.app.entity.ProductSku;
import com.bryllyant.kona.app.entity.Purchase;
import com.bryllyant.kona.app.service.GooglePlayService;
import com.bryllyant.kona.app.service.InvoiceService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.app.service.PaymentService;
import com.bryllyant.kona.app.service.ProductSkuService;
import com.bryllyant.kona.app.service.PurchaseService;
import com.bryllyant.kona.encryption.KEncryptUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.util.Date;


@Service(GooglePlayService.SERVICE_PATH)
public class GooglePlayServiceImpl implements GooglePlayService {

    private static Logger logger = LoggerFactory.getLogger(GooglePlayServiceImpl.class);

    protected static AndroidPublisher androidPublisher = null;

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    ProductSkuService productSkuService;

    @Autowired
    InvoiceService invoiceService;

    protected String getGooglePlayPackageName() {
        return null;
    }

    protected String getGooglePlayAppName()  {
        return null;
    }

    protected String getGooglePlayServiceAccountPrivateKey() {
        return null;
    }

    protected String getGooglePlayServiceAccountEmail() {
        return null;
    }



    protected AndroidPublisher getAndroidPublisher() {
        if (androidPublisher != null) {
            return androidPublisher;
        }

        try {
            //String serviceAccountEmail = getConfig(appId).getString("google.api.oauth.serviceAccountEmail");
            //String serviceAccountPrivateKey = getConfig(appId).getString("google.api.oauth.serviceAccountPrivateKey");
            //String appName = getConfig(appId).getString("google.api.client.applicationName");

            String serviceAccountEmail = getGooglePlayServiceAccountEmail();
            String serviceAccountPrivateKey = getGooglePlayServiceAccountPrivateKey();
            String appName = getGooglePlayAppName();


            logger.debug("Initializing GoogleAndroid Service:"
                    + "\nappName: [" + appName + "]"
                    + "\nserviceAccountEmail: [" + serviceAccountEmail + "]"
                    + "\nserviceAccountPrivateKey: [" + serviceAccountPrivateKey + "]"
            );

            //File keyFile = KFileUtil.writeTempFile(serviceAccountPrivateKey);

            // make sure key doesn't include any headers
            serviceAccountPrivateKey = serviceAccountPrivateKey.replaceAll(
                    "(-+BEGIN PRIVATE KEY-+\\r?\\n|-+END PRIVATE KEY-+\\r?\\n?)"
                    , "");

            PrivateKey privateKey = KEncryptUtil.loadPrivateKey(serviceAccountPrivateKey);

            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

            // service account credential (uncomment setServiceAccountUser for domain-wide delegation)
            GoogleCredential credential;
            credential = new GoogleCredential.Builder().setTransport(httpTransport)
                    .setJsonFactory(jsonFactory)
                    .setServiceAccountId(serviceAccountEmail)
                    .setServiceAccountScopes(AndroidPublisherScopes.all())
                    .setServiceAccountPrivateKey(privateKey)
                    //.setServiceAccountPrivateKeyFromP12File(keyFile)
                    // .setServiceAccountUser("user@example.com")
                    .build();

            // set up global AndroidPublisher instance
            androidPublisher = new AndroidPublisher.Builder(httpTransport, jsonFactory, credential)
                    .setApplicationName(appName).build();

            return androidPublisher;

        } catch (GeneralSecurityException | IOException e) {
            throw new KServiceException(e);
        }
    }



    private String getSubscriptionToken(Long accountId, Long productSkuId) {
        Purchase purchase = purchaseService.fetchLastPurchaseByAccountIdAndProductSkuId(accountId, productSkuId);

        if (purchase == null) {
            logger.warn("Purchase is null for accountId: " + accountId + "  productSkuId: " + productSkuId);
            return null;
        }

        if (purchase.getInvoiceId() == null) {
            logger.warn("No account invoiceId for subscriptionPurchaseId: " + purchase.getId());
            return null;
        }

        Invoice invoice = invoiceService.fetchById(purchase.getInvoiceId());

        if (invoice == null) {
            logger.warn("No invoice found for invoiceId: " + purchase.getInvoiceId());
            return null;
        }

        if (invoice.getPaymentRef() == null) {
            logger.warn("No invoice paymentRef found for invoiceId: " + purchase.getInvoiceId());
            return null;
        }

        Payment payment = paymentService.fetchByProcessRef(invoice.getPaymentRef());

        if (payment == null) {
            logger.warn("No payment found for paymentRef: " + invoice.getPaymentRef());
            return null;
        }

        if (payment.getType() != Payment.Type.GOOGLE_PLAY) {
            logger.warn("PaymentType for payment is not GOOGLE_PLAY.  type: " + payment.getType());
            return null;
        }

        String token = payment.getProcessorReceipt();

        if (token == null) {
            logger.warn("No processor receipt for paymentId: " + payment.getId());
            return null;
        }

        return token;
    }




    @Override
    public Purchase getSubscription(Long accountId, Long productSkuId) throws IOException {
        String token = getSubscriptionToken(accountId, productSkuId);

        if (token == null) {
            logger.warn("No subscription token found for accountId: " + accountId);
            return null;
        }

        return getSubscription(productSkuId, token);
    }



    @Override
    public void cancelSubscription(Long accountId, Long productSkuId) throws IOException {
        String token = getSubscriptionToken(accountId, productSkuId);

        if (token == null) {
            logger.warn("No subscription token found for accountId: " + accountId);
            return;
        }

        cancelSubscription(productSkuId, token);
    }



    @Override
    public Purchase getSubscription(Long productSkuId, String token) throws IOException {
        ProductSku productSku = productSkuService.fetchById(productSkuId);
        //String packageName = getConfig(product.getAppId()).getString("google.play.packageName");
        String packageName = getGooglePlayPackageName();
        String googleProductId = productSku.getSku();
        return getSubscription(packageName, googleProductId, token);
    }



    @Override
    public Purchase getSubscription(String packageName, String productId, String token) throws IOException {
        AndroidPublisher.Purchases.Subscriptions subs = getAndroidPublisher().purchases().subscriptions();
        AndroidPublisher.Purchases.Subscriptions.Get get = subs.get(packageName, productId, token);
        com.google.api.services.androidpublisher.model.SubscriptionPurchase result = get.execute();
        Purchase purchase = new Purchase();
        purchase.setEnabled(true);

        if (result.getAutoRenewing() != null) {
            purchase.setAutoRenew(result.getAutoRenewing());
        }

        if (result.getExpiryTimeMillis() != null) {
            Date expirationDate = new Date(result.getExpiryTimeMillis());
            purchase.setExpirationDate(expirationDate);
        }

        if (result.getStartTimeMillis() != null) {
            Date createdDate = new Date(result.getStartTimeMillis());
            purchase.setCreatedDate(createdDate);
        }

        purchase.setKind(result.getKind());
        return purchase;
    }



    @Override
    public void cancelSubscription(String packageName, String productId, String token) throws IOException {
        AndroidPublisher.Purchases.Subscriptions subs = getAndroidPublisher().purchases().subscriptions();
        AndroidPublisher.Purchases.Subscriptions.Cancel cancel = subs.cancel(packageName, productId, token);
        cancel.execute();
    }



    @Override
    public void cancelSubscription(Long productSkuId, String token) throws IOException {
        ProductSku productSku = productSkuService.fetchById(productSkuId);
        //String packageName = getConfig(product.getAppId()).getString("google.play.packageName");
        String packageName = getGooglePlayPackageName();
        String googleProductId = productSku.getSku();
        cancelSubscription(packageName, googleProductId, token);
    }

}
