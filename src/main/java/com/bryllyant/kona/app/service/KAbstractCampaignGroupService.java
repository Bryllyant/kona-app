package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCampaign;
import com.bryllyant.kona.app.entity.KCampaignGroup;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


public abstract class KAbstractCampaignGroupService<
        CAMPAIGN_GROUP extends KCampaignGroup,
        CAMPAIGN_GROUP_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<CAMPAIGN_GROUP, CAMPAIGN_GROUP_EXAMPLE>,
        CAMPAIGN extends KCampaign>
        extends KAbstractService<CAMPAIGN_GROUP,CAMPAIGN_GROUP_EXAMPLE,MAPPER>
		implements KCampaignGroupService<CAMPAIGN_GROUP,CAMPAIGN> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractCampaignGroupService.class);

    @Override
    public void validate(CAMPAIGN_GROUP campaignGroup) {
        if (campaignGroup.getCreatedDate() == null) {
            campaignGroup.setCreatedDate(new Date());
        }

        campaignGroup.setUpdatedDate(new Date());

        if (campaignGroup.getUid() == null) {
            campaignGroup.setUid(uuid());
        }

        if (campaignGroup.getConversionCount() == null) {
            campaignGroup.setConversionCount(0);
        }
    }

    @Override
    public CAMPAIGN_GROUP fetchByUid(String uid) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<CAMPAIGN_GROUP> fetchByCampaignId(Long campaignId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CAMPAIGN_GROUP> fetchByPartnerId(Long partnerId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("partnerId", partnerId);
        return fetchByCriteria(filter);
    }

    @Override @Transactional
    public CAMPAIGN_GROUP create(CAMPAIGN campaign, CAMPAIGN_GROUP campaignGroup) {
        return create(
                campaign,
                campaignGroup.getName(),
                campaignGroup.getPartnerId(),
                campaignGroup.getDescription(),
                campaignGroup.getStartDate(),
                campaignGroup.getEndDate()
        );
    }

    @Override @Transactional
    public CAMPAIGN_GROUP create(
            CAMPAIGN campaign,
            String name,
            Long partnerId,
            String description,
            Date startDate,
            Date endDate
    ) {

        if (name == null) {
            name = campaign.getName().trim() + " Group";
        }

        if (startDate == null) {
            startDate = campaign.getStartDate();
        }

        if (endDate == null) {
            endDate = campaign.getEndDate();
        }

        CAMPAIGN_GROUP group = getEntityObject();
        group.setCampaignId(campaign.getId());
        group.setName(name);
        group.setPartnerId(partnerId);
        group.setEnabled(true);
        group.setStartDate(startDate);
        group.setEndDate(endDate);

        return add(group);
    }
}
