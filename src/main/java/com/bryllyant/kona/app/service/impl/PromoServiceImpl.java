/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PromoMapper;
import com.bryllyant.kona.app.entity.ProductSku;
import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.app.entity.PromoCode;
import com.bryllyant.kona.app.entity.PromoExample;
import com.bryllyant.kona.app.entity.PromoProduct;
import com.bryllyant.kona.app.entity.Purchase;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.ProductSkuService;
import com.bryllyant.kona.app.service.PromoCodeService;
import com.bryllyant.kona.app.service.PromoProductService;
import com.bryllyant.kona.app.service.PromoService;
import com.bryllyant.kona.app.service.PurchaseService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(PromoService.SERVICE_PATH)
public class PromoServiceImpl extends KAbstractService<Promo,PromoExample,PromoMapper>
		implements PromoService {
	
	private static Logger logger = LoggerFactory.getLogger(PromoServiceImpl.class);
    
    @Autowired
    private PromoMapper promoMapper;
    
    @Autowired
    PurchaseService purchaseService;

    @Autowired
    PromoCodeService promoCodeService;

    @Autowired
    PromoProductService promoProductService;

    @Autowired
    ProductSkuService productSkuService;


    @Override @SuppressWarnings("unchecked")
    protected PromoMapper getMapper() {
        return promoMapper;
    }
    

    @Override
    public void validate(Promo promo) {
        if (promo.getCreatedDate() == null) {
            promo.setCreatedDate(new Date());
        }

        if (promo.getUid() == null) {
            promo.setUid(uuid());
        }

        promo.setUpdatedDate(new Date());
    }


    @Override
    public Promo fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public Promo fetchByPromoCode(String promoCode) {
        Promo promo = null;

        PromoCode pc = promoCodeService.fetchByPromoCode(promoCode);

        if (pc != null) {
            promo = fetchById(pc.getPromoId());
        }

        return promo;
    }


    @Override
    public Promo fetchAndValidateByPromoCode(
            String promoCode,
            Long accountId,
            Long productCategoryId,
            Long productId,
            Long productSkuId
    ) {
        Promo promo = fetchByPromoCode(promoCode);

        if (promo == null) return null;

        if (!isPromoValid(promo, accountId, productCategoryId, productId, productSkuId)) {
            return null;
        }

        return promo;
    }

    @Override
    // rule variables: price,
    public boolean isPromoValid(
            Promo promo,
            Long accountId,
            Long productCategoryId,
            Long productId,
            Long productSkuId
    ) {
        if (!isValid(promo, accountId, productCategoryId, productId, productSkuId)) {
            return false;
        }

        if (productSkuId == null) {
            return true;
        }

        ProductSku productSku = productSkuService.fetchById(productSkuId);

        String rule = promo.getValidationRule();

        if (rule != null) {
            Map<String,Object> ruleVarMap = getRuleVarMap(productSku);

            if (!KValidator.matchRule(ruleVarMap, rule)) {
                logger.info("Invalid Promo: rule violation: "
                        + "\nrule: " + rule
                        + "\nvarMap: " + KJsonUtil.toJson(ruleVarMap));

                return false;
            }
        }

        return true;
    }


    private Map<String,Object> getRuleVarMap(ProductSku productSku) {
        Map<String,Object> varMap = new HashMap<>();
        varMap.put("productSku", productSku.getSku());
        varMap.put("price", productSku.getPrice());
        varMap.put("setupFee", productSku.getSetupFee());
        varMap.put("trialDays", productSku.getTrialDays());
        varMap.put("subscription", productSku.isSubscription());
        varMap.put("subscriptionDays", productSku.getSubscriptionDays());
        return varMap;
    }

    private boolean isValid(
            Promo promo,
            Long accountId,
            Long productCategoryId,
            Long productId,
            Long productSkuId
    ) {
        if (promo == null) {
            logger.info("Invalid Promo: promo is null");
            return false;
        }

        if (!promo.isEnabled()) {
            logger.info("Invalid Promo: promo is disabled: " + promo.getName());
            return false;
        }

        Date now = new Date();

        Date startDate = promo.getStartDate();

        if (startDate != null && now.before(startDate)) {
            logger.info("Invalid Promo: promo start date: " + startDate);
            return false;
        }

        Date endDate = promo.getEndDate();

        if (endDate != null && now.after(endDate)) {
            logger.info("Invalid Promo: promo expired: " + endDate);
            return false;
        }

        Integer useCount = promo.getUseCount();

        Integer maxUseCount = promo.getMaxUseCount();

        if (useCount != null && maxUseCount != null
                && useCount >= maxUseCount) {
            logger.info("Invalid Promo: max use count: " + maxUseCount);
            return false;
        }


        if (promo.getUsePerAccount() != null && accountId != null) {
            List<Purchase> purchases = purchaseService.fetchByAccountId(accountId);

            useCount = 0;

            for (Purchase purchase : purchases) {
                if (purchase.getPromoId() != null && purchase.getPromoId().equals(promo.getId())) {
                    useCount += 1;
                }
            }

            if (useCount >= promo.getUsePerAccount()) {
                logger.info("Invalid Promo: account reached max use count:\naccountId: " + accountId);
                return false;
            }
        }


        List<PromoProduct> promoProducts = promoProductService.fetchByPromoId(promo.getId());

        // If no results then promo applies to all products
        if (promoProducts == null || promoProducts.size() == 0) {
            return true;
        }

        if (productCategoryId != null) {
            boolean enabled = false;
            for (PromoProduct promoProduct : promoProducts) {
                if (promoProduct.getProductCategoryId() != null && promoProduct.getProductCategoryId().equals(productCategoryId)) {
                    enabled = true;
                }
            }
            if (!enabled) return false;
        }

        if (productId != null) {
            boolean enabled = false;
            for (PromoProduct promoProduct : promoProducts) {
                if (promoProduct.getProductId() != null && promoProduct.getProductId().equals(productId)) {
                    enabled = true;
                }
            }
            if (!enabled) return false;
        }

        if (productSkuId != null) {
            boolean enabled = false;
            for (PromoProduct promoProduct : promoProducts) {
                if (promoProduct.getProductSkuId() != null && promoProduct.getProductSkuId().equals(productSkuId)) {
                    enabled = true;
                }
            }
            if (!enabled) return false;
        }

        return true;
    }

}
