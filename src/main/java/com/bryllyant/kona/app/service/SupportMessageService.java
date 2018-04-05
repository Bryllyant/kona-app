/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.SupportMessage;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;

import java.util.List;

public interface SupportMessageService extends KService, KEntityService<SupportMessage> {
	String SERVICE_PATH = "rpc/SupportMessageService";

    List<SupportMessage> fetchByUserId(Long userId);

    List<SupportMessage> fetchByEmail(String email);

    List<SupportMessage> fetchByMobileNumber(String mobileNumber);

    SupportMessage send(SupportMessage message);

    SupportMessage send(KServiceClient client, String name, String email, String mobileNumber, String message);
}
