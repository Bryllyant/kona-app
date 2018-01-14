package com.bryllyant.kona.app.api.model.place;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.data.model.KJsonModel;
import com.bryllyant.kona.data.model.KEntityModel;

import java.util.Date;


public class PlaceModel extends KJsonModel implements KEntityModel {

	private static final long serialVersionUID = 1L;

    // ----------------------------------------------------------------------
	private String uid;

	@RestdocsNotExpanded
	private UserModel addedBy;

	private String name;
	private String description;
	private String photoUrl;
	private String thumbnailUrl;

	private String street1;
	private String street2;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	private String formattedAddress;
	private String phoneNumber;
	private String email;
	private String url;
	
	private Double latitude;
	private Double longitude;
	
	private String refPlaceId;
	private String refGoogleUrl;
	private Integer utcOffset;
	private Integer rating;

	private Date createdDate;

    // ----------------------------------------------------------------------

    public static PlaceModel create(String uid) {
        PlaceModel model = new PlaceModel();
        model.setUid(uid);
        return model;
    }

    // ----------------------------------------------------------------------

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

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.set("street1", street1);
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.set("street2", street2);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.set("city", city);
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.set("state", state);
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.set("postalCode", postalCode);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.set("country", country);
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.set("formattedAddress", formattedAddress);
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.set("phoneNumber", phoneNumber);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.set("email", email);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.set("url", url);
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.set("createdDate", createdDate);
	}


	// ----------------------------------------------------------------------

}
