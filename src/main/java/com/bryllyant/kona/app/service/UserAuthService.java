/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.entity.UserAuth;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

public interface UserAuthService extends KService, KEntityService<UserAuth> {
	String SERVICE_PATH = "rpc/UserAuthService";

    UserAuth fetchByUserId(Long userId);

    UserAuth setPlainPassword(Long userId, String password);

    UserAuth setEncryptedPassword(Long userId, String encryptedPassword);

    UserAuth resetPassword(Long userId);

    User validateCredentials(String username, String password);
}
