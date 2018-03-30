package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KPromoCode extends KEntityObject {
    @Override
    Long getId();

    void setId(Long id);

    String getUid();

    void setUid(String uid);

    Long getPromoId();

    void setPromoId(Long promoId);

    Long getCampaignChannelId();

    void setCampaignChannelId(Long campaignChannelId);

    String getPromoCode();

    void setPromoCode(String promoCode);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);
}

