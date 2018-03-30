package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCampaignChannel;
import com.bryllyant.kona.app.entity.KCampaignGroup;
import com.bryllyant.kona.app.entity.KCampaignReply;
import com.bryllyant.kona.app.entity.KCampaignTarget;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


public interface KCampaignChannelService<
        CAMPAIGN_CHANNEL extends KCampaignChannel,
        CAMPAIGN_GROUP extends KCampaignGroup,
        CAMPAIGN_TARGET extends KCampaignTarget,
        CAMPAIGN_REPLY extends KCampaignReply>
        extends KService, KEntityService<CAMPAIGN_CHANNEL> {
            
    CAMPAIGN_CHANNEL fetchByUid(String uid);

    CAMPAIGN_CHANNEL fetchByGroupIdAndSlug(Long groupId, String slug);

    CAMPAIGN_CHANNEL fetchBySmsNumberAndKeyword(String smsNumber, String smsKeyword);

    CAMPAIGN_CHANNEL fetchByPromoCode(String promoCode);

    List<CAMPAIGN_CHANNEL> fetchByCampaignId(Long campaignId);

    List<CAMPAIGN_CHANNEL> fetchByGroupId(Long groupId);

    @Transactional
    CAMPAIGN_CHANNEL create(CAMPAIGN_GROUP group, CAMPAIGN_CHANNEL channel);

    @Transactional
    CAMPAIGN_CHANNEL create(
            CAMPAIGN_GROUP group,
            CAMPAIGN_CHANNEL.Type type,
            CAMPAIGN_CHANNEL.TargetStrategy targetStrategy,
            CAMPAIGN_CHANNEL.ReplyStrategy replyStrategy,
            String name,
            Date startDate,
            Date endDate
    );


    CAMPAIGN_TARGET nextCampaignTarget(CAMPAIGN_CHANNEL channel);

    CAMPAIGN_REPLY nextCampaignReply(CAMPAIGN_CHANNEL channel, CAMPAIGN_TARGET target);
}
