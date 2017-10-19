/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.app.entity.CartItem;
import com.bryllyant.kona.remote.service.KService;

public interface CartItemService extends KService, KCartItemService<CartItem,Cart> {
	public static final String SERVICE_PATH = "rpc/CartItemService";
	
}
