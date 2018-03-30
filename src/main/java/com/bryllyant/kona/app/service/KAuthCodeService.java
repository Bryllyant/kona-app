/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KAuthCode;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface KAuthCodeService<AUTH_CODE extends KAuthCode> extends KService, KEntityService<AUTH_CODE> {
	AUTH_CODE fetchByCode(String code);
	
	// indicate code has been accessed
	AUTH_CODE accessCode(String code);
    
	String generateAuthCodeUrl(AUTH_CODE.Type type, Long userId);

	void requestAuthCode(AUTH_CODE.Type type, Long userId, boolean resend);

	void requestAuthCodes(List<AUTH_CODE.Type> typeList, Long userId, boolean resend);

	List<AUTH_CODE> expireByUserId(Long userId);
    
}
