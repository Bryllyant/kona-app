package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.model.SocialHandle;

import java.util.Date;
import java.util.List;

public class KBaseContact extends KBaseEntity implements KContact {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long ownerId;
    private Long refUserId;
    private Long placeId;
    private Long photoId;
    private String photoUrl;
    private String thumbnailUrl;

    private String firstName;
    private String lastName;
    private String displayName;
    private String gender;
    private Date birthDate;
    private String locale;
    private String timeZone;

    private String url;
    private String email;
    private String mobileNumber;
    private List<SocialHandle> SocialHandles;

    private boolean emailVerified;
    private boolean mobileVerified;

    private Date invitedDate;
    private Date registeredDate;
    private Date createdDate;
    private Date updatedDate;

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
    public Long getRefUserId() {
        return refUserId;
    }

    @Override
    public void setRefUserId(Long refUserId) {
        this.refUserId = refUserId;
    }

    @Override
    public Long getPlaceId() {
        return placeId;
    }

    @Override
    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
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
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String getLocale() {
        return locale;
    }

    @Override
    public void setLocale(String locale) {
        this.locale = locale;
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
    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public List<SocialHandle>  getSocialHandles() {
        return SocialHandles;
    }

    @Override
    public void setSocialHandles(List<SocialHandle> SocialHandles) {
        this.SocialHandles = SocialHandles;
    }

    @Override
    public boolean isEmailVerified() {
        return emailVerified;
    }

    @Override
    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @Override
    public boolean isMobileVerified() {
        return mobileVerified;
    }

    @Override
    public void setMobileVerified(boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    @Override
    public Date getInvitedDate() {
        return invitedDate;
    }

    @Override
    public void setInvitedDate(Date invitedDate) {
        this.invitedDate = invitedDate;
    }

    @Override
    public Date getRegisteredDate() {
        return registeredDate;
    }

    @Override
    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
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
