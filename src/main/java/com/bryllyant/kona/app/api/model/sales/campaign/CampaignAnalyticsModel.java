package com.bryllyant.kona.app.api.model.sales.campaign;

import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.CampaignAnalytics;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class CampaignAnalyticsModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private CampaignModel campaign;
    private CampaignGroupModel group;
    private CampaignChannelModel channel;
    private CampaignTargetModel target;
    private UserModel user;
    private String category;
    private String action;
    private String label;
    private Double value;
    private Boolean conversionEvent;
    private String mobileNumber;
    private String url;
    private String hostname;
    private String userAgent;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String timeZone;
    private Integer utcOffset;
    private Double latitude;
    private Double longitude;
    private Date createdDate;
    private Date updatedDate;

    public static CampaignAnalyticsModel from(CampaignAnalytics analytics) {
        CampaignAnalyticsModel model = new CampaignAnalyticsModel();
        model.setUid(analytics.getUid());
        model.setAction(analytics.getAction());
        model.setCategory(analytics.getCategory());
        model.setLabel(analytics.getLabel());
        model.setValue(analytics.getValue());
        model.setConversionEvent(analytics.isConversionEvent());
        return model;
    }

    public static CampaignAnalyticsModel create(String uid) {
        CampaignAnalyticsModel model = new CampaignAnalyticsModel();
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

    public CampaignTargetModel getTarget() {
        return target;
    }

    public void setTarget(CampaignTargetModel target) {
        this.set("target", target);
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.set("user", user);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.set("category", category);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.set("action", action);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.set("label", label);
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.set("value", value);
    }

    public Boolean getConversionEvent() {
        return conversionEvent;
    }

    public void setConversionEvent(Boolean conversionEvent) {
        this.set("conversionEvent", conversionEvent);
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.set("mobileNumber", mobileNumber);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.set("url", url);
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.set("hostname", hostname);
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.set("userAgent", userAgent);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.set("city", city);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.set("state", state);
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.set("postalCode", postalCode);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.set("country", country);
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.set("timeZone", timeZone);
    }

    public Integer getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(Integer utcOffset) {
        this.set("utcOffset", utcOffset);
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.set("latitude", latitude);
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.set("longitude", longitude);
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
