package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BaseCampaignTarget extends KBaseEntity implements Serializable {
    public enum Type {
        WEBSITE,
        LANDING_PAGE,
        APP_STORE
    }
}