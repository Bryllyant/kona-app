package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KCampaignReplyMessage extends KEntityObject {

    @Override
    Long getId();

    void setId(Long id);

    String getUid();

    void setUid(String uid);

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

    Long getEmailId();

    void setEmailId(Long emailId);

    Long getSmsId();

    void setSmsId(Long smsId);

    Long getPushId();

    void setPushId(Long pushId);

    Long getToUserId();

    void setToUserId(Long toUserId);

    String getToEmail();

    void setToEmail(String toEmail);

    String getToMobileNumber();

    void setToMobileNumber(String toMobileNumber);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);
}