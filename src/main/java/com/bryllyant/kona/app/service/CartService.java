/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.remote.service.KService;

public interface CartService extends KService, KCartService<Cart> {
	public static final String SERVICE_PATH = "rpc/CartService";
	
}
