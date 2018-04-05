/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PartnerMapper;
import com.bryllyant.kona.app.entity.Partner;
import com.bryllyant.kona.app.entity.PartnerExample;
import com.bryllyant.kona.app.entity.Place;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.PartnerService;
import com.bryllyant.kona.app.service.PlaceService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service(PartnerService.SERVICE_PATH)
public class PartnerServiceImpl 
		extends KAbstractService<Partner, PartnerExample, PartnerMapper>
		implements PartnerService {
	
	private static Logger logger = LoggerFactory.getLogger(PartnerServiceImpl.class);
    
    @Autowired
    private PartnerMapper partnerMapper;

    @Autowired
    private PlaceService placeService;


    @Override @SuppressWarnings("unchecked")
    protected PartnerMapper getMapper() {
        return partnerMapper;
    }


    @Override
    public void validate(Partner partner) {
        if (partner.getCreatedDate() == null) {
            partner.setCreatedDate(new Date());
        }

        if (partner.getUid() == null) {
            partner.setUid(uuid());
        }

        String slug = KInflector.getInstance().slug(partner.getName());

        partner.setSlug(slug);
    }


    @Override
    public Partner retire(Partner partner) {
        // fetch fresh object
        partner = fetchById(partner.getId());

        // this isn't a slip up in logic. it's possible that the app object passed in
        // is not retired but by the time this call is made and the object is refreshed
        // is has been retired.  if so, fetchById should return null.
        // return app if already retired
        if (partner == null || partner.getDeletedDate() != null) {
            return partner;
        }

        // NOTE: we need uuid here in case multiple apps with the same name are deleted.
        // first app called test is deleted, then second app created called test gets deleted.
        String prefix = "$RETIRED_" + uuid() + "_";
        partner.setSlug(prefix + partner.getSlug());
        partner.setName(prefix + partner.getName());
        partner.setEnabled(false);
        partner.setDeletedDate(new Date());
        partner = update(partner);

        return partner;
    }



    @Override
    public Partner fetchBySlug(String slug) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public Partner fetchByUid(String uid) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    public List<Partner> fetchByParentId(Long parentId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("parentId", parentId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public List<Partner> fetchAllByParentId(Long parentId) {
        List<Partner> list = fetchByParentId(parentId);

        ArrayList<Partner> result = new ArrayList<>();

        for (Partner partner : list) {
            result.add(partner);

            List<Partner> children = fetchAllByParentId(partner.getId());

            if (children != null && children.size() > 0) {
                result.addAll(children);
            }
        }

        return result;
    }

    @Override
    public List<Partner> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    ) {

        Map<String, Object> filter = null;

        if (objectIdList != null && objectIdList.size() > 0) {
            filter = KMyBatisUtil.createFilter("|id", objectIdList);
        }

        List<Partner> all = fetchByCriteria(filter);

        Map<Long, List<Partner>> placeMap = new HashMap<>();

        for (Partner partner : all) {
            if (partner.getPlaceId() != null) {
                List<Partner> partners = placeMap.get(partner.getPlaceId());

                if (partners == null) {
                    partners = new ArrayList<>();
                    placeMap.put(partner.getPlaceId(), partners);
                }

                partners.add(partner);
            }
        }


        List<Place> places = placeService.fetchProximate(
                latitude,
                longitude,
                radius,
                startDate,
                endDate,
                new ArrayList<>(placeMap.keySet())
        );

        Set<Partner> result = new HashSet<>();

        for (Place place : places) {
            List<Partner> partners = placeMap.get(place.getId());

            if (partners != null) {
                result.addAll(partners);
            }

        }

        return new ArrayList<>(result);
    }

}
