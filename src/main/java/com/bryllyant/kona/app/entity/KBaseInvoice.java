package com.bryllyant.kona.app.entity;

import java.math.BigDecimal;
import java.util.Date;

public class KBaseInvoice extends KBaseEntity implements KInvoice {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long userId;
    private Long accountId;
    private Long currencyId;
    private Long promoId;
    private Long campaignChannelId;
    private String invoiceNo;
    private BigDecimal startBalance;
    private BigDecimal endBalance;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal shipping;
    private BigDecimal discount;
    private BigDecimal total;
    private BigDecimal amountDue;
    private BigDecimal amountPaid;
    private boolean paid;
    private boolean closed;
    private Date invoiceDate;
    private Date dueDate;
    private Date paidDate;
    private Date closedDate;
    private boolean paymentAttempted;
    private Integer paymentAttemptCount;
    private Date lastPaymentAttemptDate;
    private Date nextPaymentAttemptDate;
    private String paymentCardLast4;
    private String paymentRef;
    private String notes;
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
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
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
    public Long getCurrencyId() {
        return currencyId;
    }

    @Override
    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
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
    public String getInvoiceNo() {
        return invoiceNo;
    }

    @Override
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    @Override
    public BigDecimal getStartBalance() {
        return startBalance;
    }

    @Override
    public void setStartBalance(BigDecimal startBalance) {
        this.startBalance = startBalance;
    }

    @Override
    public BigDecimal getEndBalance() {
        return endBalance;
    }

    @Override
    public void setEndBalance(BigDecimal endBalance) {
        this.endBalance = endBalance;
    }

    @Override
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    @Override
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public BigDecimal getTax() {
        return tax;
    }

    @Override
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    @Override
    public BigDecimal getShipping() {
        return shipping;
    }

    @Override
    public void setShipping(BigDecimal shipping) {
        this.shipping = shipping;
    }

    @Override
    public BigDecimal getDiscount() {
        return discount;
    }

    @Override
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public BigDecimal getAmountDue() {
        return amountDue;
    }

    @Override
    public void setAmountDue(BigDecimal amountDue) {
        this.amountDue = amountDue;
    }

    @Override
    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    @Override
    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
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
    public boolean isClosed() {
        return closed;
    }

    @Override
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    @Override
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @Override
    public Date getDueDate() {
        return dueDate;
    }

    @Override
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
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
    public Date getClosedDate() {
        return closedDate;
    }

    @Override
    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    @Override
    public boolean isPaymentAttempted() {
        return paymentAttempted;
    }

    @Override
    public void setPaymentAttempted(boolean paymentAttempted) {
        this.paymentAttempted = paymentAttempted;
    }

    @Override
    public Integer getPaymentAttemptCount() {
        return paymentAttemptCount;
    }

    @Override
    public void setPaymentAttemptCount(Integer paymentAttemptCount) {
        this.paymentAttemptCount = paymentAttemptCount;
    }

    @Override
    public Date getLastPaymentAttemptDate() {
        return lastPaymentAttemptDate;
    }

    @Override
    public void setLastPaymentAttemptDate(Date lastPaymentAttemptDate) {
        this.lastPaymentAttemptDate = lastPaymentAttemptDate;
    }

    @Override
    public Date getNextPaymentAttemptDate() {
        return nextPaymentAttemptDate;
    }

    @Override
    public void setNextPaymentAttemptDate(Date nextPaymentAttemptDate) {
        this.nextPaymentAttemptDate = nextPaymentAttemptDate;
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
    public String getNotes() {
        return notes;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
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

