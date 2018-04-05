/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;

import java.util.List;

public interface CartItemService extends KService, KEntityService<CartItem> {
	String SERVICE_PATH = "rpc/CartItemService";

    List<CartItem> fetchByCartId(Long cartId);

    List<CartItem> getCartItemList(Cart cart);

    CartItem addCartItem(
            Cart cart,
            Long userId,
            Long productSkuId,
            Long promoId
    );

    CartItem addCartItem(
            KServiceClient client,
            Long userId,
            Long productSkuId,
            Integer quantity,
            Long promoId
    );

    CartItem addCartItem(
            Cart cart,
            Long userId,
            Long productSkuId,
            Integer quantity,
            Long promoId
    );

    CartItem removeCartItem(Long itemId);

    CartItem removeCartItem(Long cartId, Long itemId);

    CartItem removeCartItem(Cart cart, CartItem item);

    CartItem updateCartItem(Long itemId);

    CartItem updateCartItem(Long cartId, Long itemId);

    CartItem updateCartItem(Cart cart, CartItem item);

    CartItem updateCartItem(CartItem item);
	
}
