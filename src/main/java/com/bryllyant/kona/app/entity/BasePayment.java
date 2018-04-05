package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BasePayment extends KBaseEntity implements Serializable {
    public enum Type {
        CASH,
        CHECK,
        CARD,
        WIRE,
        ACH,
        PAYPAL,
        CREDIT,
        Promo,
        EXTERNAL,
        APPLE_APPSTORE,
        GOOGLE_PLAY,
        Partner,
        OTHER
    }

    public enum Status {
        SUCCESS,
        CARD_INVALID_NUMBER,
        CARD_INVALID_MONTH,
        CARD_INVALID_YEAR,
        CARD_INVALID_CVC,
        CARD_INVALID_ADDRESS,
        CARD_INVALID_ZIP,
        CARD_EXPIRED,
        CARD_DECLINED,
        CARD_MISSING,
        ACCOUNT_INVALID,
        ACCOUNT_DISABLED,
        AMOUNT_INVALID,
        PROCESSOR_ERROR,
        SYSTEM_ERROR
    }
}