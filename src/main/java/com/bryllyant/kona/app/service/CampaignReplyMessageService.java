/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignReplyMessage;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CampaignReplyMessageService
        extends KService, KEntityService<CampaignReplyMessage> {

	String SERVICE_PATH = "rpc/CampaignReplyMessageService";

    List<CampaignReplyMessage> fetchByCampaignId(Long campaignId);

    List<CampaignReplyMessage> fetchByGroupId(Long groupId);

    List<CampaignReplyMessage> fetchByChannelId(Long channelId);

    List<CampaignReplyMessage> fetchByTargetId(Long targetId);

    List<CampaignReplyMessage> fetchByReplyId(Long replyId);


    @Transactional
    CampaignReplyMessage create(CampaignReply reply, CampaignReplyMessage message);

    @Transactional
    CampaignReplyMessage createEmail(CampaignReply reply, String toEmail, Long emailId);

    @Transactional
    CampaignReplyMessage createSms(CampaignReply reply, String toMobileNumber, Long smsId);

    @Transactional
    CampaignReplyMessage createPush(CampaignReply reply, Long toUserId, Long pushId);
}
