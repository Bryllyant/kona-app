package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCampaignAnalytics;
import com.bryllyant.kona.app.entity.KCampaignReply;
import com.bryllyant.kona.app.entity.KCampaignReplyMessage;
import com.bryllyant.kona.app.entity.KCampaignTarget;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


public interface KCampaignReplyService<
        CAMPAIGN_REPLY extends KCampaignReply,
        CAMPAIGN_TARGET extends KCampaignTarget,
        CAMPAIGN_ANALYTICS extends KCampaignAnalytics,
        CAMPAIGN_REPLY_MESSAGE extends KCampaignReplyMessage>
        extends KService, KEntityService<CAMPAIGN_REPLY> {
            
    CAMPAIGN_REPLY fetchByUid(String uid);

    CAMPAIGN_REPLY fetchByTargetIdAndSlug(Long targetId, String slug);

    List<CAMPAIGN_REPLY> fetchByCampaignId(Long campaignId);

    List<CAMPAIGN_REPLY> fetchByGroupId(Long groupId);

    List<CAMPAIGN_REPLY> fetchByChannelId(Long channelId);

    List<CAMPAIGN_REPLY> fetchByTargetId(Long targetId);


    @Transactional
    CAMPAIGN_REPLY create(CAMPAIGN_TARGET target, CAMPAIGN_REPLY reply);

    @Transactional
    CAMPAIGN_REPLY create(
            CAMPAIGN_TARGET target,
            String name,
            CAMPAIGN_REPLY.Type type,
            String content,
            Date startDate,
            Date endDate
    );

    @Transactional
    CAMPAIGN_REPLY_MESSAGE execute(CAMPAIGN_REPLY reply, CAMPAIGN_ANALYTICS analytics);
}
