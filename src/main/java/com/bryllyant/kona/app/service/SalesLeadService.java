/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.SalesLead;
import com.bryllyant.kona.remote.service.KService;

public interface SalesLeadService extends KService, KSalesLeadService<SalesLead> {
	String SERVICE_PATH = "rpc/SalesLeadService";
	
}
