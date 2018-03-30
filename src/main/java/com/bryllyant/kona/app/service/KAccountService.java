/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAccount;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;


public interface KAccountService<ACCOUNT extends KAccount> extends KService, KEntityService<ACCOUNT> {
    ACCOUNT fetchByUid(String uid);
    
	ACCOUNT fetchBySlug(String slug);
    
	ACCOUNT fetchByOwnerId(Long ownerId);
	
	ACCOUNT retire(ACCOUNT account);
    
	ACCOUNT createAccount(String name);
    
	boolean isAccountNameAvailable(String name);
}
