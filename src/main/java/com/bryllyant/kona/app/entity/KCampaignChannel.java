package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KCampaignChannel extends KEntityObject {
    enum Type {
        ORGANIC,
        AFFILIATE,
        BLOG_POST,
        EMAIL,
        SMS,
        GOOGLE_ADWORDS,
        FACEBOOK,
        INSTAGRAM,
        TWITTER,
        LINKEDIN,
        SNAPCHAT,
        PINTEREST,
        BANNER_AD,
        SOCIAL_DEAL,
        OTHER
    }

    enum TargetStrategy {
        RANDOM,
        ROUND_ROBIN,
        MAX_CONVERSIONS,
    }

    enum ReplyStrategy {
        RANDOM,
        ROUND_ROBIN,
        MAX_VIEWS,
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

    Type getType();
    void setType(Type type);

    TargetStrategy getTargetStrategy();
    void setTargetStrategy(TargetStrategy targetStrategy);

    ReplyStrategy getReplyStrategy();
    void setReplyStrategy(ReplyStrategy replyStrategy);

    String getName();
    void setName(String name);

    String getSlug();
    void setSlug(String slug);

    String getAdwordsKeywords();
    void setAdwordsKeywords(String adwordsKeywords);

    String getSmsKeyword();
    void setSmsKeyword(String smsKeyword);

    String getSmsNumber();
    void setSmsNumber(String smsNumber);

    String getShortUrl();
    void setShortUrl(String shortUrl);

    String getPromoCode();
    void setPromoCode(String promoCode);

    Integer getConversionCount();
    void setConversionCount(Integer conversionCount);

    boolean isEnabled();
    void setEnabled(boolean enabled);

    boolean isReplyEnabled();
    void setReplyEnabled(boolean replyEnabled);

    Date getStartDate();
    void setStartDate(Date startDate);

    Date getEndDate();
    void setEndDate(Date endDate);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();
    void setUpdatedDate(Date updatedDate);
}