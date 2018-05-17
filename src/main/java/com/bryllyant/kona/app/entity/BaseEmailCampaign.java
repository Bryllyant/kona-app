package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BaseEmailCampaign extends KBaseEntity implements Serializable {
    public enum Status {
        CREATED,
        RUNNING, // sending messages
        PROCESSING, // processing notifications
        COMPLETED,
        ERROR
    }
}