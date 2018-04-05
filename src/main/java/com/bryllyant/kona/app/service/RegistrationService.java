/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Registration;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;

import java.util.Date;
import java.util.List;

public interface RegistrationService extends KService, KEntityService<Registration> {
	String SERVICE_PATH = "rpc/RegistrationService";

    Registration fetchByUserId(Long userId);

    Registration createRegistration(User user, KServiceClient client, Long signupTime);

    List<Registration> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    );
}
