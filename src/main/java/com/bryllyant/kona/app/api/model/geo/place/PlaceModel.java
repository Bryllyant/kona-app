package com.bryllyant.kona.app.api.model.geo.place;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.model.SocialHandle;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;
import java.util.List;


public class PlaceModel extends KJsonModel implements KEntityModel {

	private static final long serialVersionUID = 1L;

    public static class Address extends KJsonModel {
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
        private Double latitude;
        private Double longitude;
    }

	private String uid;

	@RestdocsNotExpanded
	private UserModel addedBy;

	private String name;
    private String slug;
    private String category;
    private List<String> tags;
    private String description;

	private String photoUrl;
	private String thumbnailUrl;

    private Address address;

	private String url;
    private String email;
    private String phoneNumber;
    private List<SocialHandle> socialHandles;

	private Double latitude;
	private Double longitude;
	
	private String refPlaceId;
	private String refGoogleUrl;
	private Integer utcOffset;
	private Integer rating;

	// number of people that dwell in this space at any given time
	private Integer population;

	private Date createdDate;


    public static PlaceModel create(String uid) {
        PlaceModel model = new PlaceModel();
        model.setUid(uid);
        return model;
    }


    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.set("uid", uid);
    }

    public UserModel getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(UserModel addedBy) {
        this.set("addedBy", addedBy);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.set("name", name);
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.set("slug", slug);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.set("category", category);
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.set("tags", tags);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.set("description", description);
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.set("photoUrl", photoUrl);
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.set("thumbnailUrl", thumbnailUrl);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.set("address", address);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.set("url", url);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.set("email", email);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.set("phoneNumber", phoneNumber);
    }

    public List<SocialHandle> getSocialHandles() {
        return socialHandles;
    }

    public void setSocialHandles(List<SocialHandle> socialHandles) {
        this.set("socialHandles", socialHandles);
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.set("latitude", latitude);
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.set("longitude", longitude);
    }

    public String getRefPlaceId() {
        return refPlaceId;
    }

    public void setRefPlaceId(String refPlaceId) {
        this.set("refPlaceId", refPlaceId);
    }

    public String getRefGoogleUrl() {
        return refGoogleUrl;
    }

    public void setRefGoogleUrl(String refGoogleUrl) {
        this.set("refGoogleUrl", refGoogleUrl);
    }

    public Integer getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(Integer utcOffset) {
        this.set("utcOffset", utcOffset);
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.set("rating", rating);
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.set("population", population);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }
}
