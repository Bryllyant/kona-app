/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.EmailGroupAddress;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;


public interface EmailGroupAddressService extends KService, KEntityService<EmailGroupAddress> {
	String SERVICE_PATH = "rpc/EmailGroupAddressService";

    EmailGroupAddress fetchByGroupIdAndAddressId(Long groupId, Long addressId);

    List<EmailGroupAddress> fetchByGroupId(Long groupId);

    List<EmailGroupAddress> fetchByAddressId(Long addressId);
	
}
