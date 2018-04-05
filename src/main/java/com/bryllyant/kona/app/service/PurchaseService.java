/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Purchase;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.Date;
import java.util.List;

public interface PurchaseService extends KService, KEntityService<Purchase> {
	String SERVICE_PATH = "rpc/PurchaseService";

    List<Purchase> fetchByAccountIdAndProductId(Long accountId, Long productId);

    List<Purchase> fetchByAccountIdAndProductSkuId(Long accountId, Long productSkuId);

    Purchase fetchLastPurchaseByAccountIdAndProductSkuId(Long accountId, Long productSkuId);

    List<Purchase> fetchByProductId(Long productId);

    List<Purchase> fetchByProductSkuId(Long productSkuId);

    List<Purchase> fetchByParentId(Long parentId);

    List<Purchase> fetchByAccountId(Long accountId);

    List<Purchase> fetchSubscriptionsByExpirationDate(Date startDate, Date endDate, Boolean autoRenew);

    // fetch Purchase that will expire (autoRenew is off) in the next "days" days
    List<Purchase> fetchSubscriptionsPendingExpiration(int days);

    void remindSubscriptionsPendingExpiration(int days);

    void expireSubscriptions();

    Purchase savePromoSubscriptionPurchase(Long userId, Long productSkuId, Long promoId);
	
}
