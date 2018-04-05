package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.model.geo.Location;
import com.bryllyant.kona.app.model.geo.GooglePlace;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface GeocodingService extends KService {
	String SERVICE_PATH = "rpc/GeocodingService";

    Location geocode(String address);

    Location geocode(double latitude, double longitude);

    Location findNearestIntersection(String address);

    Location findNearestIntersection(double latitude, double longitude);

    GooglePlace getPlace(String placeId);

    List<GooglePlace> findPlaces(String query);

    GooglePlace findPlaceDetail(String query);

}
