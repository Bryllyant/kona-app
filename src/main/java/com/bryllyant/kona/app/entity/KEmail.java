package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KEmail extends KEntityObject {

	@Override
	Long getId();
	void setId(Long id);

	String getUid();
	void setUid(String uid);

    Long getCampaignId();
    void setCampaignId(Long campaignId);

    Long getCampaignGroupId();
    void setCampaignGroupId(Long campaignGroupId);

	Long getCampaignChannelId();
	void setCampaignChannelId(Long campaignChannelId);

	Long getEmailGroupId();
	void setEmailGroupId(Long emailGroupId);

	Long getEmailAddressId();
	void setEmailAddressId(Long emailAddressId);

    Long getEmailContentId();
    void setEmailContentId(Long emailContentId);

	String getSesId();
	void setSesId(String sesId);

	String getFromAddress();
	void setFromAddress(String fromAddress);

	String getToAddress();
	void setToAddress(String toAddress);

	String getSubject();
	void setSubject(String subject);

	boolean isFailed();
	void setFailed(boolean failed);

	boolean isDelivered();
	void setDelivered(boolean delivered);

	boolean isBounced();
	void setBounced(boolean bounced);

	boolean isComplained();
	void setComplained(boolean complained);

	boolean isOptedOut();
	void setOptedOut(boolean optedOut);

	Integer getOpenCount();
	void setOpenCount(Integer openCount);

	Integer getClickCount();
	void setClickCount(Integer clickCount);

	Integer getPrintCount();
	void setPrintCount(Integer printCount);

	Integer getForwardCount();
	void setForwardCount(Integer forwardCount);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getSentDate();
	void setSentDate(Date sentDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);



}
