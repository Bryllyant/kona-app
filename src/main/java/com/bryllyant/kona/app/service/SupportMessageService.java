/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.SupportMessage;
import com.bryllyant.kona.remote.service.KService;

public interface SupportMessageService extends KService, KSupportMessageService<SupportMessage> {
	String SERVICE_PATH = "rpc/SupportMessageService";
	
}
