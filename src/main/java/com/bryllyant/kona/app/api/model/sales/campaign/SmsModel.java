package com.bryllyant.kona.app.api.model.sales.campaign;

import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Sms;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class SmsModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private CampaignModel campaign;
    private CampaignGroupModel campaignGroup;
    private CampaignChannelModel campaignChannel;
    private UserModel toUser;
    private String toNumber;
    private String fromNumber;
    private String message;
    private String mediaUrls;
    private String status;
    private String errorCode;
    private String errorMessage;
    private String messageSid;
    private Boolean failed;
    private Boolean delivered;
    private Boolean optedOut;
    private Integer clickCount;
    private Date sentDate;
    private Date viewedDate;
    private Date createdDate;
    private Date updatedDate;


    public static SmsModel from(Sms sms) {
        SmsModel model = new SmsModel();
        model.setUid(sms.getUid());
        model.setToNumber(sms.getToNumber());
        return model;
    }

    public static SmsModel create(String uid) {
        SmsModel model = new SmsModel();
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

    public UserModel getToUser() {
        return toUser;
    }

    public void setToUser(UserModel toUser) {
        this.set("toUser", toUser);
    }

    public String getToNumber() {
        return toNumber;
    }

    public void setToNumber(String toNumber) {
        this.set("toNumber", toNumber);
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(String fromNumber) {
        this.set("fromNumber", fromNumber);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.set("message", message);
    }

    public String getMediaUrls() {
        return mediaUrls;
    }

    public void setMediaUrls(String mediaUrls) {
        this.set("mediaUrls", mediaUrls);
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

    public String getMessageSid() {
        return messageSid;
    }

    public void setMessageSid(String messageSid) {
        this.set("messageSid", messageSid);
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

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.set("clickCount", clickCount);
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.set("sentDate", sentDate);
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
