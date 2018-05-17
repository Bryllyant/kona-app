package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EmailTemplateMapper;
import com.bryllyant.kona.app.entity.EmailTemplate;
import com.bryllyant.kona.app.entity.EmailTemplateExample;
import com.bryllyant.kona.app.service.EmailTemplateService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service(EmailTemplateService.SERVICE_PATH)
public class EmailTemplateServiceImpl
		extends KAbstractService<EmailTemplate, EmailTemplateExample, EmailTemplateMapper>
		implements EmailTemplateService {
	
	private static Logger logger = LoggerFactory.getLogger(EmailTemplateServiceImpl.class);

	@Autowired
	private EmailTemplateMapper emailTemplateMapper;
	

	@Override @SuppressWarnings("unchecked")
	protected EmailTemplateMapper getMapper() {
		return emailTemplateMapper;
	}
    
    @Override
    protected boolean entityHasBlobs() {
        return true;
    }


    @Override
    public void validate(EmailTemplate template) {
        if (template.getCreatedDate() == null) {
            template.setCreatedDate(new Date());
        }

        template.setUpdatedDate(new Date());

        if (template.getUid() == null) {
            template.setUid(uuid());
        }

        template.setSlug(KInflector.getInstance().slug(template.getName()));
    }
}
