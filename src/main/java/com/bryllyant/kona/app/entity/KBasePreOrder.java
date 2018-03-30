package com.bryllyant.kona.app.entity;

import java.math.BigDecimal;
import java.util.Date;

public class KBasePreOrder extends KBaseEntity implements KPreOrder {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long partnerId;
    private Long productSkuId;
    private Long campaignChannelId;
    private Long paymentId;
    private Long referredById;
    private BigDecimal amount;
    private boolean reconciled;
    private boolean proxyPayment;
    private String processor;
    private String paymentDescription;
    private String paymentToken;
    private String paymentCardLast4;
    private String paymentRef;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingCity;
    private String shippingState;
    private String shippingPostalCode;
    private String shippingCountry;
    private String notes;
    private String hostname;
    private String userAgent;
    private Date shippedDate;
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
    public Long getPartnerId() {
        return partnerId;
    }

    @Override
    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    @Override
    public Long getProductSkuId() {
        return productSkuId;
    }

    @Override
    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
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
    public Long getPaymentId() {
        return paymentId;
    }

    @Override
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public Long getReferredById() {
        return referredById;
    }

    @Override
    public void setReferredById(Long referredById) {
        this.referredById = referredById;
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
    public boolean isReconciled() {
        return reconciled;
    }

    @Override
    public void setReconciled(boolean reconciled) {
        this.reconciled = reconciled;
    }

    @Override
    public boolean isProxyPayment() {
        return proxyPayment;
    }

    @Override
    public void setProxyPayment(boolean proxyPayment) {
        this.proxyPayment = proxyPayment;
    }

    @Override
    public String getProcessor() {
        return processor;
    }

    @Override
    public void setProcessor(String processor) {
        this.processor = processor;
    }

    @Override
    public String getPaymentDescription() {
        return paymentDescription;
    }

    @Override
    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    @Override
    public String getPaymentToken() {
        return paymentToken;
    }

    @Override
    public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
    }

    @Override
    public String getPaymentCardLast4() {
        return paymentCardLast4;
    }

    @Override
    public void setPaymentCardLast4(String paymentCardLast4) {
        this.paymentCardLast4 = paymentCardLast4;
    }

    @Override
    public String getPaymentRef() {
        return paymentRef;
    }

    @Override
    public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String getShippingAddress1() {
        return shippingAddress1;
    }

    @Override
    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    @Override
    public String getShippingAddress2() {
        return shippingAddress2;
    }

    @Override
    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    @Override
    public String getShippingCity() {
        return shippingCity;
    }

    @Override
    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    @Override
    public String getShippingState() {
        return shippingState;
    }

    @Override
    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    @Override
    public String getShippingPostalCode() {
        return shippingPostalCode;
    }

    @Override
    public void setShippingPostalCode(String shippingPostalCode) {
        this.shippingPostalCode = shippingPostalCode;
    }

    @Override
    public String getShippingCountry() {
        return shippingCountry;
    }

    @Override
    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
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
    public Date getShippedDate() {
        return shippedDate;
    }

    @Override
    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
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
