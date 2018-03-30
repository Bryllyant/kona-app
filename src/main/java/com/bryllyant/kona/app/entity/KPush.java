package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KPush extends KEntityObject {

    enum Platform {
        // Apple Push Notification Service
        APNS,

        // Sandbox version of Apple Push Notification Service
        APNS_SANDBOX,

        // Amazon Device Messaging
        ADM,

        // Google Cloud Messaging
        GCM,

        // Baidu CloudMessaging Service
        BAIDU,

        // Windows Notification Service
        WNS,

        // Microsoft Push Notificaion Service
        MPNS;
    }

    @Override
    Long getId();
    void setId(Long id);

    String getUid();
    void setUid(String uid);

    Long getUserId();
    void setUserId(Long userId);

    Long getDeviceId();
    void setDeviceId(Long deviceId);

    Long getCampaignId();
    void setCampaignId(Long campaignId);

    Long getCampaignGroupId();
    void setCampaignGroupId(Long campaignGroupId);

    Long getCampaignChannelId();
    void setCampaignChannelId(Long campaignChannelId);

    Platform getPlatform();
    void setPlatform(Platform platform);

    boolean isSandbox();
    void setSandbox(boolean sandbox);

    String getProviderMessageId();
    void setProviderMessageId(String providerMessageId);

    String getTitle();
    void setTitle(String title);

    String getMessage();
    void setMessage(String message);

    String getImageUrl();
    void setImageUrl(String imageUrl);

    String getActionUrl();
    void setActionUrl(String actionUrl);

    String getStatus();
    void setStatus(String status);

    String getErrorCode();
    void setErrorCode(String errorCode);

    String getErrorMessage();
    void setErrorMessage(String errorMessage);

    boolean isFailed();
    void setFailed(boolean failed);

    boolean isDelivered();
    void setDelivered(boolean delivered);

    boolean isOptedOut();
    void setOptedOut(boolean optedOut);

    boolean isViewed();
    void setViewed(boolean viewed);

    Date getSentDate();
    void setSentDate(Date sentDate);

    Date getDeliveredDate();
    void setDeliveredDate(Date deliveredDate);

    Date getViewedDate();
    void setViewedDate(Date viewedDate);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();
    void setUpdatedDate(Date updatedDate);
}
