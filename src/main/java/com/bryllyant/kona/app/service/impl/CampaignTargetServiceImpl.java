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
import com.bryllyant.kona.app.service.ShortUrlService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.util.KInflector;
import com.bryllyant.kona.util.KStringUtil;
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

    @Autowired
    private ShortUrlService shortUrlService;


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
        if (target.getUid() != null) {
            throw new KServiceException("CampaignTarget object has non-null 'uid' field");
        }

        target.setUid(uuid());
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

        if (target.getLandingPageId() != null && target.getUrl() == null) {
            String uid = target.getUid();

            String targetUrl = getLandingPageBaseUrl();

            if (!targetUrl.endsWith("/")) {
                targetUrl += "/";
            }

            targetUrl += uid + "/";

            target.setWebsiteUrl(targetUrl);
        }

        target.setUrl(getDecoratedUrl(channel, target));

        return add(target);
    }

//    @Override @Transactional
//    public CampaignTarget create(
//            CampaignChannel channel,
//            String name,
//            CampaignTarget.Type type,
//            Long landingPageId,
//            Date startDate,
//            Date endDate
//    ) {
//        CampaignTarget target = getEntityObject();
//
//        target.setName(name);
//        target.setType(type);
//        target.setLandingPageId(landingPageId);
//        target.setEnabled(true);
//        target.setStartDate(startDate);
//        target.setEndDate(endDate);
//
//        return create(channel, target);
//    }

    protected String getDecoratedUrl(
            CampaignChannel channel,
            CampaignTarget target
    ) {

        CampaignTarget.Type type = target.getType();

        String url = null;

        if (type == CampaignTarget.Type.WEBSITE  || type == CampaignTarget.Type.LANDING_PAGE) {
            url = target.getWebsiteUrl();
        }

        if (type == CampaignTarget.Type.APP_STORE) {
            url = shortUrlService.createAppStoreShortUrl(target.getAppStoreUrl(), target.getGooglePlayUrl(), target.getAppStoreUrl());
        }

        if (url == null) {
            return null;
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

        // add utm_ fields to url
        URI uri = URI.create(url);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(uri);

        String source = group.getSlug() + "/" + channel.getType().name();

        builder = builder
                .queryParam("utm_campaign", encode(campaign.getSlug()))
                .queryParam("utm_source", encode(source))
                .queryParam("utm_medium", encode(medium))
                .queryParam("utm_content", encode(channel.getSlug()));


        if (channel.getAdwordsKeywords() != null) {
            String keywords = KStringUtil.join(channel.getAdwordsKeywords(), ",");
            builder = builder.queryParam("utm_term", encode(keywords));
        }


        // for apple app store we need to add other specific params
        // https://stackoverflow.com/questions/43114257/how-to-track-utm-tags-in-app-store-urls?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
        // https://blog.attributionapp.com/lets-solve-ios-attribution-methods


        // generate app store campaign links:
        // https://analytics.itunes.apple.com/#/campaigngenerator?app=1059538166
        if (type == CampaignTarget.Type.APP_STORE && target.getAppStoreUrl() != null) {
            if (target.getAppStoreProviderId() != null) {
                String campaignToken = campaign.getSlug() + "/" + source + "/" + channel.getSlug() + "/" + medium;

                builder = builder.queryParam("pt", encode(target.getAppStoreProviderId()));
                builder = builder.queryParam("ct", encode(campaignToken));
            }
        }


        url = builder.build().toUriString();

        return url;
    }

    private String encode(String term) {
        if (term == null) {
            return null;
        }

        try {
            return URLEncoder.encode(term, "UTF-8");
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw new KServiceException(t.getMessage(), t);
        }
    }
}
