/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PromoCodeMapper;
import com.bryllyant.kona.app.entity.PromoCode;
import com.bryllyant.kona.app.entity.PromoCodeExample;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.PromoCodeService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(PromoCodeService.SERVICE_PATH)
public class PromoCodeServiceImpl extends KAbstractService<PromoCode,PromoCodeExample,PromoCodeMapper>
		implements PromoCodeService {
	
	private static Logger logger = LoggerFactory.getLogger(PromoCodeServiceImpl.class);
    
    @Autowired
    private PromoCodeMapper promoCodeMapper;
    

    @Override @SuppressWarnings("unchecked")
    protected PromoCodeMapper getMapper() {
        return promoCodeMapper;
    }


    @Override
    public void validate(PromoCode promoCode) {
        if (promoCode.getCreatedDate() == null) {
            promoCode.setCreatedDate(new Date());
        }

        if (promoCode.getUid() == null) {
            promoCode.setUid(uuid());
        }

        promoCode.setUpdatedDate(new Date());
    }


    @Override
    public PromoCode fetchByPromoCode(String promoCode) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("promoCode", promoCode);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public PromoCode fetchByCampaignChannelId(Long campaignChannelId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignChannelId", campaignChannelId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<PromoCode> fetchByPromoId(Long promoId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("promoId", promoId);
        return fetchByCriteria(filter);
    }


    @Override
    public PromoCode create(Long promoId, Long campaignChannelId, String promoCode) {
        PromoCode pc = new PromoCode();
        pc.setPromoId(promoId);
        pc.setCampaignChannelId(campaignChannelId);
        pc.setPromoCode(promoCode);

        return add(pc);
    }


}
