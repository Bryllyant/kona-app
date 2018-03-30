package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseCampaignAnalytics extends KBaseEntity implements KCampaignAnalytics {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long campaignId;
    private Long groupId;
    private Long channelId;
    private Long targetId;
    private Long replyId;
    private Long replyMessageId;
    private String category;
    private String action;
    private String label;
    private Double value;
    private boolean conversionEvent;
    private Long conversionUserId;
    private String conversionEmail;
    private String conversionMobileNumber;
    private String sourceMobileNumber;
    private String sourceUrl;
    private String hostname;
    private String userAgent;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String timeZone;
    private Integer utcOffset;
    private Double latitude;
    private Double longitude;
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
    public Long getReplyId() {
        return replyId;
    }

    @Override
    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    @Override
    public Long getReplyMessageId() {
        return replyMessageId;
    }

    @Override
    public void setReplyMessageId(Long replyMessageId) {
        this.replyMessageId = replyMessageId;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean isConversionEvent() {
        return conversionEvent;
    }

    @Override
    public void setConversionEvent(boolean conversionEvent) {
        this.conversionEvent = conversionEvent;
    }

    @Override
    public Long getConversionUserId() {
        return conversionUserId;
    }

    @Override
    public void setConversionUserId(Long conversionUserId) {
        this.conversionUserId = conversionUserId;
    }

    @Override
    public String getConversionEmail() {
        return conversionEmail;
    }

    @Override
    public void setConversionEmail(String conversionEmail) {
        this.conversionEmail = conversionEmail;
    }

    @Override
    public String getConversionMobileNumber() {
        return conversionMobileNumber;
    }

    @Override
    public void setConversionMobileNumber(String conversionMobileNumber) {
        this.conversionMobileNumber = conversionMobileNumber;
    }

    @Override
    public String getSourceMobileNumber() {
        return sourceMobileNumber;
    }

    @Override
    public void setSourceMobileNumber(String sourceMobileNumber) {
        this.sourceMobileNumber = sourceMobileNumber;
    }

    @Override
    public String getSourceUrl() {
        return sourceUrl;
    }

    @Override
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
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
