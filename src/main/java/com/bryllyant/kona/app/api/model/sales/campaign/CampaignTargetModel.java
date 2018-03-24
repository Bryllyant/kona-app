package com.bryllyant.kona.app.api.model.sales.campaign;

import com.bryllyant.kona.app.api.model.sales.landingPage.LandingPageModel;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class CampaignTargetModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private CampaignModel campaign;
    private CampaignGroupModel group;
    private CampaignChannelModel channel;
    private CampaignTarget.Type type;
    private String name;
    private String slug;

    private LandingPageModel landingPage;

    private String url;
    private String shortUrl;

    private String analyticsTrackingId;
    private String conversionPixel;
    private Integer conversionCount;
    private Boolean enabled;
    private Date startDate;
    private Date endDate;
    private Date createdDate;
    private Date updatedDate;

    public static CampaignTargetModel from(CampaignTarget target) {
        CampaignTargetModel model = new CampaignTargetModel();
        model.setUid(target.getUid());
        model.setName(target.getName());
        return model;
    }

    public static CampaignTargetModel create(String uid) {
        CampaignTargetModel model = new CampaignTargetModel();
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

    public CampaignChannelModel getChannel() {
        return channel;
    }

    public void setChannel(CampaignChannelModel channel) {
        this.set("channel", channel);
    }

    public CampaignTarget.Type getType() {
        return type;
    }

    public void setType(CampaignTarget.Type type) {
        this.set("type", type);
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

    public LandingPageModel getLandingPage() {
        return landingPage;
    }

    public void setLandingPage(LandingPageModel landingPage) {
        this.set("landingPage", landingPage);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.set("url", url);
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.set("shortUrl", shortUrl);
    }

    public String getAnalyticsTrackingId() {
        return analyticsTrackingId;
    }

    public void setAnalyticsTrackingId(String analyticsTrackingId) {
        this.set("analyticsTrackingId", analyticsTrackingId);
    }

    public String getConversionPixel() {
        return conversionPixel;
    }

    public void setConversionPixel(String conversionPixel) {
        this.set("conversionPixel", conversionPixel);
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
