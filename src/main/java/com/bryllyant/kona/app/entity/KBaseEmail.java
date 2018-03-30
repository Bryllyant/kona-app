package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseEmail extends KBaseEntity implements KEmail {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long campaignId;
    private Long campaignGroupId;
    private Long campaignChannelId;
    private Long emailGroupId;
    private Long emailAddressId;
    private Long emailContentId;
    private String sesId;
    private String fromAddress;
    private String toAddress;
    private String subject;
    private boolean failed;
    private boolean delivered;
    private boolean bounced;
    private boolean complained;
    private boolean optedOut;
    private Integer openCount;
    private Integer clickCount;
    private Integer printCount;
    private Integer forwardCount;
    private Date createdDate;
    private Date sentDate;
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
    public Long getCampaignGroupId() {
        return campaignGroupId;
    }

    @Override
    public void setCampaignGroupId(Long campaignGroupId) {
        this.campaignGroupId = campaignGroupId;
    }

    @Override
    public Long getCampaignChannelId() {
        return campaignChannelId;
    }

    @Override
    public void setCampaignChannelId(Long campaignChannelId) {
        this.campaignChannelId = campaignChannelId;
    }

    @Override
    public Long getEmailGroupId() {
        return emailGroupId;
    }

    @Override
    public void setEmailGroupId(Long emailGroupId) {
        this.emailGroupId = emailGroupId;
    }

    @Override
    public Long getEmailAddressId() {
        return emailAddressId;
    }

    @Override
    public void setEmailAddressId(Long emailAddressId) {
        this.emailAddressId = emailAddressId;
    }

    @Override
    public Long getEmailContentId() {
        return emailContentId;
    }

    @Override
    public void setEmailContentId(Long emailContentId) {
        this.emailContentId = emailContentId;
    }

    @Override
    public String getSesId() {
        return sesId;
    }

    @Override
    public void setSesId(String sesId) {
        this.sesId = sesId;
    }

    @Override
    public String getFromAddress() {
        return fromAddress;
    }

    @Override
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    @Override
    public String getToAddress() {
        return toAddress;
    }

    @Override
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean isFailed() {
        return failed;
    }

    @Override
    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    @Override
    public boolean isDelivered() {
        return delivered;
    }

    @Override
    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    @Override
    public boolean isBounced() {
        return bounced;
    }

    @Override
    public void setBounced(boolean bounced) {
        this.bounced = bounced;
    }

    @Override
    public boolean isComplained() {
        return complained;
    }

    @Override
    public void setComplained(boolean complained) {
        this.complained = complained;
    }

    @Override
    public boolean isOptedOut() {
        return optedOut;
    }

    @Override
    public void setOptedOut(boolean optedOut) {
        this.optedOut = optedOut;
    }

    @Override
    public Integer getOpenCount() {
        return openCount;
    }

    @Override
    public void setOpenCount(Integer openCount) {
        this.openCount = openCount;
    }

    @Override
    public Integer getClickCount() {
        return clickCount;
    }

    @Override
    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    @Override
    public Integer getPrintCount() {
        return printCount;
    }

    @Override
    public void setPrintCount(Integer printCount) {
        this.printCount = printCount;
    }

    @Override
    public Integer getForwardCount() {
        return forwardCount;
    }

    @Override
    public void setForwardCount(Integer forwardCount) {
        this.forwardCount = forwardCount;
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
    public Date getSentDate() {
        return sentDate;
    }

    @Override
    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
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
