/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CampaignReplyMessageMapper;
import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignReplyMessage;
import com.bryllyant.kona.app.entity.CampaignReplyMessageExample;
import com.bryllyant.kona.app.service.CampaignReplyMessageService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(CampaignReplyMessageService.SERVICE_PATH)
public class CampaignReplyMessageServiceImpl
        extends KAbstractService<CampaignReplyMessage,CampaignReplyMessageExample,CampaignReplyMessageMapper>
        implements CampaignReplyMessageService {

    private static Logger logger = LoggerFactory.getLogger(CampaignReplyMessageServiceImpl.class);

    @Autowired
    private KConfig config;

    @Autowired
    private CampaignReplyMessageMapper campaignReplyMessageMapper;


    @Override @SuppressWarnings("unchecked")
    protected CampaignReplyMessageMapper getMapper() {
        return campaignReplyMessageMapper;
    }

    @Override
    public void validate(CampaignReplyMessage campaignReplyMessage) {
        if (campaignReplyMessage.getCreatedDate() == null) {
            campaignReplyMessage.setCreatedDate(new Date());
        }

        campaignReplyMessage.setUpdatedDate(new Date());

        if (campaignReplyMessage.getUid() == null) {
            campaignReplyMessage.setUid(uuid());
        }
    }

    @Override
    public CampaignReplyMessage fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public List<CampaignReplyMessage> fetchByCampaignId(Long campaignId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CampaignReplyMessage> fetchByGroupId(Long groupId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CampaignReplyMessage> fetchByChannelId(Long channelId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("channelId", channelId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CampaignReplyMessage> fetchByTargetId(Long targetId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("targetId",targetId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CampaignReplyMessage> fetchByReplyId(Long replyId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("replyId", replyId);
        return fetchByCriteria(filter);
    }


    @Override @Transactional
    public CampaignReplyMessage create(CampaignReply reply, CampaignReplyMessage message) {
        message.setCampaignId(reply.getCampaignId());
        message.setGroupId(reply.getGroupId());
        message.setChannelId(reply.getChannelId());
        message.setTargetId(reply.getTargetId());
        message.setReplyId(reply.getId());


        return add(message);
    }

    @Override @Transactional
    public CampaignReplyMessage createEmail(CampaignReply reply, String toEmail, Long emailId) {
        CampaignReplyMessage message = getEntityObject();
        message.setToEmail(toEmail);
        message.setEmailId(emailId);
        return create(reply, message);
    }

    @Override @Transactional
    public CampaignReplyMessage createSms(CampaignReply reply, String toMobileNumber, Long smsId) {
        CampaignReplyMessage message = getEntityObject();
        message.setToMobileNumber(toMobileNumber);
        message.setSmsId(smsId);
        return create(reply, message);
    }

    @Override @Transactional
    public CampaignReplyMessage createPush(CampaignReply reply, Long toUserId, Long pushId) {
        CampaignReplyMessage message = getEntityObject();
        message.setToUserId(toUserId);
        message.setPushId(pushId);
        return create(reply, message);
    }


}
