package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCart;
import com.bryllyant.kona.app.entity.KCartItem;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;

import java.util.List;


public interface KCartItemService<CART_ITEM extends KCartItem, CART extends KCart>
        extends KService, KEntityService<CART_ITEM> {

    CART_ITEM fetchByUid(String uid);

	List<CART_ITEM> fetchByCartId(Long cartId);
	
	List<CART_ITEM> getCartItemList(CART cart);
    
	CART_ITEM addCartItem(
	        CART cart,
            Long userId,
            Long productSkuId,
            Long promoId
    );

	CART_ITEM addCartItem(
	        KServiceClient client,
            Long userId,
            Long productSkuId,
            Integer quantity,
			Long promoId
    );

	CART_ITEM addCartItem(
	        CART cart,
            Long userId,
            Long productSkuId,
            Integer quantity,
			Long promoId
    );
	
	CART_ITEM removeCartItem(Long itemId);
    
	CART_ITEM removeCartItem(Long cartId, Long itemId);
    
	CART_ITEM removeCartItem(CART cart, CART_ITEM item);
	
	CART_ITEM updateCartItem(Long itemId);
    
	CART_ITEM updateCartItem(Long cartId, Long itemId);
    
	CART_ITEM updateCartItem(CART cart, CART_ITEM item);
    
	CART_ITEM updateCartItem(CART_ITEM item);
}
