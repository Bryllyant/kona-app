/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CampaignChannelMapper;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignChannelExample;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.service.CampaignChannelService;
import com.bryllyant.kona.app.service.CampaignReplyService;
import com.bryllyant.kona.app.service.CampaignTargetService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.ShortUrlService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import com.bryllyant.kona.util.KRandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service(CampaignChannelService.SERVICE_PATH)
public class CampaignChannelServiceImpl 
		extends KAbstractService<CampaignChannel, CampaignChannelExample, CampaignChannelMapper>
		implements CampaignChannelService {
	
	private static Logger logger = LoggerFactory.getLogger(CampaignChannelServiceImpl.class);

    private Map<String,Integer> replyIndex = new HashMap<>();
    private Map<String,Integer> targetIndex = new HashMap<>();

    @Autowired
    private KConfig config;
    
    @Autowired
    private CampaignChannelMapper campaignChannelMapper;

    @Autowired
    private ShortUrlService shortUrlService;

    @Autowired
    private CampaignTargetService campaignTargetService;

    @Autowired
    private CampaignReplyService campaignReplyService;

    protected String getLandingPageBaseUrl() {
        return config.getString("landingpage.baseUrl");
    }

    @Override @SuppressWarnings("unchecked")
    protected CampaignChannelMapper getMapper() {
        return campaignChannelMapper;
    }


    @Override
    public void validate(CampaignChannel campaignChannel) {
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

            String shortUrl = shortUrlService.getChannelRedirectShortUrl(
                    campaignChannel.getCampaignId(),
                    campaignChannel.getGroupId(),
                    campaignChannel.getId()
            );

            campaignChannel.setShortUrl(shortUrl);
        }
    }

    @Override
    public CampaignChannel fetchByGroupIdAndSlug(Long groupId, String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
        filter.put("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public CampaignChannel fetchBySmsNumberAndKeyword(String smsNumber, String smsKeyword) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("smsNumber", smsNumber);
        if (smsKeyword != null) {
            filter.put("smsKeyword", smsKeyword);
        }
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public CampaignChannel fetchByPromoCode(String promoCode) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("promoCode", promoCode);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<CampaignChannel> fetchByCampaignId(Long campaignId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CampaignChannel> fetchByGroupId(Long groupId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
        return fetchByCriteria(filter);
    }


    @Override @Transactional
    public CampaignChannel create(CampaignGroup group, CampaignChannel channel) {
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
    public CampaignChannel create(
            CampaignGroup group,
            CampaignChannel.Type type,
            CampaignChannel.TargetStrategy targetStrategy,
            CampaignChannel.ReplyStrategy replyStrategy,
            String name,
            Date startDate,
            Date endDate
    ) {
        CampaignChannel channel = getEntityObject();

        channel.setName(name);
        channel.setType(type);
        channel.setTargetStrategy(targetStrategy);
        channel.setReplyStrategy(replyStrategy);
        channel.setStartDate(startDate);
        channel.setEndDate(endDate);

        return create(group, channel);
    }

    @Override
    public CampaignReply nextCampaignReply(CampaignChannel channel, CampaignTarget target) {

        List<CampaignReply> replyList = campaignReplyService.fetchByChannelId(channel.getId());

        if (replyList == null || replyList.size() == 0) {
            logger.info("No campaign replies defined for channel");
            return null;
        }

        if (target != null) {
            List<CampaignReply> filteredList = replyList.stream().filter((CampaignReply item) ->
                    item.getTargetId() != null && item.getTargetId().equals(target.getId())
            ).collect(Collectors.toList());

            if (filteredList != null && filteredList.size() > 0) {
                replyList = filteredList;
            }
        }


        CampaignReply campaignReply = null;

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
    public CampaignTarget nextCampaignTarget(CampaignChannel channel) {
        List<CampaignTarget> targets = campaignTargetService.fetchByChannelId(channel.getId());

        if (targets == null || targets.size() == 0) {
            logger.info("No campaign targets for channel redirect");
            return null;
        }

        CampaignTarget campaignTarget = null;

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
