/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

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
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.ProductService;
import com.bryllyant.kona.app.service.PromoService;
import com.bryllyant.kona.app.service.PurchaseService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.Callback;
import com.bryllyant.kona.util.KDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(PurchaseService.SERVICE_PATH)
public class PurchaseServiceImpl
        extends KAbstractService<Purchase,PurchaseExample,PurchaseMapper>
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

        system.sendEmail(templateName, params, subject, from, replyTo, to, null, new Callback<Email>() {
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

    @Override
    public void validate(Purchase purchase) {
        if (purchase.getCreatedDate() == null) {
            purchase.setCreatedDate(new Date());
        }

        if (purchase.getUid() == null) {
            purchase.setUid(uuid());
        }

        purchase.setUpdatedDate(new Date());
    }



    @Override
    public Purchase update(Purchase purchase) {
        super.update(purchase);

        List<Purchase> children = fetchByParentId(purchase.getId());

        if (children != null && children.size()>0) {
            for (Purchase child : children) {
                child.setEnabled(purchase.isEnabled());
                child.setExpirationDate(purchase.getExpirationDate());
                update(child);
            }
        }

        return purchase;
    }



    @Override
    public List<Purchase> fetchSubscriptionsByExpirationDate(Date startDate, Date endDate, Boolean autoRenew) {
        if (endDate == null) {
            endDate = startDate;
        }

        // .andExpirationDateIsNotNull()
        Map<String,Object> filter = KMyBatisUtil.createFilter("!expirationDate", null);

        // .andExpirationDateGreaterThanOrEqualTo(startDate)
        filter.put(">=expirationDate", startDate);

        // .andExpirationDateLessThanOrEqualTo(endDate);
        filter.put("<=expirationDate", endDate);

        if (autoRenew != null) {
            filter.put("autoRenew", autoRenew);
        }

        return fetchByCriteria(filter);
    }



    private List<Purchase> fetchExpired() {
        Date now = new Date();

        // .andExpirationDateIsNotNull()
        Map<String,Object> filter = KMyBatisUtil.createFilter("!expirationDate", null);

        // .andExpirationDateLessThanOrEqualTo(now);
        filter.put("<=expirationDate", now);

        return fetchByCriteria(filter);
    }

    @Override
    public List<Purchase> fetchByAccountIdAndProductId(Long accountId, Long productId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        filter.put("productId", productId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<Purchase> fetchByAccountIdAndProductSkuId(Long accountId, Long productSkuId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        filter.put("productSkuId", productSkuId);
        return fetchByCriteria(filter);
    }


    @Override
    public List<Purchase> fetchByAccountId(Long accountId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<Purchase> fetchByProductId(Long productId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("productId", productId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<Purchase> fetchByProductSkuId(Long productSkuId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("productSkuId", productSkuId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<Purchase> fetchByParentId(Long parentId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("parentId", parentId);
        return fetchByCriteria(filter);
    }

    @Override
    public void expireSubscriptions() {
        List<Purchase> result = fetchExpired();

        for (Purchase purchase : result) {
            logger.debug("expireProducts: removing Purchase:\n" + purchase);
            purchase.setAutoRenew(false);
            purchase.setExpirationDate(new Date());
            update(purchase);
        }
    }

    @Override
    public List<Purchase> fetchSubscriptionsPendingExpiration(int days) {
        Date startDate = new Date();
        Date endDate = KDateUtil.addDays(startDate, days);
        return fetchSubscriptionsByExpirationDate(startDate, endDate, false);
    }

    @Override
    public void remindSubscriptionsPendingExpiration(int days) {
        List<Purchase> purchaseList = fetchSubscriptionsPendingExpiration(days);

        for (Purchase purchase : purchaseList) {
            if (purchase.getProductId() == null || purchase.isAutoRenew()) {
                continue;
            }

            try {
                sendPendingProductExpirationEmail(purchase, days);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public Purchase fetchLastPurchaseByAccountIdAndProductSkuId(Long accountId, Long productSkuId) {
        Purchase lastPurchase = null;

        List<Purchase> purchases = fetchByAccountIdAndProductSkuId(accountId, productSkuId);

        if (purchases != null && purchases.size() > 0) {
            purchases.sort((Purchase a, Purchase b) -> {
                Long diff = b.getCreatedDate().getTime() - a.getCreatedDate().getTime();
                return diff.intValue();
            });

            lastPurchase = purchases.get(0);
        }

        return lastPurchase;
    }


    @Override
    public Purchase savePromoSubscriptionPurchase(Long userId, Long productSkuId, Long promoId) {
        User user = userService.fetchById(userId);

        Purchase purchase = new Purchase();
        purchase.setAccountId(user.getAccountId());
        purchase.setUserId(userId);
        purchase.setProductSkuId(productSkuId);
        purchase.setCreatedDate(new Date());

        purchase.setPromoId(promoId);
        purchase.setAutoRenew(false);
        purchase.setEnabled(true);

        if (promoId != null) {
            Promo promo = promoService.fetchById(promoId);

            Integer days = promo.getSubscriptionDays();

            if (days != null && days >= 0) {
                Purchase lastPurchase = fetchLastPurchaseByAccountIdAndProductSkuId(user.getAccountId(), productSkuId);

                Date startDate = new Date();

                if (lastPurchase != null
                        && lastPurchase.getExpirationDate() != null
                        && lastPurchase.getExpirationDate().getTime() > startDate.getTime()) {
                    startDate = lastPurchase.getExpirationDate();
                }

                Date expirationDate = KDateUtil.addDays(startDate, days);

                purchase.setExpirationDate(expirationDate);
            } else {
                purchase.setExpirationDate(null);
            }
        }

        return save(purchase);
    }

}
