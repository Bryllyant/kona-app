package com.bryllyant.kona.app.api.model.sales.campaign;

import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class EmailModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private CampaignModel campaign;
    private CampaignGroupModel campaignGroup;
    private CampaignChannelModel campaignChannel;
    private EmailGroupModel emailGroup;
    private EmailAddressModel emailAddress;
    private EmailContentModel emailContent;
    private String sesId;
    private String fromAddress;
    private String toAddress;
    private String subject;
    private Boolean failed;
    private Boolean delivered;
    private Boolean bounced;
    private Boolean complained;
    private Boolean optedOut;
    private Integer openCount;
    private Integer clickCount;
    private Integer printCount;
    private Integer forwardCount;
    private Date sentDate;
    private Date createdDate;
    private Date updatedDate;


    public static EmailModel from(Email email) {
        EmailModel model = new EmailModel();
        model.setUid(email.getUid());
        model.setToAddress(email.getToAddress());
        return model;
    }

    public static EmailModel create(String uid) {
        EmailModel model = new EmailModel();
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

    public EmailGroupModel getEmailGroup() {
        return emailGroup;
    }

    public void setEmailGroup(EmailGroupModel emailGroup) {
        this.set("emailGroup", emailGroup);
    }

    public EmailAddressModel getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddressModel emailAddress) {
        this.set("emailAddress", emailAddress);
    }

    public EmailContentModel getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(EmailContentModel emailContent) {
        this.set("emailContent", emailContent);
    }

    public String getSesId() {
        return sesId;
    }

    public void setSesId(String sesId) {
        this.set("sesId", sesId);
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.set("fromAddress", fromAddress);
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.set("toAddress", toAddress);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.set("subject", subject);
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

    public Boolean getBounced() {
        return bounced;
    }

    public void setBounced(Boolean bounced) {
        this.set("bounced", bounced);
    }

    public Boolean getComplained() {
        return complained;
    }

    public void setComplained(Boolean complained) {
        this.set("complained", complained);
    }

    public Boolean getOptedOut() {
        return optedOut;
    }

    public void setOptedOut(Boolean optedOut) {
        this.set("optedOut", optedOut);
    }

    public Integer getOpenCount() {
        return openCount;
    }

    public void setOpenCount(Integer openCount) {
        this.set("openCount", openCount);
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.set("clickCount", clickCount);
    }

    public Integer getPrintCount() {
        return printCount;
    }

    public void setPrintCount(Integer printCount) {
        this.set("printCount", printCount);
    }

    public Integer getForwardCount() {
        return forwardCount;
    }

    public void setForwardCount(Integer forwardCount) {
        this.set("forwardCount", forwardCount);
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.set("sentDate", sentDate);
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
