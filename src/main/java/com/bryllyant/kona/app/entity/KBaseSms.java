package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseSms extends KBaseEntity implements KSms {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long campaignId;
    private Long campaignChannelId;
    private Long campaignGroupId;
    private Long toUserId;
    private String toNumber;
    private String fromNumber;
    private String message;
    private String mediaUrls;
    private String status;
    private String errorCode;
    private String errorMessage;
    private String messageSid;
    private boolean failed;
    private boolean delivered;
    private boolean optedOut;
    private Integer clickCount;
    private Date sentDate;
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
    public Long getCampaignId() {
        return campaignId;
    }

    @Override
    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
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
    public Long getCampaignGroupId() {
        return campaignGroupId;
    }

    @Override
    public void setCampaignGroupId(Long campaignGroupId) {
        this.campaignGroupId = campaignGroupId;
    }

    @Override
    public Long getToUserId() {
        return toUserId;
    }

    @Override
    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public String getToNumber() {
        return toNumber;
    }

    @Override
    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    @Override
    public String getFromNumber() {
        return fromNumber;
    }

    @Override
    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
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
    public String getMediaUrls() {
        return mediaUrls;
    }

    @Override
    public void setMediaUrls(String mediaUrls) {
        this.mediaUrls = mediaUrls;
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
    public String getMessageSid() {
        return messageSid;
    }

    @Override
    public void setMessageSid(String messageSid) {
        this.messageSid = messageSid;
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
    public Integer getClickCount() {
        return clickCount;
    }

    @Override
    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
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
