package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BaseCampaignChannel extends KBaseEntity implements Serializable {
    public enum Type {
        ORGANIC,
        AFFILIATE,
        BLOG_POST,
        Email,
        Sms,
        GOOGLE_ADWORDS,
        FACEBOOK,
        INSTAGRAM,
        TWITTER,
        LINKEDIN,
        SNAPCHAT,
        PINTEREST,
        BANNER_AD,
        SOCIAL_DEAL,
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