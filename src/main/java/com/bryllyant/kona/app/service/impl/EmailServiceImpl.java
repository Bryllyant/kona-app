/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.EmailEventMapper;
import com.bryllyant.kona.app.dao.EmailMapper;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailAttachment;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailEvent;
import com.bryllyant.kona.app.entity.EmailEventExample;
import com.bryllyant.kona.app.entity.EmailExample;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.entity.EmailGroupAddress;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.service.EmailAddressService;
import com.bryllyant.kona.app.service.EmailAttachmentService;
import com.bryllyant.kona.app.service.EmailContentService;
import com.bryllyant.kona.app.service.EmailGroupAddressService;
import com.bryllyant.kona.app.service.EmailGroupService;
import com.bryllyant.kona.app.service.EmailService;
import com.bryllyant.kona.app.service.KAbstractEmailService;
import com.bryllyant.kona.app.service.QueueService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service(EmailService.SERVICE_PATH)
public class EmailServiceImpl 
		extends KAbstractEmailService<Email,
                                      EmailExample,
                                      EmailEvent,
                                      EmailEventExample,
                                      EmailGroup,
                                      EmailAddress,
                                      EmailContent,
                                      EmailAttachment,
                                      EmailGroupAddress,
                                      User> 
		implements EmailService {
	
	private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	


	@Autowired
	private EmailMapper emailDao;
	
	@Autowired
	private EmailEventMapper emailEventDao;
    
	@Autowired
	private KConfig config;
    
	@Autowired
	private EmailAddressService emailAddressService;

	@Autowired
	private EmailGroupService emailGroupService;

	@Autowired
	private EmailGroupAddressService emailGroupAddressService;

	@Autowired
	private EmailContentService emailContentService;

	@Autowired
	private EmailAttachmentService emailAttachmentService;
	
	@Autowired
	private QueueService queueService;

	@Autowired
	private UserService userService;
	

	
	private AmazonSimpleEmailServiceClient client = null;



	@Override @SuppressWarnings("unchecked")
	protected EmailMapper getDao() {
		return emailDao;
	}
	

	
	@Override @SuppressWarnings("unchecked")
	protected EmailEventMapper getEmailEventDao() {
		return emailEventDao;
	}
    


	@Override @SuppressWarnings("unchecked")
	protected EmailAddressService getEmailAddressService() {
		return emailAddressService;
	}
    


	@Override @SuppressWarnings("unchecked")
	protected EmailGroupService getEmailGroupService() {
		return emailGroupService;
	}



	@Override @SuppressWarnings("unchecked")
	protected EmailGroupAddressService getEmailGroupAddressService() {
		return emailGroupAddressService;
	}
	


    @Override @SuppressWarnings("unchecked")
    protected EmailContentService getEmailContentService() {
        return emailContentService;
    }
	


    @Override @SuppressWarnings("unchecked")
    protected EmailAttachmentService getEmailAttachmentService() {
        return emailAttachmentService;
    }



	@Override @SuppressWarnings("unchecked")
	protected QueueService getQueueService() {
		return queueService;
	}
	

	@Override @SuppressWarnings("unchecked")
    protected UserService getUserService() {
        return userService;
    }



    @Override
    protected Email getNewEmailObject() {
    	return new Email();
    }
    


    @Override
    protected EmailEvent getNewEmailEventObject() {
    	return new EmailEvent();
    }



    @Override
    protected EmailAddress getNewEmailAddressObject() {
    	return new EmailAddress();
    }
    


    @Override
    public String getEmailTestDomain() {
    	return config.getString("email.testDomain");
    }
    


	 @Override
    protected EmailExample getEntityExampleObject() { return new EmailExample(); }




	@Override
	protected AmazonSimpleEmailServiceClient getAmazonSESClient() {
		
        if (client == null) {
            String accessKey = config.getString("aws.ses.accessKey");
            String secretKey = config.getString("aws.ses.secretKey");
            String region = config.getString("aws.ses.region");

            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

            // Instantiate an Amazon SES client, which will make the service call with the supplied AWS credentials.
            client = new AmazonSimpleEmailServiceClient(credentials);

            Region REGION = null;
            
            region = region.trim().toLowerCase();
            
            switch (region) {
                case "us-east-1":
                    REGION = Region.getRegion(Regions.US_EAST_1);
                    break;
                case "us-west-2":
                    REGION = Region.getRegion(Regions.US_WEST_2);
                    break;
            }

            client.setRegion(REGION);
        }

        return client;
	}
	


	@Override
	protected String getAmazonSESBounceQueueName() {
		return config.getString("aws.ses.bounce.queue");
	}
	


	@Override
	protected String getAmazonSESComplaintQueueName() {
		return config.getString("aws.ses.complaint.queue");
	}
	


	@Override
	protected String getAmazonSESDeliveryQueueName() {
		return config.getString("aws.ses.delivery.queue");
	}
	


	@Override
	protected String getSystemMailhost() {
		return config.getString("system.mail.mailhost");
	}
	


	@Override
	protected String getSystemSenderEmailAddress() {
		return config.getString("system.mail.from");
	}
	


	@Override
	protected String getSystemFromEmailAddress() {
		return config.getString("system.mail.from");
	}
	


	@Override
	protected String getSystemBaseUrl() {
		return config.getString("system.app.baseUrl");
	}
	


	@Override
	protected String getEmailEventUrl() {
        return config.getString("email.event.baseUrl");
	}
	


	@Override
	protected String getEmailFooterHtmlSelector() {
		return config.getString("email.footer.selector");
	}
	


	@Override
	protected String getEmailTextFooterTemplatePath() {
		return config.getString("email.templates.footer.emailTextFooter");
	}
	


	@Override
	protected String getEmailHtmlFooterTemplatePath() {
		return config.getString("email.templates.footer.emailHtmlFooter");
	}
	

    
}
