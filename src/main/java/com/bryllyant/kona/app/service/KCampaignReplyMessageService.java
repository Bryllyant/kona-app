package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCampaignReply;
import com.bryllyant.kona.app.entity.KCampaignReplyMessage;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface KCampaignReplyMessageService<
        CAMPAIGN_REPLY_MESSAGE extends KCampaignReplyMessage,
        CAMPAIGN_REPLY extends KCampaignReply>
        extends KService, KEntityService<CAMPAIGN_REPLY_MESSAGE> {

    CAMPAIGN_REPLY_MESSAGE fetchByUid(String uid);

    List<CAMPAIGN_REPLY_MESSAGE> fetchByCampaignId(Long campaignId);

    List<CAMPAIGN_REPLY_MESSAGE> fetchByGroupId(Long groupId);

    List<CAMPAIGN_REPLY_MESSAGE> fetchByChannelId(Long channelId);

    List<CAMPAIGN_REPLY_MESSAGE> fetchByTargetId(Long targetId);

    List<CAMPAIGN_REPLY_MESSAGE> fetchByReplyId(Long replyId);


    @Transactional
    CAMPAIGN_REPLY_MESSAGE create(CAMPAIGN_REPLY reply, CAMPAIGN_REPLY_MESSAGE message);

    @Transactional
    CAMPAIGN_REPLY_MESSAGE createEmail(CAMPAIGN_REPLY reply, String toEmail, Long emailId);

    @Transactional
    CAMPAIGN_REPLY_MESSAGE createSms(CAMPAIGN_REPLY reply, String toMobileNumber, Long smsId);

    @Transactional
    CAMPAIGN_REPLY_MESSAGE createPush(CAMPAIGN_REPLY reply, Long toUserId, Long pushId);
}
