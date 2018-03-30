package com.bryllyant.kona.app.service;

import java.io.IOException;
import java.util.List;

import com.bryllyant.kona.app.entity.KEmailAttachment;
import com.bryllyant.kona.app.entity.KEmailContent;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

public interface KEmailContentService<EMAIL_CONTENT extends KEmailContent, EMAIL_ATTACHMENT extends KEmailAttachment> 
		extends KService, KEntityService<EMAIL_CONTENT> {
			
	EMAIL_CONTENT fetchByUid(String uid);

    EMAIL_CONTENT create(Long ownerId, String html, String text, List<EMAIL_ATTACHMENT> attachments) throws IOException;
}
