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
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(CartService.SERVICE_PATH)
public class CartServiceImpl 
		extends KAbstractCartService<Cart,CartExample,CartItem,User,Token> 
		implements CartService {
	
	private static Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    
    @Autowired
    private CartMapper cartDao;
    
    @Autowired
    CartItemService cartItemService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    TokenService tokenService;



    @Override @SuppressWarnings("unchecked")
    protected CartMapper getDao() {
        return cartDao;
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

    


    @Override 
    protected void updateCoords(Long cartId) {
        getDao().updateCoords(cartId);
    }


    @Override
    public List<Cart> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    ) {
        return getDao().selectProximate(latitude, longitude, radius, startDate, endDate, objectIdList);
    }
}
