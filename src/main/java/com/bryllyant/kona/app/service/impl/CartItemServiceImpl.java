/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CartItemMapper;
import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.app.entity.CartItemExample;
import com.bryllyant.kona.app.entity.ProductSku;
import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.app.entity.Purchase;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.CartItemService;
import com.bryllyant.kona.app.service.CartService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.ProductSkuService;
import com.bryllyant.kona.app.service.PromoService;
import com.bryllyant.kona.app.service.PurchaseService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.util.KDateUtil;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(CartItemService.SERVICE_PATH)
public class CartItemServiceImpl 
		extends KAbstractService<CartItem,CartItemExample,CartItemMapper>
		implements CartItemService {
	
	private static Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);
    
    @Autowired
    private CartItemMapper cartItemMapper;
    
    @Autowired
    PurchaseService purchaseService;
    
    @Autowired
    PromoService promoService;
    
    @Autowired
    CartService cartService;
    
    @Autowired
    UserService userService;

    @Autowired
    ProductSkuService productSkuService;

    @Override @SuppressWarnings("unchecked")
    protected CartItemMapper getMapper() {
        return cartItemMapper;
    }
    

    @Override
    public void validate(CartItem cartItem) {
        if (cartItem.getCreatedDate() == null) {
            cartItem.setCreatedDate(new Date());
        }

        if (cartItem.getUid() == null) {
            cartItem.setUid(uuid());
        }

        cartItem.setUpdatedDate(new Date());
    }

    @Override
    public CartItem fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<CartItem> fetchByCartId(Long cartId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("cartId", cartId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public List<CartItem> getCartItemList(Cart cart) {
        if (cart == null || cart.getId() == null) return null;
        return fetchByCartId(cart.getId());
    }



    @Override
    public CartItem updateCartItem(Long itemId) {
        CartItem item = fetchById(itemId);
        return updateCartItem(item.getCartId(), itemId);
    }



    @Override
    public CartItem updateCartItem(Long cartId, Long itemId) {
        Cart cart = cartService.fetchById(cartId);
        CartItem item = fetchById(itemId);
        return updateCartItem(cart, item);
    }



    @Override
    public CartItem updateCartItem(CartItem item) {
        if (item == null) return null;
        Cart cart = cartService.fetchById(item.getCartId());
        return updateCartItem(cart, item);
    }

    @Override
    public CartItem updateCartItem(Cart cart, CartItem item) {
        if (item == null) return null;
        item = update(item);
        cartService.updateCartTotals(cart);
        return item;
    }

    @Override
    public CartItem addCartItem(
            Cart cart,
            Long userId,
            Long productSkuId,
            Long promoId
    ) {
        return addCartItem(cart, userId, productSkuId,1, promoId);
    }


    @Override
    public CartItem addCartItem(
            KServiceClient client,
            Long userId,
            Long productSkuId,
            Integer quantity,
            Long promoId
    ) {
        Cart cart = cartService.getCart(userId, client);
        return addCartItem(cart, userId, productSkuId, quantity, promoId);
    }


    @Override
    public CartItem addCartItem(
            Cart cart,
            Long userId,
            Long productSkuId,
            Integer quantity,
            Long promoId
    ) {

        User user = userService.fetchById(userId);

        ProductSku productSku = productSkuService.fetchById(productSkuId);

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

        Promo promo = promoService.fetchById(promoId);
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

        CartItem item = new CartItem();

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
        cartService.updateCartTotals(cart);

        logger.debug("added cart item: " + KJsonUtil.toJson(item));
        return item;
    }

    @Override
    public CartItem removeCartItem(Long itemId) {
        CartItem item = fetchById(itemId);
        return removeCartItem(item.getCartId(), itemId);
    }

    @Override
    public CartItem removeCartItem(Long cartId, Long itemId) {
        Cart cart = cartService.fetchById(cartId);
        CartItem item = fetchById(itemId);
        return removeCartItem(cart, item);
    }

    @Override
    public CartItem removeCartItem(Cart cart, CartItem item) {
        if (!cart.getId().equals(item.getCartId())) {
            throw new IllegalStateException("Cart and CartItem mismatch: "
                    + "\nCart: " + KJsonUtil.toJson(cart)
                    + "\nCartItem: " + KJsonUtil.toJson(item));
        }

        remove(item);
        cartService.updateCartTotals(cart);
        return item;
    }


    public BigDecimal getItemDiscount(ProductSku productSku, Promo promo) {
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

    private String getItemDescription(User user, ProductSku productSku) {
        return productSku.getDescription();
    }

    private String getItemDiscountDescription(Promo promo) {
        String description = null;

        if (promo != null) {
            description = "[" + promo.getName() + "] "
                    + promo.getDescription();
        }

        return description;
    }

    private Date getSubscriptionEndDate(User user, ProductSku productSku, Integer quantity, Promo promo) {
        if (!productSku.isSubscription()) return null;

        Date endDate = null;

        // if we have a product, then add to its endDate
        Purchase purchase = getLastSubscriptionPurchase(user.getAccountId(), productSku);

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



    private Purchase getLastSubscriptionPurchase(Long accountId, ProductSku productSku) {
        Purchase lastPurchase = purchaseService
                .fetchLastPurchaseByAccountIdAndProductSkuId(accountId, productSku.getId());

        Date now = new Date();

        if (lastPurchase.getExpirationDate() == null || lastPurchase.getExpirationDate().getTime() < now.getTime()) {
            return null;
        }

        return lastPurchase;
    }
}
