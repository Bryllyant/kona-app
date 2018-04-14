package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

import java.io.Serializable;

public class BasePaymentAccount extends KBaseEntity implements Serializable {
    public enum Type {
        CREDIT_CARD,
        DEBIT_CARD,
        PAYPAL,
        BANK_PERSONAL_CHECKING,
        BANK_PERSONAL_SAVINGS,
        BANK_BUSINESS_CHECKING,
        BANK_BUSINESS_SAVINGS,
        CRYPTOCURRENCY,
    }
}