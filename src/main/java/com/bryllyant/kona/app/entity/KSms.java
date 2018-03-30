package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KSms extends KEntityObject {

    Long getId();
    void setId(Long id);

    String getUid();
    void setUid(String uid);

    Long getCampaignId();
    void setCampaignId(Long campaignId);

    Long getCampaignGroupId();
    void setCampaignGroupId(Long campaignGroupId);

    Long getCampaignChannelId();
    void setCampaignChannelId(Long campaignChannelId);

    Long getToUserId();
    void setToUserId(Long toUserId);

    String getToNumber();
    void setToNumber(String toNumber);

    String getFromNumber();
    void setFromNumber(String fromNumber);

    String getMessage();
    void setMessage(String message);
    
    String getMediaUrls();
    void setMediaUrls(String mediaUrls);

    String getMessageSid();
    void setMessageSid(String messageSid);

    boolean isFailed();
    void setFailed(boolean failed);

    boolean isDelivered();
    void setDelivered(boolean delivered);

    boolean isOptedOut();
    void setOptedOut(boolean optedOut);

    Integer getClickCount();
    void setClickCount(Integer clickCount);

    Date getSentDate();
    void setSentDate(Date sentDate);

    Date getViewedDate();
    void setViewedDate(Date viewedDate);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();
    void setUpdatedDate(Date updatedDate);

    String getStatus();
    void setStatus(String status);

    String getErrorCode();
    void setErrorCode(String errorCode);

    String getErrorMessage();
    void setErrorMessage(String errorMessage);

}