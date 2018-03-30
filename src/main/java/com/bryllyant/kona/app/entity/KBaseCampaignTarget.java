package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseCampaignTarget extends KBaseEntity implements KCampaignTarget {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long campaignId;
    private Long groupId;
    private Long channelId;
    private Long landingPageId;
    private Type type;
    private String name;
    private String slug;
    private String url;
    private String analyticsTrackingId;
    private String conversionPixel;
    private Integer conversionCount;
    private boolean enabled;
    private Date startDate;
    private Date endDate;
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
    public Long getLandingPageId() {
        return landingPageId;
    }

    @Override
    public void setLandingPageId(Long landingPageId) {
        this.landingPageId = landingPageId;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
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
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getAnalyticsTrackingId() {
        return analyticsTrackingId;
    }

    @Override
    public void setAnalyticsTrackingId(String analyticsTrackingId) {
        this.analyticsTrackingId = analyticsTrackingId;
    }

    @Override
    public String getConversionPixel() {
        return conversionPixel;
    }

    @Override
    public void setConversionPixel(String conversionPixel) {
        this.conversionPixel = conversionPixel;
    }

    @Override
    public Integer getConversionCount() {
        return conversionCount;
    }

    @Override
    public void setConversionCount(Integer conversionCount) {
        this.conversionCount = conversionCount;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
