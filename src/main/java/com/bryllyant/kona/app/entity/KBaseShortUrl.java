/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseShortUrl extends KBaseEntity implements KShortUrl {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long userId;
    private Long campaignId;
    private Long groupId;
    private Long channelId;
    private Long targetId;
    private Long replyId;
    private Long replyMessageId;
    private Long scriptId;
    private String domain;
    private String path;
    private String shortUrl;
    private String longUrl;
    private String description;
    private boolean singleMapped;
    private boolean channelRedirect;
    private boolean enabled;
    private Date expirationDate;
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
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
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
    public Long getReplyMessageId() {
        return replyMessageId;
    }

    @Override
    public void setReplyMessageId(Long replyMessageId) {
        this.replyMessageId = replyMessageId;
    }

    @Override
    public Long getScriptId() {
        return scriptId;
    }

    @Override
    public void setScriptId(Long scriptId) {
        this.scriptId = scriptId;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getShortUrl() {
        return shortUrl;
    }

    @Override
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public String getLongUrl() {
        return longUrl;
    }

    @Override
    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean isSingleMapped() {
        return singleMapped;
    }

    @Override
    public void setSingleMapped(boolean singleMapped) {
        this.singleMapped = singleMapped;
    }

    @Override
    public boolean isChannelRedirect() {
        return channelRedirect;
    }

    @Override
    public void setChannelRedirect(boolean channelRedirect) {
        this.channelRedirect = channelRedirect;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
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

