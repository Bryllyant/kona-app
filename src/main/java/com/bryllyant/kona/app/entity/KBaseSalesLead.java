package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.model.SocialHandle;

import java.util.Date;
import java.util.List;

public class KBaseSalesLead extends KBaseEntity implements KSalesLead {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long campaignId;
    private Long groupId;
    private Long channelId;
    private Long targetId;
    private Long analyticsId;
    private Long referredById;
    private Long userId;
    private boolean campaignConversion;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String mobileNumber;
    private List<SocialHandle> socialHandles;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String timeZone;
    private Integer utcOffset;
    private String formattedAddress;
    private String message;
    private String interests;
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
    public Long getCampaignId() {
        return campaignId;
    }

    @Override
    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    @Override
    public Long getGroupId() {
        return groupId;
    }

    @Override
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public Long getChannelId() {
        return channelId;
    }

    @Override
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    @Override
    public Long getTargetId() {
        return targetId;
    }

    @Override
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    @Override
    public Long getAnalyticsId() {
        return analyticsId;
    }

    @Override
    public void setAnalyticsId(Long analyticsId) {
        this.analyticsId = analyticsId;
    }

    @Override
    public Long getReferredById() {
        return referredById;
    }

    @Override
    public void setReferredById(Long referredById) {
        this.referredById = referredById;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean isCampaignConversion() {
        return campaignConversion;
    }

    @Override
    public void setCampaignConversion(boolean campaignConversion) {
        this.campaignConversion = campaignConversion;
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
    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public List<SocialHandle> getSocialHandles() {
        return socialHandles;
    }

    @Override
    public void setSocialHandles(List<SocialHandle> socialHandles) {
        this.socialHandles = socialHandles;
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
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
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
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getInterests() {
        return interests;
    }

    @Override
    public void setInterests(String interests) {
        this.interests = interests;
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

