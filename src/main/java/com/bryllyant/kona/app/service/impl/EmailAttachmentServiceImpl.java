package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EmailAttachmentMapper;
import com.bryllyant.kona.app.entity.EmailAttachment;
import com.bryllyant.kona.app.entity.EmailAttachmentExample;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.EmailAttachmentService;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.service.KAbstractEmailAttachmentService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service(EmailAttachmentService.SERVICE_PATH)
public class EmailAttachmentServiceImpl 
		extends KAbstractEmailAttachmentService<EmailAttachment, EmailAttachmentExample, EmailAttachmentMapper,
                                      User,
                                      File>
		implements EmailAttachmentService {
	
	private static Logger logger = LoggerFactory.getLogger(EmailAttachmentServiceImpl.class);

	@Autowired
	private EmailAttachmentMapper emailAttachmentMapper;
	
	@Autowired
	private FileService fileService;

	@Autowired
	private UserService userService;
    


	@Override @SuppressWarnings("unchecked")
	protected EmailAttachmentMapper getMapper() {
		return emailAttachmentMapper;
	}
    

	@Override 
    protected boolean entityHasBlobs() {
	    return false;
    }



    @Override
    protected EmailAttachment getNewObject() {
    	return new EmailAttachment();
    }
    


    @Override
    protected File getNewFileObject() {
        return new File();
    }
    


    @Override @SuppressWarnings("unchecked")
    protected FileService getFileService() {
        return fileService;
    }
    


    @Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }
    


	 @Override
    protected EmailAttachmentExample getEntityExampleObject() { return new EmailAttachmentExample(); }




    
}
