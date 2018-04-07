package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BaseCampaignReply extends KBaseEntity implements Serializable {
    public enum Type {
        EMAIL,
        SMS,
        PUSH
    }
}