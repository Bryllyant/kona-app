package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BasePaymentAccount.Type;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PaymentAccount extends BasePaymentAccount implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.type
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Type type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.account_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Long accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.owner_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Long ownerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.default_account
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private boolean defaultAccount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.max_transaction_amount
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private BigDecimal maxTransactionAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.max_transaction_count
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Integer maxTransactionCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.provider_name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String providerName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.provider_slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String providerSlug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.provider_customer_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String providerCustomerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.card_token
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String cardToken;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.card_last4
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String cardLast4;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.card_type
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String cardType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.account_holder
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String accountHolder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.account_number
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String accountNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.routing_number
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String routingNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.account_street1
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String accountStreet1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.account_street2
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String accountStreet2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.account_city
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String accountCity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.account_state
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String accountState;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.account_postal_code
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String accountPostalCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.account_country
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String accountCountry;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment_account.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__payment_account
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.uid
     *
     * @return the value of kona__payment_account.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.uid
     *
     * @param uid the value for kona__payment_account.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.type
     *
     * @return the value of kona__payment_account.type
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.type
     *
     * @param type the value for kona__payment_account.type
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.account_id
     *
     * @return the value of kona__payment_account.account_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.account_id
     *
     * @param accountId the value for kona__payment_account.account_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.owner_id
     *
     * @return the value of kona__payment_account.owner_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.owner_id
     *
     * @param ownerId the value for kona__payment_account.owner_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.default_account
     *
     * @return the value of kona__payment_account.default_account
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public boolean isDefaultAccount() {
        return defaultAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.default_account
     *
     * @param defaultAccount the value for kona__payment_account.default_account
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setDefaultAccount(boolean defaultAccount) {
        this.defaultAccount = defaultAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.max_transaction_amount
     *
     * @return the value of kona__payment_account.max_transaction_amount
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public BigDecimal getMaxTransactionAmount() {
        return maxTransactionAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.max_transaction_amount
     *
     * @param maxTransactionAmount the value for kona__payment_account.max_transaction_amount
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setMaxTransactionAmount(BigDecimal maxTransactionAmount) {
        this.maxTransactionAmount = maxTransactionAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.max_transaction_count
     *
     * @return the value of kona__payment_account.max_transaction_count
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Integer getMaxTransactionCount() {
        return maxTransactionCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.max_transaction_count
     *
     * @param maxTransactionCount the value for kona__payment_account.max_transaction_count
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setMaxTransactionCount(Integer maxTransactionCount) {
        this.maxTransactionCount = maxTransactionCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.name
     *
     * @return the value of kona__payment_account.name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.name
     *
     * @param name the value for kona__payment_account.name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.slug
     *
     * @return the value of kona__payment_account.slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.slug
     *
     * @param slug the value for kona__payment_account.slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.provider_name
     *
     * @return the value of kona__payment_account.provider_name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.provider_name
     *
     * @param providerName the value for kona__payment_account.provider_name
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setProviderName(String providerName) {
        this.providerName = providerName == null ? null : providerName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.provider_slug
     *
     * @return the value of kona__payment_account.provider_slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getProviderSlug() {
        return providerSlug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.provider_slug
     *
     * @param providerSlug the value for kona__payment_account.provider_slug
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setProviderSlug(String providerSlug) {
        this.providerSlug = providerSlug == null ? null : providerSlug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.provider_customer_id
     *
     * @return the value of kona__payment_account.provider_customer_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getProviderCustomerId() {
        return providerCustomerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.provider_customer_id
     *
     * @param providerCustomerId the value for kona__payment_account.provider_customer_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setProviderCustomerId(String providerCustomerId) {
        this.providerCustomerId = providerCustomerId == null ? null : providerCustomerId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.card_token
     *
     * @return the value of kona__payment_account.card_token
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getCardToken() {
        return cardToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.card_token
     *
     * @param cardToken the value for kona__payment_account.card_token
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setCardToken(String cardToken) {
        this.cardToken = cardToken == null ? null : cardToken.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.card_last4
     *
     * @return the value of kona__payment_account.card_last4
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getCardLast4() {
        return cardLast4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.card_last4
     *
     * @param cardLast4 the value for kona__payment_account.card_last4
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setCardLast4(String cardLast4) {
        this.cardLast4 = cardLast4 == null ? null : cardLast4.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.card_type
     *
     * @return the value of kona__payment_account.card_type
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.card_type
     *
     * @param cardType the value for kona__payment_account.card_type
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.account_holder
     *
     * @return the value of kona__payment_account.account_holder
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getAccountHolder() {
        return accountHolder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.account_holder
     *
     * @param accountHolder the value for kona__payment_account.account_holder
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder == null ? null : accountHolder.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.account_number
     *
     * @return the value of kona__payment_account.account_number
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.account_number
     *
     * @param accountNumber the value for kona__payment_account.account_number
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber == null ? null : accountNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.routing_number
     *
     * @return the value of kona__payment_account.routing_number
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getRoutingNumber() {
        return routingNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.routing_number
     *
     * @param routingNumber the value for kona__payment_account.routing_number
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber == null ? null : routingNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.account_street1
     *
     * @return the value of kona__payment_account.account_street1
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getAccountStreet1() {
        return accountStreet1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.account_street1
     *
     * @param accountStreet1 the value for kona__payment_account.account_street1
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setAccountStreet1(String accountStreet1) {
        this.accountStreet1 = accountStreet1 == null ? null : accountStreet1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.account_street2
     *
     * @return the value of kona__payment_account.account_street2
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getAccountStreet2() {
        return accountStreet2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.account_street2
     *
     * @param accountStreet2 the value for kona__payment_account.account_street2
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setAccountStreet2(String accountStreet2) {
        this.accountStreet2 = accountStreet2 == null ? null : accountStreet2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.account_city
     *
     * @return the value of kona__payment_account.account_city
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getAccountCity() {
        return accountCity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.account_city
     *
     * @param accountCity the value for kona__payment_account.account_city
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setAccountCity(String accountCity) {
        this.accountCity = accountCity == null ? null : accountCity.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.account_state
     *
     * @return the value of kona__payment_account.account_state
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getAccountState() {
        return accountState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.account_state
     *
     * @param accountState the value for kona__payment_account.account_state
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setAccountState(String accountState) {
        this.accountState = accountState == null ? null : accountState.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.account_postal_code
     *
     * @return the value of kona__payment_account.account_postal_code
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getAccountPostalCode() {
        return accountPostalCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.account_postal_code
     *
     * @param accountPostalCode the value for kona__payment_account.account_postal_code
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setAccountPostalCode(String accountPostalCode) {
        this.accountPostalCode = accountPostalCode == null ? null : accountPostalCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.account_country
     *
     * @return the value of kona__payment_account.account_country
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getAccountCountry() {
        return accountCountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.account_country
     *
     * @param accountCountry the value for kona__payment_account.account_country
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setAccountCountry(String accountCountry) {
        this.accountCountry = accountCountry == null ? null : accountCountry.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.created_date
     *
     * @return the value of kona__payment_account.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.created_date
     *
     * @param createdDate the value for kona__payment_account.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment_account.updated_date
     *
     * @return the value of kona__payment_account.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment_account.updated_date
     *
     * @param updatedDate the value for kona__payment_account.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}