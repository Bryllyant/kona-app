package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KCampaignGroup extends KEntityObject {
    @Override
    Long getId();
    void setId(Long id);

    String getUid();
    void setUid(String uid);

    Long getCampaignId();
    void setCampaignId(Long campaignId);

    Long getPartnerId();
    void setPartnerId(Long partnerId);

    String getName();
    void setName(String name);

    String getDescription();
    void setDescription(String description);

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