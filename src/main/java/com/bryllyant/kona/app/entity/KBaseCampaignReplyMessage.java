package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseCampaignReplyMessage extends KBaseEntity implements KCampaignReplyMessage {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long campaignId;
    private Long groupId;
    private Long channelId;
    private Long targetId;
    private Long replyId;

    private Long emailId;
    private Long smsId;
    private Long pushId;

    private Long toUserId;
    private String toEmail;
    private String toMobileNumber;

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
    public Long getChannelId() {
        return channelId;
    }

    @Override
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    @Override
    public Long getTargetId() {
        return targetId;
    }

    @Override
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    @Override
    public Long getReplyId() {
        return replyId;
    }

    @Override
    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    @Override
    public Long getEmailId() {
        return emailId;
    }

    @Override
    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    @Override
    public Long getSmsId() {
        return smsId;
    }

    @Override
    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    @Override
    public Long getPushId() {
        return pushId;
    }

    @Override
    public void setPushId(Long pushId) {
        this.pushId = pushId;
    }

    @Override
    public Long getToUserId() {
        return toUserId;
    }

    @Override
    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    @Override
    public String getToEmail() {
        return toEmail;
    }

    @Override
    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    @Override
    public String getToMobileNumber() {
        return toMobileNumber;
    }

    @Override
    public void setToMobileNumber(String toMobileNumber) {
        this.toMobileNumber = toMobileNumber;
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

