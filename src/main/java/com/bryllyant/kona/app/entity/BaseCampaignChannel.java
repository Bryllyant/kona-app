package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BaseCampaignChannel extends KBaseEntity implements Serializable {
    public enum Type {
        ORGANIC,
        AFFILIATE,
        BLOG_POST,
        PRESS_RELEASE,
        EMAIL,
        SMS,
        GOOGLE_ADWORDS,
        FACEBOOK,
        INSTAGRAM,
        TWITTER,
        LINKEDIN,
        SNAPCHAT,
        PINTEREST,
        BANNER_AD,
        SOCIAL_DEAL,
        VIDEO,
        OTHER
    }

    public enum TargetStrategy {
        RANDOM,
        ROUND_ROBIN,
        MAX_CONVERSIONS,
    }

    public enum ReplyStrategy {
        RANDOM,
        ROUND_ROBIN,
        MAX_VIEWS,
    }
}