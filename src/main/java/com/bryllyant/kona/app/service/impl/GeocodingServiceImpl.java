/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.app.model.geo.Location;
import com.bryllyant.kona.app.model.geo.GooglePlace;
import com.bryllyant.kona.app.service.GeocodingService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.locale.KLocaleUtil;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;
import com.google.maps.model.Photo;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service(GeocodingService.SERVICE_PATH)
public class GeocodingServiceImpl implements GeocodingService {

	private static Logger logger = LoggerFactory.getLogger(GeocodingService.class);

	@Autowired
	private KConfig config;

    private GeoApiContext googleGeoApiContext = null;

	
	protected String getGoogleApiKey() {
	    return config.getString("google.apiKey");
	}


    private GeoApiContext getGoogleContext() {

        if (googleGeoApiContext == null) {
            String apiKey = getGoogleApiKey();
            googleGeoApiContext = new GeoApiContext.Builder().apiKey(apiKey).build();
        }

        return googleGeoApiContext;
    }



    @Override
    public Location geocode(String address) {
        Location location = null;

        try {
            // address = "1600 Amphitheatre Parkway Mountain View, CA 94043"
            GeocodingResult[] results =  GeocodingApi.geocode(getGoogleContext(), address).await();

            if (results.length > 0) {
                location = process(results[0]);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return location;
    }


    @Override
    public Location geocode(double latitude, double longitude) {
        Location location = null;

        try {
            GeocodingResult[] results = GeocodingApi.newRequest(getGoogleContext())
                    .latlng(new LatLng(latitude, longitude)).await();

            if (results.length > 0) {
                location = process(results[0]);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }

        return location;
    }


    private Location process(GeocodingResult result) {
        Geometry geometry = result.geometry;

        if (geometry == null) return null;

        LatLng latlng = geometry.location;

        if (latlng == null) return null;

        return Location.from(latlng.lat, latlng.lng, result.formattedAddress);
    }


    @Override
    public Location findNearestIntersection(String address) {
        Location location = geocode(address);
        if (location == null) return null;
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();
        return findNearestIntersection(latitude, longitude);
    }



    @Override
    public Location findNearestIntersection(double latitude, double longitude) {
        /*
        try {
			Intersection intersection = getGeoNamesWebService().findNearestIntersection(latitude, longitude);

			Map<String,Object> map = new HashMap<String,Object>();
            map.put("distance", intersection.getDistance());
            map.put("street1", intersection.getStreet1());
            map.put("street2", intersection.getStreet2());
            map.put("latitude", intersection.getLatitude());
            map.put("longitude", intersection.getLongitude());
            map.put("postalCode", intersection.getPostalCode());

            return map;
		} catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
		}
         */
        return null;
    }



    @Override
    public GooglePlace getPlace(String placeId) {
        try {
            PlaceDetails placeDetails = PlacesApi.placeDetails(getGoogleContext(), placeId).await();
            return toPlace(placeDetails);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }


    private GooglePlace toPlace(PlaceDetails placeDetails) {
        if (placeDetails == null) {
            return null;
        }

        GooglePlace googlePlace = new GooglePlace();

        googlePlace.setFormattedAddress(placeDetails.formattedAddress);

        if (placeDetails.internationalPhoneNumber != null) {
            try {
                String phoneNumber = KLocaleUtil.toE164PhoneNumber(placeDetails.internationalPhoneNumber);
                googlePlace.setPhoneNumber(phoneNumber);
            } catch (NumberParseException e) {
                logger.error(e.getMessage(), e);
            }
        }

        if (placeDetails.geometry != null) {
            googlePlace.setLatitude(placeDetails.geometry.location.lat);
            googlePlace.setLongitude(placeDetails.geometry.location.lng);
        }

        if (placeDetails.icon != null) {
            googlePlace.setIconUrl(placeDetails.icon.toString());
        }

        if (placeDetails.url != null) {
            googlePlace.setGoogleUrl(placeDetails.url.toString());
        }

        if (placeDetails.website != null) {
            googlePlace.setPlaceUrl(placeDetails.website.toString());
        }

        googlePlace.setName(placeDetails.name);
        googlePlace.setUtcOffset(placeDetails.utcOffset); // in minutes
        googlePlace.setPlaceId(placeDetails.placeId);
        googlePlace.setRating(Double.valueOf(placeDetails.rating));


        if (placeDetails.photos != null) {
            List<Media> photos = new ArrayList<>();

            for (Photo photo : placeDetails.photos) {
                Media media = new Media();

                //https://developers.google.com/places/web-service/photos
                String url = "https://maps.googleapis.com/maps/api/googlePlace/photo"
                        + "?photoreference=" + photo.photoReference
                        + "&key=" + getGoogleApiKey();

                media.setUrl(url);
                media.setWidth(photo.width);
                media.setHeight(photo.height);

                photos.add(media);
            }

            googlePlace.setPhotos(photos);
        }

        // TODO:
        //placeDetails.openingHours

        return googlePlace;
    }



    private GooglePlace toPlace(PlacesSearchResult result) {
        if (result == null) {
            return null;
        }

        GooglePlace googlePlace = new GooglePlace();

        googlePlace.setFormattedAddress(result.formattedAddress);

        if (result.geometry != null) {
            googlePlace.setLatitude(result.geometry.location.lat);
            googlePlace.setLongitude(result.geometry.location.lng);
        }

        if (result.icon != null) {
            googlePlace.setIconUrl(result.icon.toString());
        }

        googlePlace.setName(result.name);
        googlePlace.setPlaceId(result.placeId);
        googlePlace.setRating(Double.valueOf(result.rating));

        if (result.photos != null) {
            List<Media> photos = new ArrayList<>();

            for (Photo photo : result.photos) {
                Media media = new Media();

                //https://developers.google.com/places/web-service/photos
                String url = "https://maps.googleapis.com/maps/api/googlePlace/photo"
                        + "?photoreference=" + photo.photoReference
                        + "&key=" + getGoogleApiKey();

                media.setUrl(url);
                media.setWidth(photo.width);
                media.setHeight(photo.height);

                photos.add(media);
            }

            googlePlace.setPhotos(photos);
        }


        // TODO:
        //placeDetails.openingHours

        return googlePlace;
    }



    @Override
    public List<GooglePlace> findPlaces(String query) {
        List<GooglePlace> googlePlaces = new ArrayList<>();

        try {
            PlacesSearchResponse response = PlacesApi.textSearchQuery(getGoogleContext(), query).await();

            PlacesSearchResult[] results = response.results;

            for (PlacesSearchResult result : results) {
                googlePlaces.add(toPlace(result));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return googlePlaces;
    }



    @Override
    public GooglePlace findPlaceDetail(String query) {
        GooglePlace googlePlace = null;

        List<GooglePlace> googlePlaces = findPlaces(query);

        if (googlePlaces.size() > 0 && googlePlaces.get(0) != null) {
            googlePlace = getPlace(googlePlaces.get(0).getPlaceId());
        }

        return googlePlace;
    }
}
