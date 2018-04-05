/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;

import java.util.List;

public interface CartService extends KService, KEntityService<Cart> {
	String SERVICE_PATH = "rpc/CartService";

    List<Cart> fetchByUserId(Long userId);

    Cart fetchActiveByTokenId(Long tokenId);

    Cart fetchActiveByUserId(Long userId);

    Cart getActiveCartByUserId(Long userId);

    Cart getCart(Long userId);

    Cart getCart(KServiceClient client);

    Cart getCart(Long userId, KServiceClient client);

    Cart createCart(Long userId, KServiceClient client);

    Cart checkout(Cart cart);

    Cart updateCartTotals(Cart cart);

    void updateCart(Cart cart);

    void expireCart(Long cartId);

    void expireCart(Cart cart);
	
}
