/*
 * Copyright (C) 2011 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPlace;
import com.bryllyant.kona.app.model.KGeoLocation;
import com.bryllyant.kona.app.model.KGeoPlace;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractPlaceService<PLACE extends KPlace, PLACE_EXAMPLE extends KEntityExample, MAPPER extends KMyBatisMapper<PLACE, PLACE_EXAMPLE>> extends KAbstractService<PLACE,PLACE_EXAMPLE,MAPPER>
		implements KPlaceService<PLACE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractPlaceService.class);


	
	protected abstract void updateCoords(Long placeId);

	protected abstract <S extends KGeocodingService> S getGeocodingService();


	
    private String getAddress(PLACE place) {
        String address = "";

        if (place.getStreet1() != null) {
            address += place.getStreet1() + " ";
        }

        if (place.getCity() != null) {
            address += place.getCity() + " ";
        }

        if (place.getState() != null) {
            address += place.getState() + " ";
        }

        if (place.getPostalCode() != null) {
            address += place.getPostalCode();
        }

        address = address.trim();

        if (address.length() == 0) {
            address = null;
        }

        return address;
    }
    


    protected void purgeEmpty(PLACE example) {
        if (example.getOwnerId() == null) return;

        List<PLACE> result = fetchByOwnerId(example.getOwnerId());

        // purge empty records
        for (PLACE place : result) {
            if (place.getName() == null && place.getPostalCode() == null) {
                remove(place);
            }
        }
    }



	@Override
	public void validate(PLACE place) {
	    if (place.getCreatedDate() == null) {
	        place.setCreatedDate(new Date());
	    }

	    place.setUpdatedDate(new Date());

	    if (place.getUid() == null) {
	        place.setUid(uuid());
	    }


	    
	    String address = getAddress(place);

        String oldAddress = null;

        if (place.getId() != null) {
            PLACE old = fetchById(place.getId());

            if (old != null) {
                oldAddress = getAddress(old);
            }
        }

        boolean updateAddress = false;

        if ((place.getLatitude() == null || place.getLongitude() == null) && address != null) {
            updateAddress = true;
        }

        if (address != null && oldAddress != null && !oldAddress.equals(address)) {
            updateAddress = true;
        }

        if (updateAddress) {
            KGeoPlace _place = getGeocodingService().findPlaceDetail(address);

            if (_place != null) {
                place.setLatitude(_place.getLatitude());
                place.setLongitude(_place.getLongitude());
                place.setFormattedAddress(_place.getAddress());
                place.setRefPlaceId(_place.getPlaceId());
                place.setRefGoogleUrl(_place.getGoogleUrl());
                place.setUtcOffset(_place.getUtcOffset());
                place.setRating(_place.getRating());

                if (place.getPhoneNumber() == null) {
                    place.setPhoneNumber(_place.getPhoneNumber());
                }

                if (place.getUrl() == null) {
                    place.setUrl(_place.getPlaceUrl());
                }

                if (place.getName() == null) {
                    place.setName(_place.getName());
                }

            } else {
                KGeoLocation location = getGeocodingService().geocode(address);

                if (location != null) {
                    place.setLatitude(location.getLatitude());
                    place.setLongitude(location.getLongitude());
                    place.setFormattedAddress(location.getAddress());
                }
            }
        }

        String slug = KInflector.getInstance().slug(place.getName());
        place.setSlug(slug);
	}



    @Override
    public PLACE add(PLACE place) {
        purgeEmpty(place);

        place = super.add(place);
        updateCoords(place.getId());
        return place;
    }
    


    @Override
    public PLACE update(PLACE place) {
        place = super.update(place);
        updateCoords(place.getId());
        return place;
    }



    @Override
    public PLACE fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public PLACE fetchBySlug(String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public PLACE fetchByRefPlaceId(String refPlaceId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("refPlaceId", refPlaceId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
    


    @Override
    public List<PLACE> fetchByOwnerId(Long ownerId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public List<PLACE> fetchByGroupId(Long groupId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }
}