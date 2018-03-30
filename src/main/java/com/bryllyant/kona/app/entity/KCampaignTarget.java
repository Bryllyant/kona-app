package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KCampaignTarget extends KEntityObject {
    enum Type {
        WEBSITE,
        LANDING_PAGE,
        APP_STORE
    }



    @Override
    Long getId();
    void setId(Long id);

    String getUid();
    void setUid(String uid);

    Long getCampaignId();
    void setCampaignId(Long campaignId);

    Long getGroupId();
    void setGroupId(Long groupId);

    Long getChannelId();
    void setChannelId(Long channelId);

    Long getLandingPageId();
    void setLandingPageId(Long landingPageId);

    Type getType();
    void setType(Type type);

    String getName();
    void setName(String name);

    String getSlug();
    void setSlug(String slug);

    String getUrl();
    void setUrl(String url);

    String getAnalyticsTrackingId();
    void setAnalyticsTrackingId(String analyticsTrackingId);

    String getConversionPixel();
    void setConversionPixel(String conversionPixel);

    Integer getConversionCount();
    void setConversionCount(Integer conversionCount);

    boolean isEnabled();
    void setEnabled(boolean enabled);

    Date getStartDate();
    void setStartDate(Date startDate);

    Date getEndDate();
    void setEndDate(Date endDate);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();
    void setUpdatedDate(Date updatedDate);
}