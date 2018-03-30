package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCampaignChannel;
import com.bryllyant.kona.app.entity.KCampaignTarget;
import com.bryllyant.kona.app.entity.KShortUrl;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


public abstract class KAbstractCampaignTargetService<
        CAMPAIGN_TARGET extends KCampaignTarget,
        CAMPAIGN_TARGET_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<CAMPAIGN_TARGET, CAMPAIGN_TARGET_EXAMPLE>,
        CAMPAIGN_CHANNEL extends KCampaignChannel,
        SHORT_URL extends KShortUrl>
        extends KAbstractService<CAMPAIGN_TARGET,CAMPAIGN_TARGET_EXAMPLE,MAPPER>
		implements KCampaignTargetService<CAMPAIGN_TARGET,CAMPAIGN_CHANNEL> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractCampaignTargetService.class);

	protected abstract String getLandingPageBaseUrl();

    protected abstract <S extends KShortUrlService<SHORT_URL>> S getShortUrlService();

	@Override
	public void validate(CAMPAIGN_TARGET campaignTarget) {
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
    public CAMPAIGN_TARGET fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public CAMPAIGN_TARGET fetchByChannelIdAndSlug(Long channelId, String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("channelId", channelId);
        filter.put("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public CAMPAIGN_TARGET fetchByUrl(String url) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("url", url);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public CAMPAIGN_TARGET fetchByShortUrl(String shortUrl) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("shortUrl", shortUrl);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


	@Override
	public List<CAMPAIGN_TARGET> fetchByCampaignId(Long campaignId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
		return fetchByCriteria(filter);
	}

    @Override
    public List<CAMPAIGN_TARGET> fetchByGroupId(Long groupId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CAMPAIGN_TARGET> fetchByChannelId(Long channelId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("channelId", channelId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CAMPAIGN_TARGET> fetchByLandingPageId(Long landingPageId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("landingPageId", landingPageId);
        return fetchByCriteria(filter);
    }


    @Override @Transactional
    public CAMPAIGN_TARGET create(CAMPAIGN_CHANNEL channel, CAMPAIGN_TARGET target) {
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
    public CAMPAIGN_TARGET create(
            CAMPAIGN_CHANNEL channel,
            String name,
            CAMPAIGN_TARGET.Type type,
            Long landingPageId,
            Date startDate,
            Date endDate
    ) {
        CAMPAIGN_TARGET target = getEntityObject();

        target.setName(name);
        target.setType(type);
        target.setLandingPageId(landingPageId);
        target.setEnabled(true);
        target.setStartDate(startDate);
        target.setEndDate(endDate);

        return create(channel, target);
    }
}
