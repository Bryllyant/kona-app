package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCart;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;

import java.util.List;

public interface KCartService<CART extends KCart>
        extends KService, KEntityService<CART> {

    CART fetchByUid(String uid);

	List<CART> fetchByUserId(Long userId);

	CART fetchActiveByTokenId(Long tokenId);

	CART fetchActiveByUserId(Long userId);
	
	CART getActiveCartByUserId(Long userId);

	CART getCart(Long userId);

	CART getCart(KServiceClient client);

	CART getCart(Long userId, KServiceClient client);

	CART createCart(Long userId, KServiceClient client);
    
	CART checkout(CART cart);

	CART updateCartTotals(CART cart);
    
	void updateCart(CART cart);
    
	void expireCart(Long cartId);
    
	void expireCart(CART cart);
}
