package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EmailAttachmentMapper;
import com.bryllyant.kona.app.entity.EmailAttachment;
import com.bryllyant.kona.app.entity.EmailAttachmentExample;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.EmailAttachmentService;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(EmailAttachmentService.SERVICE_PATH)
public class EmailAttachmentServiceImpl 
		extends KAbstractService<EmailAttachment, EmailAttachmentExample, EmailAttachmentMapper>
		implements EmailAttachmentService {
	
	private static Logger logger = LoggerFactory.getLogger(EmailAttachmentServiceImpl.class);

	@Autowired
	private EmailAttachmentMapper emailAttachmentMapper;
	
	@Autowired
	private FileService fileService;

	@Autowired
	private UserService userService;

    @Autowired
    private AppUtil util;


	@Override @SuppressWarnings("unchecked")
	protected EmailAttachmentMapper getMapper() {
		return emailAttachmentMapper;
	}
    

	@Override 
    protected boolean entityHasBlobs() {
	    return false;
    }


    @Override
    public void validate(EmailAttachment attachment) {
        if (attachment.getCreatedDate() == null) {
            attachment.setCreatedDate(new Date());
        }

        attachment.setUpdatedDate(new Date());

        if (attachment.getUid() == null) {
            attachment.setUid(uuid());
        }
    }

    @Override
    public List<EmailAttachment> fetchByContentId(Long contentId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("contentId", contentId);
        List<EmailAttachment> result = fetchByCriteria(filter);
        return result;
    }

    @Override
    public EmailAttachment create(
            Long userId,
            Long contentId,
            String name,
            String contentType,
            byte[] data
    ) throws IOException {
        try {
            User user = userService.fetchById(userId);
            File file = util.toFile(user, data, contentType, name);
            return create(contentId, file);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    public EmailAttachment create(Long contentId, File file) {

        if (file.getId() == null) {
            file = fileService.add(file);
        }

        EmailAttachment attachment = new EmailAttachment();

        attachment.setContentId(contentId);
        attachment.setFileId(file.getId());

        return add(attachment);
    }
}
