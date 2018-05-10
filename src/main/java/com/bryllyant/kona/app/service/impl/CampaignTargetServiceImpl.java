/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CampaignTargetMapper;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.entity.CampaignTargetExample;
import com.bryllyant.kona.app.service.CampaignChannelService;
import com.bryllyant.kona.app.service.CampaignGroupService;
import com.bryllyant.kona.app.service.CampaignService;
import com.bryllyant.kona.app.service.CampaignTargetService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
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
    private CampaignService campaignService;

    @Autowired
    private CampaignChannelService campaignChannelService;

    @Autowired
    private CampaignGroupService campaignGroupService;


    protected String getLandingPageBaseUrl() {
        return config.getString("landingPage.baseUrl");
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
    public List<CampaignTarget> fetchByUrl(String url) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("url", url);
        return fetchByCriteria(filter);
        // return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
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

    @Override @Transactional
    public CampaignTarget create(
            CampaignChannel channel,
            String name,
            CampaignTarget.Type type,
            String url,
            Date startDate,
            Date endDate
    ) {
        CampaignTarget target = getEntityObject();

        target.setName(name);
        target.setType(type);
        target.setEnabled(true);
        target.setStartDate(startDate);
        target.setEndDate(endDate);


        if (url == null) {
            throw new KServiceException("CampaignTarget: create: url must be set");
        }

        if (!(type == CampaignTarget.Type.WEBSITE  || type == CampaignTarget.Type.APP_STORE)) {
            throw new KServiceException("CampaignTarget: create: invalid campaign type: " + type);
        }

        Campaign campaign = campaignService.fetchById(channel.getCampaignId());
        CampaignGroup group = campaignGroupService.fetchById(channel.getGroupId());

        String medium = null;

        switch (channel.getType()) {
            case SMS:
                medium = "sms";
                break;

            case EMAIL:
                medium = "email";
                break;

            case BLOG_POST:
                medium = "blog";
                break;

            case PRESS_RELEASE:
                medium = "press-release";
                break;

            case ORGANIC:
                medium = "organic";
                break;

            case VIDEO:
                medium = "video";
                break;


            case BANNER_AD:
            case GOOGLE_ADWORDS:
            case AFFILIATE:
            case SOCIAL_DEAL:
                medium = "cpc";
                break;

            case FACEBOOK:
            case TWITTER:
            case LINKEDIN:
            case INSTAGRAM:
            case SNAPCHAT:
            case PINTEREST:
                medium = "social";
                break;

            case OTHER:
            default:
                medium = "referral";
                break;
        }


        String term = null;

        try {
            term = URLEncoder.encode(channel.getAdwordsKeywords(), "UTF-8");
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
        }


        // add utm_ fields to url
        URI uri = URI.create(url);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(uri);

        String source = group.getSlug() + "/" + channel.getType().name();

        builder = builder
                .queryParam("utm_campaign", campaign.getSlug())
                .queryParam("utm_source", source)
                .queryParam("utm_medium", medium)
                .queryParam("utm_content", channel.getSlug());


        if (term != null) {
            builder = builder.queryParam("utm_term", term);
        }


        url = builder.build().toUriString();



        // for apple app store we need to add other specific params
        // https://stackoverflow.com/questions/43114257/how-to-track-utm-tags-in-app-store-urls?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
        // https://blog.attributionapp.com/lets-solve-ios-attribution-methods


        target.setUrl(url);


        return create(channel, target);
    }
}
