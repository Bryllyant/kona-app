/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.remote.service.KService;

public interface AccountService extends KService, KAccountService<Account> {
	public static final String SERVICE_PATH = "rpc/AccountService";
	
}
