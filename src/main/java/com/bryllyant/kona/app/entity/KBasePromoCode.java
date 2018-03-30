package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBasePromoCode extends KBaseEntity implements KPromoCode {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long promoId;
    private Long campaignChannelId;
    private String promoCode;
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
    public Long getPromoId() {
        return promoId;
    }

    @Override
    public void setPromoId(Long promoId) {
        this.promoId = promoId;
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
    public String getPromoCode() {
        return promoCode;
    }

    @Override
    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
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

