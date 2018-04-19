package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BaseCampaignReply.Type;
import java.io.Serializable;
import java.util.Date;

public class CampaignReply extends BaseCampaignReply implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_reply.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_reply.campaign_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Long campaignId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_reply.group_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Long groupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_reply.channel_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Long channelId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_reply.target_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Long targetId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_reply.type
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Type type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_reply.name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_reply.slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_reply.subject
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String subject;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_reply.enabled
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_reply.start_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date startDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_reply.end_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date endDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_reply.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_reply.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date updatedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_reply.content
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__campaign_reply
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_reply.uid
     *
     * @return the value of kona__campaign_reply.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_reply.uid
     *
     * @param uid the value for kona__campaign_reply.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_reply.campaign_id
     *
     * @return the value of kona__campaign_reply.campaign_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Long getCampaignId() {
        return campaignId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_reply.campaign_id
     *
     * @param campaignId the value for kona__campaign_reply.campaign_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_reply.group_id
     *
     * @return the value of kona__campaign_reply.group_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_reply.group_id
     *
     * @param groupId the value for kona__campaign_reply.group_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_reply.channel_id
     *
     * @return the value of kona__campaign_reply.channel_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Long getChannelId() {
        return channelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_reply.channel_id
     *
     * @param channelId the value for kona__campaign_reply.channel_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_reply.target_id
     *
     * @return the value of kona__campaign_reply.target_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Long getTargetId() {
        return targetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_reply.target_id
     *
     * @param targetId the value for kona__campaign_reply.target_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_reply.type
     *
     * @return the value of kona__campaign_reply.type
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_reply.type
     *
     * @param type the value for kona__campaign_reply.type
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_reply.name
     *
     * @return the value of kona__campaign_reply.name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_reply.name
     *
     * @param name the value for kona__campaign_reply.name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_reply.slug
     *
     * @return the value of kona__campaign_reply.slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_reply.slug
     *
     * @param slug the value for kona__campaign_reply.slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_reply.subject
     *
     * @return the value of kona__campaign_reply.subject
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_reply.subject
     *
     * @param subject the value for kona__campaign_reply.subject
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_reply.enabled
     *
     * @return the value of kona__campaign_reply.enabled
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_reply.enabled
     *
     * @param enabled the value for kona__campaign_reply.enabled
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_reply.start_date
     *
     * @return the value of kona__campaign_reply.start_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_reply.start_date
     *
     * @param startDate the value for kona__campaign_reply.start_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_reply.end_date
     *
     * @return the value of kona__campaign_reply.end_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_reply.end_date
     *
     * @param endDate the value for kona__campaign_reply.end_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_reply.created_date
     *
     * @return the value of kona__campaign_reply.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_reply.created_date
     *
     * @param createdDate the value for kona__campaign_reply.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_reply.updated_date
     *
     * @return the value of kona__campaign_reply.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_reply.updated_date
     *
     * @param updatedDate the value for kona__campaign_reply.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_reply.content
     *
     * @return the value of kona__campaign_reply.content
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_reply.content
     *
     * @param content the value for kona__campaign_reply.content
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}