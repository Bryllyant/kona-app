/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.CampaignReplyMapper;
import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignReplyExample;
import com.bryllyant.kona.app.entity.CampaignReplyMessage;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.EmailAttachment;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailEvent;
import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.app.entity.Sms;
import com.bryllyant.kona.app.model.KEmailFooter;
import com.bryllyant.kona.app.service.CampaignReplyMessageService;
import com.bryllyant.kona.app.service.CampaignReplyService;
import com.bryllyant.kona.app.service.EmailService;
import com.bryllyant.kona.app.service.KAbstractCampaignReplyService;
import com.bryllyant.kona.app.service.PushService;
import com.bryllyant.kona.app.service.SmsService;
import com.bryllyant.kona.app.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(CampaignReplyService.SERVICE_PATH)
public class CampaignReplyServiceImpl
		extends KAbstractCampaignReplyService<
        CampaignReply,
        CampaignReplyExample,
        CampaignReplyMapper,
        CampaignTarget,
        CampaignAnalytics,
        CampaignReplyMessage,
        Email,
        EmailEvent,
        EmailContent,
        EmailAttachment,
        Sms,
        Push>
		implements CampaignReplyService {
	
	private static Logger logger = LoggerFactory.getLogger(CampaignReplyServiceImpl.class);

    @Autowired
    private KConfig config;
    
    @Autowired
    private CampaignReplyMapper campaignReplyMapper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private PushService pushService;

    @Autowired
    private CampaignReplyMessageService campaignReplyMessageService;

    @Autowired
    private AppUtil util;


    @Override @SuppressWarnings("unchecked")
    protected CampaignReplyMapper getMapper() {
        return campaignReplyMapper;
    }

    @Override
    protected String getSystemMailReplyTo() {
        return config.getString("system.mail.alertTo");
    }

    @Override
    protected String getSystemMailFrom() {
        return config.getString("system.mail.from");
    }

    @Override
    protected KEmailFooter getSystemMailFooter() {
        return util.getEmailFooter();
    }


    @Override @SuppressWarnings("unchecked")
    protected EmailService getEmailService() {
        return emailService;
    }

    @Override @SuppressWarnings("unchecked")
    protected SmsService getSmsService() {
        return smsService;
    }

    @Override @SuppressWarnings("unchecked")
    protected PushService getPushService() {
        return pushService;
    }

    @Override @SuppressWarnings("unchecked")
    protected CampaignReplyMessageService getCampaignReplyMessageService() {
        return campaignReplyMessageService;
    }
}
