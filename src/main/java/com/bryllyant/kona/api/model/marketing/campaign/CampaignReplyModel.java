package com.bryllyant.kona.api.model.marketing.campaign;

import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class CampaignReplyModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private CampaignModel campaign;
    private CampaignGroupModel group;
    private CampaignChannelModel channel;
    private CampaignTargetModel target;
    private CampaignReply.Type type;

    private String name;
    private String slug;

    private String subject;
    private String content;

    private Boolean enabled;
    private Date startDate;
    private Date endDate;
    private Date createdDate;
    private Date updatedDate;


    public static CampaignReplyModel from(CampaignReply reply) {
        CampaignReplyModel model = new CampaignReplyModel();
        model.setUid(reply.getUid());
        model.setName(reply.getName());
        return model;
    }

    public static CampaignReplyModel create(String uid) {
        CampaignReplyModel model = new CampaignReplyModel();
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

    public CampaignReply.Type getType() {
        return type;
    }

    public void setType(CampaignReply.Type type) {
        this.set("type", type);
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.set("subject", subject);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.set("content", content);
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.set("enabled", enabled);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.set("startDate", startDate);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.set("endDate", endDate);
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
