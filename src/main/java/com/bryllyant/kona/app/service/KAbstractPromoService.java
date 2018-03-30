package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KProductSku;
import com.bryllyant.kona.app.entity.KPromo;
import com.bryllyant.kona.app.entity.KPromoCode;
import com.bryllyant.kona.app.entity.KPromoProduct;
import com.bryllyant.kona.app.entity.KPurchase;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class KAbstractPromoService<
        PROMO extends KPromo,
        PROMO_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<PROMO, PROMO_EXAMPLE>,
        PROMO_CODE extends KPromoCode,
        PROMO_PRODUCT extends KPromoProduct,
        PRODUCT_SKU extends KProductSku,
        PURCHASE extends KPurchase>
        extends KAbstractService<PROMO,PROMO_EXAMPLE,MAPPER>
        implements KPromoService<PROMO> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractPromoService.class);

    protected abstract <S extends KPurchaseService<PURCHASE>> S getPurchaseService();

    protected abstract <S extends KProductSkuService<PRODUCT_SKU>> S getProductSkuService();

    protected abstract <S extends KPromoCodeService<PROMO_CODE>> S getPromoCodeService();

    protected abstract <S extends KPromoProductService<PROMO_PRODUCT>> S getPromoProductService();

    @Override
    public void validate(PROMO promo) {
        if (promo.getCreatedDate() == null) {
            promo.setCreatedDate(new Date());
        }

        if (promo.getUid() == null) {
            promo.setUid(uuid());
        }

        promo.setUpdatedDate(new Date());
    }


    @Override
    public PROMO fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public PROMO fetchByPromoCode(String promoCode) {
        PROMO promo = null;

        PROMO_CODE pc = getPromoCodeService().fetchByPromoCode(promoCode);

        if (pc != null) {
            promo = fetchById(pc.getPromoId());
        }

        return promo;
    }


    @Override
    public PROMO fetchAndValidateByPromoCode(
            String promoCode,
            Long accountId,
            Long productCategoryId,
            Long productId,
            Long productSkuId
    ) {
        PROMO promo = fetchByPromoCode(promoCode);

        if (promo == null) return null;

        if (!isPromoValid(promo, accountId, productCategoryId, productId, productSkuId)) {
            return null;
        }

        return promo;
    }

    @Override
    // rule variables: price,
    public boolean isPromoValid(
            PROMO promo,
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

        PRODUCT_SKU productSku = getProductSkuService().fetchById(productSkuId);

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


    private Map<String,Object> getRuleVarMap(PRODUCT_SKU productSku) {
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
            PROMO promo,
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
            List<PURCHASE> purchases = getPurchaseService().fetchByAccountId(accountId);

            useCount = 0;

            for (PURCHASE purchase : purchases) {
                if (purchase.getPromoId() != null && purchase.getPromoId().equals(promo.getId())) {
                    useCount += 1;
                }
            }

            if (useCount >= promo.getUsePerAccount()) {
                logger.info("Invalid Promo: account reached max use count:\naccountId: " + accountId);
                return false;
            }
        }


        List<PROMO_PRODUCT> promoProducts = getPromoProductService().fetchByPromoId(promo.getId());

        // If no results then promo applies to all products
        if (promoProducts == null || promoProducts.size() == 0) {
            return true;
        }

        if (productCategoryId != null) {
            boolean enabled = false;
            for (PROMO_PRODUCT promoProduct : promoProducts) {
                if (promoProduct.getProductCategoryId() != null && promoProduct.getProductCategoryId().equals(productCategoryId)) {
                    enabled = true;
                }
            }
            if (!enabled) return false;
        }

        if (productId != null) {
            boolean enabled = false;
            for (PROMO_PRODUCT promoProduct : promoProducts) {
                if (promoProduct.getProductId() != null && promoProduct.getProductId().equals(productId)) {
                    enabled = true;
                }
            }
            if (!enabled) return false;
        }

        if (productSkuId != null) {
            boolean enabled = false;
            for (PROMO_PRODUCT promoProduct : promoProducts) {
                if (promoProduct.getProductSkuId() != null && promoProduct.getProductSkuId().equals(productSkuId)) {
                    enabled = true;
                }
            }
            if (!enabled) return false;
        }

        return true;
    }


}
