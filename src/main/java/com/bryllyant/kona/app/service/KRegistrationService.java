/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KRegistration;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;

import java.util.Date;
import java.util.List;


/**
 * The client side stub for the RPC service.
 */
public interface KRegistrationService<REGISTRATION extends KRegistration, USER extends KUser>
		extends KService, KEntityService<REGISTRATION> {
    
    
	public REGISTRATION fetchByUserId(Long userId);

	public REGISTRATION fetchByUid(String uid);

	public REGISTRATION createRegistration(USER user, KServiceClient client, Long signupTime);


    List<REGISTRATION> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    );
}
