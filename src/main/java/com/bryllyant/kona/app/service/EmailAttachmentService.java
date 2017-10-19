package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.EmailAttachment;
import com.bryllyant.kona.remote.service.KService;


public interface EmailAttachmentService extends KService, KEmailAttachmentService<EmailAttachment> {
	public static final String SERVICE_PATH = "rpc/EmailAttachmentService";
	
}
