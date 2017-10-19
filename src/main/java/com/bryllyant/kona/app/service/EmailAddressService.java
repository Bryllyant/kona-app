/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.remote.service.KService;


public interface EmailAddressService extends KService, KEmailAddressService<EmailAddress> {
	public static final String SERVICE_PATH = "rpc/EmailAddressService";
	
}
