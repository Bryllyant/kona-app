package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPurchase;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.Date;
import java.util.List;


public interface KPurchaseService<PURCHASE extends KPurchase> 
		extends KService, KEntityService<PURCHASE> {
    
	List<PURCHASE> fetchByAccountIdAndProductId(Long accountId, Long productId);

    List<PURCHASE> fetchByAccountIdAndProductSkuId(Long accountId, Long productSkuId);

    PURCHASE fetchLastPurchaseByAccountIdAndProductSkuId(Long accountId, Long productSkuId);

    List<PURCHASE> fetchByProductId(Long productId);

    List<PURCHASE> fetchByProductSkuId(Long productSkuId);

    List<PURCHASE> fetchByParentId(Long parentId);
    
	List<PURCHASE> fetchByAccountId(Long accountId);
    
	List<PURCHASE> fetchSubscriptionsByExpirationDate(Date startDate, Date endDate, Boolean autoRenew);
	
    // fetch Purchase that will expire (autoRenew is off) in the next "days" days
	List<PURCHASE> fetchSubscriptionsPendingExpiration(int days);
	
	void remindSubscriptionsPendingExpiration(int days);
	
	void expireSubscriptions();

    PURCHASE savePromoSubscriptionPurchase(Long userId, Long productSkuId, Long promoId);
}
       

   
