package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPromo;
import com.bryllyant.kona.app.entity.KPurchase;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractPurchaseService<
        PURCHASE extends KPurchase,
        PURCHASE_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<PURCHASE, PURCHASE_EXAMPLE>,
		PROMO extends KPromo,
		USER extends KUser>
		extends KAbstractService<PURCHASE,PURCHASE_EXAMPLE,MAPPER>
		implements KPurchaseService<PURCHASE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractPurchaseService.class);

	protected abstract PURCHASE getNewObject();
    
	protected abstract <S extends KPromoService<PROMO>> S getPromoService();
    
	protected abstract <S extends KUserService<USER>> S getUserService();
    
	protected abstract void sendPendingProductExpirationEmail(PURCHASE purchase, int days);



	@Override 
	public void validate(PURCHASE purchase) {
		if (purchase.getCreatedDate() == null) {
			purchase.setCreatedDate(new Date());
		}

		if (purchase.getUid() == null) {
			purchase.setUid(uuid());
		}
        
		purchase.setUpdatedDate(new Date());
	}
    


	@Override
	public PURCHASE update(PURCHASE purchase) {
		super.update(purchase);
		
		List<PURCHASE> children = fetchByParentId(purchase.getId());
        
		if (children != null && children.size()>0) {
			for (PURCHASE child : children) {
				child.setEnabled(purchase.isEnabled());
				child.setExpirationDate(purchase.getExpirationDate());
				update(child);
			}
		}
		
		return purchase;
	}
    

	
	@Override 
	public List<PURCHASE> fetchSubscriptionsByExpirationDate(Date startDate, Date endDate, Boolean autoRenew) {
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
        
		return fetchByCriteria(0, 99999, null, filter, false);
	}
    


	private List<PURCHASE> fetchExpired() {
		Date now = new Date();
        
        // .andExpirationDateIsNotNull()
		Map<String,Object> filter = KMyBatisUtil.createFilter("!expirationDate", null);
        
		// .andExpirationDateLessThanOrEqualTo(now);
		filter.put("<=expirationDate", now);
        
		return fetchByCriteria(0, 99999, null, filter, false);
	}

    @Override
    public List<PURCHASE> fetchByAccountIdAndProductId(Long accountId, Long productId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
        filter.put("productId", productId);
        return fetchByCriteria(filter);
    }

	@Override
	public List<PURCHASE> fetchByAccountIdAndProductSkuId(Long accountId, Long productSkuId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
		filter.put("productSkuId", productSkuId);
		return fetchByCriteria(filter);
	}
    

	@Override
	public List<PURCHASE> fetchByAccountId(Long accountId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("accountId", accountId);
		return fetchByCriteria(filter);
	}

    @Override
    public List<PURCHASE> fetchByProductId(Long productId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("productId", productId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<PURCHASE> fetchByProductSkuId(Long productSkuId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("productSkuId", productSkuId);
        return fetchByCriteria(filter);
    }

	@Override
	public List<PURCHASE> fetchByParentId(Long parentId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("parentId", parentId);
        return fetchByCriteria(filter);
	}
    
	@Override
	public void expireSubscriptions() {
		List<PURCHASE> result = fetchExpired();

		for (PURCHASE purchase : result) {
			logger.debug("expireProducts: removing Purchase:\n" + purchase);
			purchase.setAutoRenew(false);
			purchase.setExpirationDate(new Date());
			update(purchase);
		}
	}
    
	@Override
	public List<PURCHASE> fetchSubscriptionsPendingExpiration(int days) {
		Date startDate = new Date();
		Date endDate = KDateUtil.addDays(startDate, days);
		return fetchSubscriptionsByExpirationDate(startDate, endDate, false); 
	}
    
	@Override
	public void remindSubscriptionsPendingExpiration(int days) {
		List<PURCHASE> purchaseList = fetchSubscriptionsPendingExpiration(days);

		for (PURCHASE purchase : purchaseList) {
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
    public PURCHASE fetchLastPurchaseByAccountIdAndProductSkuId(Long accountId, Long productSkuId) {
        PURCHASE lastPurchase = null;

        List<PURCHASE> purchases = fetchByAccountIdAndProductSkuId(accountId, productSkuId);

        if (purchases != null && purchases.size() > 0) {
            purchases.sort((PURCHASE a, PURCHASE b) -> {
                Long diff = b.getCreatedDate().getTime() - a.getCreatedDate().getTime();
                return diff.intValue();
            });

            lastPurchase = purchases.get(0);
        }

        return lastPurchase;
    }
    

	@Override
	public PURCHASE savePromoSubscriptionPurchase(Long userId, Long productSkuId, Long promoId) {
		USER user = getUserService().fetchById(userId);

	    PURCHASE purchase = getNewObject();
		purchase.setAccountId(user.getAccountId());
		purchase.setUserId(userId);
		purchase.setProductSkuId(productSkuId);
		purchase.setCreatedDate(new Date());

		purchase.setPromoId(promoId);
		purchase.setAutoRenew(false);
		purchase.setEnabled(true);

		if (promoId != null) {
            PROMO promo = getPromoService().fetchById(promoId);

            Integer days = promo.getSubscriptionDays();

            if (days != null && days >= 0) {
                PURCHASE lastPurchase = fetchLastPurchaseByAccountIdAndProductSkuId(user.getAccountId(), productSkuId);

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
