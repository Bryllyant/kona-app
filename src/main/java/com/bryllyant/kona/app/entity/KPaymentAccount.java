package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.math.BigDecimal;
import java.util.Date;

public interface KPaymentAccount extends KEntityObject {
    enum Type {
        CREDIT_CARD,
        DEBIT_CARD,
        PAYPAL,
        BANK_PERSONAL_CHECKING,
        BANK_PERSONAL_SAVINGS,
        BANK_BUSINESS_CHECKING,
        BANK_BUSINESS_SAVINGS
    }


    Long getId();
    void setId(Long id);

    String getUid();
    void setUid(String uid);

    Type getType();
    void setType(Type type);
    
    Long getAccountId();
    void setAccountId(Long accountId);

    Long getOwnerId();
    void setOwnerId(Long ownerId);

    boolean isDefaultAccount();
    void setDefaultAccount(boolean defaultAccount);

    String getName();
    void setName(String name);

    String getSlug();
    void setSlug(String slug);

    String getProviderName();
    void setProviderName(String providerName);

    String getProviderCustomerId();
    void setProviderCustomerId(String providerCustomerId);

    String getCardToken();
    void setCardToken(String cardToken);
    
    String getCardLast4();
    void setCardLast4(String cardLast4);

    String getCardType();
    void setCardType(String cardType);

    String getAccountHolder();
    void setAccountHolder(String accountHolder);

    String getAccountNumber();
    void setAccountNumber(String accountNumber);

    String getRoutingNumber();
    void setRoutingNumber(String routingNumber);

    String getAccountStreet1();
    void setAccountStreet1(String accountStreet1);

    String getAccountStreet2();
    void setAccountStreet2(String accountStreet2);

    String getAccountCity();
    void setAccountCity(String accountCity);

    String getAccountState();
    void setAccountState(String accountState);

    String getAccountPostalCode();
    void setAccountPostalCode(String accountPostalCode);

    String getAccountCountry();
    void setAccountCountry(String accountCountry);

    BigDecimal getMaxTransactionAmount();
    void setMaxTransactionAmount(BigDecimal maxTransactionAmount);

    Integer getMaxTransactionCount();
    void setMaxTransactionCount(Integer maxTransactionCount);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();
    void setUpdatedDate(Date updatedDate);

}