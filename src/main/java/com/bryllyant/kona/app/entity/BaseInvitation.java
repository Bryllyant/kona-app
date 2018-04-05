package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BaseInvitation extends KBaseEntity implements Serializable {
    public enum Type {
        JOIN,
        FRIEND
    }

    public enum Status {
        PENDING,
        ACCEPTED,
        DECLINED,
        IGNORED
    }

    public enum Channel {
        IN_APP,
        Email,
        Sms,
        TWITTER,
        FACEBOOK
    }

}