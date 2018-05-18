package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class CampaignAnalytics extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.campaign_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long campaignId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.group_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long groupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.channel_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long channelId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.target_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long targetId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.reply_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long replyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.reply_message_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long replyMessageId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.category
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String category;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.action
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String action;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.label
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String label;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.value
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Double value;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.conversion_event
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private boolean conversionEvent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.conversion_user_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long conversionUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.conversion_email
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String conversionEmail;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.conversion_mobile_number
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String conversionMobileNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.source_mobile_number
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String sourceMobileNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.source_url
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String sourceUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.hostname
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String hostname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.user_agent
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String userAgent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.city
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String city;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.state
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.postal_code
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String postalCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.country
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String country;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.time_zone
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String timeZone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.utc_offset
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Integer utcOffset;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.latitude
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Double latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.longitude
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Double longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_analytics.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__campaign_analytics
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.uid
     *
     * @return the value of kona__campaign_analytics.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.uid
     *
     * @param uid the value for kona__campaign_analytics.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.campaign_id
     *
     * @return the value of kona__campaign_analytics.campaign_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getCampaignId() {
        return campaignId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.campaign_id
     *
     * @param campaignId the value for kona__campaign_analytics.campaign_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.group_id
     *
     * @return the value of kona__campaign_analytics.group_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.group_id
     *
     * @param groupId the value for kona__campaign_analytics.group_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.channel_id
     *
     * @return the value of kona__campaign_analytics.channel_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getChannelId() {
        return channelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.channel_id
     *
     * @param channelId the value for kona__campaign_analytics.channel_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.target_id
     *
     * @return the value of kona__campaign_analytics.target_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getTargetId() {
        return targetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.target_id
     *
     * @param targetId the value for kona__campaign_analytics.target_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.reply_id
     *
     * @return the value of kona__campaign_analytics.reply_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getReplyId() {
        return replyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.reply_id
     *
     * @param replyId the value for kona__campaign_analytics.reply_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.reply_message_id
     *
     * @return the value of kona__campaign_analytics.reply_message_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getReplyMessageId() {
        return replyMessageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.reply_message_id
     *
     * @param replyMessageId the value for kona__campaign_analytics.reply_message_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setReplyMessageId(Long replyMessageId) {
        this.replyMessageId = replyMessageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.category
     *
     * @return the value of kona__campaign_analytics.category
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getCategory() {
        return category;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.category
     *
     * @param category the value for kona__campaign_analytics.category
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.action
     *
     * @return the value of kona__campaign_analytics.action
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getAction() {
        return action;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.action
     *
     * @param action the value for kona__campaign_analytics.action
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.label
     *
     * @return the value of kona__campaign_analytics.label
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getLabel() {
        return label;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.label
     *
     * @param label the value for kona__campaign_analytics.label
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.value
     *
     * @return the value of kona__campaign_analytics.value
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Double getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.value
     *
     * @param value the value for kona__campaign_analytics.value
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.conversion_event
     *
     * @return the value of kona__campaign_analytics.conversion_event
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public boolean isConversionEvent() {
        return conversionEvent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.conversion_event
     *
     * @param conversionEvent the value for kona__campaign_analytics.conversion_event
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setConversionEvent(boolean conversionEvent) {
        this.conversionEvent = conversionEvent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.conversion_user_id
     *
     * @return the value of kona__campaign_analytics.conversion_user_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getConversionUserId() {
        return conversionUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.conversion_user_id
     *
     * @param conversionUserId the value for kona__campaign_analytics.conversion_user_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setConversionUserId(Long conversionUserId) {
        this.conversionUserId = conversionUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.conversion_email
     *
     * @return the value of kona__campaign_analytics.conversion_email
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getConversionEmail() {
        return conversionEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.conversion_email
     *
     * @param conversionEmail the value for kona__campaign_analytics.conversion_email
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setConversionEmail(String conversionEmail) {
        this.conversionEmail = conversionEmail == null ? null : conversionEmail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.conversion_mobile_number
     *
     * @return the value of kona__campaign_analytics.conversion_mobile_number
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getConversionMobileNumber() {
        return conversionMobileNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.conversion_mobile_number
     *
     * @param conversionMobileNumber the value for kona__campaign_analytics.conversion_mobile_number
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setConversionMobileNumber(String conversionMobileNumber) {
        this.conversionMobileNumber = conversionMobileNumber == null ? null : conversionMobileNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.source_mobile_number
     *
     * @return the value of kona__campaign_analytics.source_mobile_number
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getSourceMobileNumber() {
        return sourceMobileNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.source_mobile_number
     *
     * @param sourceMobileNumber the value for kona__campaign_analytics.source_mobile_number
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setSourceMobileNumber(String sourceMobileNumber) {
        this.sourceMobileNumber = sourceMobileNumber == null ? null : sourceMobileNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.source_url
     *
     * @return the value of kona__campaign_analytics.source_url
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.source_url
     *
     * @param sourceUrl the value for kona__campaign_analytics.source_url
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl == null ? null : sourceUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.hostname
     *
     * @return the value of kona__campaign_analytics.hostname
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.hostname
     *
     * @param hostname the value for kona__campaign_analytics.hostname
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.user_agent
     *
     * @return the value of kona__campaign_analytics.user_agent
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.user_agent
     *
     * @param userAgent the value for kona__campaign_analytics.user_agent
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.city
     *
     * @return the value of kona__campaign_analytics.city
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.city
     *
     * @param city the value for kona__campaign_analytics.city
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.state
     *
     * @return the value of kona__campaign_analytics.state
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.state
     *
     * @param state the value for kona__campaign_analytics.state
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.postal_code
     *
     * @return the value of kona__campaign_analytics.postal_code
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.postal_code
     *
     * @param postalCode the value for kona__campaign_analytics.postal_code
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.country
     *
     * @return the value of kona__campaign_analytics.country
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.country
     *
     * @param country the value for kona__campaign_analytics.country
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.time_zone
     *
     * @return the value of kona__campaign_analytics.time_zone
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.time_zone
     *
     * @param timeZone the value for kona__campaign_analytics.time_zone
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone == null ? null : timeZone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.utc_offset
     *
     * @return the value of kona__campaign_analytics.utc_offset
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Integer getUtcOffset() {
        return utcOffset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.utc_offset
     *
     * @param utcOffset the value for kona__campaign_analytics.utc_offset
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUtcOffset(Integer utcOffset) {
        this.utcOffset = utcOffset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.latitude
     *
     * @return the value of kona__campaign_analytics.latitude
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.latitude
     *
     * @param latitude the value for kona__campaign_analytics.latitude
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.longitude
     *
     * @return the value of kona__campaign_analytics.longitude
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.longitude
     *
     * @param longitude the value for kona__campaign_analytics.longitude
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.created_date
     *
     * @return the value of kona__campaign_analytics.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.created_date
     *
     * @param createdDate the value for kona__campaign_analytics.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_analytics.updated_date
     *
     * @return the value of kona__campaign_analytics.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_analytics.updated_date
     *
     * @param updatedDate the value for kona__campaign_analytics.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}