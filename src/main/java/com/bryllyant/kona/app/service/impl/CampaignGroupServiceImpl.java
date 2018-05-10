/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.CampaignGroupMapper;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignGroupExample;
import com.bryllyant.kona.app.service.CampaignGroupService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(CampaignGroupService.SERVICE_PATH)
public class CampaignGroupServiceImpl
		extends KAbstractService<CampaignGroup, CampaignGroupExample, CampaignGroupMapper>
		implements CampaignGroupService {
	
	private static Logger logger = LoggerFactory.getLogger(CampaignGroupServiceImpl.class);
    
    @Autowired
    private CampaignGroupMapper campaignGroupMapper;


    @Override @SuppressWarnings("unchecked")
    protected CampaignGroupMapper getMapper() {
        return campaignGroupMapper;
    }

    @Override
    public void validate(CampaignGroup campaignGroup) {
        if (campaignGroup.getCreatedDate() == null) {
            campaignGroup.setCreatedDate(new Date());
        }

        campaignGroup.setUpdatedDate(new Date());

        if (campaignGroup.getUid() == null) {
            campaignGroup.setUid(uuid());
        }

        String slug = KInflector.getInstance().slug(campaignGroup.getName());
        campaignGroup.setSlug(slug);

        if (campaignGroup.getConversionCount() == null) {
            campaignGroup.setConversionCount(0);
        }
    }

    @Override
    public CampaignGroup fetchByUid(String uid) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<CampaignGroup> fetchByCampaignId(Long campaignId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CampaignGroup> fetchByPartnerId(Long partnerId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("partnerId", partnerId);
        return fetchByCriteria(filter);
    }

    @Override @Transactional
    public CampaignGroup create(Campaign campaign, CampaignGroup campaignGroup) {
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
    public CampaignGroup create(
            Campaign campaign,
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

        CampaignGroup group = getEntityObject();
        group.setCampaignId(campaign.getId());
        group.setName(name);
        group.setPartnerId(partnerId);
        group.setEnabled(true);
        group.setStartDate(startDate);
        group.setEndDate(endDate);

        return add(group);
    }
}
