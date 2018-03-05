/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.InvoiceMapper;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.InvoiceExample;
import com.bryllyant.kona.app.entity.InvoiceItem;
import com.bryllyant.kona.app.entity.Product;
import com.bryllyant.kona.app.service.AccountService;
import com.bryllyant.kona.app.service.CartItemService;
import com.bryllyant.kona.app.service.CartService;
import com.bryllyant.kona.app.service.InvoiceItemService;
import com.bryllyant.kona.app.service.InvoiceService;
import com.bryllyant.kona.app.service.KAbstractInvoiceService;
import com.bryllyant.kona.app.service.ProductService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(InvoiceService.SERVICE_PATH)
public class InvoiceServiceImpl 
		extends KAbstractInvoiceService<Invoice,InvoiceExample,InvoiceItem,Cart,CartItem,Product,Account> 
		implements InvoiceService {
	
	private static Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);
    
    @Autowired
    private InvoiceMapper invoiceDao;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private CartItemService cartItemService;
    
    @Autowired
    private InvoiceItemService invoiceItemService;

    @Autowired
    private ProductService productService;
    
    @Autowired
    private AccountService accountService;
    


    @Override @SuppressWarnings("unchecked")
    protected InvoiceMapper getDao() {
        return invoiceDao;
    }
    

    
    @Override
    protected Invoice getNewInvoiceObject() {
    	return new Invoice();
    }
    

    
    @Override
    protected InvoiceItem getNewInvoiceItemObject() {
    	return new InvoiceItem();
    }
    


    @Override @SuppressWarnings("unchecked")
    protected AccountService getAccountService() {
        return accountService;
    }
    


    @Override @SuppressWarnings("unchecked")
    protected CartService getCartService() {
        return cartService;
    }
    


    @Override @SuppressWarnings("unchecked")
    protected CartItemService getCartItemService() {
        return cartItemService;
    }
    


    @Override @SuppressWarnings("unchecked")
    protected InvoiceItemService getInvoiceItemService() {
        return invoiceItemService;
    }
    


    @Override @SuppressWarnings("unchecked")
    protected ProductService getProductService() {
        return productService;
    }



    @Override
    protected InvoiceExample getEntityExampleObject() { return new InvoiceExample(); }


}
