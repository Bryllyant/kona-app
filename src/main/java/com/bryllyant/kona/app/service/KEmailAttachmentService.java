package com.bryllyant.kona.app.service;

import java.io.IOException;
import java.util.List;

import com.bryllyant.kona.app.entity.KEmailAttachment;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

public interface KEmailAttachmentService<EMAIL_ATTACHMENT extends KEmailAttachment> 
		extends KService, KEntityService<EMAIL_ATTACHMENT> {
			
	EMAIL_ATTACHMENT fetchByUid(String uid);

	List<EMAIL_ATTACHMENT> fetchByContentId(Long contentId);

    EMAIL_ATTACHMENT create(Long userId, Long contentId, String name, String contentType, byte[] attachment) throws IOException;
	
}
