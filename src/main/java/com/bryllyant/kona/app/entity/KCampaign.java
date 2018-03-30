package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KCampaign extends KEntityObject {
    enum Goal {
        LEAD_GEN,
        EDUCATION,
        CONVERSION,
        ACTIVATION,
        RETENTION,
        REENGAGEMENT
    }

    enum KPI {
        WEBSITE_VISIT,
        LINK_CLICK,
        PRODUCT_PURCHASE,
        APP_DOWNLOAD,
        USER_REGISTRATION,
        USER_ACTIVATION,
        EMAIL_SUBMISSION,
        VIDEO_VIEWED_5,
        VIDEO_VIEWED_10,
        VIDEO_VIEWED_15,
    }

    Long getId();
    void setId(Long id);

    String getUid();
    void setUid(String uid);

    Long getOwnerId();
    void setOwnerId(Long ownerId);

    Goal getGoal();
    void setGoal(Goal goal);

    KPI getKpi();
    void setKpi(KPI kpi);

    String getName();
    void setName(String name);

    String getSlug();
    void setSlug(String slug);

    String getDescription();
    void setDescription(String description);

    Integer getConversionCount();
    void setConversionCount(Integer conversionCount);

    Integer getConversionTarget();
    void setConversionTarget(Integer conversionTarget);

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