/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CampaignReplyMapper;
import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignReplyExample;
import com.bryllyant.kona.app.entity.CampaignReplyMessage;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.app.entity.Sms;
import com.bryllyant.kona.app.model.EmailFooter;
import com.bryllyant.kona.app.service.CampaignReplyMessageService;
import com.bryllyant.kona.app.service.CampaignReplyService;
import com.bryllyant.kona.app.service.EmailService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.app.service.PushService;
import com.bryllyant.kona.app.service.SmsService;
import com.bryllyant.kona.util.AppUtil;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(CampaignReplyService.SERVICE_PATH)
public class CampaignReplyServiceImpl
		extends KAbstractService<CampaignReply,CampaignReplyExample,CampaignReplyMapper>
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

    protected String getSystemMailReplyTo() {
        return config.getString("system.mail.alertTo");
    }

    protected String getSystemMailFrom() {
        return config.getString("system.mail.from");
    }

    protected EmailFooter getSystemMailFooter() {
        return util.getEmailFooter();
    }


    @Override
    public void validate(CampaignReply campaignReply) {
        if (campaignReply.getCreatedDate() == null) {
            campaignReply.setCreatedDate(new Date());
        }

        campaignReply.setUpdatedDate(new Date());

        if (campaignReply.getUid() == null) {
            campaignReply.setUid(uuid());
        }

        String slug = KInflector.getInstance().slug(campaignReply.getName());
        campaignReply.setSlug(slug);
    }

    @Override
    public CampaignReply fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public CampaignReply fetchByTargetIdAndSlug(Long targetId, String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("targetId", targetId);
        filter.put("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<CampaignReply> fetchByCampaignId(Long campaignId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CampaignReply> fetchByGroupId(Long groupId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CampaignReply> fetchByChannelId(Long channelId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("channelId", channelId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CampaignReply> fetchByTargetId(Long targetId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("targetId", targetId);
        return fetchByCriteria(filter);
    }


    @Override @Transactional
    public CampaignReply create(CampaignTarget target, CampaignReply reply) {
        reply.setCampaignId(target.getCampaignId());
        reply.setGroupId(target.getGroupId());
        reply.setTargetId(target.getId());
        reply.setEnabled(true);

        if (reply.getStartDate() == null) {
            reply.setStartDate(target.getStartDate());
        }

        if (reply.getEndDate() == null) {
            reply.setEndDate(target.getEndDate());
        }

        return add(reply);
    }

    @Override @Transactional
    public CampaignReply create(
            CampaignTarget target,
            String name,
            CampaignReply.Type type,
            String content,
            Date startDate,
            Date endDate
    ) {
        CampaignReply reply = getEntityObject();

        reply.setName(name);
        reply.setType(type);
        reply.setContent(content);
        reply.setEnabled(true);
        reply.setStartDate(startDate);
        reply.setEndDate(endDate);

        return create(target, reply);
    }

    @Override @Transactional
    public CampaignReplyMessage execute(CampaignReply reply, CampaignAnalytics analytics) {
        switch (reply.getType()) {
            case Email:
                return sendEmail(reply, analytics);

            case Sms:
                return sendSms(reply, analytics);

            case Push:
                return sendPush(reply, analytics);

            default:
                throw new IllegalArgumentException("Invalid reply type: " + reply.getType());
        }
    }

    protected CampaignReplyMessage sendSms(CampaignReply reply, CampaignAnalytics analytics) {
        if (analytics.getConversionMobileNumber() == null) {
            throw new KServiceException("sendPush: conversionMobileNumber is null: " + analytics);
        }

        Sms sms = smsService.sendMessage(analytics.getConversionMobileNumber(), reply.getContent());

        return campaignReplyMessageService.createSms(reply, analytics.getConversionMobileNumber(), sms.getId());
    }

    protected CampaignReplyMessage sendEmail(CampaignReply reply, CampaignAnalytics analytics) {
        if (analytics.getConversionEmail() == null) {
            throw new KServiceException("sendPush: conversionEmail is null: " + analytics);
        }

        String subject = reply.getSubject();
        String body = reply.getContent();
        String from = getSystemMailFrom();
        String to = analytics.getConversionEmail();
        String replyTo = getSystemMailReplyTo();
        EmailFooter footer = getSystemMailFooter();
        boolean html = true;

        Email email = emailService.send(body, subject, from, replyTo, to, html, null, footer);

        return campaignReplyMessageService.createEmail(reply, analytics.getConversionEmail(), email.getId());
    }

    protected CampaignReplyMessage sendPush(CampaignReply reply, CampaignAnalytics analytics) {
        if (analytics.getConversionUserId() == null) {
            throw new KServiceException("sendPush: conversionUserId is null: " + analytics);
        }

        boolean sandbox = false;

        Push push = pushService.send(
                analytics.getConversionUserId(),
                reply.getSubject(),
                reply.getContent(),
                null,
                null,
                sandbox
        );

        return campaignReplyMessageService.createPush(reply, analytics.getConversionUserId(), push.getId());
    }
}
