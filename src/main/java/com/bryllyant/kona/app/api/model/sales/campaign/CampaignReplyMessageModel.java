package com.bryllyant.kona.app.api.model.sales.campaign;

import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.CampaignReplyMessage;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class CampaignReplyMessageModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private CampaignModel campaign;
    private CampaignGroupModel group;
    private CampaignChannelModel channel;
    private CampaignTargetModel target;
    private CampaignReplyModel reply;

    private EmailModel email;
    private SmsModel sms;
    private PushModel push;

    private UserModel toUser;
    private String toEmail;
    private String toMobileNumber;

    private Date createdDate;
    private Date updatedDate;


    public static CampaignReplyMessageModel from(CampaignReplyMessage message) {
        CampaignReplyMessageModel model = new CampaignReplyMessageModel();
        model.setUid(message.getUid());
        return model;
    }

    public static CampaignReplyMessageModel create(String uid) {
        CampaignReplyMessageModel model = new CampaignReplyMessageModel();
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

    public CampaignReplyModel getReply() {
        return reply;
    }

    public void setReply(CampaignReplyModel reply) {
        this.set("reply", reply);
    }

    public EmailModel getEmail() {
        return email;
    }

    public void setEmail(EmailModel email) {
        this.set("email", email);
    }

    public SmsModel getSms() {
        return sms;
    }

    public void setSms(SmsModel sms) {
        this.set("sms", sms);
    }

    public PushModel getPush() {
        return push;
    }

    public void setPush(PushModel push) {
        this.set("push", push);
    }

    public UserModel getToUser() {
        return toUser;
    }

    public void setToUser(UserModel toUser) {
        this.set("toUser", toUser);
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.set("toEmail", toEmail);
    }

    public String getToMobileNumber() {
        return toMobileNumber;
    }

    public void setToMobileNumber(String toMobileNumber) {
        this.set("toMobileNumber", toMobileNumber);
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
