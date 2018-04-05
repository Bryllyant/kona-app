/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CampaignTargetMapper;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.entity.CampaignTargetExample;
import com.bryllyant.kona.app.service.CampaignTargetService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.ShortUrlService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(CampaignTargetService.SERVICE_PATH)
public class CampaignTargetServiceImpl
		extends KAbstractService<CampaignTarget,CampaignTargetExample,CampaignTargetMapper>
		implements CampaignTargetService {
	
	private static Logger logger = LoggerFactory.getLogger(CampaignTargetServiceImpl.class);

    @Autowired
    private KConfig config;
    
    @Autowired
    private CampaignTargetMapper campaignTargetMapper;

    @Autowired
    private ShortUrlService shortUrlService;

    protected String getLandingPageBaseUrl() {
        return config.getString("landingpage.baseUrl");
    }

    @Override @SuppressWarnings("unchecked")
    protected CampaignTargetMapper getMapper() {
        return campaignTargetMapper;
    }

    @Override
    public void validate(CampaignTarget campaignTarget) {
        if (campaignTarget.getCreatedDate() == null) {
            campaignTarget.setCreatedDate(new Date());
        }

        campaignTarget.setUpdatedDate(new Date());

        if (campaignTarget.getUid() == null) {
            campaignTarget.setUid(uuid());
        }

        if (campaignTarget.getConversionCount() == null) {
            campaignTarget.setConversionCount(0);
        }

        String slug = KInflector.getInstance().slug(campaignTarget.getName());
        campaignTarget.setSlug(slug);

        if (campaignTarget.getLandingPageId() != null && campaignTarget.getUrl() == null) {
            String uid = campaignTarget.getUid();

            String targetUrl = getLandingPageBaseUrl();

            if (!targetUrl.endsWith("/")) {
                targetUrl += "/";
            }

            targetUrl += uid + "/";

            campaignTarget.setUrl(targetUrl);
        }


    }

    @Override
    public CampaignTarget fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public CampaignTarget fetchByChannelIdAndSlug(Long channelId, String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("channelId", channelId);
        filter.put("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public CampaignTarget fetchByUrl(String url) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("url", url);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public CampaignTarget fetchByShortUrl(String shortUrl) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("shortUrl", shortUrl);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public List<CampaignTarget> fetchByCampaignId(Long campaignId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CampaignTarget> fetchByGroupId(Long groupId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CampaignTarget> fetchByChannelId(Long channelId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("channelId", channelId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CampaignTarget> fetchByLandingPageId(Long landingPageId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("landingPageId", landingPageId);
        return fetchByCriteria(filter);
    }


    @Override @Transactional
    public CampaignTarget create(CampaignChannel channel, CampaignTarget target) {
        target.setCampaignId(channel.getCampaignId());
        target.setGroupId(channel.getGroupId());
        target.setChannelId(channel.getId());
        target.setEnabled(true);

        if (target.getStartDate() == null) {
            target.setStartDate(channel.getStartDate());
        }

        if (target.getEndDate() == null) {
            target.setEndDate(channel.getEndDate());
        }



        return add(target);
    }

    @Override @Transactional
    public CampaignTarget create(
            CampaignChannel channel,
            String name,
            CampaignTarget.Type type,
            Long landingPageId,
            Date startDate,
            Date endDate
    ) {
        CampaignTarget target = getEntityObject();

        target.setName(name);
        target.setType(type);
        target.setLandingPageId(landingPageId);
        target.setEnabled(true);
        target.setStartDate(startDate);
        target.setEndDate(endDate);

        return create(channel, target);
    }
}
