package com.bryllyant.kona.api.model.marketing.campaign;

import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class CampaignChannelModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private CampaignModel campaign;
    private CampaignGroupModel group;
    private String promoCode;
    private CampaignChannel.Type type;
    private CampaignChannel.TargetStrategy targetStrategy;
    private CampaignChannel.ReplyStrategy replyStrategy;
    private String name;
    private String slug;
    private String adwordsKeywords;
    private String smsNumber;
    private String smsKeyword;
    private String shortUrl;
    private Integer conversionCount;
    private Boolean enabled;
    private Boolean replyEnabled;
    private Date startDate;
    private Date endDate;
    private Date createdDate;
    private Date updatedDate;

    public static CampaignChannelModel from(CampaignChannel channel) {
        CampaignChannelModel model = new CampaignChannelModel();
        model.setUid(channel.getUid());
        model.setName(channel.getName());
        return model;
    }

    public static CampaignChannelModel create(String uid) {
        CampaignChannelModel model = new CampaignChannelModel();
        model.setUid(uid);
        return model;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.set("uid", uid);
    }

    public CampaignModel getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignModel campaign) {
        this.set("campaign", campaign);
    }

    public CampaignGroupModel getGroup() {
        return group;
    }

    public void setGroup(CampaignGroupModel group) {
        this.set("group", group);
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.set("promoCode", promoCode);
    }

    public CampaignChannel.Type getType() {
        return type;
    }

    public void setType(CampaignChannel.Type type) {
        this.set("type", type);
    }

    public CampaignChannel.TargetStrategy getTargetStrategy() {
        return targetStrategy;
    }

    public void setTargetStrategy(CampaignChannel.TargetStrategy targetStrategy) {
        this.set("targetStrategy", targetStrategy);
    }

    public CampaignChannel.ReplyStrategy getReplyStrategy() {
        return replyStrategy;
    }

    public void setReplyStrategy(CampaignChannel.ReplyStrategy replyStrategy) {
        this.set("replyStrategy", replyStrategy);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.set("name", name);
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.set("slug", slug);
    }

    public String getAdwordsKeywords() {
        return adwordsKeywords;
    }

    public void setAdwordsKeywords(String adwordsKeywords) {
        this.set("adwordsKeywords", adwordsKeywords);
    }

    public String getSmsNumber() {
        return smsNumber;
    }

    public void setSmsNumber(String smsNumber) {
        this.set("smsNumber", smsNumber);
    }

    public String getSmsKeyword() {
        return smsKeyword;
    }

    public void setSmsKeyword(String smsKeyword) {
        this.set("smsKeyword", smsKeyword);
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.set("shortUrl", shortUrl);
    }

    public Integer getConversionCount() {
        return conversionCount;
    }

    public void setConversionCount(Integer conversionCount) {
        this.set("conversionCount", conversionCount);
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.set("enabled", enabled);
    }

    public Boolean getReplyEnabled() {
        return replyEnabled;
    }

    public void setReplyEnabled(Boolean replyEnabled) {
        this.set("replyEnabled", replyEnabled);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.set("startDate", startDate);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.set("endDate", endDate);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.set("updatedDate", updatedDate);
    }
}
