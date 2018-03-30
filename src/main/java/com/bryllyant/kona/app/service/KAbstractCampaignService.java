package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCampaign;
import com.bryllyant.kona.app.entity.KCampaignGroup;
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

public abstract class KAbstractCampaignService<
        CAMPAIGN extends KCampaign,
        CAMPAIGN_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<CAMPAIGN, CAMPAIGN_EXAMPLE>,
        CAMPAIGN_GROUP extends KCampaignGroup>
        extends KAbstractService<CAMPAIGN,CAMPAIGN_EXAMPLE,MAPPER>
		implements KCampaignService<CAMPAIGN> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractCampaignService.class);

    protected abstract <S extends KCampaignGroupService<CAMPAIGN_GROUP,CAMPAIGN>> S getCampaignGroupService();

	@Override 
	public void validate(CAMPAIGN campaign) {
		if (campaign.getCreatedDate() == null) {
			campaign.setCreatedDate(new Date());
		}

		campaign.setUpdatedDate(new Date());
        
		if (campaign.getUid() == null) {
			campaign.setUid(uuid());
		}

		if (campaign.getConversionCount() == null) {
		    campaign.setConversionCount(0);
        }

        String slug = KInflector.getInstance().slug(campaign.getName());
        campaign.setSlug(slug);
	}

    @Override
    public CAMPAIGN fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

	@Override
	public CAMPAIGN fetchBySlug(String slug) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
	}

    @Override
    public List<CAMPAIGN> fetchByOwnerId(Long ownerId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    // create() has side effects, add() does not.
    @Override @Transactional
    public CAMPAIGN create(CAMPAIGN campaign) {
        return create(
                campaign.getOwnerId(),
                campaign.getName(),
                campaign.getGoal(),
                campaign.getKpi(),
                campaign.getConversionTarget(),
                campaign.getStartDate(),
                campaign.getEndDate()
        );
    }


    @Override @Transactional
    public CAMPAIGN create(
            Long ownerId,
            String name,
            CAMPAIGN.Goal goal,
            CAMPAIGN.KPI kpi,
            Integer conversionTarget,
            Date startDate,
            Date endDate
    ) {
        String slug = KInflector.getInstance().slug(name);

        CAMPAIGN campaign = fetchBySlug(slug);

        if (campaign != null) {
            throw new KServiceException("Campaign name already exists: " + name);
        }

        campaign = getEntityObject();
        campaign.setOwnerId(ownerId);
        campaign.setName(name);
        campaign.setSlug(slug);
        campaign.setGoal(goal);
        campaign.setKpi(kpi);
        campaign.setConversionTarget(conversionTarget);
        campaign.setEnabled(true);
        campaign.setStartDate(startDate);
        campaign.setEndDate(endDate);

        campaign = add(campaign);

        // create default campaign group
        getCampaignGroupService().create(campaign, null, null, null, null, null);

        return campaign;
    }
}
