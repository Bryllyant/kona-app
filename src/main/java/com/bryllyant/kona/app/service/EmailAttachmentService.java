package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.EmailAttachment;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.io.IOException;
import java.util.List;


public interface EmailAttachmentService extends KService, KEntityService<EmailAttachment> {
	String SERVICE_PATH = "rpc/EmailAttachmentService";

    List<EmailAttachment> fetchByContentId(Long contentId);

    EmailAttachment create(Long contentId, File file) throws IOException;

    EmailAttachment create(Long userId, Long contentId, String name, String contentType, byte[] attachment) throws IOException;
}
