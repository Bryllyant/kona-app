package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KCampaignAnalytics extends KEntityObject {


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

    Long getTargetId();

    void setTargetId(Long targetId);

    Long getReplyId();

    void setReplyId(Long replyId);

    Long getReplyMessageId();

    void setReplyMessageId(Long replyMessageId);

    String getCategory();

    void setCategory(String category);

    String getAction();

    void setAction(String action);

    String getLabel();

    void setLabel(String label);

    Double getValue();

    void setValue(Double value);

    boolean isConversionEvent();

    void setConversionEvent(boolean conversionEvent);

    Long getConversionUserId();

    void setConversionUserId(Long conversionUserId);

    String getConversionEmail();

    void setConversionEmail(String conversionEmail);

    String getConversionMobileNumber();

    void setConversionMobileNumber(String conversionMobileNumber);

    String getSourceMobileNumber();

    void setSourceMobileNumber(String sourceMobileNumber);

    String getSourceUrl();

    void setSourceUrl(String sourceUrl);

    String getHostname();

    void setHostname(String hostname);

    String getUserAgent();

    void setUserAgent(String userAgent);

    String getCity();

    void setCity(String city);

    String getState();

    void setState(String state);

    String getPostalCode();

    void setPostalCode(String postalCode);

    String getCountry();

    void setCountry(String country);

    String getTimeZone();

    void setTimeZone(String timeZone);

    Integer getUtcOffset();

    void setUtcOffset(Integer utcOffset);

    Double getLatitude();

    void setLatitude(Double latitude);

    Double getLongitude();

    void setLongitude(Double longitude);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);
}