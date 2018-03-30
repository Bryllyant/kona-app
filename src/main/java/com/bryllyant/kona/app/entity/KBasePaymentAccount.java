package com.bryllyant.kona.app.entity;

import java.math.BigDecimal;
import java.util.Date;

public class KBasePaymentAccount extends KBaseEntity implements KPaymentAccount {
    private static final long serialVersionUID = 1L;

    private Long id = null;
    private String uid = null;
    private Type type = null;
    private Long accountId = null;
    private Long ownerId = null;
    private boolean defaultAccount = false;
    private BigDecimal maxTransactionAmount = null;
    private Integer maxTransactionCount = null;
    private String name = null; // name of the account
    private String slug = null;
    private String providerName = null; //
    private String providerCustomerId = null; //
    private String cardToken = null; // one time token issued by provider used by this user/account
    private String cardLast4 = null;
    private String cardType = null;
    private String accountHolder = null; // full name of the account holder
    private String accountNumber = null;
    private String routingNumber = null;
    private String accountStreet1 = null;
    private String accountStreet2 = null;
    private String accountCity = null;
    private String accountState = null;
    private String accountPostalCode = null;
    private String accountCountry = null;
    private Date createdDate = null;
    private Date updatedDate = null;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public Long getAccountId() {
        return accountId;
    }

    @Override
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public Long getOwnerId() {
        return ownerId;
    }

    @Override
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean isDefaultAccount() {
        return defaultAccount;
    }

    @Override
    public void setDefaultAccount(boolean defaultAccount) {
        this.defaultAccount = defaultAccount;
    }

    @Override
    public BigDecimal getMaxTransactionAmount() {
        return maxTransactionAmount;
    }

    @Override
    public void setMaxTransactionAmount(BigDecimal maxTransactionAmount) {
        this.maxTransactionAmount = maxTransactionAmount;
    }

    @Override
    public Integer getMaxTransactionCount() {
        return maxTransactionCount;
    }

    @Override
    public void setMaxTransactionCount(Integer maxTransactionCount) {
        this.maxTransactionCount = maxTransactionCount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSlug() {
        return slug;
    }

    @Override
    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String getProviderName() {
        return providerName;
    }

    @Override
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    @Override
    public String getProviderCustomerId() {
        return providerCustomerId;
    }

    @Override
    public void setProviderCustomerId(String providerCustomerId) {
        this.providerCustomerId = providerCustomerId;
    }

    @Override
    public String getCardToken() {
        return cardToken;
    }

    @Override
    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    @Override
    public String getCardLast4() {
        return cardLast4;
    }

    @Override
    public void setCardLast4(String cardLast4) {
        this.cardLast4 = cardLast4;
    }

    @Override
    public String getCardType() {
        return cardType;
    }

    @Override
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public String getAccountHolder() {
        return accountHolder;
    }

    @Override
    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String getRoutingNumber() {
        return routingNumber;
    }

    @Override
    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    @Override
    public String getAccountStreet1() {
        return accountStreet1;
    }

    @Override
    public void setAccountStreet1(String accountStreet1) {
        this.accountStreet1 = accountStreet1;
    }

    @Override
    public String getAccountStreet2() {
        return accountStreet2;
    }

    @Override
    public void setAccountStreet2(String accountStreet2) {
        this.accountStreet2 = accountStreet2;
    }

    @Override
    public String getAccountCity() {
        return accountCity;
    }

    @Override
    public void setAccountCity(String accountCity) {
        this.accountCity = accountCity;
    }

    @Override
    public String getAccountState() {
        return accountState;
    }

    @Override
    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }

    @Override
    public String getAccountPostalCode() {
        return accountPostalCode;
    }

    @Override
    public void setAccountPostalCode(String accountPostalCode) {
        this.accountPostalCode = accountPostalCode;
    }

    @Override
    public String getAccountCountry() {
        return accountCountry;
    }

    @Override
    public void setAccountCountry(String accountCountry) {
        this.accountCountry = accountCountry;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Date getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
