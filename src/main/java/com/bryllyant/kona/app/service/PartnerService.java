/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Partner;
import com.bryllyant.kona.remote.service.KService;

public interface PartnerService extends KService, KPartnerService<Partner> {
	public static final String SERVICE_PATH = "rpc/PartnerService";
	
}
