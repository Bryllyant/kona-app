/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CartMapper;
import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.app.entity.CartExample;
import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.CartItemService;
import com.bryllyant.kona.app.service.CartService;
import com.bryllyant.kona.app.service.KAbstractCartService;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(CartService.SERVICE_PATH)
public class CartServiceImpl 
		extends KAbstractCartService<Cart, CartExample, CartMapper,CartItem,User,Token>
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
    
    protected Cart getNewObject() {
    	return new Cart();
    }
    
    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }
    
    @Override @SuppressWarnings("unchecked")
    protected TokenService getTokenService() {
        return tokenService;
    }
    
    @Override @SuppressWarnings("unchecked")
    protected CartItemService getCartItemService() {
        return cartItemService;
    }
    
    @Override
    protected CartExample getEntityExampleObject() { return new CartExample(); }
}
