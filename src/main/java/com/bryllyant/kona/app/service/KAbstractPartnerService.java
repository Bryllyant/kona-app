package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPartner;
import com.bryllyant.kona.app.entity.KPlace;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class KAbstractPartnerService<
            PARTNER extends KPartner,
            PARTNER_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<PARTNER, PARTNER_EXAMPLE>,
            PLACE extends KPlace>
        extends KAbstractService<PARTNER, PARTNER_EXAMPLE,MAPPER>
        implements KPartnerService<PARTNER> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractPartnerService.class);

    protected abstract <S extends KPlaceService<PLACE>> S getPlaceService();


    @Override
    public void validate(PARTNER partner) {
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
    public PARTNER retire(PARTNER partner) {
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
    public PARTNER fetchBySlug(String slug) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public PARTNER fetchByUid(String uid) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }


    public List<PARTNER> fetchByParentId(Long parentId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("parentId", parentId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public List<PARTNER> fetchAllByParentId(Long parentId) {
        List<PARTNER> list = fetchByParentId(parentId);

        ArrayList<PARTNER> result = new ArrayList<>();

        for (PARTNER partner : list) {
            result.add(partner);

            List<PARTNER> children = fetchAllByParentId(partner.getId());

            if (children != null && children.size() > 0) {
                result.addAll(children);
            }
        }

        return result;
    }

    @Override
    public List<PARTNER> fetchProximate(
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

        List<PARTNER> all = fetchByCriteria(filter);

        Map<Long, List<PARTNER>> placeMap = new HashMap<>();

        for (PARTNER partner : all) {
            if (partner.getPlaceId() != null) {
                List<PARTNER> partners = placeMap.get(partner.getPlaceId());

                if (partners == null) {
                    partners = new ArrayList<>();
                    placeMap.put(partner.getPlaceId(), partners);
                }

                partners.add(partner);
            }
        }


        List<PLACE> places = getPlaceService().fetchProximate(
                latitude,
                longitude,
                radius,
                startDate,
                endDate,
                new ArrayList<>(placeMap.keySet())
        );

        Set<PARTNER> result = new HashSet<>();

        for (PLACE place : places) {
            List<PARTNER> partners = placeMap.get(place.getId());

            if (partners != null) {
                result.addAll(partners);
            }

        }

        return new ArrayList<>(result);
    }
}
