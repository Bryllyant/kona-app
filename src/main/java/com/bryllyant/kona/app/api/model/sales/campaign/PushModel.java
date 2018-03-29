package com.bryllyant.kona.app.api.model.sales.campaign;

import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class PushModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private UserModel user;
    private PushDeviceModel device;
    private CampaignModel campaign;
    private CampaignGroupModel campaignGroup;
    private CampaignChannelModel campaignChannel;
    private Push.Platform platform;
    private Boolean sandbox;
    private String providerMessageId;
    private String title;
    private String message;
    private String imageUrl;
    private String actionUrl;
    private String status;
    private String errorCode;
    private String errorMessage;
    private Boolean failed;
    private Boolean delivered;
    private Boolean optedOut;
    private Boolean viewed;
    private Date sentDate;
    private Date deliveredDate;
    private Date viewedDate;
    private Date createdDate;
    private Date updatedDate;


    public static PushModel from(Push push) {
        PushModel model = new PushModel();
        model.setUid(push.getUid());
        return model;
    }

    public static PushModel create(String uid) {
        PushModel model = new PushModel();
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

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.set("user", user);
    }

    public PushDeviceModel getDevice() {
        return device;
    }

    public void setDevice(PushDeviceModel device) {
        this.set("device", device);
    }

    public CampaignModel getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignModel campaign) {
        this.set("campaign", campaign);
    }

    public CampaignGroupModel getCampaignGroup() {
        return campaignGroup;
    }

    public void setCampaignGroup(CampaignGroupModel campaignGroup) {
        this.set("campaignGroup", campaignGroup);
    }

    public CampaignChannelModel getCampaignChannel() {
        return campaignChannel;
    }

    public void setCampaignChannel(CampaignChannelModel campaignChannel) {
        this.set("campaignChannel", campaignChannel);
    }

    public Push.Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Push.Platform platform) {
        this.set("platform", platform);
    }

    public Boolean getSandbox() {
        return sandbox;
    }

    public void setSandbox(Boolean sandbox) {
        this.set("sandbox", sandbox);
    }

    public String getProviderMessageId() {
        return providerMessageId;
    }

    public void setProviderMessageId(String providerMessageId) {
        this.set("providerMessageId", providerMessageId);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.set("title", title);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.set("message", message);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.set("imageUrl", imageUrl);
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.set("actionUrl", actionUrl);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.set("status", status);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.set("errorCode", errorCode);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.set("errorMessage", errorMessage);
    }

    public Boolean getFailed() {
        return failed;
    }

    public void setFailed(Boolean failed) {
        this.set("failed", failed);
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.set("delivered", delivered);
    }

    public Boolean getOptedOut() {
        return optedOut;
    }

    public void setOptedOut(Boolean optedOut) {
        this.set("optedOut", optedOut);
    }

    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(Boolean viewed) {
        this.set("viewed", viewed);
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.set("sentDate", sentDate);
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.set("deliveredDate", deliveredDate);
    }

    public Date getViewedDate() {
        return viewedDate;
    }

    public void setViewedDate(Date viewedDate) {
        this.set("viewedDate", viewedDate);
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
