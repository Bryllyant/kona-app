package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KEmailAttachment;
import com.bryllyant.kona.app.entity.KEmailContent;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractEmailContentService<EMAIL_CONTENT extends KEmailContent, EMAIL_CONTENT_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<EMAIL_CONTENT, EMAIL_CONTENT_EXAMPLE>,
												   EMAIL_ATTACHMENT extends KEmailAttachment>
        extends KAbstractService<EMAIL_CONTENT, EMAIL_CONTENT_EXAMPLE,MAPPER>
        implements KEmailContentService<EMAIL_CONTENT,EMAIL_ATTACHMENT> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractEmailContentService.class);


	protected abstract <S extends KEmailAttachmentService<EMAIL_ATTACHMENT>> S getEmailAttachmentService();
	
	protected abstract EMAIL_CONTENT getNewObject();

    @Override
    protected boolean entityHasBlobs() {
        return true;
    }


	@Override
	public void validate(EMAIL_CONTENT content) {
		if (content.getCreatedDate() == null) {
			content.setCreatedDate(new Date());
		}
		
		content.setUpdatedDate(new Date());
		
		if (content.getUid() == null) {
			content.setUid(uuid());
		}
	}



	@Override
	public EMAIL_CONTENT fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		List<EMAIL_CONTENT> result = fetchByCriteria(0, 99999, null, filter, false);
		return KMyBatisUtil.fetchOne(result);
	}


	
	@Override
	public EMAIL_CONTENT create(Long ownerId, String html, String text, List<EMAIL_ATTACHMENT> attachments) throws IOException {
	    EMAIL_CONTENT content = getNewObject();

        content.setOwnerId(ownerId);
	    content.setHtml(html);
	    content.setText(text);

	    content = add(content);
	    
	    if (attachments != null) {
	        for (EMAIL_ATTACHMENT attachment : attachments) {
	            if (attachment.getFileId() == null) {
	                attachment = getEmailAttachmentService().create(
	                        ownerId, 
	                        content.getId(), 
	                        attachment.getName(), 
	                        attachment.getContentType(), 
	                        attachment.getData());
	            }

	            if (attachment.getContentId() == null) {
	                attachment.setContentId(content.getId());
	                attachment = getEmailAttachmentService().save(attachment);
	            }
	        }
	    }
	    
	    return content;
	}

	
}

