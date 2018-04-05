package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BaseCampaign extends KBaseEntity implements Serializable {
    public enum Goal {
        LEAD_GEN,
        EDUCATION,
        CONVERSION,
        ACTIVATION,
        RETENTION,
        REENGAGEMENT
    }

    public enum KPI {
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
}