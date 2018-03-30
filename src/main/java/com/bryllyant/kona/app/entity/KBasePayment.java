package com.bryllyant.kona.app.entity;

import java.math.BigDecimal;
import java.util.Date;

public class KBasePayment extends KBaseEntity implements KPayment {
	private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Type type;
    private Status status;
    private Long accountId;
    private Long userId;
    private Long tokenId;
    private Long paymentAccountId;
    private Long currencyId;
    private Long invoiceId;
    private Long promoId;
    private Long campaignChannelId;
    private String hostname;
    private String userAgent;
    private Double latitude;
    private Double longitude;
    private String cardToken;
    private String cardLast4;
    private BigDecimal amount;
    private BigDecimal amountRefunded;
    private String processorRef;
    private String processorReceipt;
    private String processorError;
    private BigDecimal processorFee;
    private boolean paid;
    private boolean refunded;
    private boolean disputed;
    private boolean failed;
    private Date paidDate;
    private Date disputedDate;
    private Date refundedDate;
    private Date failedDate;
    private Date createdDate;
    private Date updatedDate;


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
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
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
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Long getTokenId() {
        return tokenId;
    }

    @Override
    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    @Override
    public Long getPaymentAccountId() {
        return paymentAccountId;
    }

    @Override
    public void setPaymentAccountId(Long paymentAccountId) {
        this.paymentAccountId = paymentAccountId;
    }

    @Override
    public Long getCurrencyId() {
        return currencyId;
    }

    @Override
    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public Long getInvoiceId() {
        return invoiceId;
    }

    @Override
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public Long getPromoId() {
        return promoId;
    }

    @Override
    public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    @Override
    public Long getCampaignChannelId() {
        return campaignChannelId;
    }

    @Override
    public void setCampaignChannelId(Long campaignChannelId) {
        this.campaignChannelId = campaignChannelId;
    }

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public Double getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public Double getLongitude() {
        return longitude;
    }

    @Override
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public BigDecimal getAmountRefunded() {
        return amountRefunded;
    }

    @Override
    public void setAmountRefunded(BigDecimal amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    @Override
    public String getProcessorRef() {
        return processorRef;
    }

    @Override
    public void setProcessorRef(String processorRef) {
        this.processorRef = processorRef;
    }

    @Override
    public String getProcessorReceipt() {
        return processorReceipt;
    }

    @Override
    public void setProcessorReceipt(String processorReceipt) {
        this.processorReceipt = processorReceipt;
    }

    @Override
    public String getProcessorError() {
        return processorError;
    }

    @Override
    public void setProcessorError(String processorError) {
        this.processorError = processorError;
    }

    @Override
    public BigDecimal getProcessorFee() {
        return processorFee;
    }

    @Override
    public void setProcessorFee(BigDecimal processorFee) {
        this.processorFee = processorFee;
    }

    @Override
    public boolean isPaid() {
        return paid;
    }

    @Override
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public boolean isRefunded() {
        return refunded;
    }

    @Override
    public void setRefunded(boolean refunded) {
        this.refunded = refunded;
    }

    @Override
    public boolean isDisputed() {
        return disputed;
    }

    @Override
    public void setDisputed(boolean disputed) {
        this.disputed = disputed;
    }

    @Override
    public boolean isFailed() {
        return failed;
    }

    @Override
    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    @Override
    public Date getPaidDate() {
        return paidDate;
    }

    @Override
    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    @Override
    public Date getDisputedDate() {
        return disputedDate;
    }

    @Override
    public void setDisputedDate(Date disputedDate) {
        this.disputedDate = disputedDate;
    }

    @Override
    public Date getRefundedDate() {
        return refundedDate;
    }

    @Override
    public void setRefundedDate(Date refundedDate) {
        this.refundedDate = refundedDate;
    }

    @Override
    public Date getFailedDate() {
        return failedDate;
    }

    @Override
    public void setFailedDate(Date failedDate) {
        this.failedDate = failedDate;
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
