/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.entity.EmailGroupAddress;
import com.bryllyant.kona.remote.service.KService;


public interface EmailGroupService extends KService, KEmailGroupService<EmailGroup,EmailAddress,EmailGroupAddress> {
	public static final String SERVICE_PATH = "rpc/EmailGroupService";
	
}
