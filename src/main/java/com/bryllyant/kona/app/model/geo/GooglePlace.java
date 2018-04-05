package com.bryllyant.kona.app.model.geo;

import com.bryllyant.kona.app.entity.Media;

import java.util.List;

public class GooglePlace extends Location {
    private static final long serialVersionUID = 1L;

    private String placeId;
    private String iconUrl;
    private String name;
    private String phoneNumber;
    private String googleUrl;
    private String placeUrl;
    private Integer utcOffset;
    private Double rating;
    private List<Media> photos;


    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGoogleUrl() {
        return googleUrl;
    }

    public void setGoogleUrl(String googleUrl) {
        this.googleUrl = googleUrl;
    }

    public String getPlaceUrl() {
        return placeUrl;
    }

    public void setPlaceUrl(String placeUrl) {
        this.placeUrl = placeUrl;
    }

    public Integer getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(Integer utcOffset) {
        this.utcOffset = utcOffset;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Media> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Media> photos) {
        this.photos = photos;
    }
}

