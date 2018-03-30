package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBasePlace extends KBaseEntity implements KPlace {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long ownerId;
    private Long categoryId;
    private String name;
    private String slug;
    private Long photoId;
    private String photoUrl;
    private String thumbnailUrl;
    private String description;
    private String street1;
    private String street2;
    private String neighborhood;
    private String borough;
    private String city;
    private String county;
    private String state;
    private String postalCode;
    private String country;
    private String timeZone;
    private Integer utcOffset;
    private String formattedAddress;
    private String url;
    private String email;
    private String phoneNumber;
    private String socialHandles;
    private Double latitude;
    private Double longitude;
    private Integer indoorFloor;
    private String perimeter;
    private String encodedPerimeter;
    private String refPlaceId;
    private String refGoogleUrl;
    private Double rating;
    private Date createdDate;
    private Date updatedDate;

    // in meters
    private Double distance;

    // hold distance in search results
    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public Long getOwnerId() {
        return ownerId;
    }

    @Override
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public Long getCategoryId() {
        return categoryId;
    }

    @Override
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSlug() {
        return slug;
    }

    @Override
    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public Long getPhotoId() {
        return photoId;
    }

    @Override
    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    @Override
    public String getPhotoUrl() {
        return photoUrl;
    }

    @Override
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @Override
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getStreet1() {
        return street1;
    }

    @Override
    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    @Override
    public String getStreet2() {
        return street2;
    }

    @Override
    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    @Override
    public String getNeighborhood() {
        return neighborhood;
    }

    @Override
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    @Override
    public String getBorough() {
        return borough;
    }

    @Override
    public void setBorough(String borough) {
        this.borough = borough;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getCounty() {
        return county;
    }

    @Override
    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getTimeZone() {
        return timeZone;
    }

    @Override
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public Integer getUtcOffset() {
        return utcOffset;
    }

    @Override
    public void setUtcOffset(Integer utcOffset) {
        this.utcOffset = utcOffset;
    }

    @Override
    public String getFormattedAddress() {
        return formattedAddress;
    }

    @Override
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getSocialHandles() {
        return socialHandles;
    }

    @Override
    public void setSocialHandles(String socialHandles) {
        this.socialHandles = socialHandles;
    }

    @Override
    public Double getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public Double getLongitude() {
        return longitude;
    }

    @Override
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public Integer getIndoorFloor() {
        return indoorFloor;
    }

    @Override
    public void setIndoorFloor(Integer indoorFloor) {
        this.indoorFloor = indoorFloor;
    }

    @Override
    public String getPerimeter() {
        return perimeter;
    }

    @Override
    public void setPerimeter(String perimeter) {
        this.perimeter = perimeter;
    }

    @Override
    public String getEncodedPerimeter() {
        return encodedPerimeter;
    }

    @Override
    public void setEncodedPerimeter(String encodedPerimeter) {
        this.encodedPerimeter = encodedPerimeter;
    }

    @Override
    public String getRefPlaceId() {
        return refPlaceId;
    }

    @Override
    public void setRefPlaceId(String refPlaceId) {
        this.refPlaceId = refPlaceId;
    }

    @Override
    public String getRefGoogleUrl() {
        return refGoogleUrl;
    }

    @Override
    public void setRefGoogleUrl(String refGoogleUrl) {
        this.refGoogleUrl = refGoogleUrl;
    }

    @Override
    public Double getRating() {
        return rating;
    }

    @Override
    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Date getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
