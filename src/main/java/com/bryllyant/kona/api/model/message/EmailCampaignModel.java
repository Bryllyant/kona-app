package com.bryllyant.kona.api.model.message;


import com.bryllyant.kona.api.model.marketing.campaign.CampaignChannelModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignGroupModel;
import com.bryllyant.kona.api.model.marketing.campaign.CampaignModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.EmailCampaign;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class EmailCampaignModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private UserModel owner;
    private CampaignModel campaign;
    private CampaignGroupModel campaignGroup;
    private CampaignChannelModel campaignChannel;
    private EmailGroupModel emailGroup;
    private EmailContentModel emailContent;
    private EmailCampaign.Status status;
    private String name;
    private String slug;
    private String description;
    private String fromAddress;
    private String replyTo;
    private String subject;
    private Double failedCount;
    private Double failedRate;
    private Double deliveredCount;
    private Double deliveredRate;
    private Double bouncedCount;
    private Double bouncedRate;
    private Double complainedCount;
    private Double complainedRate;
    private Double optedOutCount;
    private Double optedOutRate;
    private Double openedCount;
    private Double openedAllRate;
    private Double clickedCount;
    private Double clickedAllRate;
    private Double printedCount;
    private Double printedAllRate;
    private Double forwardedCount;
    private Double forwardedAllRate;
    private Double openedDeliveredRate;
    private Double clickedDeliveredRate;
    private Double printedDeliveredRate;
    private Double forwardedDeliveredRate;
    private Double clickedOpenedRate;
    private Double printedOpenedRate;
    private Double forwardedOpenedRate;
    private Double emailCount;
    private Date createdDate;
    private Date updatedDate;

    public static EmailCampaignModel from(EmailCampaign emailCampaign) {
        EmailCampaignModel model = new EmailCampaignModel();
        model.setUid(emailCampaign.getUid());
        model.setName(emailCampaign.getName());
        model.setSlug(emailCampaign.getSlug());
        return model;
    }

    public static EmailCampaignModel create(String uid) {
        EmailCampaignModel model = new EmailCampaignModel();
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

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.set("owner", owner);
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

    public EmailContentModel getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(EmailContentModel emailContent) {
        this.set("emailContent", emailContent);
    }

    public EmailCampaign.Status getStatus() {
        return status;
    }

    public void setStatus(EmailCampaign.Status status) {
        this.set("status", status);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.set("description", description);
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.set("fromAddress", fromAddress);
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.set("replyTo", replyTo);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.set("subject", subject);
    }

    public Double getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(Double failedCount) {
        this.set("failedCount", failedCount);
    }

    public Double getFailedRate() {
        return failedRate;
    }

    public void setFailedRate(Double failedRate) {
        this.set("failedRate", failedRate);
    }

    public Double getDeliveredCount() {
        return deliveredCount;
    }

    public void setDeliveredCount(Double deliveredCount) {
        this.set("deliveredCount", deliveredCount);
    }

    public Double getDeliveredRate() {
        return deliveredRate;
    }

    public void setDeliveredRate(Double deliveredRate) {
        this.set("deliveredRate", deliveredRate);
    }

    public Double getBouncedCount() {
        return bouncedCount;
    }

    public void setBouncedCount(Double bouncedCount) {
        this.set("bouncedCount", bouncedCount);
    }

    public Double getBouncedRate() {
        return bouncedRate;
    }

    public void setBouncedRate(Double bouncedRate) {
        this.set("bouncedRate", bouncedRate);
    }

    public Double getComplainedCount() {
        return complainedCount;
    }

    public void setComplainedCount(Double complainedCount) {
        this.set("complainedCount", complainedCount);
    }

    public Double getComplainedRate() {
        return complainedRate;
    }

    public void setComplainedRate(Double complainedRate) {
        this.set("complainedRate", complainedRate);
    }

    public Double getOptedOutCount() {
        return optedOutCount;
    }

    public void setOptedOutCount(Double optedOutCount) {
        this.set("optedOutCount", optedOutCount);
    }

    public Double getOptedOutRate() {
        return optedOutRate;
    }

    public void setOptedOutRate(Double optedOutRate) {
        this.set("optedOutRate", optedOutRate);
    }

    public Double getOpenedCount() {
        return openedCount;
    }

    public void setOpenedCount(Double openedCount) {
        this.set("openedCount", openedCount);
    }

    public Double getOpenedAllRate() {
        return openedAllRate;
    }

    public void setOpenedAllRate(Double openedAllRate) {
        this.set("openedAllRate", openedAllRate);
    }

    public Double getClickedCount() {
        return clickedCount;
    }

    public void setClickedCount(Double clickedCount) {
        this.set("clickedCount", clickedCount);
    }

    public Double getClickedAllRate() {
        return clickedAllRate;
    }

    public void setClickedAllRate(Double clickedAllRate) {
        this.set("clickedAllRate", clickedAllRate);
    }

    public Double getPrintedCount() {
        return printedCount;
    }

    public void setPrintedCount(Double printedCount) {
        this.set("printedCount", printedCount);
    }

    public Double getPrintedAllRate() {
        return printedAllRate;
    }

    public void setPrintedAllRate(Double printedAllRate) {
        this.set("printedAllRate", printedAllRate);
    }

    public Double getForwardedCount() {
        return forwardedCount;
    }

    public void setForwardedCount(Double forwardedCount) {
        this.set("forwardedCount", forwardedCount);
    }

    public Double getForwardedAllRate() {
        return forwardedAllRate;
    }

    public void setForwardedAllRate(Double forwardedAllRate) {
        this.set("forwardedAllRate", forwardedAllRate);
    }

    public Double getOpenedDeliveredRate() {
        return openedDeliveredRate;
    }

    public void setOpenedDeliveredRate(Double openedDeliveredRate) {
        this.set("openedDeliveredRate", openedDeliveredRate);
    }

    public Double getClickedDeliveredRate() {
        return clickedDeliveredRate;
    }

    public void setClickedDeliveredRate(Double clickedDeliveredRate) {
        this.set("clickedDeliveredRate", clickedDeliveredRate);
    }

    public Double getPrintedDeliveredRate() {
        return printedDeliveredRate;
    }

    public void setPrintedDeliveredRate(Double printedDeliveredRate) {
        this.set("printedDeliveredRate", printedDeliveredRate);
    }

    public Double getForwardedDeliveredRate() {
        return forwardedDeliveredRate;
    }

    public void setForwardedDeliveredRate(Double forwardedDeliveredRate) {
        this.set("forwardedDeliveredRate", forwardedDeliveredRate);
    }

    public Double getClickedOpenedRate() {
        return clickedOpenedRate;
    }

    public void setClickedOpenedRate(Double clickedOpenedRate) {
        this.set("clickedOpenedRate", clickedOpenedRate);
    }

    public Double getPrintedOpenedRate() {
        return printedOpenedRate;
    }

    public void setPrintedOpenedRate(Double printedOpenedRate) {
        this.set("printedOpenedRate", printedOpenedRate);
    }

    public Double getForwardedOpenedRate() {
        return forwardedOpenedRate;
    }

    public void setForwardedOpenedRate(Double forwardedOpenedRate) {
        this.set("forwardedOpenedRate", forwardedOpenedRate);
    }

    public Double getEmailCount() {
        return emailCount;
    }

    public void setEmailCount(Double emailCount) {
        this.set("emailCount", emailCount);
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
