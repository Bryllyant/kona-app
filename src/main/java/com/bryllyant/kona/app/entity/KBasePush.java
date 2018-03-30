package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBasePush extends KBaseEntity implements KPush {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long userId;
    private Long deviceId;
    private Long campaignId;
    private Long campaignGroupId;
    private Long campaignChannelId;
    private Platform platform;
    private boolean sandbox;
    private String providerMessageId;
    private String title;
    private String message;
    private String imageUrl;
    private String actionUrl;
    private String status;
    private String errorCode;
    private String errorMessage;
    private boolean failed;
    private boolean delivered;
    private boolean optedOut;
    private boolean viewed;
    private Date sentDate;
    private Date deliveredDate;
    private Date viewedDate;
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
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Long getDeviceId() {
        return deviceId;
    }

    @Override
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
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
    public Long getCampaignGroupId() {
        return campaignGroupId;
    }

    @Override
    public void setCampaignGroupId(Long campaignGroupId) {
        this.campaignGroupId = campaignGroupId;
    }

    @Override
    public Long getCampaignChannelId() {
        return campaignChannelId;
    }

    @Override
    public void setCampaignChannelId(Long campaignChannelId) {
        this.campaignChannelId = campaignChannelId;
    }

    @Override
    public Platform getPlatform() {
        return platform;
    }

    @Override
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    @Override
    public boolean isSandbox() {
        return sandbox;
    }

    @Override
    public void setSandbox(boolean sandbox) {
        this.sandbox = sandbox;
    }

    @Override
    public String getProviderMessageId() {
        return providerMessageId;
    }

    @Override
    public void setProviderMessageId(String providerMessageId) {
        this.providerMessageId = providerMessageId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
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
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String getActionUrl() {
        return actionUrl;
    }

    @Override
    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean isFailed() {
        return failed;
    }

    @Override
    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    @Override
    public boolean isDelivered() {
        return delivered;
    }

    @Override
    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    @Override
    public boolean isOptedOut() {
        return optedOut;
    }

    @Override
    public void setOptedOut(boolean optedOut) {
        this.optedOut = optedOut;
    }

    @Override
    public boolean isViewed() {
        return viewed;
    }

    @Override
    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    @Override
    public Date getSentDate() {
        return sentDate;
    }

    @Override
    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    @Override
    public Date getDeliveredDate() {
        return deliveredDate;
    }

    @Override
    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    @Override
    public Date getViewedDate() {
        return viewedDate;
    }

    @Override
    public void setViewedDate(Date viewedDate) {
        this.viewedDate = viewedDate;
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

