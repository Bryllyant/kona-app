/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.io.IOException;
import java.util.List;


public interface EmailContentService extends KService, KEntityService<EmailContent> {
	String SERVICE_PATH = "rpc/EmailContentService";

    EmailContent create(Long ownerId, String html, String text, List<File> attachments) throws IOException;
}
