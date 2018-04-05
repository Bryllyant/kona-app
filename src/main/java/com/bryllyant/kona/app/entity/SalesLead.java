package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SalesLead extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.uid
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.campaign_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private Long campaignId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.group_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private Long groupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.channel_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private Long channelId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.target_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private Long targetId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.analytics_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private Long analyticsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.campaign_conversion
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private boolean campaignConversion;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.referred_by_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private Long referredById;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.user_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.first_name
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String firstName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.last_name
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String lastName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.email
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.phone_number
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String phoneNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.mobile_number
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String mobileNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.social_handles
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private List socialHandles;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.street1
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String street1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.street2
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String street2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.city
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String city;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.state
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.postal_code
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String postalCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.country
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String country;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.time_zone
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String timeZone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.utc_offset
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private Integer utcOffset;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.formatted_address
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String formattedAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.message
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String message;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.interests
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private String interests;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.created_date
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__sales_lead.updated_date
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__sales_lead
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.uid
     *
     * @return the value of kona__sales_lead.uid
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.uid
     *
     * @param uid the value for kona__sales_lead.uid
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.campaign_id
     *
     * @return the value of kona__sales_lead.campaign_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public Long getCampaignId() {
        return campaignId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.campaign_id
     *
     * @param campaignId the value for kona__sales_lead.campaign_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.group_id
     *
     * @return the value of kona__sales_lead.group_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.group_id
     *
     * @param groupId the value for kona__sales_lead.group_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.channel_id
     *
     * @return the value of kona__sales_lead.channel_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public Long getChannelId() {
        return channelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.channel_id
     *
     * @param channelId the value for kona__sales_lead.channel_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.target_id
     *
     * @return the value of kona__sales_lead.target_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public Long getTargetId() {
        return targetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.target_id
     *
     * @param targetId the value for kona__sales_lead.target_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.analytics_id
     *
     * @return the value of kona__sales_lead.analytics_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public Long getAnalyticsId() {
        return analyticsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.analytics_id
     *
     * @param analyticsId the value for kona__sales_lead.analytics_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setAnalyticsId(Long analyticsId) {
        this.analyticsId = analyticsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.campaign_conversion
     *
     * @return the value of kona__sales_lead.campaign_conversion
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public boolean isCampaignConversion() {
        return campaignConversion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.campaign_conversion
     *
     * @param campaignConversion the value for kona__sales_lead.campaign_conversion
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setCampaignConversion(boolean campaignConversion) {
        this.campaignConversion = campaignConversion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.referred_by_id
     *
     * @return the value of kona__sales_lead.referred_by_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public Long getReferredById() {
        return referredById;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.referred_by_id
     *
     * @param referredById the value for kona__sales_lead.referred_by_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setReferredById(Long referredById) {
        this.referredById = referredById;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.user_id
     *
     * @return the value of kona__sales_lead.user_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.user_id
     *
     * @param userId the value for kona__sales_lead.user_id
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.first_name
     *
     * @return the value of kona__sales_lead.first_name
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.first_name
     *
     * @param firstName the value for kona__sales_lead.first_name
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.last_name
     *
     * @return the value of kona__sales_lead.last_name
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.last_name
     *
     * @param lastName the value for kona__sales_lead.last_name
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.email
     *
     * @return the value of kona__sales_lead.email
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.email
     *
     * @param email the value for kona__sales_lead.email
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.phone_number
     *
     * @return the value of kona__sales_lead.phone_number
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.phone_number
     *
     * @param phoneNumber the value for kona__sales_lead.phone_number
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.mobile_number
     *
     * @return the value of kona__sales_lead.mobile_number
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.mobile_number
     *
     * @param mobileNumber the value for kona__sales_lead.mobile_number
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber == null ? null : mobileNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.social_handles
     *
     * @return the value of kona__sales_lead.social_handles
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public List getSocialHandles() {
        return socialHandles;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.social_handles
     *
     * @param socialHandles the value for kona__sales_lead.social_handles
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setSocialHandles(List socialHandles) {
        this.socialHandles = socialHandles;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.street1
     *
     * @return the value of kona__sales_lead.street1
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getStreet1() {
        return street1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.street1
     *
     * @param street1 the value for kona__sales_lead.street1
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setStreet1(String street1) {
        this.street1 = street1 == null ? null : street1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.street2
     *
     * @return the value of kona__sales_lead.street2
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getStreet2() {
        return street2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.street2
     *
     * @param street2 the value for kona__sales_lead.street2
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setStreet2(String street2) {
        this.street2 = street2 == null ? null : street2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.city
     *
     * @return the value of kona__sales_lead.city
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.city
     *
     * @param city the value for kona__sales_lead.city
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.state
     *
     * @return the value of kona__sales_lead.state
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.state
     *
     * @param state the value for kona__sales_lead.state
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.postal_code
     *
     * @return the value of kona__sales_lead.postal_code
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.postal_code
     *
     * @param postalCode the value for kona__sales_lead.postal_code
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.country
     *
     * @return the value of kona__sales_lead.country
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.country
     *
     * @param country the value for kona__sales_lead.country
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.time_zone
     *
     * @return the value of kona__sales_lead.time_zone
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.time_zone
     *
     * @param timeZone the value for kona__sales_lead.time_zone
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone == null ? null : timeZone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.utc_offset
     *
     * @return the value of kona__sales_lead.utc_offset
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public Integer getUtcOffset() {
        return utcOffset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.utc_offset
     *
     * @param utcOffset the value for kona__sales_lead.utc_offset
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setUtcOffset(Integer utcOffset) {
        this.utcOffset = utcOffset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.formatted_address
     *
     * @return the value of kona__sales_lead.formatted_address
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getFormattedAddress() {
        return formattedAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.formatted_address
     *
     * @param formattedAddress the value for kona__sales_lead.formatted_address
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress == null ? null : formattedAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.message
     *
     * @return the value of kona__sales_lead.message
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.message
     *
     * @param message the value for kona__sales_lead.message
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.interests
     *
     * @return the value of kona__sales_lead.interests
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public String getInterests() {
        return interests;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.interests
     *
     * @param interests the value for kona__sales_lead.interests
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setInterests(String interests) {
        this.interests = interests == null ? null : interests.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.created_date
     *
     * @return the value of kona__sales_lead.created_date
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.created_date
     *
     * @param createdDate the value for kona__sales_lead.created_date
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__sales_lead.updated_date
     *
     * @return the value of kona__sales_lead.updated_date
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__sales_lead.updated_date
     *
     * @param updatedDate the value for kona__sales_lead.updated_date
     *
     * @mbg.generated Thu Apr 05 05:51:02 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}