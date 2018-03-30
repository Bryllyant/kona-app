package com.bryllyant.kona.app.model;

import java.util.List;

import com.bryllyant.kona.app.entity.KMedia;

public interface KGeoPlace extends KGeoLocation {

    String getPlaceId(); 
    void setPlaceId(String placeId);

    String getIconUrl(); 
    void setIconUrl(String iconUrl);

    String getName(); 
    void setName(String name);

    String getPhoneNumber(); 
    void setPhoneNumber(String phoneNumber);

    String getGoogleUrl(); 
    void setGoogleUrl(String googleUrl);

    String getPlaceUrl(); 
    void setPlaceUrl(String placeUrl);

    Integer getUtcOffset(); 
    void setUtcOffset(Integer utcOffset);

    Double getRating(); 
    void setRating(Double rating);

    List<KMedia> getPhotos();
    void setPhotos(List<KMedia> photos);
}

