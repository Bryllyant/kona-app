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
import com.bryllyant.kona.app.entity.CampaignReplyMessage;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.service.CampaignAnalyticsService;
import com.bryllyant.kona.app.service.CampaignChannelService;
import com.bryllyant.kona.app.service.CampaignGroupService;
import com.bryllyant.kona.app.service.CampaignReplyService;
import com.bryllyant.kona.app.service.CampaignService;
import com.bryllyant.kona.app.service.CampaignTargetService;
import com.bryllyant.kona.app.service.KAbstractCampaignAnalyticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(CampaignAnalyticsService.SERVICE_PATH)
public class CampaignAnalyticsServiceImpl
		extends KAbstractCampaignAnalyticsService<
        CampaignAnalytics,
        CampaignAnalyticsExample,
        CampaignAnalyticsMapper,
        Campaign,
        CampaignGroup,
        CampaignChannel,
        CampaignTarget,
        CampaignReply,
        CampaignReplyMessage>
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

    @Override @SuppressWarnings("unchecked")
    protected CampaignService getCampaignService() {
        return campaignService;
    }

    @Override @SuppressWarnings("unchecked")
    protected CampaignGroupService getCampaignGroupService() {
        return campaignGroupService;
    }

    @Override @SuppressWarnings("unchecked")
    protected CampaignChannelService getCampaignChannelService() {
        return campaignChannelService;
    }

    @Override @SuppressWarnings("unchecked")
    protected CampaignTargetService getCampaignTargetService() {
        return campaignTargetService;
    }

    @Override @SuppressWarnings("unchecked")
    protected CampaignReplyService getCampaignReplyService() {
        return campaignReplyService;
    }

    @Override
    protected void updateCoords(Long campaignAnalyticsId) {
        getMapper().updateCoords(campaignAnalyticsId);
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
