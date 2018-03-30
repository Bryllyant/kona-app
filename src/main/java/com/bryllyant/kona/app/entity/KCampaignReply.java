package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KCampaignReply extends KEntityObject {
    enum Type {
        EMAIL,
        SMS,
        PUSH
    }

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

    Type getType();

    void setType(Type type);

    String getName();

    void setName(String name);

    String getSlug();

    void setSlug(String slug);

    String getSubject();
    void setSubject(String subject);

    String getContent();
    void setContent(String content);

    boolean isEnabled();

    void setEnabled(boolean enabled);

    Date getStartDate();

    void setStartDate(Date startDate);

    Date getEndDate();

    void setEndDate(Date endDate);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);
}