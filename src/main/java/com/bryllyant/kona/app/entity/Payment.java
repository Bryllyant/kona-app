package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BasePayment.Status;
import com.bryllyant.kona.app.entity.BasePayment.Type;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Payment extends BasePayment implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.type
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Type type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.status
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Status status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.account_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Long accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.user_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.token_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Long tokenId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.payment_account_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Long paymentAccountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.currency_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Long currencyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.invoice_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Long invoiceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.promo_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Long promoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.campaign_channel_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Long campaignChannelId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.hostname
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String hostname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.user_agent
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String userAgent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.latitude
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Double latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.longitude
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Double longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.card_token
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String cardToken;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.card_last4
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String cardLast4;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.amount
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private BigDecimal amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.amount_refunded
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private BigDecimal amountRefunded;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.processor_ref
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String processorRef;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.processor_error
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String processorError;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.processor_fee
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private BigDecimal processorFee;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.paid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private boolean paid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.refunded
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private boolean refunded;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.disputed
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private boolean disputed;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.failed
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private boolean failed;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.paid_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date paidDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.disputed_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date disputedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.refunded_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date refundedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.failed_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date failedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private Date updatedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__payment.processor_receipt
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private String processorReceipt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__payment
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.uid
     *
     * @return the value of kona__payment.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.uid
     *
     * @param uid the value for kona__payment.uid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.type
     *
     * @return the value of kona__payment.type
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.type
     *
     * @param type the value for kona__payment.type
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.status
     *
     * @return the value of kona__payment.status
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Status getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.status
     *
     * @param status the value for kona__payment.status
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.account_id
     *
     * @return the value of kona__payment.account_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.account_id
     *
     * @param accountId the value for kona__payment.account_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.user_id
     *
     * @return the value of kona__payment.user_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.user_id
     *
     * @param userId the value for kona__payment.user_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.token_id
     *
     * @return the value of kona__payment.token_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Long getTokenId() {
        return tokenId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.token_id
     *
     * @param tokenId the value for kona__payment.token_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.payment_account_id
     *
     * @return the value of kona__payment.payment_account_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Long getPaymentAccountId() {
        return paymentAccountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.payment_account_id
     *
     * @param paymentAccountId the value for kona__payment.payment_account_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setPaymentAccountId(Long paymentAccountId) {
        this.paymentAccountId = paymentAccountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.currency_id
     *
     * @return the value of kona__payment.currency_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Long getCurrencyId() {
        return currencyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.currency_id
     *
     * @param currencyId the value for kona__payment.currency_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.invoice_id
     *
     * @return the value of kona__payment.invoice_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Long getInvoiceId() {
        return invoiceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.invoice_id
     *
     * @param invoiceId the value for kona__payment.invoice_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.promo_id
     *
     * @return the value of kona__payment.promo_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Long getPromoId() {
        return promoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.promo_id
     *
     * @param promoId the value for kona__payment.promo_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.campaign_channel_id
     *
     * @return the value of kona__payment.campaign_channel_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Long getCampaignChannelId() {
        return campaignChannelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.campaign_channel_id
     *
     * @param campaignChannelId the value for kona__payment.campaign_channel_id
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setCampaignChannelId(Long campaignChannelId) {
        this.campaignChannelId = campaignChannelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.hostname
     *
     * @return the value of kona__payment.hostname
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.hostname
     *
     * @param hostname the value for kona__payment.hostname
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.user_agent
     *
     * @return the value of kona__payment.user_agent
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.user_agent
     *
     * @param userAgent the value for kona__payment.user_agent
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.latitude
     *
     * @return the value of kona__payment.latitude
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.latitude
     *
     * @param latitude the value for kona__payment.latitude
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.longitude
     *
     * @return the value of kona__payment.longitude
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.longitude
     *
     * @param longitude the value for kona__payment.longitude
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.card_token
     *
     * @return the value of kona__payment.card_token
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getCardToken() {
        return cardToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.card_token
     *
     * @param cardToken the value for kona__payment.card_token
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setCardToken(String cardToken) {
        this.cardToken = cardToken == null ? null : cardToken.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.card_last4
     *
     * @return the value of kona__payment.card_last4
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getCardLast4() {
        return cardLast4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.card_last4
     *
     * @param cardLast4 the value for kona__payment.card_last4
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setCardLast4(String cardLast4) {
        this.cardLast4 = cardLast4 == null ? null : cardLast4.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.amount
     *
     * @return the value of kona__payment.amount
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.amount
     *
     * @param amount the value for kona__payment.amount
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.amount_refunded
     *
     * @return the value of kona__payment.amount_refunded
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public BigDecimal getAmountRefunded() {
        return amountRefunded;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.amount_refunded
     *
     * @param amountRefunded the value for kona__payment.amount_refunded
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setAmountRefunded(BigDecimal amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.processor_ref
     *
     * @return the value of kona__payment.processor_ref
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getProcessorRef() {
        return processorRef;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.processor_ref
     *
     * @param processorRef the value for kona__payment.processor_ref
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setProcessorRef(String processorRef) {
        this.processorRef = processorRef == null ? null : processorRef.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.processor_error
     *
     * @return the value of kona__payment.processor_error
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getProcessorError() {
        return processorError;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.processor_error
     *
     * @param processorError the value for kona__payment.processor_error
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setProcessorError(String processorError) {
        this.processorError = processorError == null ? null : processorError.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.processor_fee
     *
     * @return the value of kona__payment.processor_fee
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public BigDecimal getProcessorFee() {
        return processorFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.processor_fee
     *
     * @param processorFee the value for kona__payment.processor_fee
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setProcessorFee(BigDecimal processorFee) {
        this.processorFee = processorFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.paid
     *
     * @return the value of kona__payment.paid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.paid
     *
     * @param paid the value for kona__payment.paid
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.refunded
     *
     * @return the value of kona__payment.refunded
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public boolean isRefunded() {
        return refunded;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.refunded
     *
     * @param refunded the value for kona__payment.refunded
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setRefunded(boolean refunded) {
        this.refunded = refunded;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.disputed
     *
     * @return the value of kona__payment.disputed
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public boolean isDisputed() {
        return disputed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.disputed
     *
     * @param disputed the value for kona__payment.disputed
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setDisputed(boolean disputed) {
        this.disputed = disputed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.failed
     *
     * @return the value of kona__payment.failed
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public boolean isFailed() {
        return failed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.failed
     *
     * @param failed the value for kona__payment.failed
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.paid_date
     *
     * @return the value of kona__payment.paid_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getPaidDate() {
        return paidDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.paid_date
     *
     * @param paidDate the value for kona__payment.paid_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.disputed_date
     *
     * @return the value of kona__payment.disputed_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getDisputedDate() {
        return disputedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.disputed_date
     *
     * @param disputedDate the value for kona__payment.disputed_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setDisputedDate(Date disputedDate) {
        this.disputedDate = disputedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.refunded_date
     *
     * @return the value of kona__payment.refunded_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getRefundedDate() {
        return refundedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.refunded_date
     *
     * @param refundedDate the value for kona__payment.refunded_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setRefundedDate(Date refundedDate) {
        this.refundedDate = refundedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.failed_date
     *
     * @return the value of kona__payment.failed_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getFailedDate() {
        return failedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.failed_date
     *
     * @param failedDate the value for kona__payment.failed_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setFailedDate(Date failedDate) {
        this.failedDate = failedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.created_date
     *
     * @return the value of kona__payment.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.created_date
     *
     * @param createdDate the value for kona__payment.created_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.updated_date
     *
     * @return the value of kona__payment.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.updated_date
     *
     * @param updatedDate the value for kona__payment.updated_date
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__payment.processor_receipt
     *
     * @return the value of kona__payment.processor_receipt
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public String getProcessorReceipt() {
        return processorReceipt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__payment.processor_receipt
     *
     * @param processorReceipt the value for kona__payment.processor_receipt
     *
     * @mbg.generated Tue Apr 17 08:48:01 EDT 2018
     */
    public void setProcessorReceipt(String processorReceipt) {
        this.processorReceipt = processorReceipt == null ? null : processorReceipt.trim();
    }
}