package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCampaign;
import com.bryllyant.kona.app.entity.KCampaignAnalytics;
import com.bryllyant.kona.app.entity.KCampaignChannel;
import com.bryllyant.kona.app.entity.KCampaignGroup;
import com.bryllyant.kona.app.entity.KCampaignReply;
import com.bryllyant.kona.app.entity.KCampaignReplyMessage;
import com.bryllyant.kona.app.entity.KCampaignTarget;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractCampaignAnalyticsService<
        CAMPAIGN_ANALYTICS extends KCampaignAnalytics,
        CAMPAIGN_ANALYTICS_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<CAMPAIGN_ANALYTICS, CAMPAIGN_ANALYTICS_EXAMPLE>,
        CAMPAIGN extends KCampaign,
        CAMPAIGN_GROUP extends KCampaignGroup,
        CAMPAIGN_CHANNEL extends KCampaignChannel,
        CAMPAIGN_TARGET extends KCampaignTarget,
        CAMPAIGN_REPLY extends KCampaignReply,
        CAMPAIGN_REPLY_MESSAGE extends KCampaignReplyMessage>
        extends KAbstractService<CAMPAIGN_ANALYTICS,CAMPAIGN_ANALYTICS_EXAMPLE,MAPPER>
		implements KCampaignAnalyticsService<CAMPAIGN_ANALYTICS> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractCampaignAnalyticsService.class);

    protected abstract void updateCoords(Long campaignAnalyticsId);

    protected abstract <S extends KCampaignService<CAMPAIGN>> S getCampaignService();
    protected abstract <S extends KCampaignGroupService<CAMPAIGN_GROUP, CAMPAIGN>> S getCampaignGroupService();

    protected abstract <S extends KCampaignChannelService<
            CAMPAIGN_CHANNEL,
            CAMPAIGN_GROUP,
            CAMPAIGN_TARGET,
            CAMPAIGN_REPLY>> S getCampaignChannelService();

    protected abstract <S extends KCampaignTargetService<CAMPAIGN_TARGET, CAMPAIGN_CHANNEL>> S getCampaignTargetService();

    protected abstract <S extends KCampaignReplyService<
            CAMPAIGN_REPLY,
            CAMPAIGN_TARGET,
            CAMPAIGN_ANALYTICS,
            CAMPAIGN_REPLY_MESSAGE>> S getCampaignReplyService();


    @Override @Transactional
    public CAMPAIGN_ANALYTICS add(CAMPAIGN_ANALYTICS campaignAnalytics) {
        campaignAnalytics = super.add(campaignAnalytics);
        updateCoords(campaignAnalytics.getId());
        return campaignAnalytics;
    }
    

    @Override @Transactional
    public CAMPAIGN_ANALYTICS update(CAMPAIGN_ANALYTICS campaignAnalytics) {
        campaignAnalytics = super.update(campaignAnalytics);
        updateCoords(campaignAnalytics.getId());
        return campaignAnalytics;
    }


	@Override
	public void validate(CAMPAIGN_ANALYTICS campaignAnalytics) {
		if (campaignAnalytics.getCreatedDate() == null) {
			campaignAnalytics.setCreatedDate(new Date());
		}

		campaignAnalytics.setUpdatedDate(new Date());

		if (campaignAnalytics.getUid() == null) {
		    campaignAnalytics.setUid(uuid());
        }
	}

	@Override
	public List<CAMPAIGN_ANALYTICS> fetchByCampaignId(Long campaignId, Date startDate, Date endDate) {
        KMyBatisUtil.Criteria criteria = KMyBatisUtil.filter();

        criteria = criteria.and("campaignId", campaignId);

        if (startDate != null) {
            criteria = criteria.and(">=createdDate", startDate);
        }

        if (endDate != null) {
            criteria = criteria.and("<createdDate", endDate);
        }

        Map<String,Object> filter = criteria.build();

		return fetchByCriteria(0, 99999, null, filter, false);
	}

    @Override
    public List<CAMPAIGN_ANALYTICS> fetchByGroupId(Long groupId, Date startDate, Date endDate) {
        KMyBatisUtil.Criteria criteria = KMyBatisUtil.filter();

        criteria = criteria.and("groupId", groupId);

        if (startDate != null) {
            criteria = criteria.and(">=createdDate", startDate);
        }

        if (endDate != null) {
            criteria = criteria.and("<createdDate", endDate);
        }

        Map<String,Object> filter = criteria.build();

        return fetchByCriteria(0, 99999, null, filter, false);
    }
	
	@Override
	public List<CAMPAIGN_ANALYTICS> fetchByChannelId(Long channelId, Date startDate, Date endDate) {
		KMyBatisUtil.Criteria criteria = KMyBatisUtil.filter();

		criteria = criteria.and("channelId", channelId);

		if (startDate != null) {
            criteria = criteria.and(">=createdDate", startDate);
        }

        if (endDate != null) {
            criteria = criteria.and("<createdDate", endDate);
        }

        Map<String,Object> filter = criteria.build();

		return fetchByCriteria(0, 99999, null, filter, false);
	}

    @Override
    public List<CAMPAIGN_ANALYTICS> fetchByTargetId(Long targetId, Date startDate, Date endDate) {
        KMyBatisUtil.Criteria criteria = KMyBatisUtil.filter();

        criteria = criteria.and("targetId", targetId);

        if (startDate != null) {
            criteria = criteria.and(">=createdDate", startDate);
        }

        if (endDate != null) {
            criteria = criteria.and("<createdDate", endDate);
        }

        Map<String,Object> filter = criteria.build();

        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<CAMPAIGN_ANALYTICS> fetchByUserId(Long userId, Date startDate, Date endDate) {
        KMyBatisUtil.Criteria criteria = KMyBatisUtil.filter();

        criteria = criteria.and("userId", userId);

        if (startDate != null) {
            criteria = criteria.and(">=createdDate", startDate);
        }

        if (endDate != null) {
            criteria = criteria.and("<createdDate", endDate);
        }

        Map<String,Object> filter = criteria.build();

        return fetchByCriteria(0, 99999, null, filter, false);
    }
	
	@Override
	public CAMPAIGN_ANALYTICS fetchByUid(String uid) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

    @Override @Transactional
    public CAMPAIGN_ANALYTICS create(
            KServiceClient client,
            Long targetId,
            String action,
            String category,
            String label,
            Double value
    ) {
        return create(
                client,
                targetId,
                action,
                category,
                label,
                value,
                false,
                null,
                null,
                null
        );
    }

    @Transactional
    public CAMPAIGN_ANALYTICS create(
            KServiceClient client,
            Long targetId,
            String action,
            String category,
            String label,
            Double value,
            boolean conversionEvent,
            Long conversionUserId,
            String conversionEmail,
            String conversionMobileNumber
    ) {
        CAMPAIGN_TARGET target = getCampaignTargetService().fetchById(targetId);

        if (target == null) {
            throw new KServiceException("Campaign target is null for targetId: " + targetId);
        }

        final CAMPAIGN_ANALYTICS analytics = getEntityObject();

        analytics.setCampaignId(target.getCampaignId());
        analytics.setGroupId(target.getGroupId());
        analytics.setChannelId(target.getChannelId());
        analytics.setTargetId(target.getId());

        analytics.setAction(action);
        analytics.setCategory(category);
        analytics.setLabel(label);
        analytics.setValue(value);

        analytics.setConversionEvent(conversionEvent);
        analytics.setConversionUserId(conversionUserId);
        analytics.setConversionEmail(conversionEmail);
        analytics.setConversionMobileNumber(conversionMobileNumber);

        analytics.setHostname(client.getHostname());
        analytics.setUserAgent(client.getUserAgent());
        analytics.setSourceUrl(client.getReferrerUrl());
        analytics.setLatitude(client.getLatitude());
        analytics.setLongitude(client.getLongitude());

        add(analytics);

        if (conversionEvent) {
            target.setConversionCount(target.getConversionCount() + 1);
            getCampaignTargetService().save(target);

            CAMPAIGN_CHANNEL channel = getCampaignChannelService().fetchById(target.getChannelId());
            channel.setConversionCount(channel.getConversionCount() + 1);
            getCampaignChannelService().save(channel);

            CAMPAIGN_GROUP group = getCampaignGroupService().fetchById(target.getGroupId());
            group.setConversionCount(group.getConversionCount() + 1);
            getCampaignGroupService().save(group);

            CAMPAIGN campaign = getCampaignService().fetchById(target.getCampaignId());
            campaign.setConversionCount(campaign.getConversionCount() + 1);
            getCampaignService().save(campaign);


            new Thread(() -> processCampaignReply(analytics, channel, target)).start();
        }

        return analytics;
    }

    protected void processCampaignReply(CAMPAIGN_ANALYTICS analytics, CAMPAIGN_CHANNEL channel, CAMPAIGN_TARGET target) {
        CAMPAIGN_REPLY reply = getCampaignChannelService().nextCampaignReply(channel, target);

        if (reply == null) return;

        getCampaignReplyService().execute(reply, analytics);
    }


}
