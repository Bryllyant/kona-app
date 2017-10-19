/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Cart;
import com.bryllyant.kona.app.entity.Invoice;
import com.bryllyant.kona.app.entity.Payment;
import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.remote.service.KService;

public interface CommerceService extends KService, KCommerceService<Payment,PaymentAccount,Cart,Invoice> {
	public static final String SERVICE_PATH = "rpc/CommerceService";
	
}
