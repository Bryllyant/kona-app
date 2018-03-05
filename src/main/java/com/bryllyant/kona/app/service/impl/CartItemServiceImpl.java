/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CartItemMapper;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.app.entity.CartItemExample;
import com.bryllyant.kona.app.entity.Product;
import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.app.entity.Purchase;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.CartItemService;
import com.bryllyant.kona.app.service.CartService;
import com.bryllyant.kona.app.service.KAbstractCartItemService;
import com.bryllyant.kona.app.service.ProductService;
import com.bryllyant.kona.app.service.PromoService;
import com.bryllyant.kona.app.service.PurchaseService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(CartItemService.SERVICE_PATH)
public class CartItemServiceImpl 
		extends KAbstractCartItemService<CartItem,CartItemExample,Cart,User,Account,Promo,Product,Purchase> 
		implements CartItemService {
	
	private static Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);
    
    @Autowired
    private CartItemMapper cartItemDao;
    
    @Autowired
    PurchaseService purchaseService;
    
    @Autowired
    PromoService promoService;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    CartService cartService;
    
    @Autowired
    UserService userService;



    @Override @SuppressWarnings("unchecked")
    protected CartItemMapper getDao() {
        return cartItemDao;
    }
    

    
    @Override
    protected CartItem getNewObject() {
    	return new CartItem();
    }
    


    @Override @SuppressWarnings("unchecked")
    protected PurchaseService getPurchaseService() {
        return purchaseService;
    }
    


    @Override @SuppressWarnings("unchecked")
    protected PromoService getPromoService() {
        return promoService;
    }
    


    @Override @SuppressWarnings("unchecked")
    protected ProductService getProductService() {
        return productService;
    }
    


    @Override @SuppressWarnings("unchecked")
    protected CartService getCartService() {
        return cartService;
    }
    


    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }
    


    @Override
    protected CartItemExample getEntityExampleObject() { return new CartItemExample(); }


}
