package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KShortUrl extends KEntityObject {

	Long getId();
	void setId(Long id);

    String getUid();
    void setUid(String uid);

    Long getUserId();
    void setUserId(Long userId);

	Long getCampaignId();
	void setCampaignId(Long campaignId);

    Long getGroupId();
    void setGroupId(Long groupId);

    Long getChannelId();
    void setChannelId(Long channelId);

    Long getTargetId();
    void setTargetId(Long targetId);

    Long getReplyId();
    void setReplyId(Long replyId);

    Long getReplyMessageId();
    void setReplyMessageId(Long replyMessageId);

    Long getScriptId();
    void setScriptId(Long scriptId);

	String getDomain();
	void setDomain(String domain);

	String getPath();
	void setPath(String path);

	String getShortUrl();
	void setShortUrl(String shortUrl);

	String getLongUrl();
	void setLongUrl(String longUrl);

	String getDescription();
	void setDescription(String description);

    boolean isSingleMapped();
    void setSingleMapped(boolean singleMapped);

    boolean isChannelRedirect();
    void setChannelRedirect(boolean channelRedirect);

	boolean isEnabled();
	void setEnabled(boolean enabled);

    Date getExpirationDate();
    void setExpirationDate(Date expirationDate);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);

}