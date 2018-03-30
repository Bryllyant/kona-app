package com.bryllyant.kona.app.model;

import java.io.Serializable;
import java.util.List;

import com.bryllyant.kona.app.entity.KMedia;

public class KBaseGeoPlace extends KBaseGeoLocation implements Serializable, KGeoPlace {
    private static final long serialVersionUID = 1L;

    private String placeId;
    private String iconUrl;
    private String name;
    private String phoneNumber;
    private String googleUrl;
    private String placeUrl;
    private Integer utcOffset;
    private Double rating;
    private List<KMedia> photos;
	

    /**
     * @return the placeId
     */
    public String getPlaceId() {
        return placeId;
    }


    /**
     * @param placeId the placeId to set
     */
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }


    /**
     * @return the iconUrl
     */
    public String getIconUrl() {
        return iconUrl;
    }


    /**
     * @param iconUrl the iconUrl to set
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }


    /**
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return the photos
     */
    public List<KMedia> getPhotos() {
        return photos;
    }


    /**
     * @param photos the photos to set
     */
    public void setPhotos(List<KMedia> photos) {
        this.photos = photos;
    }


    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /**
     * @return the googleUrl
     */
    public String getGoogleUrl() {
        return googleUrl;
    }


    /**
     * @param googleUrl the googleUrl to set
     */
    public void setGoogleUrl(String googleUrl) {
        this.googleUrl = googleUrl;
    }


    /**
     * @return the placeUrl
     */
    public String getPlaceUrl() {
        return placeUrl;
    }


    /**
     * @param placeUrl the placeUrl to set
     */
    public void setPlaceUrl(String placeUrl) {
        this.placeUrl = placeUrl;
    }


    /**
     * @return the utcOffset
     */
    public Integer getUtcOffset() {
        return utcOffset;
    }


    /**
     * @param utcOffset the utcOffset to set
     */
    public void setUtcOffset(Integer utcOffset) {
        this.utcOffset = utcOffset;
    }


    /**
     * @return the rating
     */
    public Double getRating() {
        return rating;
    }


    /**
     * @param rating the rating to set
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }




}

