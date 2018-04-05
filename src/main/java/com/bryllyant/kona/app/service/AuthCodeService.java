/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.AuthCode;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface AuthCodeService extends KService, KEntityService<AuthCode> {
	String SERVICE_PATH = "rpc/AuthCodeService";

    AuthCode fetchByCode(String code);

    // indicate code has been accessed
    AuthCode accessCode(String code);

    String generateAuthCodeUrl(AuthCode.Type type, Long userId);

    void requestAuthCode(AuthCode.Type type, Long userId, boolean resend);

    void requestAuthCodes(List<AuthCode.Type> typeList, Long userId, boolean resend);

    List<AuthCode> expireByUserId(Long userId);
}
