/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.EmailTemplate;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;


public interface EmailTemplateService extends KService, KEntityService<EmailTemplate> {
	String SERVICE_PATH = "rpc/EmailTemplateService";

}
