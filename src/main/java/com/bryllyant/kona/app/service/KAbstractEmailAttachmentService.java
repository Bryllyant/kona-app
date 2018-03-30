package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KEmailAttachment;
import com.bryllyant.kona.app.entity.KFile;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.util.KUtil;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KJsonUtil;
import com.bryllyant.kona.util.KResultList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractEmailAttachmentService<EMAIL_ATTACHMENT extends KEmailAttachment, EMAIL_ATTACHMENT_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<EMAIL_ATTACHMENT, EMAIL_ATTACHMENT_EXAMPLE>,
												   USER extends KUser,
												   FILE extends KFile>
        extends KAbstractService<EMAIL_ATTACHMENT, EMAIL_ATTACHMENT_EXAMPLE,MAPPER>
        implements KEmailAttachmentService<EMAIL_ATTACHMENT> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractEmailAttachmentService.class);


	
	protected abstract EMAIL_ATTACHMENT getNewObject();

	protected abstract FILE getNewFileObject();
	
	protected abstract <S extends KFileService<FILE>> S getFileService();

	protected abstract <S extends KUserService<USER>> S getUserService();

	



	@Override
	public void validate(EMAIL_ATTACHMENT attachment) {
		if (attachment.getCreatedDate() == null) {
			attachment.setCreatedDate(new Date());
		}
		
		attachment.setUpdatedDate(new Date());
		
		if (attachment.getUid() == null) {
			attachment.setUid(uuid());
		}
		
		if (attachment.getName() == null) {
		    throw new IllegalStateException("Attachment name is null");
		}

		if (attachment.getContentType() == null) {
		    throw new IllegalStateException("Attachment contentType is null");
		}

		if (attachment.getData() == null) {
		    throw new IllegalStateException("Attachment data is null");
		}
	}



	@Override @Transactional(readOnly=true)
	public KResultList<EMAIL_ATTACHMENT> fetchByCriteria(
	        Integer startRow, 
	        Integer resultSize,
	        String[] sortOrder, 
	        Map<String, Object> filter, 
	        boolean distinct) {

		KResultList<EMAIL_ATTACHMENT> attachments = super.fetchByCriteria(startRow, resultSize, sortOrder, filter, distinct);

	    for (EMAIL_ATTACHMENT attachment : attachments) {

	        if (attachment.getFileId() == null) continue;

	        try {
	            FILE file = getFileService().fetchById(attachment.getFileId(), true);

	            attachment.setName(file.getName());
	            attachment.setContentType(file.getContentType());
	            attachment.setData(file.getData());

	        } catch (IOException e) {
	            logger.warn("Unable to fetch data file for attachment: " + KJsonUtil.toJson(attachment));
	        }

	    }

	    return attachments;
	}



	@Override
	public EMAIL_ATTACHMENT fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		List<EMAIL_ATTACHMENT> result = fetchByCriteria(0, 99999, null, filter, false);
		return KMyBatisUtil.fetchOne(result);
	}
	


    @Override
    public List<EMAIL_ATTACHMENT> fetchByContentId(Long contentId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("contentId", contentId);
        List<EMAIL_ATTACHMENT> result = fetchByCriteria(0, 99999, null, filter, false);
        return result;
    }


	
	@Override
	public EMAIL_ATTACHMENT create(
	        Long userId, 
	        Long contentId, 
	        String name, 
	        String contentType, 
	        byte[] data
	        ) throws IOException {

	   USER user = getUserService().fetchById(userId); 
	   
	   @SuppressWarnings("unchecked")
       Class<FILE> clazz = (Class<FILE>) getNewFileObject().getClass();

	   FILE file = null;

	   try {
	       file = KUtil.toFile(clazz, user, data, contentType, name);
	       file = getFileService().add(file);
	   } catch (Exception e) {
	       throw new IOException(e);
	   }
	    
	    EMAIL_ATTACHMENT attachment = getNewObject();

	    attachment.setContentId(contentId);
	    attachment.setFileId(file.getId());
	    attachment.setName(name);
	    attachment.setContentType(contentType);
	    attachment.setData(data);
	    
	    return add(attachment);
	}

	
}

