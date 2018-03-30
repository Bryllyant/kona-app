package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCart;
import com.bryllyant.kona.app.entity.KCartItem;
import com.bryllyant.kona.app.entity.KProductSku;
import com.bryllyant.kona.app.entity.KPromo;
import com.bryllyant.kona.app.entity.KPurchase;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.util.KDateUtil;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractCartItemService<
        CART_ITEM extends KCartItem,
        CART_ITEM_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<CART_ITEM, CART_ITEM_EXAMPLE>,
		CART extends KCart,
		USER extends KUser,
		PROMO extends KPromo,
        PRODUCT_SKU extends KProductSku,
		PURCHASE extends KPurchase>
		extends KAbstractService<CART_ITEM,CART_ITEM_EXAMPLE,MAPPER>
		implements KCartItemService<CART_ITEM,CART> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractCartItemService.class);

	protected abstract CART_ITEM getNewObject();

	protected abstract <S extends KUserService<USER>> S getUserService();
    
	protected abstract <S extends KCartService<CART>> S getCartService();
    
	protected abstract <S extends KPromoService<PROMO>> S getPromoService();
    
    protected abstract <S extends KProductSkuService<PRODUCT_SKU>> S getProductSkuService();

	protected abstract <S extends KPurchaseService<PURCHASE>> S getPurchaseService();
    

    @Override
    public void validate(CART_ITEM cartItem) {
        if (cartItem.getCreatedDate() == null) {
        	cartItem.setCreatedDate(new Date());
        }

		if (cartItem.getUid() == null) {
			cartItem.setUid(uuid());
		}
        
        cartItem.setUpdatedDate(new Date());
    }

    @Override
    public CART_ITEM fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<CART_ITEM> fetchByCartId(Long cartId) {
    	Map<String,Object> filter = KMyBatisUtil.createFilter("cartId", cartId);
    	return fetchByCriteria(0, 99999, null, filter, false);
    }
    

    @Override
    public List<CART_ITEM> getCartItemList(CART cart) {
        if (cart == null || cart.getId() == null) return null;
        return fetchByCartId(cart.getId());
    }
    

 
    @Override 
    public CART_ITEM updateCartItem(Long itemId) {
    	CART_ITEM item = fetchById(itemId);
        return updateCartItem(item.getCartId(), itemId);
    }
    

    
    @Override 
    public CART_ITEM updateCartItem(Long cartId, Long itemId) {
        CART cart = getCartService().fetchById(cartId);
        CART_ITEM item = fetchById(itemId);
        return updateCartItem(cart, item);
    }
    

    
    @Override
    public CART_ITEM updateCartItem(CART_ITEM item) {
        if (item == null) return null;
        CART cart = getCartService().fetchById(item.getCartId());
        return updateCartItem(cart, item);
    }
    
    @Override
    public CART_ITEM updateCartItem(CART cart, CART_ITEM item) {
        if (item == null) return null;
        item = update(item);
        getCartService().updateCartTotals(cart);
        return item;
    }
    
    @Override
    public CART_ITEM addCartItem(
            CART cart,
            Long userId,
            Long productSkuId,
            Long promoId
    ) {
        return addCartItem(cart, userId, productSkuId,1, promoId);
    }
    

    @Override
    public CART_ITEM addCartItem(
            KServiceClient client,
            Long userId,
            Long productSkuId,
            Integer quantity,
            Long promoId
    ) {
    	CART cart = getCartService().getCart(userId, client);
        return addCartItem(cart, userId, productSkuId, quantity, promoId);
    }
    

    @Override 
    public CART_ITEM addCartItem(
            CART cart,
            Long userId,
            Long productSkuId,
            Integer quantity,
            Long promoId
    ) {
        
        USER user = getUserService().fetchById(userId);
        
        PRODUCT_SKU productSku = getProductSkuService().fetchById(productSkuId);

        if (quantity == null) quantity = 1;
        
        // first make sure we're not adding a duplicate
        /*
        List<CartItem> itemList = cartItemService.fetchByCartId(cart.getId());
        if (itemList != null && itemList.size()>0) {
        	for (CartItem i : itemList) {
        		if (i.getUserId().equals(userId) 
        				&& i.getSubscriptionId().equals(productId)) {
        			}
        	}
        }
        */
        
        PROMO promo = getPromoService().fetchById(promoId);
        //promo = getPromoService().fetchSignupDefault();
        
        Date startDate = null;
        Date endDate = null;

        if (productSku.isSubscription()) {
            startDate = new Date();
            endDate = getSubscriptionEndDate(user, productSku, quantity, promo);
        }
        
        BigDecimal unitPrice = productSku.getPrice();

        BigDecimal subtotal = unitPrice.multiply(new BigDecimal(quantity));
        
        BigDecimal setupFee = productSku.getSetupFee();

        if (setupFee != null) {
        	subtotal = subtotal.add(setupFee);
        }
        
        BigDecimal discount = getItemDiscount(productSku, promo);

        BigDecimal total = subtotal.subtract(discount);
        
        subtotal = subtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
        discount = discount.setScale(2, BigDecimal.ROUND_HALF_UP);
        total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        String description = getItemDescription(user, productSku);
        String discountDescription = getItemDiscountDescription(promo);
        
        CART_ITEM item = getNewObject();

        item.setCartId(cart.getId());
        item.setProductSkuId(productSkuId);
        item.setQuantity(quantity);
        item.setPromoId(promoId);
        item.setDescription(description);
        item.setDiscountDescription(discountDescription);
        item.setUnitPrice(unitPrice);
        item.setSubtotal(subtotal);
        item.setSetupFee(setupFee);
        item.setDiscount(discount);
        item.setTotal(total);
        item.setSubscriptionStartDate(startDate);
        item.setSubscriptionEndDate(endDate);
        item.setCreatedDate(new Date());
        
        add(item);
        getCartService().updateCartTotals(cart);
        
        logger.debug("added cart item: " + KJsonUtil.toJson(item));
        return item;
    }
    
    @Override
    public CART_ITEM removeCartItem(Long itemId) {
    	CART_ITEM item = fetchById(itemId);
        return removeCartItem(item.getCartId(), itemId);
    }

    @Override
    public CART_ITEM removeCartItem(Long cartId, Long itemId) {
        CART cart = getCartService().fetchById(cartId);
        CART_ITEM item = fetchById(itemId);
        return removeCartItem(cart, item);
    }
    
    @Override
    public CART_ITEM removeCartItem(CART cart, CART_ITEM item) {
        if (!cart.getId().equals(item.getCartId())) {
        	throw new IllegalStateException("Cart and CartItem mismatch: "
                    + "\nCart: " + KJsonUtil.toJson(cart)
                    + "\nCartItem: " + KJsonUtil.toJson(item));
        }
        
        remove(item);
        getCartService().updateCartTotals(cart);
        return item;
    }
    

    public BigDecimal getItemDiscount(PRODUCT_SKU productSku, PROMO promo) {
        BigDecimal discount = null;

        if (promo == null) {
        	discount = new BigDecimal(0);
            return discount;
        }
        
        discount = promo.getDiscountAmount();

        if (discount == null || discount.compareTo(new BigDecimal(0)) == 0) {
        	Integer pct = promo.getDiscountPct();

            if (pct != null) {
                BigDecimal rate = new BigDecimal(pct);

                rate = rate.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);

            	BigDecimal price = productSku.getPrice();

                discount = price.multiply(rate);
            }
        }
        
        if (discount == null) {
        	discount = new BigDecimal(0);
        }
        
        // check setup fee
        BigDecimal setupFee = productSku.getSetupFee();

        BigDecimal promoSetupFee = promo.getSetupFee();
        
        if (setupFee != null && promoSetupFee != null
                && setupFee.compareTo(new BigDecimal(0))>0
                && promoSetupFee.compareTo(new BigDecimal(0))>0) {
            BigDecimal feeDiscount = setupFee.subtract(promoSetupFee);

            discount = discount.add(feeDiscount);
        }
        
    	return discount; 
    }
    
    private String getItemDescription(USER user, PRODUCT_SKU productSku) {
        return productSku.getDescription();
    }
    
    private String getItemDiscountDescription(PROMO promo) {
        String description = null;

        if (promo != null) {
        	description = "[" + promo.getName() + "] "
                    + promo.getDescription();
        }
        
    	return description;
    }
    
    private Date getSubscriptionEndDate(USER user, PRODUCT_SKU productSku, Integer quantity, PROMO promo) {
        if (!productSku.isSubscription()) return null;

    	Date endDate = null;
    	
    	// if we have a product, then add to its endDate
    	PURCHASE purchase = getLastSubscriptionPurchase(user.getAccountId(), productSku);

    	if (purchase != null) {
    		endDate = purchase.getExpirationDate();
    	}
        
        // If we have an endDate that already expired, then reset it to now
        Date now = new Date();

        if (endDate == null || endDate.getTime() < now.getTime()) {
        	endDate = now;
        }
        
        Integer subscriptionDays = productSku.getSubscriptionDays();

        if (promo != null && promo.getSubscriptionDays() != null) {
        	subscriptionDays = promo.getSubscriptionDays();
            
            // -1 indicates unlimited subscription so return null for endDate
            if (subscriptionDays == -1) {
            	return null;
            }
        }

        subscriptionDays = subscriptionDays * quantity;
        
        endDate = KDateUtil.addDays(endDate, subscriptionDays);

    	return endDate;
    }
    

    
    private PURCHASE getLastSubscriptionPurchase(Long accountId, PRODUCT_SKU productSku) {
        PURCHASE lastPurchase =
                getPurchaseService()
                .fetchLastPurchaseByAccountIdAndProductSkuId(accountId, productSku.getId());

        Date now = new Date();

        if (lastPurchase.getExpirationDate() == null || lastPurchase.getExpirationDate().getTime() < now.getTime()) {
            return null;
        }

        return lastPurchase;
    }
  
}

