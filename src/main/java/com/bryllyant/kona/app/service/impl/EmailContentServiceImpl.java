package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EmailContentMapper;
import com.bryllyant.kona.app.entity.EmailAttachment;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailContentExample;
import com.bryllyant.kona.app.service.EmailAttachmentService;
import com.bryllyant.kona.app.service.EmailContentService;
import com.bryllyant.kona.app.service.KAbstractEmailContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(EmailContentService.SERVICE_PATH)
public class EmailContentServiceImpl 
		extends KAbstractEmailContentService<
        EmailContent, EmailContentExample, EmailContentMapper,
        EmailAttachment>
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
    protected EmailContent getNewObject() {
    	return new EmailContent();
    }
    
    @Override @SuppressWarnings("unchecked")
    protected EmailAttachmentService getEmailAttachmentService() {
        return emailAttachmentService;
    }
}
