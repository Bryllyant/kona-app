/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

public interface AccountService extends KService, KEntityService<Account> {
	String SERVICE_PATH = "rpc/AccountService";

    Account fetchByUid(String uid);

    Account fetchBySlug(String slug);

    Account fetchByOwnerId(Long ownerId);

    Account retire(Account account);

    Account createAccount(String name);

    boolean isAccountNameAvailable(String name);
	
}
