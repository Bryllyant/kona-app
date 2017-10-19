/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.PaymentAccount;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.remote.service.KService;

public interface PaymentAccountService extends KService, KPaymentAccountService<PaymentAccount,User> {
	public static final String SERVICE_PATH = "rpc/PaymentAccountService";
	
}
