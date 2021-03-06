package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BaseCampaignTarget.Type;
import java.io.Serializable;
import java.util.Date;

public class CampaignTarget extends BaseCampaignTarget implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.campaign_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long campaignId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.group_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long groupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.channel_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long channelId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.type
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Type type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.slug
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.landing_page_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long landingPageId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.website_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String websiteUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.app_store_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String appStoreUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.google_play_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String googlePlayUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.analytics_tracking_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String analyticsTrackingId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.app_store_provider_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String appStoreProviderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.conversion_pixel
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String conversionPixel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.conversion_count
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Integer conversionCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.enabled
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.start_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date startDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.end_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date endDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_target.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__campaign_target
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.uid
     *
     * @return the value of kona__campaign_target.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.uid
     *
     * @param uid the value for kona__campaign_target.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.campaign_id
     *
     * @return the value of kona__campaign_target.campaign_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getCampaignId() {
        return campaignId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.campaign_id
     *
     * @param campaignId the value for kona__campaign_target.campaign_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.group_id
     *
     * @return the value of kona__campaign_target.group_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.group_id
     *
     * @param groupId the value for kona__campaign_target.group_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.channel_id
     *
     * @return the value of kona__campaign_target.channel_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getChannelId() {
        return channelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.channel_id
     *
     * @param channelId the value for kona__campaign_target.channel_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.type
     *
     * @return the value of kona__campaign_target.type
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.type
     *
     * @param type the value for kona__campaign_target.type
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.name
     *
     * @return the value of kona__campaign_target.name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.name
     *
     * @param name the value for kona__campaign_target.name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.slug
     *
     * @return the value of kona__campaign_target.slug
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.slug
     *
     * @param slug the value for kona__campaign_target.slug
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.landing_page_id
     *
     * @return the value of kona__campaign_target.landing_page_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getLandingPageId() {
        return landingPageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.landing_page_id
     *
     * @param landingPageId the value for kona__campaign_target.landing_page_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setLandingPageId(Long landingPageId) {
        this.landingPageId = landingPageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.url
     *
     * @return the value of kona__campaign_target.url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.url
     *
     * @param url the value for kona__campaign_target.url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.website_url
     *
     * @return the value of kona__campaign_target.website_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.website_url
     *
     * @param websiteUrl the value for kona__campaign_target.website_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl == null ? null : websiteUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.app_store_url
     *
     * @return the value of kona__campaign_target.app_store_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getAppStoreUrl() {
        return appStoreUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.app_store_url
     *
     * @param appStoreUrl the value for kona__campaign_target.app_store_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAppStoreUrl(String appStoreUrl) {
        this.appStoreUrl = appStoreUrl == null ? null : appStoreUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.google_play_url
     *
     * @return the value of kona__campaign_target.google_play_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getGooglePlayUrl() {
        return googlePlayUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.google_play_url
     *
     * @param googlePlayUrl the value for kona__campaign_target.google_play_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setGooglePlayUrl(String googlePlayUrl) {
        this.googlePlayUrl = googlePlayUrl == null ? null : googlePlayUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.analytics_tracking_id
     *
     * @return the value of kona__campaign_target.analytics_tracking_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getAnalyticsTrackingId() {
        return analyticsTrackingId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.analytics_tracking_id
     *
     * @param analyticsTrackingId the value for kona__campaign_target.analytics_tracking_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAnalyticsTrackingId(String analyticsTrackingId) {
        this.analyticsTrackingId = analyticsTrackingId == null ? null : analyticsTrackingId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.app_store_provider_id
     *
     * @return the value of kona__campaign_target.app_store_provider_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getAppStoreProviderId() {
        return appStoreProviderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.app_store_provider_id
     *
     * @param appStoreProviderId the value for kona__campaign_target.app_store_provider_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAppStoreProviderId(String appStoreProviderId) {
        this.appStoreProviderId = appStoreProviderId == null ? null : appStoreProviderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.conversion_pixel
     *
     * @return the value of kona__campaign_target.conversion_pixel
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getConversionPixel() {
        return conversionPixel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.conversion_pixel
     *
     * @param conversionPixel the value for kona__campaign_target.conversion_pixel
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setConversionPixel(String conversionPixel) {
        this.conversionPixel = conversionPixel == null ? null : conversionPixel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.conversion_count
     *
     * @return the value of kona__campaign_target.conversion_count
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Integer getConversionCount() {
        return conversionCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.conversion_count
     *
     * @param conversionCount the value for kona__campaign_target.conversion_count
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setConversionCount(Integer conversionCount) {
        this.conversionCount = conversionCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.enabled
     *
     * @return the value of kona__campaign_target.enabled
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.enabled
     *
     * @param enabled the value for kona__campaign_target.enabled
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.start_date
     *
     * @return the value of kona__campaign_target.start_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.start_date
     *
     * @param startDate the value for kona__campaign_target.start_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.end_date
     *
     * @return the value of kona__campaign_target.end_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.end_date
     *
     * @param endDate the value for kona__campaign_target.end_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.created_date
     *
     * @return the value of kona__campaign_target.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.created_date
     *
     * @param createdDate the value for kona__campaign_target.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_target.updated_date
     *
     * @return the value of kona__campaign_target.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_target.updated_date
     *
     * @param updatedDate the value for kona__campaign_target.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}