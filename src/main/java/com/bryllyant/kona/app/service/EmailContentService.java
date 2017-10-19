/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.EmailAttachment;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.remote.service.KService;


public interface EmailContentService extends KService, KEmailContentService<EmailContent,EmailAttachment> {
	public static final String SERVICE_PATH = "rpc/EmailContentService";
	
}
