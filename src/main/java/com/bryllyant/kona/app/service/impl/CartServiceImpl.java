/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CartMapper;
import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.app.entity.CartExample;
import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.app.entity.Currency;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.CartItemService;
import com.bryllyant.kona.app.service.CartService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(CartService.SERVICE_PATH)
public class CartServiceImpl 
		extends KAbstractService<Cart, CartExample, CartMapper>
		implements CartService {
	
	private static Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    
    @Autowired
    private CartMapper cartMapper;
    
    @Autowired
    CartItemService cartItemService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    TokenService tokenService;


    @Override @SuppressWarnings("unchecked")
    protected CartMapper getMapper() {
        return cartMapper;
    }
    
    @Override
    public void validate(Cart cart) {
        if (cart.getCreatedDate() == null) {
            cart.setCreatedDate(new Date());
        }

        if (cart.getUid() == null) {
            cart.setUid(uuid());
        }

        cart.setUpdatedDate(new Date());
    }

    @Override
    public Cart fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<Cart> fetchByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("userId", userId);
        return fetchByCriteria(filter);
    }

    @Override
    public Cart fetchActiveByTokenId(Long tokenId) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("tokenId", tokenId)
                .and("invoiced", false)
                .and("expired", false)
                .and("expiredDate", null)
                .and("invoicedDate", null)
                .build();

        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public Cart fetchActiveByUserId(Long userId) {
        Map<String,Object> filter = KMyBatisUtil.filter()
                .and("userId", userId)
                .and("invoiced", false)
                .and("expired", false)
                .and("expiredDate", null)
                .and("invoicedDate", null)
                .build();

        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public Cart getActiveCartByUserId(Long userId) {
        if (userId == null) return null;
        return fetchActiveByUserId(userId);
    }


    @Override
    public Cart getCart(Long userId) {
        return getCart(userId, null);
    }


    @Override
    public Cart getCart(KServiceClient client) {
        return getCart(null, client);
    }



    @Override
    public Cart getCart(Long userId, KServiceClient client) {
        Cart cart = null;
        Token token = null;

        if (client != null) {
            Long tokenId = client.getTokenId();

            if (tokenId != null) {
                token = tokenService.fetchById(tokenId);
            }
        }

        if (userId != null) {
            cart = getActiveCartByUserId(userId);
        }


        if (userId != null && token != null && token.getUserId() != null) {
            if (!token.getUserId().equals(userId)) {
                throw new IllegalStateException("getCart(): userId and token userId mismatch:"
                        + "\nuserId: " + userId
                        + "\ntoken: " + KJsonUtil.toJson(token));
            }
        }

        if (userId == null && token != null) {
            userId = token.getUserId();
        }

        // first see if we have a cart for this token key
        if (cart == null && token != null) {
            cart = fetchActiveByTokenId(token.getId());

            if (cart != null) {
                if (userId != null &&
                        (cart.getUserId() == null || !cart.getUserId().equals(userId))) {
                    expireCart(cart);
                    cart = null;
                }
            }
        }

        // if we don't have a cart, create one
        if (cart == null) {
            cart = createCart(userId, client);
        }

        logger.debug("getCart(): cart:\n" + KJsonUtil.toJson(cart));
        return cart;
    }



    @Override
    public Cart createCart(Long userId, KServiceClient client) {
        Long tokenId = null;
        Long accountId = null;

        if (client != null) {
            tokenId = client.getTokenId();
            accountId = client.getAccountId();

            if (userId == null) {
                userId = client.getUserId();
            }

            if (userId != null && client.getUserId() != null && !client.getUserId().equals(userId)) {
                throw new KServiceException("Error: createCart: userId mismatch");
            }
        }

        if (userId != null) {
            User user = userService.fetchById(userId);
            accountId = user.getAccountId();
        }


        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setAccountId(accountId);
        cart.setCurrencyId(Currency.USD.getId());
        cart.setTokenId(tokenId);
        cart.setSubtotal(new BigDecimal(0));
        cart.setDiscount(new BigDecimal(0));
        cart.setShipping(new BigDecimal(0));
        cart.setTax(new BigDecimal(0));
        cart.setTotal(new BigDecimal(0));
        cart.setCheckedOut(false);
        cart.setInvoiced(false);
        cart.setCreatedDate(new Date());

        cart = add(cart);

        return cart;
    }

    @Override
    public void updateCart(Cart cart) {
        if (cart == null) return;
        update(cart);
    }

    @Override
    public void expireCart(Long cartId) {
        if (cartId == null) return;
        Cart cart = fetchById(cartId);
        expireCart(cart);
    }


    @Override
    public void expireCart(Cart cart) {
        if (cart == null) return;
        cart.setExpired(true);
        cart.setExpiredDate(new Date());
        update(cart);
    }

    @Override
    public Cart checkout(Cart cart) {
        if (cart == null) return null;
        cart.setCheckedOutDate(new Date());
        cart.setCheckedOut(true);
        update(cart);
        return cart;
    }

    @Override
    public Cart updateCartTotals(Cart cart) {
        BigDecimal subtotal = new BigDecimal(0);
        BigDecimal discount = new BigDecimal(0);
        BigDecimal total = new BigDecimal(0);

        List<CartItem> itemList = cartItemService.getCartItemList(cart);

        for (CartItem item : itemList) {
            if (item.getTotal() != null) {
                total = total.add(item.getTotal());
            }

            if (item.getSubtotal() != null) {
                subtotal = subtotal.add(item.getSubtotal());
            }

            if (item.getDiscount() != null) {
                discount = discount.add(item.getDiscount());
            }

        }

        cart.setSubtotal(subtotal);
        cart.setDiscount(discount);
        cart.setTotal(total);

        update(cart);

        return cart;
    }
}
