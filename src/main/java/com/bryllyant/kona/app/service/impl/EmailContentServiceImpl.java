package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EmailContentMapper;
import com.bryllyant.kona.app.entity.EmailAttachment;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailContentExample;
import com.bryllyant.kona.app.service.EmailAttachmentService;
import com.bryllyant.kona.app.service.EmailContentService;
import com.bryllyant.kona.app.service.KAbstractEmailContentService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service(EmailContentService.SERVICE_PATH)
public class EmailContentServiceImpl 
		extends KAbstractEmailContentService<EmailContent,
                                      EmailContentExample,
                                      EmailAttachment>
		implements EmailContentService {
	
	private static Logger logger = LoggerFactory.getLogger(EmailContentServiceImpl.class);

	@Autowired
	private EmailContentMapper emailContentDao;
	
	@Autowired
	private EmailAttachmentService emailAttachmentService;
    


	@Override @SuppressWarnings("unchecked")
	protected EmailContentMapper getDao() {
		return emailContentDao;
	}
    

	@Override 
    protected boolean entityHasBlobs() {
	    return false;
    }



    @Override
    protected EmailContent getNewObject() {
    	return new EmailContent();
    }
    


    @Override @SuppressWarnings("unchecked")
    protected EmailAttachmentService getEmailAttachmentService() {
        return emailAttachmentService;
    }
    


	 @Override
    protected EmailContentExample getEntityExampleObject() { return new EmailContentExample(); }




    
}
