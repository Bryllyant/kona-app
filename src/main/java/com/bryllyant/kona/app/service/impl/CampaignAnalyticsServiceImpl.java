/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CampaignAnalyticsMapper;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.app.entity.CampaignAnalyticsExample;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.service.CampaignAnalyticsService;
import com.bryllyant.kona.app.service.CampaignChannelService;
import com.bryllyant.kona.app.service.CampaignGroupService;
import com.bryllyant.kona.app.service.CampaignReplyService;
import com.bryllyant.kona.app.service.CampaignService;
import com.bryllyant.kona.app.service.CampaignTargetService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.remote.service.KServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(CampaignAnalyticsService.SERVICE_PATH)
public class CampaignAnalyticsServiceImpl
		extends KAbstractService<CampaignAnalytics, CampaignAnalyticsExample, CampaignAnalyticsMapper>
		implements CampaignAnalyticsService {
	
	private static Logger logger = LoggerFactory.getLogger(CampaignAnalyticsServiceImpl.class);
    
    @Autowired
    private CampaignAnalyticsMapper campaignAnalyticsMapper;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CampaignGroupService campaignGroupService;

    @Autowired
    private CampaignChannelService campaignChannelService;

    @Autowired
    private CampaignTargetService campaignTargetService;

    @Autowired
    private CampaignReplyService campaignReplyService;



    @Override @SuppressWarnings("unchecked")
    protected CampaignAnalyticsMapper getMapper() {
        return campaignAnalyticsMapper;
    }

    protected void updateCoords(Long campaignAnalyticsId) {
        getMapper().updateCoords(campaignAnalyticsId);
    }


    @Override @Transactional
    public CampaignAnalytics add(CampaignAnalytics campaignAnalytics) {
        campaignAnalytics = super.add(campaignAnalytics);
        updateCoords(campaignAnalytics.getId());
        return campaignAnalytics;
    }


    @Override @Transactional
    public CampaignAnalytics update(CampaignAnalytics campaignAnalytics) {
        campaignAnalytics = super.update(campaignAnalytics);
        updateCoords(campaignAnalytics.getId());
        return campaignAnalytics;
    }


    @Override
    public void validate(CampaignAnalytics campaignAnalytics) {
        if (campaignAnalytics.getCreatedDate() == null) {
            campaignAnalytics.setCreatedDate(new Date());
        }

        campaignAnalytics.setUpdatedDate(new Date());

        if (campaignAnalytics.getUid() == null) {
            campaignAnalytics.setUid(uuid());
        }
    }

    @Override
    public List<CampaignAnalytics> fetchByCampaignId(Long campaignId, Date startDate, Date endDate) {
        KMyBatisUtil.Criteria criteria = KMyBatisUtil.filter();

        criteria = criteria.and("campaignId", campaignId);

        if (startDate != null) {
            criteria = criteria.and(">=createdDate", startDate);
        }

        if (endDate != null) {
            criteria = criteria.and("<createdDate", endDate);
        }

        Map<String,Object> filter = criteria.build();

        return fetchByCriteria(filter);
    }

    @Override
    public List<CampaignAnalytics> fetchByGroupId(Long groupId, Date startDate, Date endDate) {
        KMyBatisUtil.Criteria criteria = KMyBatisUtil.filter();

        criteria = criteria.and("groupId", groupId);

        if (startDate != null) {
            criteria = criteria.and(">=createdDate", startDate);
        }

        if (endDate != null) {
            criteria = criteria.and("<createdDate", endDate);
        }

        Map<String,Object> filter = criteria.build();

        return fetchByCriteria(filter);
    }

    @Override
    public List<CampaignAnalytics> fetchByChannelId(Long channelId, Date startDate, Date endDate) {
        KMyBatisUtil.Criteria criteria = KMyBatisUtil.filter();

        criteria = criteria.and("channelId", channelId);

        if (startDate != null) {
            criteria = criteria.and(">=createdDate", startDate);
        }

        if (endDate != null) {
            criteria = criteria.and("<createdDate", endDate);
        }

        Map<String,Object> filter = criteria.build();

        return fetchByCriteria(filter);
    }

    @Override
    public List<CampaignAnalytics> fetchByTargetId(Long targetId, Date startDate, Date endDate) {
        KMyBatisUtil.Criteria criteria = KMyBatisUtil.filter();

        criteria = criteria.and("targetId", targetId);

        if (startDate != null) {
            criteria = criteria.and(">=createdDate", startDate);
        }

        if (endDate != null) {
            criteria = criteria.and("<createdDate", endDate);
        }

        Map<String,Object> filter = criteria.build();

        return fetchByCriteria(filter);
    }

    @Override
    public List<CampaignAnalytics> fetchByUserId(Long userId, Date startDate, Date endDate) {
        KMyBatisUtil.Criteria criteria = KMyBatisUtil.filter();

        criteria = criteria.and("userId", userId);

        if (startDate != null) {
            criteria = criteria.and(">=createdDate", startDate);
        }

        if (endDate != null) {
            criteria = criteria.and("<createdDate", endDate);
        }

        Map<String,Object> filter = criteria.build();

        return fetchByCriteria(filter);
    }

    @Override
    public CampaignAnalytics fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override @Transactional
    public CampaignAnalytics create(
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
    public CampaignAnalytics create(
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
        CampaignTarget target = campaignTargetService.fetchById(targetId);

        if (target == null) {
            throw new KServiceException("Campaign target is null for targetId: " + targetId);
        }

        final CampaignAnalytics analytics = getEntityObject();

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
            campaignTargetService.save(target);

            CampaignChannel channel = campaignChannelService.fetchById(target.getChannelId());
            channel.setConversionCount(channel.getConversionCount() + 1);
            campaignChannelService.save(channel);

            CampaignGroup group = campaignGroupService.fetchById(target.getGroupId());
            group.setConversionCount(group.getConversionCount() + 1);
            campaignGroupService.save(group);

            Campaign campaign = campaignService.fetchById(target.getCampaignId());
            campaign.setConversionCount(campaign.getConversionCount() + 1);
            campaignService.save(campaign);


            new Thread(() -> processCampaignReply(analytics, channel, target)).start();
        }

        return analytics;
    }

    protected void processCampaignReply(CampaignAnalytics analytics, CampaignChannel channel, CampaignTarget target) {
        CampaignReply reply = campaignChannelService.nextCampaignReply(channel, target);

        if (reply == null) return;

        campaignReplyService.execute(reply, analytics);
    }


    @Override
    public List<CampaignAnalytics> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    ) {
        return getMapper().selectProximate(latitude, longitude, radius, startDate, endDate, objectIdList);
    }
}
