/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.PlaceMapper;
import com.bryllyant.kona.app.entity.Place;
import com.bryllyant.kona.app.entity.PlaceExample;
import com.bryllyant.kona.app.model.geo.Location;
import com.bryllyant.kona.app.model.geo.GooglePlace;
import com.bryllyant.kona.app.service.GeocodingService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.PlaceService;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(PlaceService.SERVICE_PATH)
public class PlaceServiceImpl 
		extends KAbstractService<Place,PlaceExample,PlaceMapper>
		implements PlaceService {
	
	private static Logger logger = LoggerFactory.getLogger(PlaceServiceImpl.class);

	@Autowired
	private PlaceMapper placeMapper;
	
	@Autowired
	GeocodingService geocodingService;


	@Override @SuppressWarnings("unchecked")
	protected PlaceMapper getMapper() {
		return placeMapper;
	}
	

    protected void updateCoords(Long placeId) {
        getMapper().updateCoords(placeId);
    }


    @Override 
    public List<Place> fetchProximate(
    		Double latitude,
			Double longitude,
			Double radius,
			Date startDate,
			Date endDate,
			List<Long> objectIdList
	) {
        return getMapper().selectProximate(
        		latitude,
				longitude,
				radius,
				startDate,
				endDate,
                objectIdList
        );
    }


    private String getAddress(Place place) {
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



    protected void purgeEmpty(Place example) {
        if (example.getOwnerId() == null) return;

        List<Place> result = fetchByOwnerId(example.getOwnerId());

        // purge empty records
        for (Place place : result) {
            if (place.getName() == null && place.getPostalCode() == null) {
                remove(place);
            }
        }
    }



    @Override
    public void validate(Place place) {
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
            Place old = fetchById(place.getId());

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
            GooglePlace _place = geocodingService.findPlaceDetail(address);

            if (_place != null) {
                place.setLatitude(_place.getLatitude());
                place.setLongitude(_place.getLongitude());
                place.setFormattedAddress(_place.getFormattedAddress());
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
                Location location = geocodingService.geocode(address);

                if (location != null) {
                    place.setLatitude(location.getLatitude());
                    place.setLongitude(location.getLongitude());
                    place.setFormattedAddress(location.getFormattedAddress());
                }
            }
        }

        String slug = KInflector.getInstance().slug(place.getName());
        place.setSlug(slug);
    }



    @Override
    public Place add(Place place) {
        purgeEmpty(place);

        place = super.add(place);
        updateCoords(place.getId());
        return place;
    }



    @Override
    public Place update(Place place) {
        place = super.update(place);
        updateCoords(place.getId());
        return place;
    }



    @Override
    public Place fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public Place fetchBySlug(String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public Place fetchByRefPlaceId(String refPlaceId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("refPlaceId", refPlaceId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public List<Place> fetchByOwnerId(Long ownerId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("ownerId", ownerId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }



    @Override
    public List<Place> fetchByGroupId(Long groupId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

}
