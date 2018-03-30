package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseCampaignChannel extends KBaseEntity implements KCampaignChannel {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long campaignId;
    private Long groupId;
    private String promoCode;
    private Type type;
    private TargetStrategy targetStrategy;
    private ReplyStrategy replyStrategy;
    private String name;
    private String slug;
    private String adwordsKeywords;
    private String smsNumber;
    private String smsKeyword;
    private String shortUrl;
    private Integer conversionCount;
    private boolean enabled;
    private boolean replyEnabled;
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
    public String getPromoCode() {
        return promoCode;
    }

    @Override
    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
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
    public TargetStrategy getTargetStrategy() {
        return targetStrategy;
    }

    @Override
    public void setTargetStrategy(TargetStrategy targetStrategy) {
        this.targetStrategy = targetStrategy;
    }

    @Override
    public ReplyStrategy getReplyStrategy() {
        return replyStrategy;
    }

    @Override
    public void setReplyStrategy(ReplyStrategy replyStrategy) {
        this.replyStrategy = replyStrategy;
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
    public String getAdwordsKeywords() {
        return adwordsKeywords;
    }

    @Override
    public void setAdwordsKeywords(String adwordsKeywords) {
        this.adwordsKeywords = adwordsKeywords;
    }

    @Override
    public String getSmsNumber() {
        return smsNumber;
    }

    @Override
    public void setSmsNumber(String smsNumber) {
        this.smsNumber = smsNumber;
    }

    @Override
    public String getSmsKeyword() {
        return smsKeyword;
    }

    @Override
    public void setSmsKeyword(String smsKeyword) {
        this.smsKeyword = smsKeyword;
    }

    @Override
    public String getShortUrl() {
        return shortUrl;
    }

    @Override
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
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
    public boolean isReplyEnabled() {
        return replyEnabled;
    }

    @Override
    public void setReplyEnabled(boolean replyEnabled) {
        this.replyEnabled = replyEnabled;
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
