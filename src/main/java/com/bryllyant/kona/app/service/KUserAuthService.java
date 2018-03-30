/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.entity.KUserAuth;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

/**
 * The client side stub for the RPC service.
 */
public interface KUserAuthService<UA extends KUserAuth,U extends KUser> extends KService, KEntityService<UA> {
	public static final String SERVICE_PATH = "rpc/kona/UserAuthService";
	
    public UA fetchByUserId(Long userId);

    public UA setPlainPassword(Long userId, String password);
    
    public UA setEncryptedPassword(Long userId, String encryptedPassword);

    public UA resetPassword(Long userId);
    
    public U validateCredentials(String username, String password);
}
