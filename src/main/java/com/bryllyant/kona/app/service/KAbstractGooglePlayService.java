package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCart;
import com.bryllyant.kona.app.entity.KCartItem;
import com.bryllyant.kona.app.entity.KInvoice;
import com.bryllyant.kona.app.entity.KInvoiceItem;
import com.bryllyant.kona.app.entity.KPayment;
import com.bryllyant.kona.app.entity.KPaymentAccount;
import com.bryllyant.kona.app.entity.KProductSku;
import com.bryllyant.kona.app.entity.KPurchase;
import com.bryllyant.kona.encryption.KEncryptUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisher.Purchases.Subscriptions;
import com.google.api.services.androidpublisher.AndroidPublisher.Purchases.Subscriptions.Cancel;
import com.google.api.services.androidpublisher.AndroidPublisher.Purchases.Subscriptions.Get;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.util.Date;

public abstract class KAbstractGooglePlayService<
        PURCHASE extends KPurchase,
        CART extends KCart,
        CART_ITEM extends KCartItem,
        PAYMENT extends KPayment,
        PAYMENT_ACCOUNT extends KPaymentAccount,
        INVOICE extends KInvoice,
        INVOICE_ITEM extends KInvoiceItem,
        PRODUCT_SKU extends KProductSku>
        implements KGooglePlayService<PURCHASE> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractGooglePlayService.class);

    //protected static Map<Long,AndroidPublisher> androidPublisherCache = new HashMap<Long,AndroidPublisher>();

    protected static AndroidPublisher androidPublisher = null;

    protected abstract PURCHASE getNewPurchaseObject();

    protected abstract String getGooglePlayPackageName();

    protected abstract String getGooglePlayServiceAccountEmail();

    protected abstract String getGooglePlayServiceAccountPrivateKey();

    protected abstract String getGooglePlayAppName();

    protected abstract <S extends KInvoiceService<INVOICE,INVOICE_ITEM,CART,CART_ITEM>> S getInvoiceService();

    protected abstract <S extends KProductSkuService<PRODUCT_SKU>> S getProductSkuService();

    protected abstract <S extends KPurchaseService<PURCHASE>> S getPurchaseService();

    protected abstract <S extends KPaymentService<PAYMENT,PAYMENT_ACCOUNT,INVOICE>> S getPaymentService();



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
        PURCHASE purchase = getPurchaseService().fetchLastPurchaseByAccountIdAndProductSkuId(accountId, productSkuId);

        if (purchase == null) {
            logger.warn("Purchase is null for accountId: " + accountId + "  productSkuId: " + productSkuId);
            return null;
        }

        if (purchase.getInvoiceId() == null) {
            logger.warn("No account invoiceId for subscriptionPurchaseId: " + purchase.getId());
            return null;
        }

        INVOICE invoice = getInvoiceService().fetchById(purchase.getInvoiceId());

        if (invoice == null) {
            logger.warn("No invoice found for invoiceId: " + purchase.getInvoiceId());
            return null;
        }

        if (invoice.getPaymentRef() == null) {
            logger.warn("No invoice paymentRef found for invoiceId: " + purchase.getInvoiceId());
            return null;
        }

        PAYMENT payment = getPaymentService().fetchByProcessRef(invoice.getPaymentRef());

        if (payment == null) {
            logger.warn("No payment found for paymentRef: " + invoice.getPaymentRef());
            return null;
        }

        if (payment.getType() != PAYMENT.Type.GOOGLE_PLAY) {
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
    public PURCHASE getSubscription(Long accountId, Long productSkuId) throws IOException {
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
    public PURCHASE getSubscription(Long productSkuId, String token) throws IOException {
        PRODUCT_SKU productSku = getProductSkuService().fetchById(productSkuId);
        //String packageName = getConfig(product.getAppId()).getString("google.play.packageName");
        String packageName = getGooglePlayPackageName();
        String googleProductId = productSku.getSku();
        return getSubscription(packageName, googleProductId, token);
    }



    @Override
    public PURCHASE getSubscription(String packageName, String productId, String token) throws IOException {
        Subscriptions subs = getAndroidPublisher().purchases().subscriptions();
        Get get = subs.get(packageName, productId, token);
        com.google.api.services.androidpublisher.model.SubscriptionPurchase result = get.execute();
        PURCHASE purchase = getNewPurchaseObject();
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
        Subscriptions subs = getAndroidPublisher().purchases().subscriptions();
        Cancel cancel = subs.cancel(packageName, productId, token);
        cancel.execute();
    }



    @Override
    public void cancelSubscription(Long productSkuId, String token) throws IOException {
        PRODUCT_SKU productSku = getProductSkuService().fetchById(productSkuId);
        //String packageName = getConfig(product.getAppId()).getString("google.play.packageName");
        String packageName = getGooglePlayPackageName();
        String googleProductId = productSku.getSku();
        cancelSubscription(packageName, googleProductId, token);
    }

}

