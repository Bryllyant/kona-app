package com.bryllyant.kona.app.service;

import java.util.List;

import com.bryllyant.kona.app.model.KGeoLocation;
import com.bryllyant.kona.app.model.KGeoPlace;
import com.bryllyant.kona.remote.service.KService;


public interface KGeocodingService extends KService {

	KGeoLocation geocode(String address);

	KGeoLocation geocode(double latitude, double longitude);
    
	KGeoLocation findNearestIntersection(String address);
    
	KGeoLocation findNearestIntersection(double latitude, double longitude);


    KGeoPlace getPlace(String placeId);

	List<KGeoPlace> findPlaces(String query);

    KGeoPlace findPlaceDetail(String query);

}
