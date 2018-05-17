package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EmailContentMapper;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailContentExample;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.service.EmailAttachmentService;
import com.bryllyant.kona.app.service.EmailContentService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@Service(EmailContentService.SERVICE_PATH)
public class EmailContentServiceImpl 
		extends KAbstractService<EmailContent, EmailContentExample, EmailContentMapper>
		implements EmailContentService {
	
	private static Logger logger = LoggerFactory.getLogger(EmailContentServiceImpl.class);

	@Autowired
	private EmailContentMapper emailContentMapper;
	
	@Autowired
	private EmailAttachmentService emailAttachmentService;
    

	@Override @SuppressWarnings("unchecked")
	protected EmailContentMapper getMapper() {
		return emailContentMapper;
	}
    
    @Override
    protected boolean entityHasBlobs() {
        return true;
    }


    @Override
    public void validate(EmailContent content) {
        if (content.getCreatedDate() == null) {
            content.setCreatedDate(new Date());
        }

        content.setUpdatedDate(new Date());

        if (content.getUid() == null) {
            content.setUid(uuid());
        }

        content.setSlug(KInflector.getInstance().slug(content.getName()));
    }


    @Override
    public EmailContent create(Long ownerId, String name, String html, String text, List<File> attachments) throws IOException {
        EmailContent content = new EmailContent();

        if (name == null) {
            content.setUid(uuid());
            content.setName(content.getUid());
        }

        if (ownerId.equals(1L)) {
            content.setSystem(true);
        }

        content.setOwnerId(ownerId);
        content.setHtml(html);
        content.setText(text);

        content = add(content);

        if (attachments != null) {
            for (File attachment : attachments) {
                emailAttachmentService.create(content.getId(), attachment);
            }
        }

        return content;
    }

}
