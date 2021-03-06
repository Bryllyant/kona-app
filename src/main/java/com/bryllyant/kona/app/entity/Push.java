package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BasePush.Platform;
import java.io.Serializable;
import java.util.Date;

public class Push extends BasePush implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.user_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.device_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long deviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.campaign_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long campaignId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.campaign_group_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long campaignGroupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.campaign_channel_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long campaignChannelId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.platform
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Platform platform;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.sandbox
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean sandbox;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.provider_message_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String providerMessageId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.title
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.message
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String message;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.image_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String imageUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.action_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String actionUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.status
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.error_code
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String errorCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.error_message
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String errorMessage;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.failed
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean failed;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.delivered
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean delivered;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.opted_out
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean optedOut;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.viewed
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean viewed;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.sent_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date sentDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.delivered_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date deliveredDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.viewed_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date viewedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__push
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.uid
     *
     * @return the value of kona__push.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.uid
     *
     * @param uid the value for kona__push.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.user_id
     *
     * @return the value of kona__push.user_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.user_id
     *
     * @param userId the value for kona__push.user_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.device_id
     *
     * @return the value of kona__push.device_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.device_id
     *
     * @param deviceId the value for kona__push.device_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.campaign_id
     *
     * @return the value of kona__push.campaign_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getCampaignId() {
        return campaignId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.campaign_id
     *
     * @param campaignId the value for kona__push.campaign_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.campaign_group_id
     *
     * @return the value of kona__push.campaign_group_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getCampaignGroupId() {
        return campaignGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.campaign_group_id
     *
     * @param campaignGroupId the value for kona__push.campaign_group_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCampaignGroupId(Long campaignGroupId) {
        this.campaignGroupId = campaignGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.campaign_channel_id
     *
     * @return the value of kona__push.campaign_channel_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getCampaignChannelId() {
        return campaignChannelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.campaign_channel_id
     *
     * @param campaignChannelId the value for kona__push.campaign_channel_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCampaignChannelId(Long campaignChannelId) {
        this.campaignChannelId = campaignChannelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.platform
     *
     * @return the value of kona__push.platform
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.platform
     *
     * @param platform the value for kona__push.platform
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.sandbox
     *
     * @return the value of kona__push.sandbox
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isSandbox() {
        return sandbox;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.sandbox
     *
     * @param sandbox the value for kona__push.sandbox
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setSandbox(boolean sandbox) {
        this.sandbox = sandbox;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.provider_message_id
     *
     * @return the value of kona__push.provider_message_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getProviderMessageId() {
        return providerMessageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.provider_message_id
     *
     * @param providerMessageId the value for kona__push.provider_message_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setProviderMessageId(String providerMessageId) {
        this.providerMessageId = providerMessageId == null ? null : providerMessageId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.title
     *
     * @return the value of kona__push.title
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.title
     *
     * @param title the value for kona__push.title
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.message
     *
     * @return the value of kona__push.message
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.message
     *
     * @param message the value for kona__push.message
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.image_url
     *
     * @return the value of kona__push.image_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.image_url
     *
     * @param imageUrl the value for kona__push.image_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.action_url
     *
     * @return the value of kona__push.action_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getActionUrl() {
        return actionUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.action_url
     *
     * @param actionUrl the value for kona__push.action_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl == null ? null : actionUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.status
     *
     * @return the value of kona__push.status
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.status
     *
     * @param status the value for kona__push.status
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.error_code
     *
     * @return the value of kona__push.error_code
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.error_code
     *
     * @param errorCode the value for kona__push.error_code
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.error_message
     *
     * @return the value of kona__push.error_message
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.error_message
     *
     * @param errorMessage the value for kona__push.error_message
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.failed
     *
     * @return the value of kona__push.failed
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isFailed() {
        return failed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.failed
     *
     * @param failed the value for kona__push.failed
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.delivered
     *
     * @return the value of kona__push.delivered
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isDelivered() {
        return delivered;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.delivered
     *
     * @param delivered the value for kona__push.delivered
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.opted_out
     *
     * @return the value of kona__push.opted_out
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isOptedOut() {
        return optedOut;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.opted_out
     *
     * @param optedOut the value for kona__push.opted_out
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setOptedOut(boolean optedOut) {
        this.optedOut = optedOut;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.viewed
     *
     * @return the value of kona__push.viewed
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isViewed() {
        return viewed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.viewed
     *
     * @param viewed the value for kona__push.viewed
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.sent_date
     *
     * @return the value of kona__push.sent_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getSentDate() {
        return sentDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.sent_date
     *
     * @param sentDate the value for kona__push.sent_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.delivered_date
     *
     * @return the value of kona__push.delivered_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getDeliveredDate() {
        return deliveredDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.delivered_date
     *
     * @param deliveredDate the value for kona__push.delivered_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.viewed_date
     *
     * @return the value of kona__push.viewed_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getViewedDate() {
        return viewedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.viewed_date
     *
     * @param viewedDate the value for kona__push.viewed_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setViewedDate(Date viewedDate) {
        this.viewedDate = viewedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.created_date
     *
     * @return the value of kona__push.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.created_date
     *
     * @param createdDate the value for kona__push.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push.updated_date
     *
     * @return the value of kona__push.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push.updated_date
     *
     * @param updatedDate the value for kona__push.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}