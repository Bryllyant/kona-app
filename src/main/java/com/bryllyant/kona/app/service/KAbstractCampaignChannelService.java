package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCampaignAnalytics;
import com.bryllyant.kona.app.entity.KCampaignChannel;
import com.bryllyant.kona.app.entity.KCampaignGroup;
import com.bryllyant.kona.app.entity.KCampaignReply;
import com.bryllyant.kona.app.entity.KCampaignReplyMessage;
import com.bryllyant.kona.app.entity.KCampaignTarget;
import com.bryllyant.kona.app.entity.KShortUrl;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import com.bryllyant.kona.util.KRandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public abstract class KAbstractCampaignChannelService<
        CAMPAIGN_CHANNEL extends KCampaignChannel,
        CAMPAIGN_CHANNEL_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<CAMPAIGN_CHANNEL, CAMPAIGN_CHANNEL_EXAMPLE>,
        CAMPAIGN_GROUP extends KCampaignGroup,
        CAMPAIGN_TARGET extends KCampaignTarget,
        CAMPAIGN_REPLY extends KCampaignReply,
        CAMPAIGN_REPLY_MESSAGE extends KCampaignReplyMessage,
        CAMPAIGN_ANALYTICS extends KCampaignAnalytics,
        SHORT_URL extends KShortUrl>
        extends KAbstractService<CAMPAIGN_CHANNEL,CAMPAIGN_CHANNEL_EXAMPLE,MAPPER>
		implements KCampaignChannelService<CAMPAIGN_CHANNEL,CAMPAIGN_GROUP, CAMPAIGN_TARGET, CAMPAIGN_REPLY> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractCampaignChannelService.class);

    private Map<String,Integer> replyIndex = new HashMap<>();
    private Map<String,Integer> targetIndex = new HashMap<>();

	protected abstract String getLandingPageBaseUrl();

    protected abstract <S extends KShortUrlService<SHORT_URL>> S getShortUrlService();
    protected abstract <S extends KCampaignTargetService<CAMPAIGN_TARGET, CAMPAIGN_CHANNEL>> S getCampaignTargetService();
    protected abstract <S extends KCampaignReplyService<CAMPAIGN_REPLY, CAMPAIGN_TARGET, CAMPAIGN_ANALYTICS, CAMPAIGN_REPLY_MESSAGE>> S getCampaignReplyService();


    @Override
	public void validate(CAMPAIGN_CHANNEL campaignChannel) {
		if (campaignChannel.getCreatedDate() == null) {
			campaignChannel.setCreatedDate(new Date());
		}

        campaignChannel.setUpdatedDate(new Date());
        
		if (campaignChannel.getUid() == null) {
			campaignChannel.setUid(uuid());
		}

        String slug = KInflector.getInstance().slug(campaignChannel.getName());
        campaignChannel.setSlug(slug);

		if (campaignChannel.getConversionCount() == null) {
		    campaignChannel.setConversionCount(0);
        }

        if (campaignChannel.getShortUrl() == null) {

            String shortUrl = getShortUrlService().getChannelRedirectShortUrl(
                    campaignChannel.getCampaignId(),
                    campaignChannel.getGroupId(),
                    campaignChannel.getId()
            );

            campaignChannel.setShortUrl(shortUrl);
        }
	}


    @Override
    public CAMPAIGN_CHANNEL fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public CAMPAIGN_CHANNEL fetchByGroupIdAndSlug(Long groupId, String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
        filter.put("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public CAMPAIGN_CHANNEL fetchBySmsNumberAndKeyword(String smsNumber, String smsKeyword) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("smsNumber", smsNumber);
        if (smsKeyword != null) {
            filter.put("smsKeyword", smsKeyword);
        }
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public CAMPAIGN_CHANNEL fetchByPromoCode(String promoCode) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("promoCode", promoCode);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

	@Override
	public List<CAMPAIGN_CHANNEL> fetchByCampaignId(Long campaignId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
		return fetchByCriteria(filter);
	}

    @Override
    public List<CAMPAIGN_CHANNEL> fetchByGroupId(Long groupId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
        return fetchByCriteria(filter);
    }


    @Override @Transactional
    public CAMPAIGN_CHANNEL create(CAMPAIGN_GROUP group, CAMPAIGN_CHANNEL channel) {
        channel.setCampaignId(group.getCampaignId());
        channel.setGroupId(group.getId());
        channel.setEnabled(true);
        channel.setReplyEnabled(true);

        if (channel.getStartDate() == null) {
            channel.setStartDate(group.getStartDate());
        }

        if (channel.getEndDate() == null) {
            channel.setEndDate(group.getEndDate());
        }

        return add(channel);
    }

    @Override @Transactional
    public CAMPAIGN_CHANNEL create(
            CAMPAIGN_GROUP group,
            CAMPAIGN_CHANNEL.Type type,
            CAMPAIGN_CHANNEL.TargetStrategy targetStrategy,
            CAMPAIGN_CHANNEL.ReplyStrategy replyStrategy,
            String name,
            Date startDate,
            Date endDate
    ) {
        CAMPAIGN_CHANNEL channel = getEntityObject();

        channel.setName(name);
        channel.setType(type);
        channel.setTargetStrategy(targetStrategy);
        channel.setReplyStrategy(replyStrategy);
        channel.setStartDate(startDate);
        channel.setEndDate(endDate);

        return create(group, channel);
    }

    @Override
    public CAMPAIGN_REPLY nextCampaignReply(CAMPAIGN_CHANNEL channel, CAMPAIGN_TARGET target) {

        List<CAMPAIGN_REPLY> replyList = getCampaignReplyService().fetchByChannelId(channel.getId());

        if (replyList == null || replyList.size() == 0) {
            logger.info("No campaign replies defined for channel");
            return null;
        }

        if (target != null) {
            List<CAMPAIGN_REPLY> filteredList = replyList.stream().filter((CAMPAIGN_REPLY item) ->
                item.getTargetId() != null && item.getTargetId().equals(target.getId())
            ).collect(Collectors.toList());

            if (filteredList != null && filteredList.size() > 0) {
                replyList = filteredList;
            }
        }


        CAMPAIGN_REPLY campaignReply = null;

        String key = channel.getUid() + "|" + (target == null ? "" : target.getUid());

        switch (channel.getReplyStrategy()) {
            case ROUND_ROBIN:
                Integer nextIndex = null;

                Integer lastReplyIndex = replyIndex.get(key);

                if (lastReplyIndex == null) {
                    nextIndex = 0;
                } else {
                    nextIndex = (lastReplyIndex + 1) % replyList.size();
                }

                replyIndex.put(key, nextIndex);

                campaignReply = replyList.get(nextIndex);
                break;

            case RANDOM:
                nextIndex = KRandomUtil.getRandomInt(0, replyList.size() - 1);
                campaignReply = replyList.get(nextIndex);

            default:
            case MAX_VIEWS:
                nextIndex = KRandomUtil.getRandomInt(0, replyList.size() - 1);
                campaignReply = replyList.get(nextIndex);
        }

        return campaignReply;

    }

    @Override
    public CAMPAIGN_TARGET nextCampaignTarget(CAMPAIGN_CHANNEL channel) {
        List<CAMPAIGN_TARGET> targets = getCampaignTargetService().fetchByChannelId(channel.getId());

        if (targets == null || targets.size() == 0) {
            logger.info("No campaign targets for channel redirect");
            return null;
        }

        CAMPAIGN_TARGET campaignTarget = null;

        String key = channel.getUid();

        switch (channel.getTargetStrategy()) {
            case ROUND_ROBIN:
                Integer nextIndex = null;

                Integer lastTargetIndex = targetIndex.get(key);

                if (lastTargetIndex == null) {
                    nextIndex = 0;
                } else {
                    nextIndex = (lastTargetIndex + 1) % targets.size();
                }

                targetIndex.put(key, nextIndex);

                campaignTarget = targets.get(nextIndex);
                break;

            case RANDOM:
                nextIndex = KRandomUtil.getRandomInt(0, targets.size() - 1);
                campaignTarget = targets.get(nextIndex);

            default:
            case MAX_CONVERSIONS:
                nextIndex = KRandomUtil.getRandomInt(0, targets.size() - 1);
                campaignTarget = targets.get(nextIndex);
        }

        return campaignTarget;
    }
}
