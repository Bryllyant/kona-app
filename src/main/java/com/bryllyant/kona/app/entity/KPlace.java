package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KPlace extends KEntityObject  {

    Long getId();
    void setId(Long id);

    String getUid();
    void setUid(String uid);

    Long getOwnerId();
    void setOwnerId(Long ownerId);

    Long getCategoryId();
    void setCategoryId(Long categoryId);

    String getName();
    void setName(String name);

    String getSlug();
    void setSlug(String slug);

    Long getPhotoId();
    void setPhotoId(Long photoId);

    String getPhotoUrl();
    void setPhotoUrl(String photoUrl);

    String getThumbnailUrl();
    void setThumbnailUrl(String thumbnailUrl);

    String getDescription();
    void setDescription(String description);

    String getStreet1();
    void setStreet1(String street1);

    String getStreet2();
    void setStreet2(String street2);


    String getNeighborhood();
    void setNeighborhood(String neighborhood);

    String getBorough();
    void setBorough(String borough);

    String getCity();
    void setCity(String city);

    String getCounty();
    void setCounty(String county);

    String getState();
    void setState(String state);

    String getPostalCode();
    void setPostalCode(String postalCode);

    String getCountry();
    void setCountry(String country);

    String getTimeZone();
    void setTimeZone(String timeZone);

    String getFormattedAddress();
    void setFormattedAddress(String formattedAddress);

    String getPhoneNumber();
    void setPhoneNumber(String phoneNumber);

    String getEmail();
    void setEmail(String email);

    String getUrl();
    void setUrl(String url);

    String getSocialHandles();
    void setSocialHandles(String socialHandles);

    Double getLatitude();
    void setLatitude(Double latitude);

    Double getLongitude();
    void setLongitude(Double longitude);

    Integer getIndoorFloor();
    void setIndoorFloor(Integer indoorFloor);

    // JSON array of lat/lng tuples
    String getPerimeter();
    void setPerimeter(String perimeter);

    String getEncodedPerimeter();
    void setEncodedPerimeter(String encodedPerimeter);

    String getRefPlaceId();
    void setRefPlaceId(String refPlaceId);

    String getRefGoogleUrl();
    void setRefGoogleUrl(String refGoogleUrl);

    Integer getUtcOffset();
    void setUtcOffset(Integer utcOffset);

    Double getRating();
    void setRating(Double rating);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();
    void setUpdatedDate(Date updatedDate);

}